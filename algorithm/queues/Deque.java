import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    /**
     * first node
     */
    private Node first;
    /**
     * last node
     */
    private Node last;
    /**
     * queue size
     */
    private int size;

    /**
     * Node
     */
    private class Node {
        /**
         * previous node
         */
        private Node prev;
        /**
         * item
         */
        private Item item;
        /**
         * next node
         */
        private Node next;
    }

    /**
     * construct an empty deque
     */
    public Deque() {
    }

    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the deque
     */
    public int size() {
        return size;
    }

    /**
     * add the item to the front
     */
    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("First could not be null!");
        }

        Node node = new Node();
        node.item = item;
        if (size == 0) {
            first = node;
            last = node;
        } else {
            first.prev = node;
            node.next = first;
            first = node;
        }
        size++;
    }

    /**
     * add the item to the end
     */
    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Last could not be null!");
        }

        Node node = new Node();
        node.item = item;
        if (size == 0) {
            first = node;
            last = node;
        } else {
            last.next = node;
            node.prev = last;
            last = node;
        }
        size++;
    }

    /**
     * remove and return the item from the front
     */
    public Item removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty!");
        }

        Node result = first;
        if (size != 1) {
            first = first.next;
            first.prev = null;
            result.next = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return result.item;
    }

    /**
     * remove and return the item from the end
     */
    public Item removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty!");
        }

        Node result = last;
        if (size != 1) {
            last = last.prev;
            last.next = null;
            result.prev = null;
        } else {
            first = null;
            last = null;
        }
        size--;
        return result.item;
    }

    /**
     * Deque Iterator
     */
    private class DequeIterator implements Iterator<Item> {
        /**
         * current node
         */
        private Node cur = size == 0 ? null : first;

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public Item next() {
            if (cur == null) {
                throw new NoSuchElementException("Iterator is empty!");
            }

            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * return an iterator over items in order from front to end
     */
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    /**
     * unit testing
     */
    public static void main(String[] args) {
        Deque<String> queue = new Deque<String>();
        System.out.println(queue.size);
        queue.addFirst("a");
        queue.addFirst("b");
        queue.addLast("c");
        queue.addFirst("d");
        queue.addLast("e");
        System.out.println(queue.size);
        Iterator<String> iter = queue.iterator();
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
    }
}