package MathTest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import Math.MathUtils;

public class MathTest {
	// [OK] factorial[5] => 120
	@Test
	public void test_factorial() {
		MathUtils utils = new MathUtils();
		var result = utils.factorial(5);
	    // TODO: Điền expected phù hợp
	    assertEquals(120, result);
	}

//	// [OK] isEven[5] => false
//	@Test
//	void test_isEven() {
//		MathUtils utils = new MathUtils();
//		var result = utils.isEven(5);
//	    // TODO: Điền expected phù hợp
//	    assertTrue(result);
//	}
	
	@Test
	public void test_isEven() throws Exception {
	    MathUtils utils = new MathUtils();
	    var method = MathUtils.class.getDeclaredMethod("isEven", int.class);
	    method.setAccessible(true);
	    boolean result = (boolean) method.invoke(utils, 5);
	    // TODO: Điền expected phù hợp
	    assertEquals(false, result);
	}

	// [OK] add[5, 5] => 10
	@Test
	public void test_add() {
		MathUtils utils = new MathUtils();
		var result = utils.add(5, 5);
	    // TODO: Điền expected phù hợp
	    assertEquals(10, result);
	}
}
