package My_Source;

public class Class_Geometry {
	//  Diện tích hình chữ nhật
	public double dienTichChuNhat(double dai, double rong) {
		return Math.round(dai * rong * 1000.0) / 1000.0;
	}

	// Chu vi hình chữ nhật
	public double chuViChuNhat(double dai, double rong) {
		return Math.round(2 * (dai + rong) * 1000.0) / 1000.0;
	}

	// Diện tích hình vuông
	public double dienTichHinhVuong(double canh) {
		return Math.round(canh * canh * 1000.0) / 1000.0;
	}

	// Chu vi hình vuông
	public double chuViHinhVuong(double canh) {
		return 4 * canh;
	}

	// Diện tích hình tròn
	public double dienTichHinhTron(double banKinh) {
		return Math.round(Math.PI * banKinh * banKinh * 1000.0) / 1000.0;
	}

	// Chu vi hình tròn
	public double chuViHinhTron(double banKinh) {
		return Math.round(2 * Math.PI * banKinh * 1000.0) / 1000.0;
	}

	// Diện tích tam giác (Heron)
	public double dienTichTamGiac(double a, double b, double c) {
		if (a > 0 && b > 0 && c > 0 && a + b > c && a + c > b && b + c > a) {
			double s = (a + b + c) / 2;
			double dienTich = Math.sqrt(s * (s - a) * (s - b) * (s - c));
			return Math.round(dienTich * 1000.0) / 1000.0;
		}
		return 0;
	}

	// Chu vi tam giác
	public double chuViTamGiac(double a, double b, double c) {
		return Math.round((a + b + c) * 1000.0) / 1000.0;
	}
}
