package Main;

import java.io.*;
import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Analyzer_Ref {

    public static String TestCases(Class<?> clazz, int testCaseCount) {
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

        for (Method method : clazz.getDeclaredMethods()) {
            if (!Modifier.isPublic(method.getModifiers()) || method.getParameterCount() == 0)
                continue;
            testCode.append(generateTestMethod(clazz, method, testCaseCount)).append("\n");
        }

        testCode.append("}\n");
        return testCode.toString();
    }
    
    private static String generateTestMethod(Class<?> clazz, Method method, int testCaseCount) {
        StringBuilder code = new StringBuilder();
        Random rand = new Random();
        Parameter[] params = method.getParameters();
        Class<?> returnType = method.getReturnType();
        String returnTypeName = getJavaTypeName(returnType);

        code.append("    @ParameterizedTest\n");
        code.append("    @CsvSource({\n");

        List<List<String>> valueRows = new ArrayList<>();
        int totalCols = params.length + 1;

        for (int i = 0; i < testCaseCount; i++) {
            Object[] inputs = new Object[params.length];
            for (int j = 0; j < params.length; j++) {
                inputs[j] = generateRandomValue(params[j].getType(), rand);
            }

            Object expected = null;
            try {
                Object instance = clazz.getDeclaredConstructor().newInstance();
                expected = method.invoke(instance, inputs);
            } catch (Exception e) {
                expected = null;
            }

            List<String> row = new ArrayList<>();
            for (int j = 0; j < params.length; j++) {
                row.add(csvFormatValue(inputs[j], params[j].getType()));
            }
            row.add((expected != null) ? csvFormatValue(expected, returnType) : "null");
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
                line.append(String.format("%-" + maxWidths[i] + "s", row.get(i)));
                if (i < totalCols - 1) line.append(", ");
            }
            line.append("\",");
            code.append(line).append("\n");
        }

        code.deleteCharAt(code.length() - 2);
        code.append("    })\n");

        code.append("    public void ").append(method.getName()).append("_Test(");
        for (int i = 0; i < params.length; i++) {
            code.append(getJavaTypeName(params[i].getType())).append(" arg").append(i);
            if (i < params.length - 1) code.append(", ");
        }
        code.append(", ").append(returnTypeName).append(" expectedResult) {\n");

        String className = clazz.getSimpleName();
        code.append("        ").append(className).append(" obj = new ").append(className).append("();\n");

        String argsList = IntStream.range(0, params.length)
                .mapToObj(i -> "arg" + i)
                .collect(Collectors.joining(", "));

        code.append("        ").append(returnTypeName).append(" actual = obj.").append(method.getName())
                .append("(").append(argsList).append(");\n");
        code.append("        assertEquals(expectedResult, actual);\n");
        code.append("    }\n");

        return code.toString();
    }

    private static Object generateRandomValue(Class<?> parameterType, Random random) {
        if (parameterType == int.class || parameterType == Integer.class) {
            return random.nextInt(100);
        } else if (parameterType == double.class || parameterType == Double.class) {
            double randomValue = random.nextDouble() * 100;
            return Math.round(randomValue * 100.0) / 100.0;
        } else if (parameterType == String.class) {
            return generateRandomString(5);
        } else if (parameterType == boolean.class || parameterType == Boolean.class) {
            return random.nextBoolean();
        } else {
            return null;
        }
    }

    private static String generateRandomString(int length) {
        String chars = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(chars.charAt(rand.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public static String csvFormatValue(Object value, Class<?> type) {
        if (value == null) {
            return "null";
        }
        if (type == String.class) {
            String s = value.toString();
            s = s.replace("\"", "\"\"").replace(",", "\\,");
            return s.contains(" ") || s.contains(",") || s.contains("\"") ? "\"" + s + "\"" : s;
        }
        return value.toString();
    }

    public static String getJavaTypeName(Class<?> type) {
        if (type == int.class) return "int";
        if (type == double.class) return "double";
        if (type == boolean.class) return "boolean";
        if (type == String.class) return "String";
        return type.getSimpleName();
    }

    public static void writeTestFile(Class<?> clazz, String testCode) {
        try {
            String projectDir = "D:\\Eclipse_Reflection_JUnit\\Test_Reflection_JUnit";
            String testFilePath = projectDir + "\\src\\test\\java\\Class_Test\\" + clazz.getSimpleName() + "_Test.java";

            // Write test file
            File file = new File(testFilePath);
            file.getParentFile().mkdirs();
            FileWriter writer = new FileWriter(file);
            writer.write(testCode);
            writer.close();
            System.out.println("✅ Đã tạo file test: " + testFilePath);

            // Compile using Maven
            ProcessBuilder pb = new ProcessBuilder("cmd.exe", "/c",
                    "D:\\apache-maven-3.9.10\\bin\\mvn.cmd", "clean", "compile");
            pb.directory(new File(projectDir));
            pb.redirectErrorStream(true);
            Process process = pb.start();

            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            StringBuilder output = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("✅ Biên dịch thành công!");
            } else {
                System.err.println("❌ Lỗi khi biên dịch file nguồn:\n" + output.toString());
                throw new RuntimeException("Maven compilation failed. Check console for details.");
            }

        } catch (IOException | InterruptedException e) {
            System.err.println("❌ Lỗi khi ghi hoặc biên dịch file test:\n" + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            System.err.println("⚠️ Vui lòng nhập tên class!");
            return;
        }

        try {
            Class<?> clazz = Class.forName(args[0]);
            String testCode = TestCases(clazz, 5);
            writeTestFile(clazz, testCode);
        } catch (ClassNotFoundException e) {
            System.err.println("❌ Không tìm thấy class: " + args[0]);
        }
    }
}