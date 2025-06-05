package Main;

import java.awt.*;
import java.awt.event.*;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

public class NhapDL extends JFrame {
    private JComboBox<String> testStrategyComboBox;
    private JTextArea methodDisplayArea;
    private JTextArea conditionTextArea;
    private JTextArea testCaseDisplayArea;
    private JTextArea guideArea;
    private static boolean useUserInput = false;
    private String selectedClass;
    private Gui mainGui;

    public NhapDL(Gui gui, Class<?> clazz) {
        this.mainGui = gui;
        this.selectedClass = clazz.getName();
        setTitle("Nhập Tham Số - " + selectedClass);
        setSize(700, 650);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // Tiêu đề (North)
        JLabel lblClassName = new JLabel("Class: " + selectedClass, JLabel.CENTER);
        lblClassName.setFont(new Font("Verdana", Font.BOLD, 15));
        lblClassName.setBorder(new EmptyBorder(5, 5, 5, 5));
        add(lblClassName, BorderLayout.NORTH);

        // Nội dung chính (Center)
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.insets = new Insets(5, 5, 5, 5);

        // Chiến lược kiểm thử
        JLabel lblChienLuoc = new JLabel("Chiến lược kiểm thử:");
        lblChienLuoc.setFont(new Font("Verdana", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        centerPanel.add(lblChienLuoc, gbc);

        testStrategyComboBox = new JComboBox<>(new String[] { "Giá trị biên", "Phân vùng tương đương" });
        testStrategyComboBox.setFont(new Font("Verdana", Font.PLAIN, 13));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        centerPanel.add(testStrategyComboBox, gbc);

        // Danh sách phương thức
        JLabel lblMethods = new JLabel("Phương thức:");
        lblMethods.setFont(new Font("Verdana", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        centerPanel.add(lblMethods, gbc);

        methodDisplayArea = new JTextArea(4, 18);
        methodDisplayArea.setEditable(false);
        methodDisplayArea.setFont(new Font("Verdana", Font.PLAIN, 13));
        methodDisplayArea.setLineWrap(true);
        methodDisplayArea.setWrapStyleWord(true);
        JScrollPane methodScrollPane = new JScrollPane(methodDisplayArea);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 0.7;
        gbc.weighty = 0.3;
        centerPanel.add(methodScrollPane, gbc);

        // Điều kiện
        JLabel lblCondition = new JLabel("Điều kiện:");
        lblCondition.setFont(new Font("Verdana", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.weighty = 0;
        centerPanel.add(lblCondition, gbc);

        conditionTextArea = new JTextArea(2, 18);
        conditionTextArea.setFont(new Font("Verdana", Font.PLAIN, 13));
        conditionTextArea.setLineWrap(true);
        conditionTextArea.setWrapStyleWord(true);
        JScrollPane conditionScrollPane = new JScrollPane(conditionTextArea);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 0.7;
        gbc.weighty = 0.3;
        centerPanel.add(conditionScrollPane, gbc);

        // Test Cases
        JLabel lblTestCases = new JLabel("Test Cases:");
        lblTestCases.setFont(new Font("Verdana", Font.BOLD, 13));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.weighty = 0;
        centerPanel.add(lblTestCases, gbc);

        testCaseDisplayArea = new JTextArea(6, 18);
        testCaseDisplayArea.setEditable(false);
        testCaseDisplayArea.setFont(new Font("Verdana", Font.PLAIN, 13));
        testCaseDisplayArea.setLineWrap(true);
        testCaseDisplayArea.setWrapStyleWord(true);
        JScrollPane testCaseScrollPane = new JScrollPane(testCaseDisplayArea);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 0.7;
        gbc.weighty = 0.4;
        centerPanel.add(testCaseScrollPane, gbc);

        // Hướng dẫn sử dụng
        JLabel lblGuide = new JLabel("Hướng dẫn sử dụng:");
        lblGuide.setFont(new Font("Verdana", Font.BOLD, 13));
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 1;
        gbc.weightx = 0.3;
        gbc.weighty = 0.0;
        centerPanel.add(lblGuide, gbc);

        guideArea = new JTextArea(10, 18);
        guideArea.setEditable(false);
        guideArea.setFont(new Font("Verdana", Font.PLAIN, 13));
        guideArea.setLineWrap(true);
        guideArea.setWrapStyleWord(true);
        guideArea.setText("----Phân vùng tương đương \n1. Chọn chiến lược kiểm thử.\n"
                + "2. Nhập điều kiện vào ô 'Điều kiện' (từ 0 đến 10).\n"
                + "3. Nhấn 'Test Case' để xem các trường hợp kiểm thử.\n"
                + "4. Nhấn 'Manual Run' để sinh test case và hiển thị trong giao diện chính.\n"
                + "----Phân tích giá trị biên \n1. Chọn chiến lược kiểm thử.\n"
                + "2. Nhập điều kiện vào ô 'Điều kiện' (a>0, a<b<=10).\n"
                + "3. Nhấn 'Test Case' để xem các trường hợp kiểm thử.\n"
                + "4. Nhấn 'Manual Run' để sinh test case và hiển thị trong giao diện chính.");
        JScrollPane guideScrollPane = new JScrollPane(guideArea);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.gridheight = 3;
        gbc.weightx = 0.3;
        gbc.weighty = 1.0;
        centerPanel.add(guideScrollPane, gbc);
        add(centerPanel, BorderLayout.CENTER);

        // Nút bấm (South)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.setBackground(Color.WHITE);

        JButton testCaseButton = new JButton("Test Case");
        testCaseButton.setFont(new Font("Arial", Font.BOLD, 12));
        testCaseButton.setPreferredSize(new Dimension(110, 30));
        JButton manualRunButton = new JButton("Manual Run");
        manualRunButton.setFont(new Font("Arial", Font.BOLD, 12));
        manualRunButton.setPreferredSize(new Dimension(110, 30));

        buttonPanel.add(testCaseButton);
        buttonPanel.add(manualRunButton);
        add(buttonPanel, BorderLayout.SOUTH);

        // Điền methodDisplayArea với các phương thức công khai có tham số
        try {
            StringBuilder methods = new StringBuilder();
            for (Method method : clazz.getDeclaredMethods()) {
                if (Modifier.isPublic(method.getModifiers()) && method.getParameterCount() > 0) {
                    String params = Arrays.stream(method.getParameters()).map(p -> p.getName())
                            .collect(Collectors.joining(", "));
                    methods.append(method.getName()).append("(").append(params).append(")\n");
                }
            }
            methodDisplayArea.setText(methods.length() > 0 ? methods.toString() : "Không có phương thức hợp lệ");
        } catch (Exception e) {
            methodDisplayArea.setText("Lỗi khi lấy danh sách phương thức: " + e.getMessage());
        }

        // Nút Test Case
        testCaseButton.addActionListener(e -> {
            String strategy = (String) testStrategyComboBox.getSelectedItem();
            String conditions = conditionTextArea.getText().trim();
            if (conditions.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập điều kiện!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            testCaseDisplayArea.setText(generateTestCaseDisplay(clazz, strategy, conditions));
        });

        // Nút Manual Run
        manualRunButton.addActionListener(e -> {
            String strategy = (String) testStrategyComboBox.getSelectedItem();
            String conditions = conditionTextArea.getText().trim();
            if (conditions.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng nhập điều kiện!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }
            String testCases = generateTestCasesForValidCases(clazz, strategy, conditions);
            mainGui.updateTestCodeArea(testCases);
            JOptionPane.showMessageDialog(null, "Đã tạo test case thành công với " + strategy + "!", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
            dispose();
        });

        setVisible(true);
    }

    private String generateTestCaseDisplay(Class<?> clazz, String strategy, String conditions) {
        StringBuilder display = new StringBuilder();
        if ("Phân vùng tương đương".equals(strategy)) {
            try {
                String[] parts = conditions.toLowerCase().split("từ | đến ");
                if (parts.length != 3) {
                    return "Điều kiện không hợp lệ. Vui lòng nhập dạng 'từ số đến số'";
                }
                int min = Integer.parseInt(parts[1].trim());
                int max = Integer.parseInt(parts[2].trim());
                if (min > max) {
                    return "Min phải nhỏ hơn hoặc bằng Max!";
                }
                display.append(String.format("TC1: %d đến %d => hợp lệ\n", min, max));
                display.append(String.format("TC2: > %d => không hợp lệ\n", max));
                display.append(String.format("TC3: < %d => không hợp lệ\n", min));
            } catch (NumberFormatException e) {
                return "Vui lòng nhập số hợp lệ!";
            }
        } else if ("Giá trị biên".equals(strategy)) {
            try {
                String[] constraints = conditions.split(",");
                int min = 0, max = 0;
                for (String constraint : constraints) {
                    constraint = constraint.trim();
                    if (constraint.matches("a>(\\d+)")) {
                        min = Integer.parseInt(constraint.replaceAll("a>(\\d+)", "$1")) + 1;
                    } else if (constraint.matches("a<b<=(\\d+)")) {
                        max = Integer.parseInt(constraint.replaceAll("a<b<=(\\d+)", "$1"));
                    }
                }
                if (min >= max) {
                    return "Điều kiện không hợp lệ!";
                }
                display.append(String.format("min-1: %d => không hợp lệ\n", min - 1));
                display.append(String.format("min: %d => hợp lệ\n", min));
                display.append(String.format("mid: %d => hợp lệ\n", (min + max) / 2));
                display.append(String.format("max: %d => hợp lệ\n", max));
                display.append(String.format("max+1: %d => không hợp lệ\n", max + 1));
            } catch (Exception e) {
                return "Điều kiện không hợp lệ. Vui lòng nhập dạng 'a>0, a<b<=10'";
            }
        }
        return display.toString();
    }

    private String generateTestCasesForValidCases(Class<?> clazz, String strategy, String conditions) {
        StringBuilder testCode = new StringBuilder();
        testCode.append("package Class_Test;\n");
        testCode.append("import org.junit.jupiter.params.ParameterizedTest;\n");
        testCode.append("import org.junit.jupiter.params.provider.CsvSource;\n");
        testCode.append("import static org.junit.jupiter.api.Assertions.*;\n");
        testCode.append("import ").append(selectedClass).append(";\n\n");

        String simpleClassName = selectedClass.substring(selectedClass.lastIndexOf(".") + 1);
        testCode.append("public class ").append(simpleClassName).append("_Test {\n\n");

        try {
            for (Method method : clazz.getDeclaredMethods()) {
                if (!Modifier.isPublic(method.getModifiers()) || method.getParameterCount() == 0) {
                    continue;
                }
                Class<?>[] parameterTypes = method.getParameterTypes();
                Class<?> returnType = method.getReturnType();
                String returnTypeName = Analyzer_Ref.getJavaTypeName(returnType);

                testCode.append("    @ParameterizedTest\n");
                testCode.append("    @CsvSource({\n");

                List<String> csvLines = new ArrayList<>();
                List<Object[]> allArgs = new ArrayList<>();
                List<Object> allExpected = new ArrayList<>();

                if ("Phân vùng tương đương".equals(strategy)) {
                    String[] parts = conditions.toLowerCase().split("từ | đến ");
                    if (parts.length == 3) {
                        int min = Integer.parseInt(parts[1].trim());
                        int max = Integer.parseInt(parts[2].trim());
                        int mid = (min + max) / 2;
                        for (int value : new int[] { min, mid, max }) {
                            Object[] args = generateArgsForParameters(parameterTypes, value);
                            Object expected = invokeMethod(clazz, method, args);
                            allArgs.add(args);
                            allExpected.add(expected);
                        }
                    }
                } else if ("Giá trị biên".equals(strategy)) {
                    String[] constraints = conditions.split(",");
                    int min = 0, max = 0;
                    for (String constraint : constraints) {
                        constraint = constraint.trim();
                        if (constraint.matches("a>(\\d+)")) {
                            min = Integer.parseInt(constraint.replaceAll("a>(\\d+)", "$1")) + 1;
                        } else if (constraint.matches("a<b<=(\\d+)")) {
                            max = Integer.parseInt(constraint.replaceAll("a<b<=(\\d+)", "$1"));
                        }
                    }
                    int mid = (min + max) / 2;
                    for (int value : new int[] { min, mid, max }) {
                        Object[] args = generateArgsForParameters(parameterTypes, value);
                        Object expected = invokeMethod(clazz, method, args);
                        allArgs.add(args);
                        allExpected.add(expected);
                    }
                }

                int[] columnWidths = new int[parameterTypes.length + 1];
                for (int i = 0; i < allArgs.size(); i++) {
                    Object[] args = allArgs.get(i);
                    Object expected = allExpected.get(i);
                    for (int j = 0; j < args.length; j++) {
                        String formattedValue = Analyzer_Ref.csvFormatValue(args[j], parameterTypes[j]);
                        columnWidths[j] = Math.max(columnWidths[j], formattedValue.length());
                    }
                    String formattedExpected = Analyzer_Ref.csvFormatValue(expected, returnType);
                    columnWidths[args.length] = Math.max(columnWidths[args.length], formattedExpected.length());
                }

                for (int i = 0; i < allArgs.size(); i++) {
                    csvLines.add(createAlignedCsvLine(allArgs.get(i), allExpected.get(i), columnWidths));
                }

                if (!csvLines.isEmpty()) {
                    testCode.append("        ").append(csvLines.get(0));
                    for (int i = 1; i < csvLines.size(); i++) {
                        testCode.append(",\n        ").append(csvLines.get(i));
                    }
                }
                testCode.append("\n    })\n");
                testCode.append("    public void ").append(method.getName()).append("_Test(");

                for (int i = 0; i < parameterTypes.length; i++) {
                    testCode.append(Analyzer_Ref.getJavaTypeName(parameterTypes[i])).append(" arg").append(i);
                    if (i < parameterTypes.length - 1) testCode.append(", ");
                }
                testCode.append(", ").append(returnTypeName).append(" expected) {\n");

                String simpleName = clazz.getSimpleName();
                testCode.append("        ").append(simpleName).append(" obj = new ").append(simpleName).append("();\n");

                String argsList = Arrays.stream(parameterTypes)
                        .map(p -> "arg" + Arrays.asList(parameterTypes).indexOf(p)).collect(Collectors.joining(", "));

                testCode.append("        ").append(returnTypeName).append(" actual = obj.").append(method.getName())
                        .append("(").append(argsList).append(");\n");
                testCode.append("        assertEquals(expected, actual);\n");
                testCode.append("    }\n\n");
            }
        } catch (Exception e) {
            return "Lỗi khi sinh test case: " + e.getMessage();
        }

        testCode.append("}\n");
        return testCode.toString();
    }
    

    private Object[] generateArgsForParameters(Class<?>[] parameterTypes, int value) {
        Object[] args = new Object[parameterTypes.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i] == int.class || parameterTypes[i] == Integer.class) {
                args[i] = value;
            } else if (parameterTypes[i] == double.class || parameterTypes[i] == Double.class) {
                args[i] = (double) value;
            } else if (parameterTypes[i] == String.class) {
                args[i] = "test" + value;
            } else if (parameterTypes[i] == boolean.class || parameterTypes[i] == Boolean.class) {
                args[i] = value % 2 == 0;
            } else {
                args[i] = null;
            }
        }
        return args;
    }

    private Object invokeMethod(Class<?> clazz, Method method, Object[] args) {
        try {
            Object obj = clazz.getDeclaredConstructor().newInstance();
            return method.invoke(obj, args);
        } catch (Exception e) {
            return null;
        }
    }

    private String createAlignedCsvLine(Object[] args, Object expected, int[] columnWidths) {
        StringBuilder csv = new StringBuilder();
        csv.append("\"");
        for (int i = 0; i < args.length; i++) {
            String formattedValue = Analyzer_Ref.csvFormatValue(args[i], args[i] != null ? args[i].getClass() : Object.class);
            int padding = columnWidths[i] - formattedValue.length();
            csv.append(formattedValue).append(" ".repeat(Math.max(0, padding))).append(",");
        }
        String formattedExpected = Analyzer_Ref.csvFormatValue(expected, expected != null ? expected.getClass() : Object.class);
        int padding = columnWidths[args.length] - formattedExpected.length();
        csv.append(formattedExpected).append(" ".repeat(Math.max(0, padding))).append("\"");
        return csv.toString();
    }

    public static void setUserDefinedParameters(int min, int max) {
        useUserInput = true;
    }
}