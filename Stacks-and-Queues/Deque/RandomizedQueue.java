import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private Item[] q;
    private int n;
    private int first;
    private int last;
    public RandomizedQueue() {
        q = (Item[]) new Object[2];
        n = 0;
        first = 0;
        last = 0;
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }

    private void resize(int capacity) {
        assert capacity >= n;
        Item[] tmp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            tmp[i] = q[(first+i) % q.length];
        }
        q = tmp;
        first = 0;
        last = n;
    }

    public void enqueue(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException("no null item.");
        if (n == q.length) resize(2*q.length);
        q[last++] = item;
        if (last == q.length) last = 0;
        n++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException("queue is empty.");
        int num = StdRandom.uniform(n);
        int index = (first + num) % q.length;
        Item tmp = q[index];
        q[index] = q[first];
        q[first] = null;
        first++;
        if (first == q.length) first = 0;
        n--;
        // shrink array if necessary
        if (n > 0 && n == q.length/4) resize(q.length/2);
        return tmp;
    }

    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException("queue is empty.");
        int num = StdRandom.uniform(n);
        int index = (first + num) % q.length;
        return q[index];
    }

    @Override
    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator implements Iterator<Item> {
        private int index;
        private Item[] iteratorItems;
        public RandomizedQueueIterator() {
            iteratorItems = (Item[]) new Object[n];
            for (int i = 0; i < n; i++) {
                iteratorItems[i] = q[(first+i) % q.length];
            }
            StdRandom.shuffle(iteratorItems);
        }

        @Override
        public boolean hasNext() {
            return index < iteratorItems.length;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("no next item");
            return iteratorItems[index++];
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Unsupported remove operation");
        }
    }
    public static void main(String[] args) {

    }
}