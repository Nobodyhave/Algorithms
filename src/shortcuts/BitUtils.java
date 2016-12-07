package shortcuts;

public final class BitUtils {

    private BitUtils() {
        // Prevent instantiation
    }

    /**
     * Union (OR) of 2 integers
     *
     * @param a first integer
     * @param b second integer
     * @return bitwise OR of a and b
     */
    public static int union(int a, int b) {
        return a | b;
    }

    /**
     * Intersection (AND) of 2 integers
     *
     * @param a first integer
     * @param b second integer
     * @return bitwise AND of a and b
     */
    public static int intersection(int a, int b) {
        return a & b;
    }

    /**
     * Subtraction of 2 integers
     *
     * @param a first integer
     * @param b second integer
     * @return bitwise result of a and b
     */
    public static int subtraction(int a, int b) {
        return a & ~b;
    }

    /**
     * Sets bit in integer
     *
     * @param a   integer
     * @param bit bit number
     * @return new a value
     */
    public static int setBit(int a, int bit) {
        return a | (1 << bit);
    }

    /**
     * Clears bit in integer
     *
     * @param a   integer
     * @param bit bit number
     * @return new a value
     */
    public static int clearBit(int a, int bit) {
        return a & ~(1 << bit);
    }

    /**
     * Tests bit in integer
     *
     * @param a   integer
     * @param bit bit number
     * @return value of tested bit
     */
    public static boolean testBit(int a, int bit) {
        return (a & (1 << bit)) != 0;
    }

    /**
     * Removes list significant bit from integer
     *
     * @param a integer
     * @return new a value
     */
    public static int lowestBitValue(int a) {
        return a & ~(a - 1);
    }

    /**
     * Checks if integer is power of 2
     *
     * @param a integer
     * @return If integer is power of 2
     */
    public static boolean isPowerOfTwo(int a) {
        return (a & ~(a - 1)) == 0;
    }

    /**
     * Counts 1 bits in integer
     *
     * @param a integer
     * @return Count of 1 bits
     */
    public static int countBits(int a) {
        int count = 0;
        while (a != 0) {
            a = (a) & (a - 1);
            count++;
        }
        return count;
    }

}
