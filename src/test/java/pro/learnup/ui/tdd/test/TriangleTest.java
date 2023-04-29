package pro.learnup.ui.tdd.test;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pro.learnup.ui.tdd.main.Triangle;

import static com.codeborne.selenide.Selenide.$;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class TriangleTest {

    @ParameterizedTest
    @CsvSource(value = {"3, 4, 5, 12", "3, 4, 6, 13", "3, 4, 4, 11"})
    void positiveTrianglesTest(int a, int b, int c, int expectedPerimeter) {
        assertThat(new Triangle(a, b, c).countPerimeter())
                .isEqualTo(expectedPerimeter);
    }

    @ParameterizedTest
    @CsvSource(value = {"-3, 4, 5", "3, -4, 6", "3, 4, -4", "3, 2, 1", "2, 3, 1", "1, 2, 3"})
    void positiveTrianglesTest(int a, int b, int c) {
        assertThatThrownBy(() -> new Triangle(a, b, c).countPerimeter())
                .isInstanceOf(IllegalArgumentException.class)
                        .hasMessage("invalid triangle");
    }
}
