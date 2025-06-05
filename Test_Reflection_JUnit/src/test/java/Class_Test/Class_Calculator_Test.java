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
"38,86,124",
        "84,38,122"
    })
    public void add_Test(int arg0, int arg1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.add(arg0,arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
"44,77,69,233772",
        "52,83,95,410020"
    })
    public void multiply_Test(int arg0, int arg1, int arg2, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.multiply(arg0,arg1,arg2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
"15,41,-26",
        "77,20,57"
    })
    public void sub_Test(int arg0, int arg1, int expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        int actual = obj.sub(arg0,arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
"49,false",
        "10,true"
    })
    public void isEven_Test(int arg0, boolean expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        boolean actual = obj.isEven(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
"86.73,32.62,119.35",
        "81.09,41.37,122.46000000000001"
    })
    public void add2_Test(double arg0, double arg1, double expectedResult) {
        Class_Calculator obj = new Class_Calculator();
        double actual = obj.add2(arg0,arg1);
        assertEquals(expectedResult, actual);
    }

}
