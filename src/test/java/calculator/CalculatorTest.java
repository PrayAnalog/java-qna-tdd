package calculator;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;


public class CalculatorTest {
    private Calculator cal;

    @Before
    public void setup() {
        cal = new Calculator();
    }

    @Test
    public void 덧셈_1() {
        assertThat(cal.add("")).isEqualTo(0);
    }

    @Test
    public void 덧셈_2() {
        assertThat(cal.add("1,2")).isEqualTo(0);
    }

    @Test
    public void 덧셈_3() {
        assertThat(cal.add("1,2,3")).isEqualTo(0);
    }

    @Test
    public void 덧셈_4() {
        assertThat(cal.add("1,2:3")).isEqualTo(0);
    }

}
