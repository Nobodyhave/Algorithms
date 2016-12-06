package shortcuts;

import java.math.BigInteger;
import java.util.*;

public final class MathUtils {
    private static final Map<Integer, Set<Integer>> DIVISORS = new HashMap<>();

    private MathUtils() {
        // Prevent instantiation
    }

    public static int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return b * a / gcd(a, b);
    }

    public static long sumOfAbsDifferencesOfAllPairs(int[] a) {
        Arrays.sort(a);

        long sum = 0;
        for (int i = 0; i < a.length; i++) {
            sum += (2 * i - a.length + 1) * a[i];
        }

        return sum;
    }

    public static long sumOfSquaredDifferencesOfAllPairs(int[] a, int left, int right) {
        long sum = 0;
        for (int i = left; i < right; i++) {
            for (int j = i + 1; j <= right; j++) {
                sum += 2 * Math.pow(a[i] - a[j], 2);
            }
        }

        return sum;
    }

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

    public static List<Integer> sieveOfEratosthenes(int n) {
        final BitSet prime = new BitSet(Math.max(3, n + 1));
        final List<Integer> primes = new ArrayList<>();
        prime.set(0, false);
        prime.set(1, false);
        prime.set(2, n + 1, true);
        final int m = (int) Math.sqrt(n) + 1;

        for (int i = 2; i <= m; i++) {
            if (prime.get(i)) {
                primes.add(i);
                for (int k = i * i; k <= n; k += i) {
                    prime.set(k, false);
                }
            }
        }

        for (int i = m + 1; i <= n; i++) {
            if (prime.get(i)) {
                primes.add(i);
            }
        }

        return primes;
    }

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

    private static BigInteger powerOfPrimeInFactorial(int N, int prime) {
        int result = 0;
        for (long p = prime; p <= N; p *= prime) {
            result = result + (int) (N / p);
        }

        return BigInteger.valueOf(result);
    }

    public static int sumOfArithmeticProgression(int[] a) {
        return a.length * (a[0] + a[a.length - 1]) / 2;
    }

    public static BigInteger sumOfArithmeticProgression(BigInteger start, BigInteger end, long length) {
        return BigInteger.valueOf(length).multiply(start.add(end)).divide(BigInteger.valueOf(2));
    }

    public static BigInteger fastFibonacciDoubling(long n) {
        BigInteger a = BigInteger.ZERO;
        BigInteger b = BigInteger.ONE;
        for (long i = 63 - Long.numberOfLeadingZeros(n); i >= 0; i--) {
            BigInteger d = a.multiply(b.shiftLeft(1).subtract(a));
            BigInteger e = a.multiply(a).add(a.multiply(b));
            a = d;
            b = e;
            if (((n >>> i) & 1) != 0) {
                BigInteger c = a.add(b);
                a = b;
                b = c;
            }
        }
        return a;
    }

    public static long findTriangularNumber(int N) {
        return (long) N * (N - 1) / 2;
    }
}
