package Auto_Calculator;

import java.lang.reflect.*;
import org.junit.Test;
import static org.junit.Assert.*;
import MyCalculator_String.Calculator_New;
import MyCalculator_String.String_New;

public class Auto_Test {

    @Test
    public void Calculator_New_add_1() throws Exception {
        Calculator_New calc = new Calculator_New();
        Method method = Calculator_New.class.getDeclaredMethod("add", int.class, int.class);
        method.setAccessible(true);
        Object arg0 = 2;
        Object arg1 = 4;
        Object result = method.invoke(calc, arg0, arg1);
        assertEquals(6, result);
    }

    @Test
    public void Calculator_New_multiply_2() throws Exception {
        Calculator_New calc = new Calculator_New();
        Method method = Calculator_New.class.getDeclaredMethod("multiply", int.class, int.class, int.class);
        method.setAccessible(true);
        Object arg0 = 4;
        Object arg1 = 4;
        Object arg2 = 4;
        Object result = method.invoke(calc, arg0, arg1, arg2);
        assertEquals(64, result);
    }

    @Test
    public void Calculator_New_sumArray_3() throws Exception {
        Calculator_New calc = new Calculator_New();
        Method method = Calculator_New.class.getDeclaredMethod("sumArray", int[].class);
        method.setAccessible(true);
        Object arg0 = new int[]{1, 2, 3};
        Object result = method.invoke(calc, arg0);
        assertEquals(6, result);
    }

    @Test
    public void Calculator_New_add2_4() throws Exception {
        Calculator_New calc = new Calculator_New();
        Method method = Calculator_New.class.getDeclaredMethod("add2", double.class, double.class);
        method.setAccessible(true);
        Object arg0 = 2.0;
        Object arg1 = 2.0;
        Object result = method.invoke(calc, arg0, arg1);
        assertEquals(4.0, result);
    }

    @Test
    public void Calculator_New_isEven_5() throws Exception {
        Calculator_New calc = new Calculator_New();
        Method method = Calculator_New.class.getDeclaredMethod("isEven", int.class);
        method.setAccessible(true);
        Object arg0 = 4;
        Object result = method.invoke(calc, arg0);
        assertEquals(true, result);
    }

    @Test
    public void String_New_length_6() throws Exception {
        String_New calc = new String_New();
        Method method = String_New.class.getDeclaredMethod("length", java.lang.String.class);
        method.setAccessible(true);
        Object arg0 = "hello _ world";
        Object result = method.invoke(calc, arg0);
        assertEquals(13, result);
    }

    @Test
    public void String_New_substring_7() throws Exception {
        String_New calc = new String_New();
        Method method = String_New.class.getDeclaredMethod("substring", java.lang.String.class, int.class, int.class);
        method.setAccessible(true);
        Object arg0 = "hello _ world";
        Object arg1 = 4;
        Object arg2 = 4;
        Object result = method.invoke(calc, arg0, arg1, arg2);
        assertEquals("", result);
    }

    @Test
    public void String_New_isEmpty_8() throws Exception {
        String_New calc = new String_New();
        Method method = String_New.class.getDeclaredMethod("isEmpty", java.lang.String.class);
        method.setAccessible(true);
        Object arg0 = "hello _ world";
        Object result = method.invoke(calc, arg0);
        assertEquals(false, result);
    }

    @Test
    public void String_New_toLowerCase_9() throws Exception {
        String_New calc = new String_New();
        Method method = String_New.class.getDeclaredMethod("toLowerCase", java.lang.String.class);
        method.setAccessible(true);
        Object arg0 = "hello _ world";
        Object result = method.invoke(calc, arg0);
        assertEquals("hello _ world", result);
    }

    @Test
    public void String_New_toUpperCase_10() throws Exception {
        String_New calc = new String_New();
        Method method = String_New.class.getDeclaredMethod("toUpperCase", java.lang.String.class);
        method.setAccessible(true);
        Object arg0 = "hello _ world";
        Object result = method.invoke(calc, arg0);
        assertEquals("HELLO _ WORLD", result);
    }

    @Test
    public void String_New_concat_11() throws Exception {
        String_New calc = new String_New();
        Method method = String_New.class.getDeclaredMethod("concat", java.lang.String.class, java.lang.String.class);
        method.setAccessible(true);
        Object arg0 = "hello _ world";
        Object arg1 = "hello _ world";
        Object result = method.invoke(calc, arg0, arg1);
        assertEquals("hello _ worldhello _ world", result);
    }

    @Test
    public void String_New_equalsString_12() throws Exception {
        String_New calc = new String_New();
        Method method = String_New.class.getDeclaredMethod("equalsString", java.lang.String.class, java.lang.String.class);
        method.setAccessible(true);
        Object arg0 = "hello _ world";
        Object arg1 = "hello _ world";
        Object result = method.invoke(calc, arg0, arg1);
        assertEquals(true, result);
    }

}
