package My_Source;

public class Class_String {
	
	//Ghép chuỗi
	public String concat(String a, String b) {
        return a + b;
    }
	
	// Đếm độ dài chuỗi
    public int length(String str) {
        return str.length();
    }

    // Kiểm tra chuỗi có rỗng không
    public boolean isEmpty(String str) {
        return str.isEmpty();
    }

    // Chuyển chuỗi sang chữ in hoa
    public String toUpperCase(String str) {
        return str.toUpperCase();
    }

    // Chuyển chuỗi sang chữ thường
    public String toLowerCase(String str) {
        return str.toLowerCase();
    }

    // So sánh hai chuỗi
    public boolean equalsString(String a, String b) {
        return a.equals(b);
    }

    // Cắt chuỗi từ chỉ số start đến end
    public String substring(String str, int start, int end) {
        return str.substring(start, end);
    }
}