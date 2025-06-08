package Class_Test;
import java.lang.reflect.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import My_Source.Class_Calculator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Class_Calculator_Test {

    @ParameterizedTest
    @CsvSource({
        "81, 87, 168",
        "83, 64, 147"
    })
    public void add_Test(int arg0, int arg1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.add(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "73, 68, 5 , 24820",
        "45, 25, 55, 61875"
    })
    public void multiply_Test(int arg0, int arg1, int arg2, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.multiply(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "45, 96, -51",
        "97, 67, 30 "
    })
    public void sub_Test(int arg0, int arg1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.sub(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "65.05, 25.23, 90.28 ",
        "33.85, 82.18, 116.03"
    })
    public void add2_Test(double arg0, double arg1, double expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        double actual = obj.add2(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "59, false",
        "91, false"
    })
    public void isEven_Test(int arg0, boolean expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        boolean actual = obj.isEven(arg0);
        assertEquals(expectedResult, actual);
    }

}
