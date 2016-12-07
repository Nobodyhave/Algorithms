package shortcuts;

import java.math.BigInteger;
import java.util.List;
import java.util.stream.IntStream;

public final class CombinatorialUtils {

    private static BigInteger[] FACTORIALS = factorials(400);
    private static BigInteger[] CATALANS = catalans(200);
    private static BigInteger[][] NARAYANA = narayana(200);

    private CombinatorialUtils() {
        // Prevent instantiation
    }

    /**
     * Generates Narayana numbers up to Nth inclusively
     *
     * @param N count of generated numbers
     * @return Narayana numbers
     */
    public static BigInteger[][] narayana(int N) {
        final BigInteger[][] nums = new BigInteger[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= i; j++) {
                nums[i][j] = binomialCoefficient(i, j).multiply(binomialCoefficient(i, j - 1)).divide(BigInteger.valueOf(i));
            }
        }

        return nums;
    }

    /**
     * Generates Catalan numbers up to Nth inclusively
     *
     * @param N count of generated numbers
     * @return Catalan numbers
     */
    public static BigInteger[] catalans(int N) {
        final BigInteger[] nums = new BigInteger[N + 1];
        nums[0] = BigInteger.ZERO;
        for (int i = 1; i <= N; i++) {
            nums[i] = binomialCoefficient(2 * i, i).divide(BigInteger.valueOf(i + 1));
        }

        return nums;
    }

    /**
     * Finds binomial coefficient C(n,k) by using factorials
     *
     * @param N total number of elements
     * @param K number of chosen elements
     * @return binomial coefficient C(n,k)
     */
    public static BigInteger binomialCoefficient(int N, int K) {
        return FACTORIALS[N].divide(FACTORIALS[N - K]).divide(FACTORIALS[K]);
    }

    /**
     * Finds binomial coefficient C(n,k) by using prime factorization
     *
     * @param N      total number of elements
     * @param K      number of chosen elements
     * @param primes list of prime numbers
     * @return binomial coefficient C(n,k)
     */
    private static BigInteger binomial(int N, int K, List<Integer> primes) {
        int i = 0;
        int curPrime;
        BigInteger result = BigInteger.ONE;
        while (primes.get(i) <= N) {
            curPrime = primes.get(i);
            final BigInteger powersOfPrime = (powerOfPrimeInFactorial(N, curPrime)
                    .subtract(powerOfPrimeInFactorial(K, curPrime)))
                    .subtract(powerOfPrimeInFactorial(N - K, curPrime));
            if (powersOfPrime.compareTo(BigInteger.ZERO) > 0) {
                result = result.multiply(BigInteger.valueOf(curPrime).pow(powersOfPrime.intValue()));
            }
            i++;
        }

        return result;
    }

    /**
     * Finds power of prime in factorial
     *
     * @param N     number which is factorized (N!)
     * @param prime prime factor of N to be tested
     * @return power of prime in N!
     */
    private static BigInteger powerOfPrimeInFactorial(int N, int prime) {
        int result = 0;
        for (long p = prime; p <= N; p *= prime) {
            result = result + (int) (N / p);
        }

        return BigInteger.valueOf(result);
    }

    /**
     * Finds number of arrangements A(n,k) by using factorials
     *
     * @param N total number of elements
     * @param K number of chosen elements
     * @return number of arrangements A(n,k)
     */
    public static BigInteger arrangements(int N, int K) {
        return FACTORIALS[N].divide(FACTORIALS[N - K]);
    }

    /**
     * Finds number of multiset permutations
     *
     * @param M counts of multiset elements
     * @return number if multiset permutations
     */
    public static BigInteger multiSetPermutation(int... M) {
        final int n = IntStream.of(M).sum();

        BigInteger result = FACTORIALS[n];
        for (Integer m : M) {
            result = result.divide(FACTORIALS[m]);
        }

        return result;
    }

    /**
     * Finds number of combinations with repetitions by using factorials
     *
     * @param N total number of elements
     * @param K number of chosen elements
     * @return binomial coefficient C(n+k-1,k)
     */
    public static BigInteger combinationsWithRepetitions(int N, int K) {
        N = N + K - 1;
        return FACTORIALS[N].divide(FACTORIALS[N - K]).divide(FACTORIALS[K]);
    }

    /**
     * Calculates factorials up to Nth inclusive
     *
     * @param N total number of elements
     * @return factorials
     */
    public static BigInteger[] factorials(int N) {
        final BigInteger[] nums = new BigInteger[N + 1];
        nums[0] = BigInteger.ONE;
        nums[1] = BigInteger.ONE;

        BigInteger fact = BigInteger.ONE;
        for (int i = 2; i <= N; i++) {
            fact = fact.multiply(BigInteger.valueOf(i));
            nums[i] = fact;
        }

        return nums;
    }

}
