package calculator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator {

    public int add(String text) {
        if (!isValid(text)) {
            throw new IllegalArgumentException();
        }

        if (isBlank(text)) {
            return 0;
        }


        Matcher m = Pattern.compile("//(.*)\n(.*)").matcher(text);
        if (m.find()) {
            String delimiter = m.group(1);
            return addWithGivenDelimiter(m.group(2), delimiter);
        }
        return addWithGivenDelimiter(text, "[,:]");

    }

    private boolean isValid(String text) {
        Matcher m = Pattern.compile("()|//(.+)\n(.*)|(([0-9]+)([,:]([0-9]+))*)").matcher(text);
        return m.find();
    }

    public int addWithGivenDelimiter(String text, String delimiter) {
        if (delimiter.isEmpty()) {
            throw new IllegalArgumentException();
        }

        String[] values = text.split(delimiter);
        int result = Integer.parseInt(values[0]);

        for (int i = 1; i < values.length; i++) {
            result = result + Integer.parseInt(values[i]);
        }

        return result;
    }

    public boolean isBlank(String text) {
        return text.equals("");
    }
}
