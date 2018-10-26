import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    /**
     * current node
     */
    private Node cur;
    /**
     * current index
     */
    private int curIndex;
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
     * construct an empty randomized queue
     */
    public RandomizedQueue() {
    }

    /**
     * is the deque empty?
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * return the number of items on the randomized queue
     */
    public int size() {
        return size;
    }

    /**
     * add the item
     */
    public void enqueue(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item could not be null!");
        }

        Node node = new Node();
        node.item = item;
        if (size == 0) {
            cur = node;
            curIndex = 0;
        } else {
            node.next = cur.next;
            cur.next = node;
            node.prev = cur;
            if (node.next != null) {
                node.next.prev = node;
            }
        }
        size++;
    }

    /**
     * remove and return a random item
     */
    public Item dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty!");
        }

        if (size == 1) {
            Node tmp = cur;
            tmp.prev = null;
            tmp.next = null;
            size--;
            cur = null;
            return tmp.item;
        }

        int r = StdRandom.uniform(0, size > 100 ? 100 : size);
        if (r > curIndex) {
            for (int i = curIndex; i < r; i++) {
                cur = cur.next;
            }
        } else {
            for (int i = curIndex; i > r; i--) {
                cur = cur.prev;
            }
        }

        Node tmp = cur;
        if (tmp.prev != null) {
            tmp.prev.next = tmp.next;
            cur = tmp.prev;
            curIndex = r - 1;
        } else {
            curIndex = 0;
            cur = tmp.next;
        }

        if (tmp.next != null) {
            tmp.next.prev = tmp.prev;
            if (cur == null) {
                cur = tmp.next;
            }
        }

        tmp.prev = null;
        tmp.next = null;
        size--;
        return tmp.item;
    }

    /**
     * return a random item (but do not remove it)
     */
    public Item sample() {
        if (size == 0) {
            throw new NoSuchElementException("Queue is empty!");
        }

        if (size == 1) {
            return cur.item;
        }

        int r = StdRandom.uniform(0, size > 100 ? 100 : size);
        Node tmp = cur;
        if (r > curIndex) {
            for (int i = curIndex; i < r; i++) {
                tmp = tmp.next;
            }
        } else {
            for (int i = curIndex; i > r; i--) {
                tmp = tmp.prev;
            }
        }

        return tmp.item;
    }

    /**
     * Randomized Queue Iterator
     */
    private class RandomizedQueueIterator implements Iterator<Item> {
        /**
         * current item array
         */
        private Object[] items;
        /**
         * current item length
         */
        private int length;

        public RandomizedQueueIterator() {
            if (size == 0) {
                items = null;
                length = 0;
            } else {
                length = size;
                items = new Object[length];
                Node tmp = cur;
                int i = 0;
                items[i++] = tmp.item;
                while (tmp.prev != null) {
                    tmp = tmp.prev;
                    items[i++] = tmp.item;
                }
                tmp = cur;
                while (tmp.next != null) {
                    tmp = tmp.next;
                    items[i++] = tmp.item;
                }
            }
        }

        @Override
        public boolean hasNext() {
            return length != 0;
        }

        @Override
        public Item next() {
            if (length == 0) {
                throw new NoSuchElementException("Iterator is empty!");
            }

            int r = StdRandom.uniform(0, length--);
            Item tmp = (Item) items[r];
            if (r != length) {
                items[r] = items[length];
            }
            return tmp;
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /**
     * return an independent iterator over items in random order
     */
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    /**
     * unit testing
     */
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        rq.enqueue("a");
        rq.enqueue("b");
        rq.enqueue("c");
        rq.enqueue("d");
        rq.enqueue("e");
        rq.enqueue("f");
        rq.enqueue("g");
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
        System.out.println(rq.dequeue());
    }
}