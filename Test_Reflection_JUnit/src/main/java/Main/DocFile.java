package Main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.swing.border.EmptyBorder;

public class DocFile extends JFrame {
    private JTextField filePathFieldDoc;
    private JTextArea contentDisplayArea;
    private String selectedClass;
    private Gui mainGui;
    private Class<?> clazz;

    public DocFile(Gui gui, Class<?> clazz) {
        this.mainGui = gui;
        this.selectedClass = clazz.getName();
        this.clazz = clazz;
        setTitle("Đọc File - " + selectedClass);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        JLabel lblClassName = new JLabel("Class: " + selectedClass, JLabel.CENTER);
        lblClassName.setFont(new Font("Verdana", Font.BOLD, 15));
        lblClassName.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(lblClassName, BorderLayout.NORTH);

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        JLabel lblFilePath = new JLabel("Đường dẫn file:");
        lblFilePath.setFont(new Font("Verdana", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        centerPanel.add(lblFilePath, gbc);

        filePathFieldDoc = new JTextField(20);
        filePathFieldDoc.setFont(new Font("Verdana", Font.PLAIN, 13));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.weightx = 0.7;
        centerPanel.add(filePathFieldDoc, gbc);

        JButton browseButton = new JButton("Browse");
        browseButton.setFont(new Font("Arial", Font.BOLD, 12));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.weightx = 0;
        centerPanel.add(browseButton, gbc);

        JLabel lblContent = new JLabel("Nội dung file:");
        lblContent.setFont(new Font("Verdana", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        centerPanel.add(lblContent, gbc);

        contentDisplayArea = new JTextArea(8, 20);
        contentDisplayArea.setEditable(false);
        contentDisplayArea.setFont(new Font("Verdana", Font.PLAIN, 13));
        contentDisplayArea.setLineWrap(true);
        contentDisplayArea.setWrapStyleWord(true);
        JScrollPane contentScrollPane = new JScrollPane(contentDisplayArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 0.7;
        gbc.weighty = 0.7;
        centerPanel.add(contentScrollPane, gbc);

        add(centerPanel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton runDocFileButton = new JButton("Run DocFile");
        runDocFileButton.setFont(new Font("Arial", Font.BOLD, 12));
        runDocFileButton.setPreferredSize(new Dimension(110, 30));

        buttonPanel.add(runDocFileButton);
        add(buttonPanel, BorderLayout.SOUTH);

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser("D:\\Eclipse_Reflection_JUnit");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Text (*.txt)", "txt"));

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                filePathFieldDoc.setText(selectedFilePath);
                displayFileContent(selectedFilePath);
            }
        });

        runDocFileButton.addActionListener(e -> {
            String filePath = filePathFieldDoc.getText().trim();
            if (filePath.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn file .txt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            File file = new File(filePath);
            if (!file.exists() || !file.getName().endsWith(".txt")) {
                JOptionPane.showMessageDialog(null, "File không hợp lệ hoặc không phải là file .txt!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                String generatedTests = generateTestsFromFile(filePath);
                mainGui.updateTestCodeArea(generatedTests);
                Analyzer_Ref.writeTestFile(clazz, generatedTests); // Pass the generated test code
                JOptionPane.showMessageDialog(null, "Đã tạo test case từ file thành công cho " + clazz.getSimpleName() + "!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi sinh test case: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
        });

        setVisible(true);
    }

    private void displayFileContent(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            StringBuilder content = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }
            contentDisplayArea.setText(content.toString());
        } catch (Exception e) {
            contentDisplayArea.setText("Lỗi khi đọc file: " + e.getMessage());
        }
    }

    private String generateTestsFromFile(String filePath) throws Exception {
        StringBuilder testCode = new StringBuilder();
        String classPackage = clazz.getPackage().getName();
        String className = clazz.getSimpleName();

        testCode.append("package Class_Test;\n");
        testCode.append("import java.lang.reflect.*;\n");
        testCode.append("import org.junit.jupiter.api.Test;\n");
        testCode.append("import static org.junit.jupiter.api.Assertions.*;\n");
        testCode.append("import ").append(classPackage).append(".").append(className).append(";\n");
        testCode.append("import org.junit.jupiter.params.ParameterizedTest;\n");
        testCode.append("import org.junit.jupiter.params.provider.CsvSource;\n\n");

        testCode.append("public class ").append(className).append("_Test {\n\n");

        Map<String, List<List<Object>>> methodInputs = new HashMap<>();
        Map<String, Method> methodMap = new HashMap<>();

        for (Method method : clazz.getDeclaredMethods()) {
            if (Modifier.isPublic(method.getModifiers()) && method.getParameterCount() > 0) {
                methodMap.put(method.getName(), method);
                methodInputs.put(method.getName(), new ArrayList<>());
            }
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) continue;

                String[] parts = line.split("\\s+");
                if (parts.length < 2) {
                    System.err.println("Skipping invalid line: " + line);
                    continue;
                }

                String methodName = parts[0];
                if (!methodMap.containsKey(methodName)) {
                    JOptionPane.showMessageDialog(null, "Phương thức '" + methodName + "' không tồn tại hoặc không hợp lệ!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                Method method = methodMap.get(methodName);
                int paramCount = method.getParameterCount();
                if (parts.length - 1 != paramCount) {
                    JOptionPane.showMessageDialog(null, "Số lượng tham số không khớp cho phương thức '" + methodName + "' (yêu cầu: " + paramCount + " tham số, nhận: " + (parts.length - 1) + " tham số)", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    continue;
                }

                List<Object> inputs = new ArrayList<>();
                boolean validRow = true;
                for (int i = 1; i < parts.length; i++) {
                    String value = parts[i];
                    Object convertedValue = convertToType(value, method.getParameters()[i - 1].getType());
                    if (convertedValue == null) {
                        validRow = false;
                        break;
                    }
                    inputs.add(convertedValue);
                }

                if (validRow) {
                    methodInputs.get(methodName).add(inputs);
                }
            }
        }

        for (Map.Entry<String, List<List<Object>>> entry : methodInputs.entrySet()) {
            String methodName = entry.getKey();
            List<List<Object>> inputsList = entry.getValue();
            if (!inputsList.isEmpty()) {
                testCode.append(generateTestMethodFromFile(methodMap.get(methodName), inputsList));
            }
        }

        testCode.append("}\n");
        return testCode.toString();
    }

    private String generateTestMethodFromFile(Method method, List<List<Object>> inputsList) {
        StringBuilder code = new StringBuilder();
        Parameter[] parameters = method.getParameters();
        Class<?> returnType = method.getReturnType();
        String returnTypeName = Analyzer_Ref.getJavaTypeName(returnType);

        code.append("    @ParameterizedTest\n");
        code.append("    @CsvSource({\n");

        List<List<String>> valueRows = new ArrayList<>();
        int totalCols = parameters.length + 1;

        for (List<Object> inputs : inputsList) {
            Object[] inputArray = inputs.toArray(new Object[0]);
            Object expected = null;
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                expected = method.invoke(instance, inputArray);
            } catch (Exception e) {
                expected = null;
            }

            List<String> row = new ArrayList<>();
            for (int i = 0; i < inputArray.length; i++) {
                row.add(Analyzer_Ref.csvFormatValue(inputArray[i], parameters[i].getType()));
            }
            row.add((expected != null) ? Analyzer_Ref.csvFormatValue(expected, returnType) : "null");
            valueRows.add(row);
        }

        int[] maxWidths = new int[totalCols];
        for (List<String> row : valueRows) {
            for (int i = 0; i < totalCols; i++) {
                maxWidths[i] = Math.max(maxWidths[i], row.get(i).length());
            }
        }

        for (List<String> row : valueRows) {
            StringBuilder line = new StringBuilder("        \"");
            for (int i = 0; i < totalCols; i++) {
                String formattedValue = row.get(i);
                int padding = maxWidths[i] - formattedValue.length();
                line.append(formattedValue).append(" ".repeat(Math.max(0, padding)));
                if (i < totalCols - 1) line.append(",");
            }
            line.append("\",");
            code.append(line).append("\n");
        }

        if (!valueRows.isEmpty()) {
            code.deleteCharAt(code.length() - 2);
        }
        code.append("    })\n");

        code.append("    public void ").append(method.getName()).append("_Test(");
        for (int i = 0; i < parameters.length; i++) {
            code.append(Analyzer_Ref.getJavaTypeName(parameters[i].getType())).append(" arg").append(i);
            if (i < parameters.length - 1) code.append(", ");
        }
        code.append(", ").append(returnTypeName).append(" expectedResult) {\n");

        String className = clazz.getSimpleName();
        code.append("        ").append(className).append(" obj = new ").append(className).append("();\n");

        String argsList = IntStream.range(0, parameters.length)
                .mapToObj(i -> "arg" + i)
                .collect(Collectors.joining(", "));

        code.append("        ").append(returnTypeName).append(" actual = obj.").append(method.getName()).append("(").append(argsList)
                .append(");\n");
        code.append("        assertEquals(expectedResult, actual);\n");
        code.append("    }\n\n");

        return code.toString();
    }

    private Object convertToType(String value, Class<?> targetType) {
        if (value == null || value.equals("null")) return null;

        try {
            if (targetType == int.class || targetType == Integer.class) {
                if (value.contains(".")) {
                    JOptionPane.showMessageDialog(this, "Giá trị '" + value + "' không hợp lệ cho kiểu int (phải là số nguyên)", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                return Integer.parseInt(value);
            } else if (targetType == double.class || targetType == Double.class) {
                return Double.parseDouble(value);
            } else if (targetType == boolean.class || targetType == Boolean.class) {
                if (!value.equalsIgnoreCase("true") && !value.equalsIgnoreCase("false")) {
                    JOptionPane.showMessageDialog(this, "Giá trị '" + value + "' không hợp lệ cho kiểu boolean (phải là true/false)", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    return null;
                }
                return Boolean.parseBoolean(value);
            } else if (targetType == String.class) {
                return value;
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Lỗi chuyển đổi kiểu dữ liệu cho: '" + value + "' sang " + targetType.getSimpleName(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        JOptionPane.showMessageDialog(null, "Kiểu dữ liệu không được hỗ trợ: " + targetType.getSimpleName(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        return null;
    }
}