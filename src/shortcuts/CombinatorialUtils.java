package shortcuts;

import java.math.BigInteger;

public final class CombinatorialUtils {

    private static BigInteger[] FACTORIALS = factorials(400);
    private static BigInteger[] CATALANS = catalans(200);
    private static BigInteger[][] NARAYANA = narayana(200);

    private CombinatorialUtils() {
        // Prevent instantiation
    }

    public static BigInteger[][] narayana(int n) {
        final BigInteger[][] nums = new BigInteger[n + 1][n + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                nums[i][j] = binomialCoefficient(i, j).multiply(binomialCoefficient(i, j - 1)).divide(BigInteger.valueOf(i));
            }
        }

        return nums;
    }

    public static BigInteger[] catalans(int n) {
        final BigInteger[] nums = new BigInteger[n + 1];
        nums[0] = BigInteger.ZERO;
        for (int i = 1; i <= n; i++) {
            nums[i] = binomialCoefficient(2 * i, i).divide(BigInteger.valueOf(i + 1));
        }

        return nums;
    }

    public static BigInteger binomialCoefficient(int n, int k) {
        return FACTORIALS[n].divide(FACTORIALS[n - k]).divide(FACTORIALS[k]);
    }

    public static BigInteger[] factorials(int n) {
        final BigInteger[] nums = new BigInteger[n + 1];
        nums[0] = BigInteger.ONE;
        nums[1] = BigInteger.ONE;

        BigInteger fact = BigInteger.ONE;
        for (int i = 2; i <= n; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
            nums[i] = fact;
        }

        return nums;
    }

}
