package shortcuts

import java.util.concurrent.ThreadLocalRandom

/**
 * Reverses array
 *
 * @param a     Array of ints to be reversed
 * @param left  Left inclusive index of sub array to be reversed
 * @param right Right inclusive index of sub array to be reversed
 */
fun reverseArray(a: IntArray, left: Int, right: Int) {
    var l = left
    var r = right
    while (l < r) {
        swap(a, l, r)
        l++
        r--
    }
}

/**
 * Rotate array cyclically to the left
 *
 * @param a     Arrays ot be rotated
 * @param steps Number of one-step rotations
 */
fun rotateArrayLeft(a: IntArray, steps: Int) {
    reverseArray(a, 0, steps - 1)
    reverseArray(a, steps, a.size - 1)
    reverseArray(a, 0, a.size - 1)
}

/**
 * Swaps 2 element in the array
 *
 * @param a Array to perform swap in
 * @param i Index of first element
 * @param j Index of second element
 */
fun swap(a: IntArray, i: Int, j: Int) {
    val temp = a[i]
    a[i] = a[j]
    a[j] = temp
}


/**
 * Shuffles array
 *
 * @param a Array to shuffke
 */
fun shuffleArray(a: IntArray) {
    val rnd = ThreadLocalRandom.current()
    for (i in a.size - 1 downTo 1) {
        val index = rnd.nextInt(i + 1)
        swap(a, i, index)
    }
}
