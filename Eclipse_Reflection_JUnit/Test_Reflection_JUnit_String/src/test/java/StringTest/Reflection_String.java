package StringTest;

import java.lang.reflect.Method;
import java.util.Arrays;
import org.apache.commons.lang3.StringUtils;

public class Reflection_String {

	public static void main(String[] args) {
		Class<?> clazz = StringUtils.class;
		Object instance;

		try {
			instance = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.err.println("Không thể tạo đối tượng: " + e.getMessage());
			return;
		}
		

		System.out.println("----TỰ ĐỘNG KIỂM THỬ VÀ SINH MÃ TEST----");
		
		// Lặp qua tất cả phương thức được khai báo
		for (Method method : clazz.getDeclaredMethods()) {
			method.setAccessible(true); // Gọi được cả method private, protected,...

			Class<?>[] paramTypes = method.getParameterTypes();

			if (paramTypes.length == 1 && paramTypes[0] == String.class) { // Chỉ xử lý method có 1 tham số duy nhất là String
				try {
					Object result = method.invoke(instance, "hello world");

					// In kết quả phù hợp 
					String displayResult;
					if (result != null && result.getClass().isArray()) {
						displayResult = Arrays.deepToString((Object[]) result);
					} else {
						displayResult = String.valueOf(result);
					}

					System.out.printf("// [OK] %s(\"hello world\") = %s\n", method.getName(), displayResult);

					// Sinh mã @Test
					System.out.println();
					System.out.println("@Test");
					System.out.println("void test_" + method.getName() + "() {");
					System.out.println("    StringUtils utils = new StringUtils();");
					System.out.printf("    var result = utils.%s(\"hello world\");\n", method.getName());

					if (result != null && result.getClass().isArray()) {
						System.out.println("    // Điền expected phù hợp");
						System.out.println("    assertArrayEquals(/* expected */, (Object[]) result);");
					} else {
						System.out.println("    // Điền expected phù hợp");
						System.out.println("    assertEquals(/* expected */, result);");
					}
					
					System.out.println("}");
					System.out.println();
					
				} catch (Exception e) {
					System.err.printf("[ERR] %s: %s\n", method.getName(), e.getMessage());
				}
			}
		}
	}
}
