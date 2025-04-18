package MathTest;

import static org.mockito.ArgumentMatchers.doubleThat;
import static org.mockito.ArgumentMatchers.intThat;
import static org.mockito.ArgumentMatchers.nullable;

import java.lang.reflect.Method;
import java.util.Arrays;

import Math.MathUtils;

public class Reflection_Math {
	public static void main(String[] args) {
		Class<?> clazz = MathUtils.class;
		Object instance;

		try {
			instance = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.err.println("Không thể tạo đối tượng: " + e.getMessage());
			return;
		}
		System.out.println("=== TỰ ĐỘNG SINH TEST JUNIT CHO MathUtils ===");
		for (Method method : clazz.getDeclaredMethods()) {
			method.setAccessible(true);

			Class<?>[] paramTypes = method.getParameterTypes();

			// Chỉ sinh test cho method có <= 2 tham số và kiểu primitive hoặc String
			if (paramTypes.length <= 2 && Arrays.stream(paramTypes)
					.allMatch(t -> t == int.class || t == double.class || t == boolean.class || t == String.class));

			Object[] sampleArgs = Arrays.stream(paramTypes).map(t -> {
				if (t == int.class)
					return 5;
				if (t == double.class)
					return 3.14;
				if (t == boolean.class)
					return true;
				if (t == String.class)
					return "abc";
				return null;
			}).toArray();
			
			try {
				Object result = method.invoke(instance, sampleArgs);
				String argsStr = Arrays.toString(sampleArgs);
				
				System.out.printf("// [OK] %s%s => %s\n", method.getName(), argsStr, result);
				
				// Sinh mã Test
				System.out.println("@Test");
				System.out.println("public void test_" + method.getName() + "() {");
				System.out.println("	MathUtils utils = new MathUtils();");
				
				// Build dòng gọi hàm
				StringBuilder callLine = new StringBuilder("	var result = utils." + method.getName() + "(");
				for (int i = 0; i < sampleArgs.length; i ++) {
					callLine.append(sampleArgs[i]);
					if (i < sampleArgs.length - 1) callLine.append(", ");
				}
				callLine.append(");");
				System.out.println(callLine);
				
				System.out.println("    // TODO: Điền expected phù hợp");
				if (result instanceof Boolean) {
					System.out.println("    assertTrue(result);");
				} else {
					System.out.println("    assertEquals(/* expected */, result);");
				}
				System.out.println("}\n");
			} catch (Exception e) {
				System.err.printf("[ERR] %s: %s\n", method.getName(), e.getMessage());
			}
		}
	}
}
