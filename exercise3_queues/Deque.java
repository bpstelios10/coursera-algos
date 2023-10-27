package exercise3_queues;

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {

    private Node first, last;
    private int size;

    // construct an empty deque
    public Deque() {
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node newFirst = new Node(item, null, first);
        if (first != null) first.previous = newFirst;
        if (isEmpty()) last = newFirst;
        first = newFirst;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        if (item == null) throw new IllegalArgumentException();

        Node newLast = new Node(item, last, null);
        if (last != null) last.next = newLast;
        last = newLast;
        if (isEmpty()) first = last;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        if (isEmpty()) throw new NoSuchElementException();

        Node previousFirst = first;

        if (first == last) {
            first = null;
            last = null;
        }
        else {
            first = first.next;
            first.previous = null;
            previousFirst.next = null; // make it available for garbage collector
        }
        size--;

        return previousFirst.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        if (isEmpty()) throw new NoSuchElementException();

        Node previousLast = last;

        if (first == last) {
            first = null;
            last = null;
        }
        else {
            last = last.previous;
            last.next = null;
            previousLast.previous = null; // make it available for garbage collector
        }
        size--;

        return previousLast.item;
    }

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();

        assertThat(deque.isEmpty());
        deque.addFirst("item1");
        assertThat(!deque.isEmpty());
        String item = deque.removeFirst();
        assertThat(deque.isEmpty());
        assertThat("item1".equals(item));


        deque.addFirst("item2");
        assertThat(!deque.isEmpty());
        deque.addFirst("item3");
        assertThat(!deque.isEmpty());
        item = deque.removeFirst();
        assertThat("item3".equals(item));
        assertThat(!deque.isEmpty());
        item = deque.removeFirst();
        assertThat("item2".equals(item));
        assertThat(deque.isEmpty());

        deque.addLast("item2");
        assertThat(!deque.isEmpty());
        deque.addLast("item3");
        assertThat(!deque.isEmpty());
        item = deque.removeLast();
        assertThat("item3".equals(item));
        assertThat(!deque.isEmpty());
        item = deque.removeLast();
        assertThat("item2".equals(item));
        assertThat(deque.isEmpty());

        deque.addFirst("item4");
        deque.addFirst("item5");
        item = deque.removeLast();
        assertThat("item4".equals(item));
        assertThat(!deque.isEmpty());
        item = deque.removeLast();
        assertThat("item5".equals(item));
        assertThat(deque.isEmpty());

        deque.addLast("item6");
        deque.addLast("item7");
        item = deque.removeFirst();
        assertThat("item6".equals(item));
        assertThat(!deque.isEmpty());
        item = deque.removeFirst();
        assertThat("item7".equals(item));
        assertThat(deque.isEmpty());

        deque.addFirst("item9");
        deque.addLast("item10");
        deque.addFirst("item8");
        deque.addLast("item11");
        assertThat("item8".equals(deque.removeFirst()));
        assertThat("item9".equals(deque.removeFirst()));
        assertThat("item10".equals(deque.removeFirst()));
        assertThat("item11".equals(deque.removeFirst()));
        assertThat(deque.isEmpty());

        deque.addFirst("item13");
        deque.addLast("item14");
        deque.addFirst("item12");
        deque.addLast("item15");
        assertThat("item15".equals(deque.removeLast()));
        assertThat("item14".equals(deque.removeLast()));
        assertThat("item13".equals(deque.removeLast()));
        assertThat("item12".equals(deque.removeLast()));
        assertThat(deque.isEmpty());

        deque.addFirst("item18");
        deque.addFirst("item17");
        deque.addFirst("item16");
        deque.addLast("item19");
        deque.addLast("item20");
        deque.addLast("item21");
        assertThat(deque.size() == 6);
        for (String s : deque) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        deque.removeFirst();
        deque.removeLast();
        assertThat(deque.size() == 4);
        deque.removeLast();
        deque.removeLast();
        deque.removeFirst();
        deque.removeLast();
        assertThat(deque.isEmpty());
    }

    private static void assertThat(boolean assertion) {
        if (!assertion) throw new RuntimeException("Assertion failed");
    }

    private class Node {
        private Item item;
        private Node previous;
        private Node next;

        Node() {
        }

        Node(Item item, Node previous, Node next) {
            this.item = item;
            this.previous = previous;
            this.next = next;
        }
    }

    private class ListIterator implements Iterator<Item> {
        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;

            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
