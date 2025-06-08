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
        "YPH0z, 5",
        "MTLqX, 5",
        "gZi1D, 5"
    })
    public void length_Test(String arg0, int expectedResult) {
        Class_String obj = new Class_String();
        int actual = obj.length(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "X37dd, 63, 79, null",
        "PQxiq, 87, 21, null",
        "GShYC, 99, 98, null"
    })
    public void substring_Test(String arg0, int arg1, int arg2, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.substring(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "nPLwB, false",
        "rmQdW, false",
        "JYq9K, false"
    })
    public void isEmpty_Test(String arg0, boolean expectedResult) {
        Class_String obj = new Class_String();
        boolean actual = obj.isEmpty(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "kVdPp, kvdpp",
        "hgt8M, hgt8m",
        "Uugxi, uugxi"
    })
    public void toLowerCase_Test(String arg0, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.toLowerCase(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "AdkDX, ADKDX",
        "HOEWT, HOEWT",
        "fQpy1, FQPY1"
    })
    public void toUpperCase_Test(String arg0, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.toUpperCase(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "j7wUg, wj4LS, j7wUgwj4LS",
        "u2w4C, VSRL8, u2w4CVSRL8",
        "QnS0o, C9p8c, QnS0oC9p8c"
    })
    public void concat_Test(String arg0, String arg1, String expectedResult) {
        Class_String obj = new Class_String();
        String actual = obj.concat(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "YFUIB, IBdHd, false",
        "uauZe, LwYXl, false",
        "pve2K, 9tYru, false"
    })
    public void equalsString_Test(String arg0, String arg1, boolean expectedResult) {
        Class_String obj = new Class_String();
        boolean actual = obj.equalsString(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

}
