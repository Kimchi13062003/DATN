package Calculator_Test;

import static org.junit.jupiter.api.Assertions.*;
import java.lang.reflect.Method;
import org.junit.jupiter.api.Test;
import Calculator.Calculator;

public class CalculatorTest {

	public static void main(String[] args) {
		Class<?> clazz = Calculator.class;

		System.out.println("SINH MÃ JUNIT TEST TỰ ĐỘNG");

		for (Method method : clazz.getDeclaredMethods()) {
			method.setAccessible(true);

			Class<?>[] paramTypes = method.getParameterTypes();

			if (paramTypes.length == 2 && paramTypes[0] == int.class && paramTypes[1] == int.class) {

				String testName = "test_" + method.getName();
				String methodCall = String.format("calculator.%s(2, 3)", method.getName());

				System.out.println();
				System.out.println("@Test");
				System.out.println("void " + testName + "() {");
				System.out.println("    Calculator calculator = new Calculator();");
				System.out.println("    int result = " + methodCall + ";");
				System.out.println("    // TODO: chỉnh sửa giá trị mong đợi cho đúng");
				System.out.println("    assertEquals( /* expected */, result);");
				System.out.println("}");
			}
		}
	}
}