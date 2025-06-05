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
    public void toLowerCase_Test(String param0, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.toLowerCase(param0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "test,4"
    })
    public void length_Test(String param0, int expectedResult) {
        Class_String obj = new Class_String();
        int actual = obj.length(param0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "abcd,ABCD"
    })
    public void toUpperCase_Test(String param0, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.toUpperCase(param0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "hello,world,helloworld"
    })
    public void concat_Test(String param0, String param1, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.concat(param0, param1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "SPKTHY,UTEHY,false"
    })
    public void equalsString_Test(String param0, String param1, boolean expectedResult) {
        Class_String obj = new Class_String();
        boolean actual = obj.equalsString(param0, param1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "xyz123,1,4,yz1"
    })
    public void substring_Test(String param0, int param1, int param2, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.substring(param0, param1, param2);
        assertEquals(expectedResult, actual);
    }

}
