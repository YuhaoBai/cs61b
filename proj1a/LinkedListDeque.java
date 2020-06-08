public class LinkedListDeque<T> {

    private class DequeNode {
        public T item;
        public DequeNode next;
        public DequeNode prev;

        public DequeNode(T i, DequeNode n, DequeNode p) {
            item = i;
            next = n;
            prev = p;
        }
    }

    private DequeNode sentinel;
    private int size;

    public LinkedListDeque() {
        size = 0;
        sentinel = new DequeNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
    }

    public void addFirst(T item) {
        size += 1;
        sentinel.next = new DequeNode(item, sentinel.next, sentinel);
        sentinel.next.next.prev = sentinel.next;
    }

    public void addLast(T item) {
        size += 1;
        sentinel.prev = new DequeNode(item, sentinel, sentinel.prev);
        sentinel.prev.prev.next = sentinel.prev;
    }

    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DequeNode ptr = sentinel.next;
        while (ptr != sentinel) {
            System.out.println(ptr.item + " ");
            ptr = ptr.next;
        }
    }

    public T removeFirst() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        T firstItem = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        return firstItem;
    }

    public T removeLast() {
        if (this.isEmpty()) {
            return null;
        }
        size -= 1;
        T lastItem = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return lastItem;
    }

    public T get(int index) {
        if(index > size - 1) {
            return null;
        }
        DequeNode ptr = sentinel.next;
        while(index > 0) {
            ptr = ptr.next;
            index -= 1;
        }
        return ptr.item;
    }

    public T getRecursive(int index) {
        if(index > size - 1) {
            return null;
        } else { return getRecursiveHelper(sentinel.next, index); }
    }

    private T getRecursiveHelper(DequeNode d, int i) {
        if (i == 0) {
            return d.item;
        } else { return getRecursiveHelper(d.next, i - 1); }
    }
}

