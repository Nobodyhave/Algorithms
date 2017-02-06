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
public class ImplicitTreap<E> {
    private static final Random RANDOM = new Random();

    private TreapNode<E> root;
    private final Delegate<E> delegate;

    public ImplicitTreap(Delegate<E> delegate) {
        this.delegate = delegate;
    }

    public ImplicitTreap(Delegate<E> delegate, Collection<E> elements) {
        this.delegate = delegate;
        addAll(elements);
    }

    public ImplicitTreap(Delegate<E> delegate, E[] elements) {
        this.delegate = delegate;
        addAll(elements);
    }

    private static <E> void inOrderTraversal(TreapNode<E> root, List<E> result) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            inOrderTraversal(root.left, result);
        }
        result.add(root.element);
        if (root.right != null) {
            inOrderTraversal(root.right, result);
        }
    }

    public E query(int index) {
        if (root == null || root.size == 0 || index < 0 || index >= root.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        return query(delegate, root, index).element;
    }

    private static <E> TreapNode<E> query(Delegate<E> delegate, TreapNode<E> root, int index) {
        final SplitResult<E> resultFirst = split(delegate, root, index);
        final SplitResult<E> resultSecond = split(delegate, resultFirst.right, 1);

        return resultSecond.left;
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

        // Reversed push
        if (!node.reversed) {
            return;
        }

        TreapNode<E> temp = node.left;
        node.left = node.right;
        node.right = temp;

        node.reversed = false;
        if (node.left != null) {
            node.left.reversed ^= true;
        }
        if (node.right != null) {
            node.right.reversed ^= true;
        }
    }

    // Keys of left tree should be less then keys of right tree
    private static <E> TreapNode<E> merge(Delegate<E> delegate, TreapNode<E> L, TreapNode<E> R) {
        if (L == null) {
            return R;
        }
        if (R == null) {
            return L;
        }

        TreapNode<E> answer;
        if (L.priority > R.priority) {
            delegate.updateData(L);
            pushDelta(L);

            final TreapNode<E> newR = merge(delegate, L.right, R);
            answer = new TreapNode<>(delegate, L.element, L.priority, L.left, newR);
        } else {
            delegate.updateData(R);
            pushDelta(R);

            final TreapNode<E> newL = merge(delegate, L, R.left);
            answer = new TreapNode<>(delegate, R.element, R.priority, newL, R.right);
        }

        delegate.updateStatistics(answer);

        return answer;
    }

    private static <E> SplitResult<E> split(Delegate<E> delegate, TreapNode<E> root, int index) {
        delegate.updateData(root);
        pushDelta(root);

        SplitResult<E> splitResult;
        TreapNode<E> L, R, newTree = null;
        final int curIndex = ((root.left == null) ? 0 : root.left.size) + 1;
        if (curIndex <= index) {
            if (root.right == null) {
                R = null;
            } else {
                splitResult = split(delegate, root.right, index - curIndex);
                newTree = splitResult.left;
                R = splitResult.right;
            }
            L = new TreapNode<>(delegate, root.element, root.priority, root.left, newTree);
            delegate.updateStatistics(L);
            splitResult = new SplitResult<>(L, R);
        } else {
            if (root.left == null) {
                L = null;
            } else {
                splitResult = split(delegate, root.left, index);
                L = splitResult.left;
                newTree = splitResult.right;
            }
            R = new TreapNode<>(delegate, root.element, root.priority, newTree, root.right);
            delegate.updateStatistics(R);
            splitResult = new SplitResult<>(L, R);
        }

        return splitResult;
    }

    public void add(E element, int index, int priority) {
        if (root == null) {
            root = new TreapNode<>(delegate, element, priority, null, null);
        } else {
            final SplitResult<E> result = split(delegate, root, index);
            final TreapNode<E> m = new TreapNode<>(delegate, element, priority, null, null);
            root = merge(delegate, merge(delegate, result.left, m), result.right);
        }
    }

    public void add(E element, int index) {
        add(element, index, RANDOM.nextInt());
    }

    public void add(E element) {
        add(element, root != null ? root.size : 0, RANDOM.nextInt());
    }

    public void addAll(Collection<E> elements) {
        elements.forEach(this::add);
    }

    public void addAll(E[] elements) {
        Stream.of(elements).forEach(this::add);
    }

    public void addCostToTreap(int cost) {
        root.costDelta += cost;
    }

    public void addCostToInterval(int left, int right, int cost) {
        SplitResult<E> result1, result2;
        result1 = split(delegate, root, left);
        result2 = split(delegate, result1.right, right - left);
        result2.left.costDelta += cost;

        root = merge(delegate, merge(delegate, result1.left, result2.left), result2.right);
    }

    public int getCostOnInterval(int left, int right) {
        SplitResult<E> result1, result2;
        result1 = split(delegate, root, left);
        result2 = split(delegate, result1.right, right - left);

        return result2.left.getCostSum();
    }

    public int getCost() {
        return root.getCostSum() + root.costDelta * size();
    }

    public void remove(int index) {
        if (root == null || root.size == 0) {
            throw new IllegalStateException("Unable to remove. Treap is empty");
        } else if (index < 0 || index >= root.size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds");
        }

        final SplitResult<E> resultFirst = split(delegate, this.root, index);
        final SplitResult<E> resultSecond = split(delegate, resultFirst.right, 1);
        root = merge(delegate, resultFirst.left, resultSecond.right);
    }

    public boolean contains(E element) {
        for (E e : inOrderTraversal()) {
            if (e.equals(element)) {
                return true;
            }
        }
        return false;
    }

    private List<E> inOrderTraversal() {
        final List<E> result = new ArrayList<>();
        inOrderTraversal(root, result);

        return result;
    }

    public int size() {
        return root != null ? root.size : 0;
    }

    public void clear() {
        root = null;
    }

    public void reverse() {
        reverse(0, root.size);
    }

    public void reverse(int left, int right) {
        SplitResult<E> splitResult1, splitResult2;
        splitResult1 = split(delegate, root, left);
        splitResult2 = split(delegate, splitResult1.right, right - left);
        splitResult2.left.reversed ^= true;
        root = merge(delegate, merge(delegate, splitResult1.left, splitResult2.left), splitResult2.right);
    }

    public void shiftLeft(int steps) {
        final SplitResult<E> splitResult = split(delegate, root, steps);
        root = merge(delegate, splitResult.right, splitResult.left);
    }

    public void shiftRight(int steps) {
        shiftLeft(root.size - steps);
    }

    public static class TreapNode<E> {
        private E element;
        private int priority;
        private TreapNode<E> left;
        private TreapNode<E> right;
        private int size;
        private int costSum;
        private int costDelta;
        private boolean reversed;

        public TreapNode(Delegate<E> delegate, E element, int priority, TreapNode<E> left, TreapNode<E> right) {
            this.element = element;
            this.priority = priority;
            this.left = left;
            this.right = right;
            delegate.updateStatistics(this);
        }

        public E getElement() {
            return element;
        }

        public TreapNode<E> getLeft() {
            return left;
        }

        public TreapNode<E> getRight() {
            return right;
        }

        public int getSize() {
            return size;
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

        public void updateCostSum(int cost) {
            this.costSum = cost;
        }
    }

    private static class SplitResult<E> {
        private TreapNode<E> left;
        private TreapNode<E> right;

        public SplitResult(TreapNode<E> left, TreapNode<E> right) {
            this.left = left;
            this.right = right;
        }
    }

    public static abstract class Delegate<E> {
        public abstract void updateStatistics(TreapNode<E> root);

        public abstract void updateData(TreapNode<E> root);
    }
}