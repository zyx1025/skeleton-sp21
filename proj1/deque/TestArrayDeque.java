package deque;

import org.junit.Test;

public class TestArrayDeque {

    @Test
    public void testArray() {
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        for (int i = 0; i < 256; i++) {
            arrayDeque.addLast(i);
        }

        for (int i = 0; i < 256; i++) {
            arrayDeque.removeFirst();
        }

        for (int i = 0; i < 256; i++) {
            arrayDeque.addLast(i);
        }

        for (Object o : arrayDeque) {
            System.out.println(o);
        }
    }
}
