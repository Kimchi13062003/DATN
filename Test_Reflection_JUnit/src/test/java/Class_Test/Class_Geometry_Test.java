package Class_Test;
import java.lang.reflect.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import My_Source.Class_Geometry;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class Class_Geometry_Test {

    @ParameterizedTest
    @CsvSource({
        "59.28, 372.467",
        "56.55, 355.314",
    })
    public void chuViHinhTron_Test(double arg0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViHinhTron(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "58.8, 10861.868",
        "44.35, 6179.269",
    })
    public void dienTichHinhTron_Test(double arg0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichHinhTron(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "2.38, 21.22, 50.504",
        "93.87, 31.91, 2995.392",
    })
    public void dienTichChuNhat_Test(double arg0, double arg1, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichChuNhat(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "15.79, 249.324",
        "33.08, 1094.286",
    })
    public void dienTichHinhVuong_Test(double arg0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichHinhVuong(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "79.71, 37.65, 12.3, 129.66",
        "98.78, 21.68, 86.7, 207.16",
    })
    public void chuViTamGiac_Test(double arg0, double arg1, double arg2, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViTamGiac(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "82.01, 328.04",
        "69.43, 277.72",
    })
    public void chuViHinhVuong_Test(double arg0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViHinhVuong(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "84.62, 23.27, 215.78",
        "82.2, 10.55, 185.5",
    })
    public void chuViChuNhat_Test(double arg0, double arg1, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViChuNhat(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "32.4, 16.18, 24.87, 196.688",
        "31.28, 58.85, 65.07, 918.949",
    })
    public void dienTichTamGiac_Test(double arg0, double arg1, double arg2, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichTamGiac(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

}
