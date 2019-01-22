package calculator;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CalculatorTest {
    private Calculator cal;

    @BeforeEach

    public void 시작() {
        cal = new Calculator();
    }

    @Test
    public void 빈칸일때_결과는_0() {
        assertThat(cal.add("")).isEqualTo(0);
    }


    @Test
    public void null일때_예외던짐() {
        assertThrows(RuntimeException.class, () -> {
            cal.add(null);
        });
    }

    @Test
    public void 입력이_1개일_경우() {
        assertThat(cal.add("1")).isEqualTo(1);
    }

    @Test
    public void 입력이_2개일_경우() {
        assertThat(cal.add("1,2")).isEqualTo(3);
    }

    @Test
    public void 입력이_3개일_경우() {
        assertThat(cal.add("1,2,3")).isEqualTo(6);
    }

    @Test
    public void 구분자가_다른_경우() {
        assertThat(cal.add("1,2:3")).isEqualTo(6);
    }

    @Test
    public void 음수도_입력된_경우() {
        assertThat(cal.add("-1,2:3")).isEqualTo(4);
    }

    @Nested
    public class 숫자_이외의_값이_입력된_경우 {


        @Test
        void _앞에() {
            assertThrows(RuntimeException.class, () -> {
                cal.add("null,2:3");
            });

        }

        @Test
        void _뒤에() {
            assertThrows(RuntimeException.class, () -> {
                cal.add("2:null");
            });
        }

    }

    @Test
    void 구분자_지정() {
        assertThat(cal.add("//;\n1;2;3")).isEqualTo(6);
    }

    @Test
    void 구분자_없는데_지정문자_있음() {
        assertThrows(IllegalArgumentException.class, () -> {
            cal.add("//\n1;2;3");
        });
    }

}
