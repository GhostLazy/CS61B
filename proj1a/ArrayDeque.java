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

    public ArrayDeque(ArrayDeque other) {
        array = (T []) new Object[other.length];
        size = other.size;
        length = other.length;
        first = length;
        last = size;

        for (int i = 0; i < size; i++) {
            array[i] = (T) other.get(i);
        }
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
        T[] new_array = (T []) new Object[length * 2];
        System.arraycopy(array, 0, new_array, 0, last);
        System.arraycopy(array, last, new_array, last + length, length - last);
        first += length;
        length *= 2;
        array = new_array;
    }

    private void shrink() {
        T[] new_array = (T []) new Object[length / 2];
        for (int i = 0; i < size; i++) {
            new_array[i] = get(i);
        }
        length /= 2;
        first = length;
        last = size;
        array = new_array;
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
}
