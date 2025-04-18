package Calculator_Test;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import Calculator.Calculator;

public class Reflection_Calculator {
	public static void main(String[] args) {
		Class<?> clazz = Calculator.class;
		Object instance;

		try {
			instance = clazz.getDeclaredConstructor().newInstance();
		} catch (Exception e) {
			System.err.println("Không thể tạo instance: " + e.getMessage());
			return;
		}

		System.out.println("SINH KIỂM THỬ TỰ ĐỘNG BẰNG REFLECTION");
		for (Method method : clazz.getDeclaredMethods()) {
			method.setAccessible(true);// Cho phép truy cập cả private
			
				Class<?>[] paramTypes = method.getParameterTypes();

				// Nếu có đúng 2 tham số int
				if (paramTypes.length == 2 && paramTypes[0] == int.class && paramTypes[1] == int.class) {
					try {
						Object result = method.invoke(instance, 2, 3);
						System.out.printf("Method: %s(2, 3) = %s\n", method.getName(), result);
					} catch (Exception e) {
						System.err.println("Lỗi khi gọi method: " + e.getMessage());
					}
				
			}
		}
	}
}