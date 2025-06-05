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
        "4.0,16.0"
    })
    public void dienTichHinhVuong_Test(double param0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichHinhVuong(param0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "10.25,64.403"
    })
    public void chuViHinhTron_Test(double param0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViHinhTron(param0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "5.5,8.3,3.6,17.4"
    })
    public void chuViTamGiac_Test(double param0, double param1, double param2, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViTamGiac(param0, param1, param2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "6.0,3.5,21.0"
    })
    public void dienTichChuNhat_Test(double param0, double param1, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichChuNhat(param0, param1);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "12.5,490.874"
    })
    public void dienTichHinhTron_Test(double param0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichHinhTron(param0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "3.5,14.0"
    })
    public void chuViHinhVuong_Test(double param0, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViHinhVuong(param0);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "10.6,8.5,6.0,25.479"
    })
    public void dienTichTamGiac_Test(double param0, double param1, double param2, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.dienTichTamGiac(param0, param1, param2);
        assertEquals(expectedResult, actual);
    }

    @ParameterizedTest
    @CsvSource({
        "4.0,3.0,14.0"
    })
    public void chuViChuNhat_Test(double param0, double param1, double expectedResult) {
        Class_Geometry obj = new Class_Geometry();
        double actual = obj.chuViChuNhat(param0, param1);
        assertEquals(expectedResult, actual);
    }

}
