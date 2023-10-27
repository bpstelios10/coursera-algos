import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int size;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[8];
        size = 0;
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        if (item == null) throw new IllegalArgumentException();
        if (size == items.length) resize(2 * items.length);

        int i = StdRandom.uniformInt(size + 1);
        Item oldItem = items[i];
        items[i] = item;
        if (oldItem != null) items[size] = oldItem;
        size++;
    }

    // remove and return a random item
    public Item dequeue() {
        if (isEmpty()) throw new NoSuchElementException();

        Item item = items[size - 1];
        items[size - 1] = null;
        size--;

        if (size > 0 && size == items.length / 4) resize(items.length / 2);

        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        if (isEmpty()) throw new NoSuchElementException();

        int i = StdRandom.uniformInt(size);

        return items[i];
    }

    // return an independent iterator over items in random order
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    public static void main(String[] args) {
        RandomizedQueue<String> randomizedQueue = new RandomizedQueue<>();

        assertThat(randomizedQueue.isEmpty());
        randomizedQueue.enqueue("item1");
        assertThat(!randomizedQueue.isEmpty());
        String item = randomizedQueue.dequeue();
        assertThat(randomizedQueue.isEmpty());
        assertThat("item1".equals(item));


        randomizedQueue.enqueue("item2");
        assertThat(!randomizedQueue.isEmpty());
        randomizedQueue.enqueue("item3");
        assertThat(!randomizedQueue.isEmpty());
        String item2 = randomizedQueue.dequeue();
        assertThat("item3".equals(item2) || "item2".equals(item2));
        assertThat(!randomizedQueue.isEmpty());
        String item3 = randomizedQueue.dequeue();
        assertThat("item3".equals(item3) || "item2".equals(item3));
        assertThat(!item2.equals(item3));
        assertThat(randomizedQueue.isEmpty());

        randomizedQueue.enqueue("item4");
        String item4 = randomizedQueue.sample();
        assertThat("item4".equals(item4));
        assertThat(!randomizedQueue.isEmpty());
        String item5 = randomizedQueue.sample();
        assertThat("item4".equals(item5));
        assertThat(!randomizedQueue.isEmpty());

        randomizedQueue.dequeue();
        randomizedQueue.enqueue("item18");
        randomizedQueue.enqueue("item17");
        randomizedQueue.enqueue("item16");
        randomizedQueue.enqueue("item19");
        randomizedQueue.enqueue("item20");
        randomizedQueue.enqueue("item21");
        assertThat(randomizedQueue.size() == 6);
        for (String s : randomizedQueue) {
            StdOut.print(s + " ");
        }
        StdOut.println();
        assertThat(!randomizedQueue.isEmpty());

        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        assertThat(randomizedQueue.size() == 6);
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        StdOut.println(randomizedQueue.sample());
        assertThat(!randomizedQueue.isEmpty());
    }

    private static void assertThat(boolean assertion) {
        if (!assertion) throw new RuntimeException("Assertion failed");
    }

    private void resize(int capacity) {
        assert capacity >= size;

        // textbook implementation
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    private class ListIterator implements Iterator<Item> {

        int currentIndex = 0;
        private Item[] iteratorItems = (Item[]) new Object[size];

        ListIterator() {
            int iteratorItemsSize = 0;
            for (int x = 0; x < size; x++) {
                int i = StdRandom.uniformInt(iteratorItemsSize + 1);
                Item oldItem = iteratorItems[i];
                iteratorItems[i] = items[x];
                if (oldItem != null) iteratorItems[iteratorItemsSize] = oldItem;
                iteratorItemsSize++;
            }
        }

        public boolean hasNext() {
            return currentIndex < size;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();

            return iteratorItems[currentIndex++];
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
