import java.util.Arrays;
import java.util.Iterator;

/**
 * Created by Aleksandr on 29/12/2016.
 * Project Algorithms
 */
public class IntArray implements Iterable<Integer> {
    private final int[] array;
    private int size = 0;

    public IntArray(int maxSize) {
        this.array = new int[maxSize];
    }

    public void add(int E) {
        array[size++] = E;
    }

    public void addAll(int... E) {
        for (int e : E) {
            array[size++] = e;
        }
    }

    public void remove(int index) {
        System.arraycopy(array, index + 1, array, index, size - index - 1);
    }

    public void removeLast() {
        size--;
    }

    public void clear() {
        size = 0;
    }

    public void trimToSize(int trimSize) {
        size = trimSize;
    }

    public int get(int index) {
        if (index < size) {
            return array[index];
        } else {
            throw new IndexOutOfBoundsException("Index out of bound: " + index + " from " + size);
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return Arrays.stream(array).iterator();
    }
}
