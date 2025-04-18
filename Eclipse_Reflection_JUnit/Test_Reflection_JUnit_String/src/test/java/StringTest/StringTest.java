package StringTest;

import org.junit.jupiter.api.Test;
import String.StringUtils;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class StringTest {
	// [OK] reverse("hello world") = dlrow olleh

	@Test
	void test_reverse() {
		StringUtils utils = new StringUtils();
		var result = utils.reverse("hello world");
		// Điền expected phù hợp
		assertEquals("dlrow olleh", result);
	}

//	// [OK] split("hello world") = [hello, world]
//
//	@Test
//	void test_split() {
//		StringUtils utils = new StringUtils();
//		var result = utils.split("hello world");
//		// Điền expected phù hợp
//		assertArrayEquals("[hello, world]", (Object[]) result);
//	}

//	// [OK] trim("hello world") = hello world
//
//	@Test
//	void test_trim() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.trim("hello world");
//	    // Điền expected phù hợp
//	    assertEquals("hello world", result);
//	}

//	// [OK] strip("hello world") = hello world
//
//	@Test
//	void test_strip() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.strip("hello world");
//	    // Điền expected phù hợp
//	    assertEquals("hello world", result);
//	}

//	// [OK] capitalize("hello world") = Hello world
//
//	@Test
//	void test_capitalize() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.capitalize("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] uncapitalize("hello world") = hello world
//
//	@Test
//	void test_uncapitalize() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.uncapitalize("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] stripToNull("hello world") = hello world
//
//	@Test
//	void test_stripToNull() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.stripToNull("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] toRootLowerCase("hello world") = hello world
//
//	@Test
//	void test_toRootLowerCase() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.toRootLowerCase("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] toRootUpperCase("hello world") = HELLO WORLD
//
//	@Test
//	void test_toRootUpperCase() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.toRootUpperCase("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] trimToEmpty("hello world") = hello world
//
//	@Test
//	void test_trimToEmpty() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.trimToEmpty("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] stripToEmpty("hello world") = hello world
//
//	@Test
//	void test_stripToEmpty() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.stripToEmpty("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] stripAccents("hello world") = hello world
//
//	@Test
//	void test_stripAccents() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.stripAccents("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] deleteWhitespace("hello world") = helloworld
//
//	@Test
//	void test_deleteWhitespace() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.deleteWhitespace("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] normalizeSpace("hello world") = hello world
//
//	@Test
//	void test_normalizeSpace() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.normalizeSpace("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] defaultString("hello world") = hello world
//
//	@Test
//	void test_defaultString() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.defaultString("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] splitPreserveAllTokens("hello world") = [hello, world]
//
//	@Test
//	void test_splitPreserveAllTokens() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.splitPreserveAllTokens("hello world");
//	    // Điền expected phù hợp
//	    assertArrayEquals(/* expected */, (Object[]) result);
//	}

//	// [OK] splitByCharacterType("hello world") = [hello, , world]
//
//	@Test
//	void test_splitByCharacterType() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.splitByCharacterType("hello world");
//	    // Điền expected phù hợp
//	    assertArrayEquals(/* expected */, (Object[]) result);
//	}

//	// [OK] chomp("hello world") = hello world
//
//	@Test
//	void test_chomp() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.chomp("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] chop("hello world") = hello worl
//
//	@Test
//	void test_chop() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.chop("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] getDigits("hello world") =
//
//	@Test
//	void test_getDigits() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.getDigits("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] lowerCase("hello world") = hello world
//
//	@Test
//	void test_lowerCase() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.lowerCase("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] swapCase("hello world") = HELLO WORLD
//
//	@Test
//	void test_swapCase() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.swapCase("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] upperCase("hello world") = HELLO WORLD
//
//	@Test
//	void test_upperCase() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.upperCase("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] trimToNull("hello world") = hello world
//
//	@Test
//	void test_trimToNull() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.trimToNull("hello world");
//	    // Điền expected phù hợp
//	    assertEquals(/* expected */, result);
//	}

//	// [OK] splitByCharacterTypeCamelCase("hello world") = [hello, , world]
//
//	@Test
//	void test_splitByCharacterTypeCamelCase() {
//	    StringUtils utils = new StringUtils();
//	    var result = utils.splitByCharacterTypeCamelCase("hello world");
//	    // Điền expected phù hợp
//	    assertArrayEquals(/* expected */, (Object[]) result);
//	}

}