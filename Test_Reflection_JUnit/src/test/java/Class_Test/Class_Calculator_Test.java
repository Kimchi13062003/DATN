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
        "2,3,5"
    })
    public void add_Test(int param0, int param1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.add(param0, param1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "10,5,5"
    })
    public void sub_Test(int param0, int param1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.sub(param0, param1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "4,true"
    })
    public void isEven_Test(int param0, boolean expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        boolean actual = obj.isEven(param0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "3,3,3,27"
    })
    public void multiply_Test(int param0, int param1, int param2, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.multiply(param0, param1, param2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "3.5,2.5,6.0"
    })
    public void add2_Test(double param0, double param1, double expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        double actual = obj.add2(param0, param1);
        assertEquals(expectedResult, actual);
    }

}
