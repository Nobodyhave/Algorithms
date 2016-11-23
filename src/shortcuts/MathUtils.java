package shortcuts;

import java.math.BigInteger;
import java.util.*;

public final class MathUtils {
    private static final Map<Integer, List<Integer>> DIVISORS = new HashMap<>();

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

    public static List<Integer> getDivisors(int N, List<Integer> primes) {
        if (!DIVISORS.containsKey(N)) {
            final List<Integer> localPrimes = new ArrayList<>();
            for (Integer prime : primes) {
                if (prime > N) {
                    break;
                } else if (N % prime == 0) {
                    localPrimes.add(prime);
                }
            }

            final List<Integer> divisors = new ArrayList<>();
            divisors.add(1);
            divisors.add(N);
            calculateDivisors(divisors, localPrimes, 0, N, (int) Math.sqrt(N) + 1, 1);

            Collections.sort(divisors);
            DIVISORS.put(N, divisors.subList(divisors.size() / 2, divisors.size()));
        }

        return DIVISORS.get(N);
    }

    private static void calculateDivisors(List<Integer> divisors, List<Integer> primes, int start, int target, int sqrt, int cur) {
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
        final boolean[] prime = new boolean[n + 1];
        final List<Integer> primes = new ArrayList<>();
        Arrays.fill(prime, true);
        prime[0] = false;
        prime[1] = false;
        final int m = (int) Math.sqrt(n) + 1;

        for (int i = 2; i <= m; i++) {
            if (prime[i]) {
                primes.add(i);
                for (int k = i * i; k <= n; k += i) {
                    prime[k] = false;
                }
            }
        }
        return primes;
    }

    public static int sumOfArithmeticProgression(int[] a) {
        return a.length * (a[0] + a[a.length - 1]) / 2;
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
}
