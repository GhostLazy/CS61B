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
    private Node last;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        last = sentinel;
        size = 0;
    }

    public void addFirst(T item) {
        sentinel.next = new Node(sentinel, item, sentinel.next);
        size += 1;
    }

    public void addLast(T item) {
        last.next = new Node(last, item, sentinel);
        last = last.next;
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
        int cnt = 0;
        while (cnt < size) {
            System.out.print(cur.item + " ");
            cur = cur.next;
            cnt += 1;
        }
    }

    public T removeFirst() {
        T removed = sentinel.next.item;
        sentinel.next.next.prev = sentinel;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return removed;
    }

    public T removeLast() {
        T removed = last.item;
        last = last.prev;
        last.next = sentinel;
        size -= 1;
        return removed;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        T res = sentinel.next.item;
        int cnt = 0;
        Node cur = sentinel.next;
        while (cur != null) {
            res = cur.item;
            cur = cur.next;
            if (cnt == index) {
                break;
            }
            cnt += 1;
        }
        return res;
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
