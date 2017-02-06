package data_structures;

import edu.princeton.cs.algs4.StdOut;

/******************************************************************************
 *  Compilation:  javac QuadTree.java
 *  Execution:    java QuadTree M N
 *
 *  Quad tree.
 *
 ******************************************************************************/

public class QuadTree<Key extends Comparable<Key>, Value>  {
    private Node root;

    // helper node data type
    private class Node {
        Key x, y;              // x- and y- coordinates
        Node NW, NE, SE, SW;   // four subtrees
        Value value;           // associated data

        Node(Key x, Key y, Value value) {
            this.x = x;
            this.y = y;
            this.value = value;
        }
    }


    /***********************************************************************
     *  Insert (x, y) into appropriate quadrant
     ***************************************************************************/
    public void insert(Key x, Key y, Value value) {
        root = insert(root, x, y, value);
    }

    private Node insert(Node h, Key x, Key y, Value value) {
        if (h == null) return new Node(x, y, value);
            //// if (eq(x, h.x) && eq(y, h.y)) h.value = value;  // duplicate
        else if ( less(x, h.x) &&  less(y, h.y)) h.SW = insert(h.SW, x, y, value);
        else if ( less(x, h.x) && !less(y, h.y)) h.NW = insert(h.NW, x, y, value);
        else if (!less(x, h.x) &&  less(y, h.y)) h.SE = insert(h.SE, x, y, value);
        else if (!less(x, h.x) && !less(y, h.y)) h.NE = insert(h.NE, x, y, value);
        return h;
    }


    /***********************************************************************
     *  Range search.
     ***************************************************************************/

    public void query2D(Interval2D<Key> rect) {
        query2D(root, rect);
    }

    private void query2D(Node h, Interval2D<Key> rect) {
        if (h == null) return;
        Key xmin = rect.intervalX.min();
        Key ymin = rect.intervalY.min();
        Key xmax = rect.intervalX.max();
        Key ymax = rect.intervalY.max();
        if (rect.contains(h.x, h.y))
            StdOut.println("    (" + h.x + ", " + h.y + ") " + h.value);
        if ( less(xmin, h.x) &&  less(ymin, h.y)) query2D(h.SW, rect);
        if ( less(xmin, h.x) && !less(ymax, h.y)) query2D(h.NW, rect);
        if (!less(xmax, h.x) &&  less(ymin, h.y)) query2D(h.SE, rect);
        if (!less(xmax, h.x) && !less(ymax, h.y)) query2D(h.NE, rect);
    }


    /***************************************************************************
     *  helper comparison functions
     ***************************************************************************/

    private boolean less(Key k1, Key k2) { return k1.compareTo(k2) <  0; }

    /***************************************************************************
     *  test client
     ***************************************************************************/
    public static void main(String[] args) {
        int M = Integer.parseInt(args[0]);   // queries
        int N = Integer.parseInt(args[1]);   // points

        QuadTree<Integer, String> st = new QuadTree<>();

        // insert N random points in the unit square
        for (int i = 0; i < N; i++) {
            Integer x = (int) (100 * Math.random());
            Integer y = (int) (100 * Math.random());
            // StdOut.println("(" + x + ", " + y + ")");
            st.insert(x, y, "P" + i);
        }
        StdOut.println("Done preprocessing " + N + " points");

        // do some range searches
        for (int i = 0; i < M; i++) {
            Integer xmin = (int) (100 * Math.random());
            Integer ymin = (int) (100 * Math.random());
            Integer xmax = xmin + (int) (10 * Math.random());
            Integer ymax = ymin + (int) (20 * Math.random());
            Interval<Integer> intX = new Interval<>(xmin, xmax);
            Interval<Integer> intY = new Interval<>(ymin, ymax);
            Interval2D<Integer> rect = new Interval2D<>(intX, intY);
            StdOut.println(rect + " : ");
            st.query2D(rect);
        }
    }

    /******************************************************************************
     *  Compilation:  javac Interval2D.java
     *  Execution:    java Interval2D
     *
     *  Implementation of 2D interval.
     *
     ******************************************************************************/

    private static class Interval2D<Key extends Comparable<Key>> {
        public final Interval<Key> intervalX;   // x-interval
        public final Interval<Key> intervalY;   // y-interval

        public Interval2D(Interval<Key> intervalX, Interval<Key> intervalY) {
            this.intervalX = intervalX;
            this.intervalY = intervalY;
        }

        // does this 2D interval a intersect b?
        public boolean intersects(Interval2D<Key> b) {
            return intervalX.intersects(b.intervalX) || intervalY.intersects(b.intervalY);
        }

        // does this 2D interval contain (x, y)?
        public boolean contains(Key x, Key y) {
            return intervalX.contains(x) && intervalY.contains(y);
        }

        // return string representation
        public String toString() {
            return intervalX + " x " + intervalY;
        }



        // test client
        public static void main(String[] args) {
            Interval<Double> intervalX = new Interval<>(0.0, 1.0);
            Interval<Double> intervalY = new Interval<>(5.0, 6.0);
            Interval2D<Double> box1 = new Interval2D<>(intervalX, intervalY);
            intervalX = new Interval<>(-5.0, 5.0);
            intervalY = new Interval<>(3.0, 7.0);
            Interval2D<Double> box2 = new Interval2D<>(intervalX, intervalY);
            System.out.println("box1 = " + box1);
            System.out.println("box2 = " + box2);
            System.out.println(box1.contains(0.5, 5.5));
            System.out.println(!box1.contains(1.5, 5.5));
            System.out.println(box1.intersects(box2));
            System.out.println(box2.intersects(box1));
        }
    }

    /******************************************************************************
     *  Compilation:  javac Interval.java
     *  Execution:    java Interval
     *
     *  Implementation of an interval.
     *
     ******************************************************************************/

    private static class Interval<Key extends Comparable<Key>> {
        private final Key min;    // min endpoint
        private final Key max;    // max endpoint

        public Interval(Key min, Key max) {
            if (less(max, min)) throw new RuntimeException("Illegal argument");
            this.min = min;
            this.max = max;
        }

        // return min endpoint
        public Key min() {
            return min;
        }

        // return max endpoint
        public Key max() {
            return max;
        }

        // is x between min and max
        public boolean contains(Key key) {
            return !less(key, min) && !less(max, key);
        }

        // does this interval a intersect interval b?
        public boolean intersects(Interval<Key> b) {
            Interval<Key> a = this;
            return !less(a.max, b.min) && !less(b.max, a.min);
        }

        // does this interval a equal interval b?
        public boolean equals(Interval<Key> b) {
            Interval<Key> a  = this;
            return a.min.equals(b.min) && a.max.equals(b.max);
        }


        // comparison helper functions
        private boolean less(Key x, Key y) {
            return x.compareTo(y) < 0;
        }

        // return string representation
        public String toString() {
            return "[" + min + ", " + max + "]";
        }



        // test client
        public static void main(String[] args) {
            int n = Integer.parseInt(args[0]);

            Interval<Integer> a = new Interval<>(5, 17);
            Interval<Integer> b = new Interval<>(5, 17);
            Interval<Integer> c = new Interval<>(5, 18);
            System.out.println(a.equals(b));
            System.out.println(!a.equals(c));
            System.out.println(!b.equals(c));


            // generate n random points in [-1, 2] and compute
            // fraction that lies in [0, 1]
            Interval<Double> interval = new Interval<>(0.0, 1.0);
            int count = 0;
            for (int i = 0; i < n; i++) {
                double x = 3 * Math.random() - 1.0;
                if (interval.contains(x)) count++;
            }
            System.out.println("fraction = " + (1.0 * count / n));
        }
    }

}
