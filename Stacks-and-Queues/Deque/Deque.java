import java.util.Iterator;

public class Deque<Item> implements Iterable<Item> {
    private Node head;
    private Node tail;
    private int n;

    private class Node {
        private Item item;
        private Node next;
        private Node prev;
    }
    public Deque() {
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.prev = head;
    }
    public boolean isEmpty() {
        return n == 0;
    }
    public int size() {
        return n;
    }
    public void addFirst(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException("No null Item");
        Node x = new Node();
        x.item = item;
        Node after = head.next;
        head.next = x;
        x.prev = head;
        x.next = after;
        after.prev = x;
        n++;
    }
    public void addLast(Item item) {
        if (item == null) throw new java.lang.IllegalArgumentException("No null Item");
        Node x = new Node();
        x.item = item;
        Node before = tail.prev;
        before.next = x;
        x.prev = before;
        x.next = tail;
        tail.prev = x;
        n++;
    }
    public Item removeFirst() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque is empty");
        Node toRemove = head.next;
        head.next = toRemove.next;
        toRemove.next.prev = head;
        toRemove.prev = null;
        toRemove.next = null;
        n--;
        return toRemove.item;
    }
    public Item removeLast() {
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque is empty");
        Node toRemove = tail.prev;
        toRemove.prev.next = tail;
        tail.prev = toRemove.prev;
        toRemove.prev = null;
        toRemove.next = null;
        n--;
        return toRemove.item;
    }

    @Override
    public Iterator<Item> iterator() {
        return new DequeIterator();
    }

    private class DequeIterator implements Iterator<Item> {
        private Node cur = head.next;

        @Override
        public boolean hasNext() {
            return cur != tail;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException("no next item");
            Item item = cur.item;
            cur = cur.next;
            return item;
        }

        @Override
        public void remove() {
            throw new java.lang.UnsupportedOperationException("Unsupported remove operation");
        }
    }

    public static void main(String[] args) {
        Deque<String> deque = new Deque<>();
        for (int i = 0; i < 10; i++) {
            deque.addFirst(i+"");
        }
        Iterator<String> iterator = deque.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }

}