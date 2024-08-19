package deque;

import java.util.Iterator;
import java.util.function.Consumer;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    protected T[] items;
    //当前数组元素个数
    protected int size;
    //元素个数上限
    private int limit;
    private int first;
    private int last;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        limit = 8;
        size = 0;
        first = 3;
        last = 4;
    }

    public ArrayDeque(int initialSize) {
        items = (T[]) new Object[initialSize];
        limit = initialSize;
        size = 0;
        first = 3;
        last = 4;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int idx = (first + 1) % limit;
        int bound = Math.min(capacity, limit);
        for (int i = 0; i < bound; i += 1) {
            a[i] = items[idx];
            idx = (idx + 1) % limit;
        }
        first = capacity - 1;
        last = bound;
        limit = capacity;

        items = a;
    }

    public void addLast(T x) {
        if (size == limit) {
            resize(size * 2);
        }

        items[last] = x;
        last = (last + 1) % limit;
        size++;
    }

    public void addFirst(T x) {
        if (size == limit) {
            resize(size * 2);
        }

        items[first] = x;
        first = (first - 1 + limit) % limit;
        size++;
    }

    public T getLast() {
        return items[(last - 1 + limit) % limit];
    }

    public T getFirst() {
        return items[(first + 1) % limit];
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 4);
        }
        first = (first + 1) % limit;
        T result = items[first];
        items[first] = null;
        size--;
        return result;
    }

    public T get(int i) {
        return items[(first + 1 + i) % limit];
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int tempIdx = (first + 1) % limit;
        for (int i = 0; i < size; i++) {
            System.out.println(items[tempIdx] + " ");
            tempIdx = (tempIdx + 1) % limit;
        }
        System.out.println();
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 4);
        }
        last = (last - 1 + limit) % limit;
        T result = items[last];
        items[last] = null;
        size--;
        return result;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            int currentIdx = 0;

            @Override
            public boolean hasNext() {
                return currentIdx != size;
            }

            @Override
            public T next() {
                T cur = items[(first + currentIdx + 1) % limit];
                currentIdx++;
                return cur;
            }
        };
    }

    @Override
    public void forEach(Consumer<? super T> action) {
        Iterable.super.forEach(action);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }
        if (((Deque<?>) o).size() != this.size()) {
            return false;
        }
        int size1 = this.size();
        for (int i = 0; i < size1; i++) {
            if (!((Deque<?>) o).get(i).equals(this.get(i))) {
                return false;
            }
        }
        return true;
    }
}
