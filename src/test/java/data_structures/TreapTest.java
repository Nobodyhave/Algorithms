package data_structures;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Aleksandr on 26/01/2017.
 * Project untitled
 */
public class TreapTest {
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
        final Treap<TestEntry> treap = new Treap<>(delegate, balancedTreapKeys);

        assertSize(treap, balancedTreapKeys.size());

        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }
    }

    @Test
    public void testCreateFromArray() {
        final TestEntry[] array = new TestEntry[balancedTreapKeys.size()];
        balancedTreapKeys.toArray(array);

        final Treap<TestEntry> treap = new Treap<>(delegate, array);

        assertSize(treap, balancedTreapKeys.size());

        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }
    }

    @Test
    public void testLeftTreap() {
        // Create
        Treap<TestEntry> treap = new Treap<>(delegate);

        // Add
        for (int i = 0; i < leftTreapKeys.size(); i++) {
            treap.add(leftTreapKeys.get(i), leftTreapPriorities.get(i));
        }

        // Check size
        assertSize(treap, leftTreapKeys.size());

        // Check elements
        for (TestEntry key : leftTreapKeys) {
            assertContains(treap, key);
        }

        // Remove
        leftTreapKeys.forEach(treap::remove);

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
        Treap<TestEntry> treap = new Treap<>(delegate);

        // Add
        for (int i = 0; i < rightTreapKeys.size(); i++) {
            treap.add(rightTreapKeys.get(i), rightTreapPriorities.get(i));
        }

        // Check size
        assertSize(treap, rightTreapKeys.size());

        // Check elements
        for (TestEntry key : rightTreapKeys) {
            assertContains(treap, key);
        }

        // Remove
        rightTreapKeys.forEach(treap::remove);

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
        Treap<TestEntry> treap = new Treap<>(delegate);

        // Add
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), balancedTreapPriorities.get(i));
        }

        // Check size
        assertSize(treap, balancedTreapKeys.size());

        // Check elements
        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }

        // Remove
        balancedTreapKeys.forEach(treap::remove);

        // Check size
        assertSize(treap, 0);

        // Check elements
        for (TestEntry key : balancedTreapKeys) {
            assertNotContains(treap, key);
        }
    }

    @Test
    public void testAddAllCollection() {
        final Treap<TestEntry> treap = new Treap<>(delegate);

        treap.addAll(balancedTreapKeys);

        assertSize(treap, balancedTreapKeys.size());

        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }
    }

    @Test
    public void testAddAllArray() {
        final Treap<TestEntry> treap = new Treap<>(delegate);
        final TestEntry[] array = new TestEntry[balancedTreapKeys.size()];
        balancedTreapKeys.toArray(array);

        treap.addAll(array);

        assertSize(treap, balancedTreapKeys.size());

        for (TestEntry key : balancedTreapKeys) {
            assertContains(treap, key);
        }
    }

    @Test
    public void testInOrderTraversal() throws Exception {
        final Treap<TestEntry> treap = new Treap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), balancedTreapPriorities.get(i));
        }

        final List<TestEntry> inOrder = treap.inOrderTraversal();
        final List<TestEntry> sortedKeys = new ArrayList<>(balancedTreapKeys);
        Collections.sort(sortedKeys);

        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            assert inOrder.get(i).equals(inOrder.get(i));
        }
    }

    @Test
    public void testInOrderTraversalNull() throws Exception {
        final Treap<TestEntry> treap = new Treap<>(delegate);
        treap.inOrderTraversal();
    }

    @Test
    public void testSum() throws Exception {
        final Treap<TestEntry> treap = new Treap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), balancedTreapPriorities.get(i));
        }

        final int expectedSum = balancedTreapKeys.stream().mapToInt(value -> value.key).sum();
        assert expectedSum == treap.getSum();
    }

    @Test
    public void testCost() throws Exception {
        final Treap<TestEntry> treap = new Treap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), balancedTreapPriorities.get(i));
        }

        int expectedCost = balancedTreapKeys.stream().mapToInt(value -> value.cost).sum();
        assertCost(treap, expectedCost);

        treap.addCostToTreap(2);

        expectedCost += balancedTreapKeys.size() * 2;
        assertCost(treap, expectedCost);

        treap.addCostToInterval(new TestEntry(5), new TestEntry(10), 4);

        expectedCost += 6 * 4;
        assertCost(treap, expectedCost);

        treap.addCostToInterval(new TestEntry(0), new TestEntry(2), 4);

        expectedCost += 3 * 4;
        assertCost(treap, expectedCost);
    }

    @Test(expected = IllegalStateException.class)
    public void tesRemoveEmpty() {
        final Treap<TestEntry> treap = new Treap<>(delegate);

        treap.remove(new TestEntry(0));
    }

    @Test
    public void testGetKthLargest() throws Exception {
        final Treap<TestEntry> treap = new Treap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), balancedTreapPriorities.get(i));
        }

        Collections.sort(balancedTreapKeys);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            assert treap.getKthElement(i).equals(balancedTreapKeys.get(i))
                    : String.format("Expected: %s Real: %s", balancedTreapKeys.get(i), treap.getKthElement(i));
        }
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetKthLargestUnderFlow() throws Exception {
        final Treap<TestEntry> treap = new Treap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), balancedTreapPriorities.get(i));
        }

        treap.getKthElement(-1);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetKthLargestOverFlow() throws Exception {
        final Treap<TestEntry> treap = new Treap<>(delegate);
        for (int i = 0; i < balancedTreapKeys.size(); i++) {
            treap.add(balancedTreapKeys.get(i), balancedTreapPriorities.get(i));
        }

        treap.getKthElement(balancedTreapKeys.size());
    }

    @Test
    public void testDuplicateKeys() throws Exception {
        final List<TestEntry> duplicateTreapKeys = new ArrayList<>();
        final List<Integer> duplicateTreapPriorities = new ArrayList<>();

        final Scanner in = new Scanner(new FileInputStream(TreapImplicitTest.class.getResource("duplicate_keys_treap.txt").getFile()));
        int N = in.nextInt();
        for (int i = 0; i < N; i++) {
            duplicateTreapKeys.add(new TestEntry(in.nextInt()));
        }
        for (int i = 0; i < N; i++) {
            duplicateTreapPriorities.add(in.nextInt());
        }
        in.close();

        final Treap<TestEntry> treap = new Treap<>(delegate);
        for (int i = 0; i < N; i++) {
            treap.add(duplicateTreapKeys.get(i), duplicateTreapPriorities.get(i));
            System.out.println();
        }

        int expectedCost = duplicateTreapKeys.stream().mapToInt(value -> value.cost).sum();
        assertCost(treap, expectedCost);

        treap.addCostToInterval(new TestEntry(-1), new TestEntry(-1), 1);
        expectedCost += 2;
        assert treap.getCost() == expectedCost;


        treap.addCostToInterval(new TestEntry(1), new TestEntry(1), 1);
        expectedCost += 2;
        assert treap.getCost() == expectedCost;

        treap.addCostToInterval(new TestEntry(-2), new TestEntry(-2), 1);
        expectedCost += 1;
        assert treap.getCost() == expectedCost;
    }

    private void assertSize(Treap<TestEntry> treap, int size) {
        assert treap.size() == size : String.format("Expected size: %d Real size: %s", size, treap.size());
    }

    private void assertContains(Treap<TestEntry> treap, TestEntry num) {
        assert treap.contains(num) : String.format("Treap doesn't contain %d", num.key);
    }

    private void assertNotContains(Treap<TestEntry> treap, TestEntry num) {
        assert !treap.contains(num) : String.format("Treap contains %d", num.key);
    }

    private void assertCost(Treap<TestEntry> treap, int cost) {
        assert treap.getCost() == cost : String.format("Expected cost: %d Real cost: %s", cost, treap.getCost());
    }

    private static class TestTreapDelegate extends Treap.Delegate<TestEntry> {
        @Override
        public void updateStatistics(Treap.TreapNode<TestEntry> root) {
            int size = (root.getLeft() == null ? 0 : root.getLeft().getSize()) + (root.getRight() == null ? 0 : root.getRight().getSize()) + 1;
            int sum = (root.getLeft() == null ? 0 : root.getLeft().getSum()) + (root.getRight() == null ? 0 : root.getRight().getSum()) + root.getKey().key;
            int costSum =
                    (root.getLeft() == null ? 0 : root.getLeft().getCostSum() + root.getLeft().getCostDelta() * root.getLeft().getSize())
                            + (root.getRight() == null ? 0 : root.getRight().getCostSum() + root.getRight().getCostDelta() * root.getRight().getSize())
                            + root.getKey().cost;

            root.updateSize(size);
            root.updateSum(sum);
            root.updateCostSum(costSum);
        }

        @Override
        public void updateKey(Treap.TreapNode<TestEntry> root) {
            root.getKey().cost += root.getCostDelta();
        }
    }

    private static class TestEntry implements Comparable<TestEntry> {
        private Integer key;
        private Integer cost;

        public TestEntry(Integer key) {
            this.key = key;
            cost = key * key;
        }

        @Override
        public int compareTo(TestEntry o) {
            return key.compareTo(o.key);
        }

        @Override
        public String toString() {
            return "TestEntry{" +
                    "key=" + key +
                    ", cost=" + cost +
                    '}';
        }
    }
}