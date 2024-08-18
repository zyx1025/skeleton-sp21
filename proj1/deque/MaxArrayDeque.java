package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comparator;

    public MaxArrayDeque(Comparator<T> c) {
        this.comparator = c;
    }

    public T max() {
        if (this.size() == 0) {
            return null;
        }
        T result = getFirst();
        for (T element : items) {
            if (comparator.compare(element, result) > 0) {
                result = element;
            }
        }
        return result;
    }

    public T max(Comparator<T> c) {
        if (this.size == 0) {
            return null;
        }
        T result = getFirst();
        for (T element : items) {
            if (c.compare(element, result) > 0) {
                result = element;
            }
        }
        return result;
    }
}
