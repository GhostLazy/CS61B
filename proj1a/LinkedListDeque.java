public class LinkedListDeque<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        public Node(Node prev, T item, Node next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }

        public Node(Node prev, Node next) {
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        Node newList = new Node(sentinel, item, sentinel.next);
        sentinel.next.prev = newList;
        sentinel.next = newList;
        size += 1;
    }

    public void addLast(T item) {
        Node newList = new Node(sentinel.prev, item, sentinel);
        sentinel.prev.next = newList;
        sentinel.prev = newList;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node cur = sentinel.next;
        for (int i = 0; i < size; i++) {
            System.out.print(cur.item + " ");
            cur = cur.next;
        }
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T removed = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T removed = sentinel.prev.item;
        sentinel.prev.prev.next = sentinel;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return removed;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        Node cur = sentinel.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.item;
    }

    private T getRecursive(int index, Node p) {
        if (index == 0) {
            return p.item;
        }
        return getRecursive(index - 1, p.next);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }
        return getRecursive(index, sentinel.next);
    }
}
