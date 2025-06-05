package Main;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Gui extends JFrame {
    private int userMin = -1, userMax = -1;
    private JTextField filePathField;
    private JButton browseButton, randomButton, manualButton, readFileButton, runButton, runJUnitButton;
    private JSlider testCaseSlider;
    private JTextArea testCodeArea;
    private JLabel statusLabel;
    private String selectedClassName = "";

    public Gui() {
        setTitle("Tool Sinh ca kiểm thử đơn vị bằng Reflection & JUnit");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        setResizable(false);

        addComponents();
        setVisible(true);
    }

    private void addComponents() {
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        JPanel filePanel = new JPanel(new FlowLayout());
        filePathField = new JTextField(30);
        browseButton = new JButton("Browse...");
        filePanel.add(new JLabel("File Java:"));
        filePanel.add(filePathField);
        filePanel.add(browseButton);
        topPanel.add(filePanel);

        JPanel controlPanel = new JPanel(new FlowLayout());
        randomButton = new JButton("Random");
        manualButton = new JButton("Nhập dữ liệu");
        readFileButton = new JButton("Đọc file");
        testCaseSlider = new JSlider(1, 10, 2);
        testCaseSlider.setMajorTickSpacing(1);
        testCaseSlider.setPaintTicks(true);
        testCaseSlider.setPaintLabels(true);
        controlPanel.add(randomButton);
        controlPanel.add(manualButton);
        controlPanel.add(readFileButton);
        controlPanel.add(new JLabel("Số lượng Test Case:"));
        controlPanel.add(testCaseSlider);
        topPanel.add(controlPanel);
        add(topPanel, BorderLayout.NORTH);

        JPanel runPanel = new JPanel(new FlowLayout());
        runButton = new JButton("Run");
        runJUnitButton = new JButton("Run JUnit");
        runPanel.add(runButton);
        runPanel.add(runJUnitButton);
        add(runPanel, BorderLayout.CENTER);

        JPanel bottomPanel = new JPanel(new BorderLayout());
        testCodeArea = new JTextArea(23, 50);
        testCodeArea.setFont(new Font("Verdana", Font.PLAIN, 13));
        bottomPanel.add(new JScrollPane(testCodeArea), BorderLayout.CENTER);

        statusLabel = new JLabel("Chọn file Java để bắt đầu!", JLabel.CENTER);
        statusLabel.setForeground(Color.BLUE);
        bottomPanel.add(statusLabel, BorderLayout.SOUTH);

        add(bottomPanel, BorderLayout.SOUTH);

        setButtonsEnabled(false);

        browseButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(
                    "D:\\Eclipse_Reflection_JUnit\\Test_Reflection_JUnit\\src\\main\\java\\My_Source");
            fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
            fileChooser.setAcceptAllFileFilterUsed(false);
            fileChooser.setFileFilter(
                    new javax.swing.filechooser.FileNameExtensionFilter("Java (*.java)", "java", "class"));

            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                String selectedFilePath = fileChooser.getSelectedFile().getAbsolutePath();
                filePathField.setText(selectedFilePath);

                if (selectedFilePath.endsWith(".java") || selectedFilePath.endsWith(".class")) {
                    String className = extractClassName(selectedFilePath);
                    if (className != null) {
                        selectedClassName = className;
                        displayMethods(className);

                        try {
                            Class<?> clazz = Class.forName(className);
                            int methodCount = clazz.getDeclaredMethods().length;
                            statusLabel.setText("Lớp '" + className + "' có " + methodCount + " phương thức.");
                            statusLabel.setForeground(Color.BLUE);
                        } catch (ClassNotFoundException ex) {
                            statusLabel.setText("Không tìm thấy lớp: " + className);
                            statusLabel.setForeground(Color.RED);
                            JOptionPane.showMessageDialog(null, "Không tìm thấy class: " + className, "Lỗi",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    setButtonsEnabled(true);
                }
            }
        });

        randomButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Bạn chọn tạo tham số ngẫu nhiên!", "Thông báo",
                    JOptionPane.INFORMATION_MESSAGE);
        });

        manualButton.addActionListener(e -> {
            String selectedClassName = extractClassName(filePathField.getText());

            if (selectedClassName != null && !selectedClassName.isEmpty()) {
                try {
                    Class<?> selectedClass = Class.forName(selectedClassName);
                    new NhapDL(this, selectedClass);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy class: " + selectedClassName, "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một class trước!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        readFileButton.addActionListener(e -> {
            String selectedClassName = extractClassName(filePathField.getText());

            if (selectedClassName != null && !selectedClassName.isEmpty()) {
                try {
                    Class<?> selectedClass = Class.forName(selectedClassName);
                    new DocFile(this, selectedClass);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy class: " + selectedClassName, "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn một class trước!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        });

        testCaseSlider.addChangeListener(e -> {
            int numTestCases = testCaseSlider.getValue();
            String selectedFile = extractClassName(filePathField.getText());
            if (selectedFile != null) {
                try {
                    Class<?> clazz = Class.forName(selectedFile);
                    System.out.println("Lớp được chọn: " + selectedFile);
                    System.out.println("Số lượng của test case: " + numTestCases);
                } catch (ClassNotFoundException ex) {
                    JOptionPane.showMessageDialog(null, "Không tìm thấy lớp: " + selectedFile, "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        runButton.addActionListener(e -> {
            String className = extractClassName(filePathField.getText());

            if (className == null || className.trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Vui lòng chọn file Java!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                return;
            }

            try {
                Class<?> clazz = Class.forName(className);
                int testCaseCount = testCaseSlider.getValue();
                String generatedTests = Analyzer_Ref.TestCases(clazz, testCaseCount);
                testCodeArea.setText(generatedTests);
                Analyzer_Ref.writeTestFile(clazz, generatedTests);
                JOptionPane.showMessageDialog(null,
                        "Đã tạo và biên dịch test case thành công cho " + clazz.getSimpleName() + "!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(null, "Không tìm thấy lớp: " + className, "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            } catch (RuntimeException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi khi biên dịch test case: " + ex.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        runJUnitButton.addActionListener(e -> {
            try {
                File projectDir = new File("D:\\Eclipse_Reflection_JUnit\\Test_Reflection_JUnit");
                String className = extractClassName(filePathField.getText());
                if (className == null || className.trim().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn file Java trước!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                String simpleClassName = className.substring(className.lastIndexOf('.') + 1);
                String testClassName = "Class_Test." + simpleClassName + "_Test";

                // Build the project first
                ProcessBuilder buildProcess = new ProcessBuilder("cmd.exe", "/c",
                        "E:\\Download\\apache-maven-3.9.6\\bin\\mvn.cmd", "clean", "compile",
                        "-Dtest=" + testClassName);
                buildProcess.directory(projectDir);
                buildProcess.redirectErrorStream(true);
                Process build = buildProcess.start();
                BufferedReader buildReader = new BufferedReader(new InputStreamReader(build.getInputStream()));
                StringBuilder buildOutput = new StringBuilder();
                String buildLine;
                while ((buildLine = buildReader.readLine()) != null) {
                    buildOutput.append(buildLine).append("\n");
                }
                int buildExitCode = build.waitFor();
                if (buildExitCode != 0) {
                    testCodeArea.setText("❌ Build thất bại\n\n==== OUTPUT ====\n" + buildOutput.toString() + "\n");
                    JOptionPane.showMessageDialog(null, "Build thất bại. Vui lòng kiểm tra console!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Run tests
                ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c",
                        "E:\\Download\\apache-maven-3.9.6\\bin\\mvn.cmd", "test",
                        "-Dtest=" + testClassName);
                pb.directory(projectDir);
                pb.redirectErrorStream(true);
                Process process = pb.start();

                BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
                StringBuilder output = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    output.append(line).append("\n");
                }
                int exitCode = process.waitFor();

                if (exitCode != 0) {
                    testCodeArea.setText("❌ Lỗi khi chạy mvn test:\n" + output.toString());
                    JOptionPane.showMessageDialog(null, "Chạy JUnit thất bại. Vui lòng kiểm tra console!", "Lỗi",
                            JOptionPane.ERROR_MESSAGE);
                    return;
                }

                // Parse test results
                String[] lines = output.toString().split("\n");
                List<String> testCaseResults = new ArrayList<>();
                int testCaseNumber = 1;
                int total = 0, passed = 0, failed = 0, skipped = 0;
                double totalTimeMs = 0;

                // Adjusted pattern to match Surefire output
                Pattern testResultPattern = Pattern.compile(
                        "\\[(INFO|ERROR)\\] (\\w+\\([^)]+\\))\\s+(\\w+)\\s+(\\d+\\s*(ms|s))");
                Pattern summaryPattern = Pattern.compile(
                        "Tests run: (\\d+), Failures: (\\d+), Errors: (\\d+), Skipped: (\\d+), Time elapsed: (\\d+\\.\\d+) s");

                for (String testLine : lines) {
                    Matcher resultMatcher = testResultPattern.matcher(testLine.trim());
                    if (resultMatcher.find()) {
                        String methodWithArgs = resultMatcher.group(2);
                        String status = resultMatcher.group(3);
                        String timeTaken = resultMatcher.group(4);

                        double timeMs = parseTimeToMs(timeTaken);
                        totalTimeMs += timeMs;

                        String resultSymbol = "SUCCESS".equals(status) ? "✅ Passed" : "❌ Failed";
                        String testCaseStr = String.format("TestCase %d.", testCaseNumber).replaceFirst("^(.{12})", "$1");
                        String methodStr = String.format("%s:", methodWithArgs).replaceFirst("^(.{50})", "$1");
                        String resultStr = resultSymbol.replaceFirst("^(.{10})", "$1");
                        String timeStr = String.format("⏱ %s", timeTaken);

                        testCaseResults.add(testCaseStr + methodStr + resultStr + timeStr);
                        testCaseNumber++;
                        if ("SUCCESS".equals(status)) passed++;
                        else failed++;
                    }

                    Matcher summaryMatcher = summaryPattern.matcher(testLine.trim());
                    if (summaryMatcher.find()) {
                        total = Integer.parseInt(summaryMatcher.group(1));
                        failed += Integer.parseInt(summaryMatcher.group(2)) + Integer.parseInt(summaryMatcher.group(3));
                        skipped = Integer.parseInt(summaryMatcher.group(4));
                        double elapsedTime = Double.parseDouble(summaryMatcher.group(5)) * 1000;
                        totalTimeMs = Math.max(totalTimeMs, elapsedTime);
                    }
                }

                testCodeArea.setText("");
                for (String result : testCaseResults) {
                    testCodeArea.append(result + "\n");
                }

                testCodeArea.append("\n");
                testCodeArea.append(String.format("❌ Failed : %d testcase%n", failed));
                testCodeArea.append(String.format("✅ Passed : %d testcase%n", passed));
                testCodeArea.append(String.format("⚠️ Skipped : %d testcase%n", skipped));
                testCodeArea.append(String.format("📊 Total : %d testcase%n", total));

                if (totalTimeMs >= 1000) {
                    testCodeArea.append(String.format("⏱ Total Time : %.3f s%n", totalTimeMs / 1000.0));
                } else {
                    testCodeArea.append(String.format("⏱ Total Time : %.3f ms%n", totalTimeMs));
                }

                JOptionPane.showMessageDialog(null, "Chạy JUnit thành công!", "Thông báo",
                        JOptionPane.INFORMATION_MESSAGE);

            } catch (Exception ex) {
                testCodeArea.setText("❌ Lỗi khi chạy mvn test:\n" + ex.getMessage() + "\n" + getStackTrace(ex));
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Chạy JUnit thất bại: " + ex.getMessage(), "Lỗi",
                        JOptionPane.ERROR_MESSAGE);
            }
        });
    }

    private double parseTimeToMs(String timeTaken) {
        if (timeTaken.contains("ms")) {
            String numberPart = timeTaken.replace("ms", "").trim();
            try {
                return Double.parseDouble(numberPart);
            } catch (NumberFormatException e) {
                return 0;
            }
        } else if (timeTaken.contains("s")) {
            String numberPart = timeTaken.replace("s", "").trim();
            try {
                return Double.parseDouble(numberPart) * 1000;
            } catch (NumberFormatException e) {
                return 0;
            }
        }
        return 0;
    }

    private String getStackTrace(Exception ex) {
        StringBuilder sb = new StringBuilder();
        for (StackTraceElement element : ex.getStackTrace()) {
            sb.append(element.toString()).append("\n");
        }
        return sb.toString();
    }

    public void setUserDefinedParameters(int min, int max) {
        this.userMin = min;
        this.userMax = max;
    }

    public String extractClassName(String selectedFilePath) {
        File file = new File(selectedFilePath);

        if (!file.exists()) {
            System.err.println("Lỗi: File không tồn tại! " + selectedFilePath);
            return null;
        }

        try {
            java.util.Scanner scanner = new java.util.Scanner(file);
            String packageName = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.startsWith("package")) {
                    packageName = line.split(" ")[1].replace(";", "").trim();
                    break;
                }
            }
            scanner.close();

            String className = file.getName().replace(".java", "").replace(".class", "");
            return packageName != null ? packageName + "." + className : className;
        } catch (Exception e) {
            System.err.println("Lỗi khi trích xuất tên lớp: " + e.getMessage());
            return null;
        }
    }

    protected void displayMethods(String className) {
        try {
            Class<?> clazz = Class.forName(className);
            Method[] methods = clazz.getDeclaredMethods();

            testCodeArea.setText("");
            testCodeArea.append("Danh sách phương thức của lớp: " + className + "\n");
            testCodeArea.append("---------------------------------------------\n");

            for (Method method : methods) {
                StringBuilder methodSignature = new StringBuilder();
                methodSignature.append("public ").append(method.getReturnType().getSimpleName()).append(" ")
                        .append(method.getName()).append("(");

                Parameter[] parameters = method.getParameters();
                for (int i = 0; i < parameters.length; i++) {
                    methodSignature.append(parameters[i].getType().getSimpleName()).append(" ")
                            .append(parameters[i].getName());
                    if (i < parameters.length - 1) {
                        methodSignature.append(", ");
                    }
                }

                methodSignature.append(");\n");
                testCodeArea.append(methodSignature.toString());
            }

        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Không tìm thấy lớp: " + className, "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setButtonsEnabled(boolean enabled) {
        randomButton.setEnabled(enabled);
        manualButton.setEnabled(enabled);
        readFileButton.setEnabled(enabled);
        runButton.setEnabled(enabled);
        runJUnitButton.setEnabled(enabled);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Gui::new);
    }

    public void updateTestCodeArea(String testCases) {
        testCodeArea.setText(testCases);
    }
}