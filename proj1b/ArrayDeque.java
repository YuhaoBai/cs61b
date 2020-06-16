public class ArrayDeque<T> implements Deque<T> {

    private T[] items;
    private int size;
    private int front;
    private int back;
    private static final int INIT_CAPACITY = 8;
    private static final int RFACTOR = 2;
    private static final double MIN_USAGE_RRATIO = 0.25;

    public ArrayDeque() {
        items = (T[]) new Object[INIT_CAPACITY];
        back = 5;
        front = 4;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        if (checkFull()) {
            resize(items.length * RFACTOR);
        }
        items[front] = item;
        front = minusOne(front);
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (checkFull()) {
            resize(items.length * RFACTOR);
        }
        items[back] = item;
        back = plusOne(back);
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        } else { return false;}
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index = 0;
        int i = plusOne(front);
        while (index < size()) {
            System.out.println(items[i] + " ");
            i = plusOne(i);
            index += 1;
        }
    }

    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }
        T first = items[plusOne(front)];
        items[plusOne(front)] = null;
        front = plusOne(front);
        size -= 1;
        if (lowUR()) {
            resize(items.length / RFACTOR);
        }
        return first;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }
        T last = items[minusOne(back)];
        items[minusOne(back)] = null;
        back = minusOne(back);
        size -= 1;
        if (lowUR()) {
            resize(items.length / RFACTOR);
        }
        return last;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int i = plusOne(front);
        while (index > 0) {
            i = plusOne(i);
            index--;
        }
        T element = items[i];
        return element;
    }


    private int minusOne(int index) {
        return (index - 1 + items.length ) % items.length;
    }

    private int plusOne(int index) {
        return (index + 1) % items.length;
    }

    private boolean checkFull() {
        if (size == items.length) {
            return true;
        } else { return false; }
    }

    private boolean lowUR() {
        if (items.length == 8) {
            return false;
        } else {
            if ((double)size / (double)items.length < MIN_USAGE_RRATIO) {
                return true;
            } else {
                return false;
            }
        }
    }

    private void printArray() {
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
    }

    private void resize(int capacity) {
        T[] array = (T[]) new Object[capacity];

        int i = plusOne(front);
        for (int j = 0; j < size; j++) {
            array[j] = items[i];
            i = plusOne(i);
        }
        items = array;
        front = capacity - 1;
        back = size();
    }
}
