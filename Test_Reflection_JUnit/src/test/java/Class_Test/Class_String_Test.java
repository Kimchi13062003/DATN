package Class_Test;
import java.lang.reflect.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import My_Source.Class_String;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Class_String_Test {

    @ParameterizedTest
    @CsvSource({
        "UTEHY,utehy"
    })
    public void toLowerCase_Test(String arg0, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.toLowerCase(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "test,4"
    })
    public void length_Test(String arg0, int expectedResult) {
        Class_String obj = new Class_String();
        int actual = obj.length(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "abcd,ABCD"
    })
    public void toUpperCase_Test(String arg0, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.toUpperCase(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "hello,world,helloworld"
    })
    public void concat_Test(String arg0, String arg1, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.concat(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "SPKTHY,UTEHY,false"
    })
    public void equalsString_Test(String arg0, String arg1, boolean expectedResult) {
        Class_String obj = new Class_String();
        boolean actual = obj.equalsString(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "xyz123,1,4,yz1"
    })
    public void substring_Test(String arg0, int arg1, int arg2, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.substring(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

}
