package Auto_Calculator;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Arrays;

public class Reflection_Calculator_New {
	public static void main(String[] args) {
		Class<?>[] classes = { MyCalculator_String.Calculator_New.class, MyCalculator_String.String_New.class };

		try (PrintWriter out = new PrintWriter(new FileWriter("src/test/java/Auto_Calculator/Auto_Test.java"))) {
			
			// Ghi phần đầu file test
			out.println("package Auto_Calculator;");
			out.println();
			out.println("import java.lang.reflect.*;");
			out.println("import org.junit.Test;");
			out.println("import static org.junit.Assert.*;");
			out.println("import MyCalculator_String.Calculator_New;");
			out.println("import MyCalculator_String.String_New;");
			out.println();
			out.println("public class Auto_Test {");
			out.println();
			
			// Tạo các @Test
			int testCount = 1;
			for (Class<?> clazz : classes) { // Duyệt qua từng class
				Object instance = clazz.getDeclaredConstructor().newInstance(); // Tạo đối tượng

				for (Method method : clazz.getDeclaredMethods()) { // Duyệt qua từng method
					method.setAccessible(true);
					Class<?>[] paramTypes = method.getParameterTypes();

					Object[] sampleArgs = new Object[paramTypes.length];
					for (int i = 0; i < paramTypes.length; i++) {
						sampleArgs[i] = getSampleValue(paramTypes[i]);
					}

					try {
						Object resultValue = method.invoke(instance, sampleArgs);
						String expected = literal(resultValue);

						// Tạo tên hàm test 
						String methodName = clazz.getSimpleName() + "_" + method.getName() + "_" + (testCount++);

						// Viết @Test
						out.println("    @Test");
						out.printf("    public void %s() throws Exception {\n", methodName);
						out.printf("        %s calc = new %s();\n", clazz.getSimpleName(), clazz.getSimpleName());
						out.printf("        Method method = %s.class.getDeclaredMethod(\"%s\", %s);\n",
								clazz.getSimpleName(), method.getName(), getParamClassesString(paramTypes));
						out.println("        method.setAccessible(true);");

						for (int i = 0; i < sampleArgs.length; i++) {
							out.printf("        Object arg%d = %s;\n", i, literal(sampleArgs[i]));
						}

						String argsList = "";
						for (int i = 0; i < sampleArgs.length; i++) {
							argsList += "arg" + i;
							if (i < sampleArgs.length - 1)
								argsList += ", ";
						}

						out.printf("        Object result = method.invoke(calc, %s);\n", argsList);
						out.printf("        assertEquals(%s, result);\n", expected);
						out.println("    }");
						out.println();

					} catch (Exception e) {
						System.err.printf("[ERR] %s.%s: %s\n", clazz.getSimpleName(), method.getName(), e.getMessage());
					}
				}
			}

			out.println("}");
			System.out.println("Đã tạo file test: src/test/java/Auto_Calculator/Auto_Test.java");

		} catch (Exception e) {
			System.err.println("Không thể ghi file: " + e.getMessage());
		}
	}

	// Giá trị mẫu cho các tham số
	static Object getSampleValue(Class<?> type) {
		if (type == int.class)
			return 4;
		if (type == double.class)
			return 2.0;
		if (type == boolean.class)
			return true;
		if (type == int[].class)
			return new int[] { 1, 2, 3 };
		if (type == String.class)
			return "hello _ world";
		return null;
	}

	// Sinh danh sách các loại tham số (vd: int.class, String.class,...)
	static String getParamClassesString(Class<?>[] types) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < types.length; i++) {
			sb.append(types[i].getCanonicalName()).append(".class");
			if (i < types.length - 1)
				sb.append(", ");
		}
		return sb.toString();
	}

	// Chuyển giá trị thành dạng literal để ghi vào code
	static String literal(Object val) {
		if (val instanceof String)
			return "\"" + val + "\"";
		if (val instanceof Integer || val instanceof Double || val instanceof Boolean)
			return val.toString();
		if (val instanceof int[])
			return "new int[]{" + Arrays.toString((int[]) val).replaceAll("[\\[\\]]", "") + "}";
		return "null";
	}
}
