package emdm.otus.quadratic.equation;

public class QuadraticEquation {

    /**
     * ax^2 + by + c = 0
     *
     * @param a
     * @param b
     * @param c
     * @param epsilon
     * @return roots
     */
    static double[] solve(double a, double b, double c, double epsilon) {
        double[] roots = null;

        if (Double.isNaN(a) || Double.isNaN(b) || Double.isNaN(c) || Double.isNaN(epsilon)) {
            throw new IllegalArgumentException("NaN");
        }

        if (a == Double.POSITIVE_INFINITY || b == Double.POSITIVE_INFINITY || c == Double.POSITIVE_INFINITY || epsilon == Double.POSITIVE_INFINITY) {
            throw new IllegalArgumentException("POSITIVE_INFINITY");
        }

        if (a == Double.NEGATIVE_INFINITY || b == Double.NEGATIVE_INFINITY || c == Double.NEGATIVE_INFINITY || epsilon == Double.NEGATIVE_INFINITY) {
            throw new IllegalArgumentException("NEGATIVE_INFINITY");
        }

        if (Math.abs(a) <= epsilon) {
            throw new IllegalArgumentException("a равно 0");
        }

        double discriminant = b * b - 4 * a * c;

        if (discriminant < -epsilon) {
            roots = new double[0];
        }
        if (Math.abs(discriminant) <= epsilon) {
            roots = new double[]{-b / (2 * a), -b / (2 * a)};
        }
        if (discriminant > epsilon) {
            roots = new double[]{(-b + Math.sqrt(discriminant)) / (2 * a), (-b - Math.sqrt(discriminant)) / (2 * a)};
        }
        return roots;
    }

    public static void main(String[] args) {
    }
}
