package data_structures;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * Created by Aleksandr on 25/01/2017.
 * Project Algorithms
 */
public class Treap<K extends Comparable<K>> {
    private static final Random RANDOM = new Random();

    private TreapNode<K> root;
    private final Delegate<K> delegate;

    public Treap(Delegate<K> delegate) {
        this.delegate = delegate;
    }

    public Treap(Delegate<K> delegate, Collection<K> keys) {
        this.delegate = delegate;
        addAll(keys);
    }

    public Treap(Delegate<K> delegate, K[] keys) {
        this.delegate = delegate;
        addAll(keys);
    }

    private static <K extends Comparable<K>> TreapNode<K> getPrevious(TreapNode<K> root, K key) {
        TreapNode<K> node = query(root, key);

        node = minSameNode(node);

        // Example 3 or Example 4
        if (node.left != null)
            return maxNode(node.left);

        // Example 1 or Example 2
        TreapNode<K> predecessor = null;
        // Start from root and search for predecessor down the tree
        while (root != null) {

            if (node.key.compareTo(root.key) == 0) {
                // by now we might found our predecessor
                break;
            } else if (node.key.compareTo(root.key) < 0) {
                root = root.left;
            } else if (node.key.compareTo(root.key) > 0) {
                predecessor = root;
                root = root.right;
            }
        }
        return predecessor;
    }

    private static <K extends Comparable<K>> TreapNode<K> minSameNode(TreapNode<K> node) {
        while (node != null && node.left != null && node.key.compareTo(node.left.key) == 0) {
            node = node.left;
        }

        return node;
    }

    private static <K> TreapNode<K> maxNode(TreapNode<K> root) {
        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    private static <K extends Comparable<K>> void inOrderTraversal(TreapNode<K> root, List<K> result) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            inOrderTraversal(root.left, result);
        }
        result.add(root.key);
        if (root.right != null) {
            inOrderTraversal(root.right, result);
        }
    }

    private static <K extends Comparable<K>> TreapNode<K> query(TreapNode<K> root, K key) {
        if (root == null || root.key.compareTo(key) == 0) {
            return root;
        } else if (key.compareTo(root.key) < 0) {
            return query(root.left, key);
        } else {
            return query(root.right, key);
        }
    }

    private static <E> void pushDelta(TreapNode<E> node) {
        // Cost push
        if (node.left != null) {
            node.left.costDelta += node.costDelta;
        }
        if (node.right != null) {
            node.right.costDelta += node.costDelta;
        }
        node.costDelta = 0;
    }

    // Keys of left tree should be less then keys of right tree
    private static <K extends Comparable<K>> TreapNode<K> merge(Delegate<K> delegate, TreapNode<K> L, TreapNode<K> R) {
        if (L == null) {
            return R;
        }
        if (R == null) {
            return L;
        }

        TreapNode<K> answer;
        if (L.priority > R.priority) {
            delegate.updateKey(L);
            pushDelta(L);
            if (L.key.compareTo(R.key) != 0) {
                final TreapNode<K> newR = merge(delegate, L.right, R);
                answer = new TreapNode<>(delegate, L.key, L.priority, L.left, newR);
            } else {
                final TreapNode<K> newR = null;
                final TreapNode<K> newL = new TreapNode<>(delegate, R.key, R.priority, merge(delegate, L.left, R.left), merge(delegate, L.right, R.right));
                answer = new TreapNode<>(delegate, L.key, L.priority, newL, newR);
            }
        } else {
            delegate.updateKey(R);
            pushDelta(R);

            if (L.key.compareTo(R.key) != 0) {
                final TreapNode<K> newL = merge(delegate, L, R.left);
                answer = new TreapNode<>(delegate, R.key, R.priority, newL, R.right);
            } else {
                final TreapNode<K> newR = null;
                final TreapNode<K> newL = new TreapNode<>(delegate, L.key, L.priority, merge(delegate, L.left, R.left), merge(delegate, L.right, R.right));
                answer = new TreapNode<>(delegate, R.key, R.priority, newL, newR);
            }
        }

        delegate.updateStatistics(answer);

        return answer;
    }

    private static <K extends Comparable<K>> SplitResult<K> split(Delegate<K> delegate, TreapNode<K> root, K key, boolean remove) {
        delegate.updateKey(root);
        pushDelta(root);

        SplitResult<K> splitResult;
        TreapNode<K> L, R, newTree = null;

        if (root == null) {
            return new SplitResult<>(null, null);
        }

        if (root.key.compareTo(key) == 0 && remove) {
            splitResult = new SplitResult<>(root.left, root);
            root.left = null;
        } else if (root.key.compareTo(key) <= 0) {
            if (root.right == null) {
                R = null;
            } else {
                splitResult = split(delegate, root.right, key, remove);
                newTree = splitResult.left;
                R = splitResult.right;
            }
            L = new TreapNode<>(delegate, root.key, root.priority, root.left, newTree);
            delegate.updateStatistics(L);
            splitResult = new SplitResult<>(L, R);
        } else {
            if (root.left == null) {
                L = null;
            } else {
                splitResult = split(delegate, root.left, key, remove);
                L = splitResult.left;
                newTree = splitResult.right;
            }
            R = new TreapNode<>(delegate, root.key, root.priority, newTree, root.right);
            delegate.updateStatistics(R);
            splitResult = new SplitResult<>(L, R);
        }

        return splitResult;
    }

    public void add(K key, int priority) {
        if (root == null) {
            root = new TreapNode<>(delegate, key, priority, null, null);
        } else {
            final SplitResult<K> result = split(delegate, root, key, false);
            final TreapNode<K> m = new TreapNode<>(delegate, key, priority, null, null);
            root = merge(delegate, merge(delegate, result.left, m), result.right);
        }
    }

    public void add(K key) {
        add(key, RANDOM.nextInt());
    }

    public void addAll(Collection<K> keys) {
        keys.forEach(this::add);
    }

    public void addAll(K[] keys) {
        Stream.of(keys).forEach(this::add);
    }

    public void addCostToTreap(int cost) {
        root.costDelta += cost;
    }

    public void addCostToInterval(K left, K right, int cost) {
        TreapNode<K> previous = getPrevious(root, left);

        SplitResult<K> result1, result2;
        if (previous == null) {
            result2 = split(delegate, root, right, false);
            result2.left.costDelta += cost;
            root = merge(delegate, result2.left, result2.right);
        } else {
            result1 = split(delegate, root, previous.key, false);
            result2 = split(delegate, result1.right, right, false);
            result2.left.costDelta += cost;
            root = merge(delegate, result1.left, merge(delegate, result2.left, result2.right));
        }
    }

    public int getSum() {
        return root.getSum();
    }

    public int getCost() {
        return root.getCostSum() + root.costDelta * size();
    }

    public void remove(K key) {
        if (root == null || root.size == 0) {
            throw new IllegalStateException("Unable to remove " + key + ". Treap is empty");
        }

        if (contains(key)) {
            final SplitResult<K> resultFirst = split(delegate, this.root, key, false);
            final SplitResult<K> resultSecond = split(delegate, resultFirst.left, key, true);
            root = merge(delegate, resultSecond.left, resultFirst.right);
        }
    }

    public boolean contains(K key) {
        return query(root, key) != null;
    }

    public List<K> inOrderTraversal() {
        final List<K> result = new ArrayList<>();
        inOrderTraversal(root, result);

        return result;
    }

    public int size() {
        return root != null ? root.size : 0;
    }

    public K getKthElement(int k) {
        TreapNode<K> node = root;
        while (node != null) {
            int sizeLeft = (node.left != null ? node.left.size : 0);

            if (sizeLeft == k) {
                return node.key;
            }

            node = sizeLeft > k ? node.left : node.right;
            if (sizeLeft < k) {
                k -= sizeLeft + 1;
            }
        }

        throw new IndexOutOfBoundsException("k exceeds treap bounds");
    }

    public static class TreapNode<K> {
        private K key;
        private int priority;
        private TreapNode<K> left;
        private TreapNode<K> right;
        private int size;
        private int sum;
        private int costSum;
        private int costDelta;

        public TreapNode(Delegate<K> delegate, K key, int priority, TreapNode<K> left, TreapNode<K> right) {
            this.key = key;
            this.priority = priority;
            this.left = left;
            this.right = right;
            delegate.updateStatistics(this);
        }

        public K getKey() {
            return key;
        }

        public TreapNode<K> getLeft() {
            return left;
        }

        public TreapNode<K> getRight() {
            return right;
        }

        public int getSize() {
            return size;
        }

        public int getSum() {
            return sum;
        }

        public int getCostSum() {
            return costSum;
        }

        public int getCostDelta() {
            return costDelta;
        }

        public void updateSize(int size) {
            this.size = size;
        }

        public void updateSum(int sum) {
            this.sum = sum;
        }

        public void updateCostSum(int cost) {
            this.costSum = cost;
        }

    }

    private static class SplitResult<K extends Comparable<K>> {
        private TreapNode<K> left;
        private TreapNode<K> right;

        public SplitResult(TreapNode<K> left, TreapNode<K> right) {
            this.left = left;
            this.right = right;
        }
    }

    public static abstract class Delegate<K> {
        public abstract void updateStatistics(TreapNode<K> root);

        public abstract void updateKey(TreapNode<K> root);
    }
}