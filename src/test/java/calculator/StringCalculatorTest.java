package calculator;

import org.junit.Test;
import support.test.BaseTest;

public class StringCalculatorTest extends BaseTest {
    @Test
    public void 덧셈() {
        softly.assertThat(StringCalculator.calculate("1 + 2")).isEqualTo(3);

        softly.assertThat(StringCalculator.calculate("3 + 2")).isEqualTo(5);
    }

    @Test
    public void 뺄셈() {
        softly.assertThat(StringCalculator.calculate("3 - 2")).isEqualTo(1);
    }

    @Test
    public void 곱셈() {

    }

    @Test
    public void 나눗셈() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void 값_null() {

    }

    @Test(expected = IllegalArgumentException.class)
    public void 값_빈공백() {

    }

    @Test
    public void calculate() {

    }
}
