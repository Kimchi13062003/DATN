package String;

public class StringUtils {
    public String reverse(String input) {
        return new StringBuilder(input).reverse().toString();
    }

    public String toUpper(String input) {
        return input.toUpperCase();
    }

    private int countWords(String input) {
        if (input == null || input.trim().isEmpty()) return 0;
        return input.trim().split("\\s+").length;
    }
}