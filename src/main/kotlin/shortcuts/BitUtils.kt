package shortcuts

/**
 * Union (OR) of 2 integers
 *
 * @param a first integer
 * @param b second integer
 * @return bitwise OR of a and b
 */
fun union(a: Int, b: Int): Int {
    return a or b
}

/**
 * Intersection (AND) of 2 integers
 *
 * @param a first integer
 * @param b second integer
 * @return bitwise AND of a and b
 */
fun intersection(a: Int, b: Int): Int {
    return a and b
}

/**
 * Subtraction of 2 integers
 *
 * @param a first integer
 * @param b second integer
 * @return bitwise result of a and b
 */
fun subtraction(a: Int, b: Int): Int {
    return a and b.inv()
}

/**
 * Sets bit in integer
 *
 * @param a   integer
 * @param bit bit number
 * @return new a value
 */
fun setBit(a: Int, bit: Int): Int {
    return a or (1 shl bit)
}

/**
 * Clears bit in integer
 *
 * @param a   integer
 * @param bit bit number
 * @return new a value
 */
fun clearBit(a: Int, bit: Int): Int {
    return a and (1 shl bit).inv()
}

/**
 * Tests bit in integer
 *
 * @param a   integer
 * @param bit bit number
 * @return value of tested bit
 */
fun testBit(a: Int, bit: Int): Boolean {
    return a and (1 shl bit) != 0
}

/**
 * Removes list significant bit from integer
 *
 * @param a integer
 * @return new a value
 */
fun lowestBitValue(a: Int): Int {
    return a and (a - 1).inv()
}

/**
 * Checks if integer is power of 2
 *
 * @param a integer
 * @return If integer is power of 2
 */
fun isPowerOfTwo(a: Int): Boolean {
    return a and (a - 1).inv() == 0
}

/**
 * Counts 1 bits in integer
 *
 * @param a integer
 * @return Count of 1 bits
 */
fun countBits(a: Int): Int {
    var num = a
    var count = 0
    while (num != 0) {
        num = num and num - 1
        count++
    }
    return count
}

