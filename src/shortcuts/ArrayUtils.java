package shortcuts;

public final class ArrayUtils {

    private ArrayUtils() {
        // Prevent instantiation
    }

    /**
     * Reverses array
     *
     * @param a     Array of ints to be reversed
     * @param left  Left inclusive index of sub array to be reversed
     * @param right Right inclusive index of sub array to be reversed
     */
    public static void reverseArray(int[] a, int left, int right) {
        while (left < right) {
            swap(a, left, right);
            left++;
            right--;
        }
    }

    /**
     * Rotate array cyclically to the left
     *
     * @param a     Arrays ot be rotated
     * @param steps Number of one-step rotations
     */
    public static void rotateArrayLeft(int[] a, int steps) {
        reverseArray(a, 0, steps - 1);
        reverseArray(a, steps, a.length - 1);
        reverseArray(a, 0, a.length - 1);
    }

    /**
     * Swaps 2 element in the array
     *
     * @param a Array to perform swap in
     * @param i Index of first element
     * @param j Index of second element
     */
    public static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

}
