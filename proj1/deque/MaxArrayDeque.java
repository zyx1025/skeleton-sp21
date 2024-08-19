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
        T result = get(0);
        int limit = size();
        for (int i = 0; i < limit; i++) {
            if (comparator.compare(get(i), result) > 0) {
                result = get(i);
            }
        }
        return result;
    }

    public T max(Comparator<T> c) {
        if (size() == 0) {
            return null;
        }
        T result = get(0);
        int limit = size();
        for (int i = 0; i < limit; i++) {
            if (c.compare(get(i), result) > 0) {
                result = get(i);
            }
        }
        return result;
    }
}
