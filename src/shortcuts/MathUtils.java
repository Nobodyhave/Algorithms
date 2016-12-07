package shortcuts;

import java.math.BigInteger;
import java.util.*;

public final class MathUtils {
    private static final BigInteger TWO = BigInteger.valueOf(2);
    private static final Map<Integer, Set<Integer>> DIVISORS = new HashMap<>();

    private MathUtils() {
        // Prevent instantiation
    }

    /**
     * Calculates greatest common divisor of 2 integers
     *
     * @param a first integer
     * @param b second integer
     * @return greatest common divisor of a and b
     */
    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    /**
     * Calculates lowest common multiple of 2 integers
     *
     * @param a first integer
     * @param b second integer
     * @return lowest common multiple of a and b
     */
    public static int lcm(int a, int b) {
        return b * a / gcd(a, b);
    }

    /**
     * Calculates sum of of absolute differences of all pairs in array
     *
     * @param a array of integers
     * @return sum of of absolute differences of all pairs in array
     */
    public static long sumOfAbsDifferencesOfAllPairs(int[] a) {
        Arrays.sort(a);

        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += (2 * i - a.length + 1) * a[i];
        }

        return sum;
    }

    /**
     * Calculates sum of of squared differences of all pairs in array
     *
     * @param a array of integers
     * @return sum of of squared differences of all pairs in array
     */
    public static long sumOfSquaredDifferencesOfAllPairs(int[] a, int left, int right) {
        long sum = 0;
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j <= right; j++) {
                sum += 2 * Math.pow(a[i] - a[j], 2);
            }
        }

        return sum;
    }

    /**
     * Checks if integer is prime
     *
     * @param n integer to be checked
     * @return if n is prime
     */
    public static boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        } else if (n == 2) {
            return true;
        } else if (n % 2 == 0) {
            return false;
        }

        final int m = (int) Math.sqrt(n) + 1;

        for (int i = 3; i <= m; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    /**
     * Calculates divisors of a number by trial division
     *
     * @param N integer to be checked
     * @return list of number's unique divisors
     */
    private static List<Integer> trialDivisors(int N) {
        final int M = (int) Math.sqrt(N);
        final List<Integer> small = new ArrayList<>();
        final Stack<Integer> big = new Stack<>();

        for (int i = 1; i < M; i++) {
            if (N % i == 0) {
                small.add(i);
                big.push(N / i);
            }
        }

        if (N % M == 0) {
            small.add(M);
            if (N / M != M) {
                big.push(N / M);
            }
        }

        while (!big.isEmpty()) {
            small.add(big.pop());
        }

        return small;
    }

    /**
     * Calculates divisors of a number by prime factorization
     *
     * @param N integer to be checked
     * @return set of number's unique divisors
     */
    public static Set<Integer> getDivisors(int N, List<Integer> primes) {
        if (!DIVISORS.containsKey(N)) {
            final List<Integer> localPrimes = new ArrayList<>();
            for (Integer prime : primes) {
                if (prime > N) {
                    break;
                } else if (N % prime == 0) {
                    localPrimes.add(prime);
                }
            }

            final Set<Integer> divisors = new TreeSet<>();
            divisors.add(1);
            divisors.add(N);
            calculateDivisors(divisors, localPrimes, 0, N, (int) Math.sqrt(N) + 1, 1);

            DIVISORS.put(N, divisors);
        }

        return DIVISORS.get(N);
    }

    /**
     * Helper recursive method to calculate divisors by prime factorization
     */
    private static void calculateDivisors(Set<Integer> divisors, List<Integer> primes, int start, int target, int sqrt, int cur) {
        if (target % cur == 0 && cur != 1) {
            divisors.add(cur);
            divisors.add(target / cur);
        } else if (cur > target || start > primes.size()) {
            return;
        } else if (target == 1) {
            return;
        }

        int current = cur;
        for (int i = start; i < primes.size(); i++) {
            if (current == 1 || current == cur) {
                calculateDivisors(divisors, primes, i + 1, target, sqrt, current);
            }
            while ((current *= primes.get(i)) <= sqrt) {
                calculateDivisors(divisors, primes, i + 1, target, sqrt, current);
            }
        }
    }

    /**
     * Calculates prime numbers up to Nth inclusive by sieve of Eratosthenes
     *
     * @param N desired prime number
     * @return list of prime numbers
     */
    public static List<Integer> sieveOfEratosthenes(int N) {
        final BitSet prime = new BitSet(Math.max(3, N + 1));
        final List<Integer> primes = new ArrayList<>();
        prime.set(0, false);
        prime.set(1, false);
        prime.set(2, N + 1, true);
        final int m = (int) Math.sqrt(N) + 1;

        for (int i = 2; i <= m; i++) {
            if (prime.get(i)) {
                primes.add(i);
                for (int k = i * i; k <= N; k += i) {
                    prime.set(k, false);
                }
            }
        }

        for (int i = m + 1; i <= N; i++) {
            if (prime.get(i)) {
                primes.add(i);
            }
        }

        return primes;
    }

    /**
     * Calculates prime numbers on interval [N, M] by segment sieve of Eratosthenes
     *
     * @param N desired prime number
     * @return list of prime numbers
     */
    public static List<Integer> segmentSieve(int N, int M) {
        if (N >= M) {
            return new ArrayList<>();
        }

        final List<Integer> lessPrimes = sieveOfEratosthenes((int) Math.sqrt(M) + 1);

        final boolean[] primes = new boolean[M - N + 1];
        Arrays.fill(primes, true);

        if (N == 0) {
            primes[0] = false;
            if (M > 0) {
                primes[1] = false;
            }
        } else if (N == 1) {
            primes[0] = false;
        }

        for (Integer prime : lessPrimes) {
            final int start = (int) Math.ceil((double) N / prime) * prime;
            for (int i = start - N; i <= M - N; i += prime) {
                if (i != prime - N) {
                    primes[i] = false;
                }
            }
        }

        final List<Integer> primesList = new ArrayList<>();
        for (int i = 0; i <= M - N; i++) {
            if (primes[i]) {
                primesList.add(i + N);
            }
        }

        return primesList;
    }

    /**
     * Calculates sum of arithmetic progression in array
     *
     * @param a input array, containing arithmetic progression
     * @return sum of arithmetic progression
     */
    public static int sumOfArithmeticProgression(int[] a) {
        return a.length * (a[0] + a[a.length - 1]) / 2;
    }

    /**
     * Calculates sum of arithmetic progression
     *
     * @param start first term
     * @param end   last term
     * @return sum of arithmetic progression
     */
    public static BigInteger sumOfArithmeticProgression(BigInteger start, BigInteger end, long length) {
        return BigInteger.valueOf(length).multiply(start.add(end)).divide(BigInteger.valueOf(2));
    }

    /**
     * Calculates Nth Fibonacci number
     *
     * @param N position of Fibonacci number
     * @return sum of arithmetic progression
     */
    public static BigInteger fastFibonacciDoubling(long N) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (long i = 63 - Long.numberOfLeadingZeros(N); i >= 0; i--) {
            BigInteger d = a.multiply(b.shiftLeft(1).subtract(a));
            BigInteger e = a.multiply(a).add(a.multiply(b));
            a = d;
            b = e;
            if (((N >>> i) & 1) != 0) {
                BigInteger c = a.add(b);
                a = b;
                b = c;
            }
        }
        return a;
    }

    /**
     * Calculates Nth triangular number
     *
     * @param N triangular number position
     * @return triangular number
     */
    public static long findTriangularNumber(int N) {
        return (long) N * (N - 1) / 2;
    }

    /**
     * Calculates integer square root of N by Herons method
     *
     * @param N number to be square rooted
     * @return integer square root of N
     */
    public static BigInteger sqrt(BigInteger N) {
        if (N.signum() >= 0) {
            final int bitLength = N.bitLength();
            BigInteger root = BigInteger.ONE.shiftLeft(bitLength / 2);

            while (!isSqrt(N, root)) {
                root = root.add(N.divide(root)).divide(TWO);
            }
            return root;
        } else {
            throw new ArithmeticException("square root of negative number");
        }
    }

    /**
     * Helper method for calculating square root
     */
    private static boolean isSqrt(BigInteger n, BigInteger root) {
        final BigInteger lowerBound = root.pow(2);
        final BigInteger upperBound = root.add(BigInteger.ONE).pow(2);

        return lowerBound.compareTo(n) <= 0 && n.compareTo(upperBound) < 0;
    }
}
