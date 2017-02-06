package shortcuts;

public final class ListUtils {

    private ListUtils() {
        // Prevent instantiation
    }

    /**
     * Prints linked list
     *
     * @param head Reference to the first node of the list
     */
    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    /**
     * Reverses linked list
     *
     * @param head First node of the linked list
     * @return New first node of the linked list (previous tail)
     */
    public static Node reverseList(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        Node p1 = head, p2 = head.next;
        p1.next = null;
        while (p2 != null) {
            final Node temp = p2.next;
            p2.next = p1;
            p1 = p2;
            p2 = temp;
        }

        return p1;
    }

    private static class Node {
        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
    }
}
