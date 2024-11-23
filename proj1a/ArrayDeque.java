public class ArrayDeque<T> {
    private T[] array;
    private int size;
    private int length;
    private int first;
    private int last;

    public ArrayDeque() {
        array = (T []) new Object[8];
        size = 0;
        length = 8;
        first = 4;
        last = 5;
    }

    private int minusOne(int x) {
        int temp = x - 1;
        if (temp >= 0) {
            return temp;
        }
        return temp + length;
    }

    private int plusOne(int x) {
        int temp = x + 1;
        if (temp < length) {
            return temp;
        }
        return temp - length;
    }

    private void grow() {
        T[] newArray = (T []) new Object[length * 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        length *= 2;
        first = length - 1;
        last = size;
        array = newArray;
    }

    private void shrink() {
        T[] newArray = (T []) new Object[length / 2];
        for (int i = 0; i < size; i++) {
            newArray[i] = get(i);
        }
        length /= 2;
        first = length - 1;
        last = size;
        array = newArray;
    }

    public void addFirst(T item) {
        if (size == length) {
            grow();
        }
        array[first] = item;
        first = minusOne(first);
        size += 1;
    }

    public void addLast(T item) {
        if (size == length) {
            grow();
        }
        array[last] = item;
        last = plusOne(last);
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        first = plusOne(first);
        T removed = array[first];
        array[first] = null;
        size -= 1;
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        return removed;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        last = minusOne(last);
        T removed = array[last];
        array[last] = null;
        size -= 1;
        if (length >= 16 && length / size >= 4) {
            shrink();
        }
        return removed;
    }

    public T get(int index) {
        if (index >= size) {
            return null;
        }
        return array[(first + index + 1) % length];
    }

    public void printDeque() {
        for (int i = 0; i < size; i++) {
            System.out.print(get(i) + " ");
        }
    }
}
