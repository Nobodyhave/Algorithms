package data_structures;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Aleksandr on 26/01/2017.
 * Project untitled
 */
public class TreapImplicitTest {
    private static final List<TestEntry> leftTreapKeys = new ArrayList<>();
    private static final List<Integer> leftTreapPriorities = new ArrayList<>();
    private static final List<TestEntry> rightTreapKeys = new ArrayList<>();
    private static final List<Integer> rightTreapPriorities = new ArrayList<>();
    private static final List<TestEntry> balancedTreapKeys = new ArrayList<>();
    private static final List<Integer> balancedTreapPriorities = new ArrayList<>();
    private static final TestTreapDelegate delegate = new TestTreapDelegate();

    @Before
    public void setUp() throws Exception {
        Scanner in = new Scanner(new FileInputStream(TreapImplicitTest.class.getResource("left_treap.txt").getFile()));
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            leftTreapKeys.add(new TestEntry(in.nextInt()));
        }
        for (int i = 0; i < N; i++) {
            leftTreapPriorities.add(in.nextInt());
        }
        in.close();

        in = new Scanner(new FileInputStream(TreapImplicitTest.class.getResource("right_treap.txt").getFile()));
        N = in.nextInt();
        for (int i = 0; i < N; i++) {
            rightTreapKeys.add(new TestEntry(in.nextInt()));
        }
        for (int i = 0; i < N; i++) {
            rightTreapPriorities.add(in.nextInt());
        }
        in.close();

        in = new Scanner(new FileInputStream(TreapImplicitTest.class.getResource("balanced_treap.txt").getFile()));
        N = in.nextInt();
        for (int i = 0; i < N; i++) {
            balancedTreapKeys.add(new TestEntry(in.nextInt()));
        }
        for (int i = 0; i < N; i++) {
            balancedTreapPriorities.add(in.nextInt());
        }
        in.close();
    }

    @After
    public void tearDown() throws Exception {
        leftTreapKeys.clear();
        leftTreapPriorities.clear();
        rightTreapKeys.clear();
        rightTreapPriorities.clear();
        balancedTreapKeys.clear();
        balancedTreapPriorities.clear();
    }

    @Test
    public void testCreateFromCollection() {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate, balancedTreapKeys);

        assertSize(treap, balancedTreapKeys.size());

        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }
    }

    @Test
    public void testCreateFromArray() {
        final TestEntry[] array = new TestEntry[balancedTreapKeys.size()];
        balancedTreapKeys.toArray(array);

        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate, array);

        assertSize(treap, balancedTreapKeys.size());

        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }
    }

    @Test
    public void testLeftTreap() {
        // Create
        ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);

        // Add
        for (int i = 0; i < leftTreapKeys.size(); i++) {
            treap.add(leftTreapKeys.get(i), i, leftTreapPriorities.get(i));
        }

        // Check size
        assertSize(treap, leftTreapKeys.size());

        // Check elements
        for (TestEntry key : leftTreapKeys) {
            assertContains(treap, key);
        }

        // Remove
        for (int i = 0; i < leftTreapKeys.size(); i++) {
            treap.remove(0);
        }

        // Check size
        assertSize(treap, 0);

        // Check elements
        for (TestEntry key : leftTreapKeys) {
            assertNotContains(treap, key);
        }
    }

    @Test
    public void testRightTreap() {
        // Create
        ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);

        // Add
        for (int i = 0; i < rightTreapKeys.size(); i++) {
            treap.add(rightTreapKeys.get(i), i, rightTreapPriorities.get(i));
        }

        // Check size
        assertSize(treap, rightTreapKeys.size());

        // Check elements
        for (TestEntry key : rightTreapKeys) {
            assertContains(treap, key);
        }

        // Remove
        for (int i = 0; i < rightTreapKeys.size(); i++) {
            treap.remove(0);
        }

        // Check size
        assertSize(treap, 0);

        // Check elements
        for (TestEntry key : rightTreapKeys) {
            assertNotContains(treap, key);
        }
    }

    @Test
    public void testBalancedTreap() {
        // Create
        ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);

        // Add
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        // Check size
        assertSize(treap, balancedTreapKeys.size());

        // Check elements
        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }

        // Remove
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.remove(0);
        }

        // Check size
        assertSize(treap, 0);

        // Check elements
        for (TestEntry key : balancedTreapKeys) {
            assertNotContains(treap, key);
        }
    }

    @Test
    public void testAddAllCollection() {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);

        treap.addAll(balancedTreapKeys);

        assertSize(treap, balancedTreapKeys.size());

        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }
    }

    @Test
    public void testAddAllArray() {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        final TestEntry[] array = new TestEntry[balancedTreapKeys.size()];
        balancedTreapKeys.toArray(array);

        treap.addAll(array);

        assertSize(treap, balancedTreapKeys.size());

        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }
    }

    /*@Test
    public void testInOrderTraversal() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        final List<TestEntry> inOrder = treap.inOrderTraversal();

        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            assert inOrder.get(i).equals(balancedTreapKeys.get(i));
        }
    }*/

    @Test
    public void testAddCostToTreap() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        int expectedCost = balancedTreapKeys.stream().mapToInt(value -> value.cost).sum();
        assertCost(treap, expectedCost);

        treap.addCostToTreap(2);

        expectedCost += balancedTreapKeys.size() * 2;
        assertCost(treap, expectedCost);
    }

    @Test
    public void testGetCostOnInterval() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        final int left = 5, right = 10;
        int expectedCost = 0;
        for (int i = left; i < right; i++) {
            expectedCost += balancedTreapKeys.get(i).cost;
        }

        assert treap.getCostOnInterval(left, right) == expectedCost : String.format("Expected cost: %d Real cost: %d", expectedCost, treap.getCostOnInterval(left, right));
    }

    @Test
    public void testAddCostToInterval() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        final int left = 5, right = 10;
        int expectedCost = 0;
        for (int i = left; i < right; i++) {
            expectedCost += balancedTreapKeys.get(i).cost;
        }

        treap.addCostToInterval(5, 10, 4);
        expectedCost += 5 * 4;

        treap.getCostOnInterval(left, right);
        treap.getCostOnInterval(left, right);
        assert treap.getCostOnInterval(left, right) == expectedCost : String.format("Expected cost: %d Real cost: %d", expectedCost, treap.getCostOnInterval(left, right));
    }

    @Test(expected = IllegalStateException.class)
    public void tesRemoveFromEmptyTreap() {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);

        treap.remove(0);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void tesRemoveUnderflow() {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        treap.add(new TestEntry(1));

        treap.remove(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void tesRemoveOverflow() {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        treap.add(new TestEntry(1));

        treap.remove(1);
    }

    @Test
    public void testQuery() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        assert balancedTreapKeys.get(5).equals(treap.query(5)) : String.format("Expected result: %s Real result: %s", balancedTreapKeys.get(5), treap.query(5));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testQueryUnderFlow() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        treap.query(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testQueryOverFlow() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        treap.query(100);
    }

    @Test
    public void testRandomAdd() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        // Case with highest priority
        TestEntry entry = new TestEntry(100);
        int index = 5;
        int priority = 100;

        treap.add(entry, index, priority);
        assert entry.equals(treap.query(index))
                : String.format("Expected result: %s Real result: %s", entry, treap.query(index));

        // Case with lowest priority
        entry = new TestEntry(200);
        index = 10;
        priority = -100;

        treap.add(entry, index, priority);
        assert entry.equals(treap.query(index))
                : String.format("Expected result: %s Real result: %s", entry, treap.query(index));
    }

    @Test
    public void testRandomRemove() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        // Case with highest priority
        TestEntry entry = new TestEntry(100);
        int index = 5;
        int priority = 100;

        treap.add(entry, index, priority);
        assert entry.equals(treap.query(index))
                : String.format("Expected result: %s Real result: %s", entry, treap.query(index));

        treap.remove(index);
        assert balancedTreapKeys.get(index).equals(treap.query(index))
                : String.format("Expected result: %s Real result: %s", balancedTreapKeys.get(index), treap.query(index));

        // Case with lowest priority
        entry = new TestEntry(200);
        index = 10;
        priority = -100;

        treap.add(entry, index, priority);
        assert entry.equals(treap.query(index))
                : String.format("Expected result: %s Real result: %s", entry, treap.query(index));

        treap.remove(index);
        assert balancedTreapKeys.get(index).equals(treap.query(index))
                : String.format("Expected result: %s Real result: %s", balancedTreapKeys.get(index), treap.query(index));
    }

    @Test
    public void testRandomPriorityAdd() throws Exception {
        final int size = 100;
        final int iterations = 100;

        for (int i = 0; i < iterations; i++) {
            final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
            for (int j = 0; j < size; j++) {
                treap.add(new TestEntry(j), 0);
            }

            for (int j = 0; j < size; j++) {
                assert treap.query(size - j - 1).equals(new TestEntry(j));
            }
        }
    }

    @Test
    public void testClear() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        assertSize(treap, balancedTreapKeys.size());

        treap.clear();
        assertSize(treap, 0);
    }


    @Test
    public void testReverse() throws Exception {
        ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        TestEntry entry = new TestEntry(200);
        int index = 10;
        int priority = -100;

        treap.add(entry, index, priority);
        balancedTreapKeys.add(index, entry);

        treap.reverse();
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            assert treap.query(balancedTreapKeys.size() - 1 - i).equals(balancedTreapKeys.get(i));
        }
    }

    @Test
    public void testReverseOnInterval() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        final int left = 5, right = 10;
        treap.reverse(left, right);

        for (int i = 0; i < left; i++) {
            assert treap.query(i).equals(balancedTreapKeys.get(i));
        }
        for (int i = left; i < right; i++) {
            assert treap.query(balancedTreapKeys.size() - 1 - i).equals(balancedTreapKeys.get(i));
        }
        for (int i = right; i < balancedTreapKeys.size(); i++) {
            assert treap.query(i).equals(balancedTreapKeys.get(i));
        }
    }

    @Test
    public void testShiftLeft() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        final int steps = 5;
        treap.shiftLeft(steps);

        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            final int index = (balancedTreapKeys.size() + (i - steps)) % balancedTreapKeys.size();
            assert balancedTreapKeys.get(i).equals(treap.query(index));
        }
    }

    @Test
    public void testShiftRight() throws Exception {
        final ImplicitTreap<TestEntry> treap = new ImplicitTreap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), i, balancedTreapPriorities.get(i));
        }

        final int steps = 5;
        treap.shiftRight(steps);

        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            final int index = (balancedTreapKeys.size() + (i + steps)) % balancedTreapKeys.size();
            assert balancedTreapKeys.get(i).equals(treap.query(index));
        }
    }

    private void assertSize(ImplicitTreap<TestEntry> treap, int size) {
        assert treap.size() == size : String.format("Expected size: %d Real size: %s", size, treap.size());
    }

    private void assertContains(ImplicitTreap<TestEntry> treap, TestEntry num) {
        assert treap.contains(num) : String.format("Treap doesn't contain %s", num);
    }

    private void assertNotContains(ImplicitTreap<TestEntry> treap, TestEntry num) {
        assert !treap.contains(num) : String.format("Treap contains %s", num);
    }

    private void assertCost(ImplicitTreap<TestEntry> treap, int cost) {
        assert treap.getCost() == cost : String.format("Expected cost: %d Real cost: %s", cost, treap.getCost());
    }

    private static class TestTreapDelegate extends ImplicitTreap.Delegate<TestEntry> {
        @Override
        public void updateStatistics(ImplicitTreap.TreapNode<TestEntry> root) {
            int size = (root.getLeft() == null ? 0 : root.getLeft().getSize()) + (root.getRight() == null ? 0 : root.getRight().getSize()) + 1;
            int costSum =
                    (root.getLeft() == null ? 0 : root.getLeft().getCostSum() + root.getLeft().getCostDelta() * root.getLeft().getSize())
                            + (root.getRight() == null ? 0 : root.getRight().getCostSum() + root.getRight().getCostDelta() * root.getRight().getSize())
                            + root.getElement().cost;

            root.updateSize(size);
            root.updateCostSum(costSum);
        }

        @Override
        public void updateData(ImplicitTreap.TreapNode<TestEntry> root) {
            root.getElement().cost += root.getCostDelta();
        }
    }

    private static class TestEntry {
        private Integer cost;

        public TestEntry(Integer cost) {
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "TestEntry{" +
                    "cost=" + cost +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            TestEntry entry = (TestEntry) o;

            return cost.equals(entry.cost);

        }

        @Override
        public int hashCode() {
            return cost.hashCode();
        }
    }
}