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
        "47, 50, 97 ",
        "17, 49, 66 ",
        "81, 74, 155"
    })
    public void add_Test(int arg0, int arg1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.add(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "68, 75, 59, 300900",
        "92, 64, 85, 500480",
        "90, 79, 76, 540360"
    })
    public void multiply_Test(int arg0, int arg1, int arg2, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.multiply(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "38, 99, -61",
        "51, 40, 11 ",
        "72, 77, -5 "
    })
    public void sub_Test(int arg0, int arg1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.sub(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "67.5 , 19.44, 86.94             ",
        "4.38 , 17.4 , 21.779999999999998",
        "33.83, 76.92, 110.75            "
    })
    public void add2_Test(double arg0, double arg1, double expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        double actual = obj.add2(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "84, true",
        "50, true",
        "8 , true"
    })
    public void isEven_Test(int arg0, boolean expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        boolean actual = obj.isEven(arg0);
        assertEquals(expectedResult, actual);
    }

}
