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
        "58.18, 21.73, 159.82",
        "96.61, 71.27, 335.76"
    })
    public void chuViChuNhat_Test(double arg0, double arg1, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViChuNhat(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "2.33 , 5.429  ",
        "67.99, 4622.64"
    })
    public void dienTichHinhVuong_Test(double arg0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichHinhVuong(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "96.42, 82.08, 62.32, 2536.101",
        "74.77, 25.62, 27.22, 0.0     "
    })
    public void dienTichTamGiac_Test(double arg0, double arg1, double arg2, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichTamGiac(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "25.9 , 103.6 ",
        "42.47, 169.88"
    })
    public void chuViHinhVuong_Test(double arg0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViHinhVuong(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "35.9, 4048.916",
        "6.57, 135.607 "
    })
    public void dienTichHinhTron_Test(double arg0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichHinhTron(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "25.22, 82.41, 2078.38",
        "1.26 , 32.36, 40.774 "
    })
    public void dienTichChuNhat_Test(double arg0, double arg1, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichChuNhat(arg0, arg1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "10.59, 66.539 ",
        "36.71, 230.656"
    })
    public void chuViHinhTron_Test(double arg0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViHinhTron(arg0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "72.88, 50.74, 53.77, 177.39",
        "10.31, 29.19, 97.57, 137.07"
    })
    public void chuViTamGiac_Test(double arg0, double arg1, double arg2, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViTamGiac(arg0, arg1, arg2);
        assertEquals(expectedResult, actual);
    }

}
