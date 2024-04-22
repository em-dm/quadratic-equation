package emdm.otus.quadratic.equation;

import org.junit.jupiter.api.Test;

import static emdm.otus.quadratic.equation.QuadraticEquation.solve;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class QuadraticEquationTest {
    private static double epsilon = 1e-5;

    @Test
    void noRootsTest() { // D < 0
        double[] roots = solve(1.0, 0.0, 1.0, epsilon); // x^2 + 1 = 0

        assertEquals(0, roots.length);
    }

    @Test
    void oneRootTest() { // D = 0
        double[] roots = solve(1.0, 2.0, 1.0, epsilon); // x^2 + 2x + 1 = 0

        assertEquals(2, roots.length);
        assertTrue(Math.abs(roots[0] - roots[1]) <= epsilon);
        assertTrue(Math.abs(roots[0] - (-1.0)) <= epsilon); // x1 = x2 = -1
    }

    @Test
    void discriminantLessEpsilonTest() { // |D| < e
        double[] roots = solve(1.0, 2e-7, 1e-7, epsilon); // x^2 + 2e-7x + 1e-7 = 0

        assertEquals(2, roots.length);
        assertTrue(Math.abs(roots[0] - roots[1]) <= epsilon);
        assertTrue(Math.abs(roots[0] - (-1.0e-7)) <= epsilon); // x1 = x2 = -1.0e-7
    }

    @Test
    void twoRootsTest() { // D > 0
        double[] roots = solve(1.0, 0.0, -1.0, epsilon); // x^2 - 1 = 0

        assertEquals(2, roots.length);
        assertTrue(Math.abs(roots[0] - roots[1]) > epsilon);
        assertTrue(Math.abs(roots[0] - 1.0) <= epsilon); // x1 = 1
        assertTrue(Math.abs(roots[1] - (-1.0)) <= epsilon); // x2 = -1
    }

    @Test
    void aNonZeroTest() { // a != 0
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> solve(1e-7, 1.0, 1.0, epsilon), // 1e-7x^2 + x + 1 = 0
                "Expected solve() to throw"
        );
        assertEquals("a равно 0", thrown.getMessage());
    }

    @Test
    void aNaNTest() { // a = NaN
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> solve(Double.NaN, 1.0, 1.0, epsilon),
                "Expected solve() to throw"
        );
        assertEquals("NaN", thrown.getMessage());
    }

    @Test
    void positiveInfinityTest() { // a = POSITIVE_INFINITY
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> solve(Double.POSITIVE_INFINITY, 1.0, 1.0, epsilon),
                "Expected solve() to throw"
        );
        assertEquals("POSITIVE_INFINITY", thrown.getMessage());
    }

    @Test
    void negativeInfinityTest() { // a = NEGATIVE_INFINITY
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> solve(Double.NEGATIVE_INFINITY, 1.0, 1.0, epsilon),
                "Expected solve() to throw"
        );
        assertEquals("NEGATIVE_INFINITY", thrown.getMessage());
    }
}