package shortcuts;

public final class BitUtils {

    private BitUtils() {
        // Prevent instantiation
    }

    public static int union(int a, int b) {
        return a | b;
    }

    public static int intersection(int a, int b) {
        return a & b;
    }

    public static int subtraction(int a, int b) {
        return a & ~b;
    }

    public static int setBit(int a, int bit) {
        return a | (1 << bit);
    }

    public static int clearBit(int a, int bit) {
        return a & ~(1 << bit);
    }

    public static boolean testBit(int a, int bit) {
        return (a & (1 << bit)) != 0;
    }

    public static int lowestBitValue(int a) {
        return a & ~(a - 1);
    }

    public static boolean isPowerOfTwo(int a) {
        return (a & ~(a - 1)) == 0;
    }

    public static int countBits(int a) {
        int count = 0;
        while (a != 0) {
            a = (a) & (a - 1);
            count++;
        }
        return count;
    }

}
