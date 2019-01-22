package calculator;

public class StringCalculator {
    public static int calculate(String text) {
        String[] values = text.split(" ");
        int first = Integer.parseInt(values[0]);
        int second = Integer.parseInt(values[2]);

        return first + second;
    }
}
