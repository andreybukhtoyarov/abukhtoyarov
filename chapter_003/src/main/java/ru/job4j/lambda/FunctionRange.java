package ru.job4j.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static java.lang.Math.*;

/**
 * Calculation of the function in the range.
 * @author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 * @version %Id%.
 * @since 0.1.
 */
public class FunctionRange {

    /**
     * Calculation of the function in the range.
     * @param start start of range.
     * @param end end of range.
     * @param func functional interface.
     * @return List of range.
     */
    private List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> result = new ArrayList<>();
        for (int iter = start; iter <= end; ++iter) {
            result.add(func.apply((double) iter));
        }
        return result;
    }

    /**
     * If num.length == 1 - logarithmic function,
     * if num.length == 2 - linear function,
     * if num.length == 3 - quadratic function.
     * @param start start of range.
     * @param end end of range.
     * @param num numbers
     * @return List of range.
     */
    public List<Double> funcRange(int start, int end, double... num) {
        return diapason(
                start, end,
                (x) -> {
                    double result = 0d;
                    try {
                        if (num.length == 3) {
                            result = num[0] * pow(x, 2) + num[1] * x + num[2];
                        } else if (num.length == 2) {
                            result = num[0] * x + num[1];
                        } else if (num.length == 1) {
                            if (num[0] < 0 || x < 0 || num[0] == 1) {
                                throw new ArithmeticException();
                            }
                            result = log(x) / log(num[0]);
                        }
                    } catch (ArithmeticException ae) {
                        System.out.println("y = loga х (где а > 0, а ≠ 1, x > 0)");
                    }
                    return result;
                }
        );
    }
}
