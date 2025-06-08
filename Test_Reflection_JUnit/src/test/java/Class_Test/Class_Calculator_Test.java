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
        "75, 73, 148",
        "18, 50, 68 "
    })
    public void add_Test(int arg0, int arg1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.add(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "51, 37, 1 , 1887  ",
        "85, 46, 72, 281520"
    })
    public void multiply_Test(int arg0, int arg1, int arg2, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.multiply(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "11, 27, -16",
        "18, 26, -8 "
    })
    public void sub_Test(int arg0, int arg1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.sub(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "9 , false",
        "97, false"
    })
    public void isEven_Test(int arg0, boolean expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        boolean actual = obj.isEven(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "52.17, 58.18, 110.35",
        "37.48, 44.14, 81.62 "
    })
    public void add2_Test(double arg0, double arg1, double expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        double actual = obj.add2(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

}
