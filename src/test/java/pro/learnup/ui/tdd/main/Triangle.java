package pro.learnup.ui.tdd.main;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triangle {
    private int a;
    private int b;
    private int c;

    public int countPerimeter() {
        if (a <= 0 || b <= 0 || c <= 0 || a >= b + c || b >= a + c || c >= b + a) {
            throw new IllegalArgumentException("invalid triangle");
        }
        return a + b + c;
    }

}
