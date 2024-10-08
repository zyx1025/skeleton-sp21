package deque;

import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private int size;
    //哨兵，prev尾，next头
    private LinkNode pointer;

    public LinkedListDeque() {
        size = 0;
        pointer = new LinkNode(null, null, null);
    }

    public int size() {
        return size;
    }

    public void addFirst(T item) {
        LinkNode beforeFirst = pointer.prev;
        LinkNode beforeLast = pointer.next;
        LinkNode newFirst = new LinkNode(item, beforeLast, beforeFirst);
        if (pointer.prev == null) {
            pointer.next = newFirst;
        }
        pointer.prev = newFirst;
        if (beforeFirst != null) {
            beforeFirst.prev = newFirst;
        }
        if (beforeLast != null) {
            beforeLast.next = newFirst;
        }
        size++;
    }

    public void addLast(T item) {
        LinkNode beforeFirst = pointer.prev;
        LinkNode beforeLast = pointer.next;
        LinkNode newLast = new LinkNode(item, beforeLast, beforeFirst);
        if (pointer.prev == null) {
            pointer.prev = newLast;
        }
        pointer.next = newLast;
        if (beforeFirst != null) {
            beforeFirst.prev = newLast;
        }
        if (beforeLast != null) {
            beforeLast.next = newLast;
        }
        size++;
    }

    public T removeFirst() {
        if (pointer.prev == null) {
            return null;
        }
        LinkNode beforeFirst = pointer.prev;
        LinkNode beforeLast = pointer.next;
        size--;
        if (beforeFirst.next == null) {
            //即元素只有一个，beforeFirst/Last指向同一个结点，该结点prev/next为空
            pointer = new LinkNode(null, null, null);
            return beforeFirst.item;
        }
        beforeFirst.next.prev = beforeLast;
        pointer.prev = beforeFirst.next;
        beforeLast.next = beforeFirst.next;
        if (size == 1) {
            pointer.prev.prev = pointer.next.next = null;
        }
        return beforeFirst.item;
    }

    public T removeLast() {
        if (pointer.next == null) {
            return null;
        }
        LinkNode beforeFirst = pointer.prev;
        LinkNode beforeLast = pointer.next;
        size--;
        if (beforeLast.prev == null) {
            //即元素只有一个，beforeFirst/Last指向同一个结点，该结点prev/next为空
            pointer = new LinkNode(null, null, null);
            return beforeLast.item;
        }
        beforeLast.prev.next = beforeFirst;
        pointer.next = beforeLast.prev;
        beforeFirst.prev = beforeLast.prev;
        if (size == 1) {
            pointer.prev.prev = pointer.next.next = null;
        }
        return beforeLast.item;
    }

    public T get(int index) {
        LinkNode curr = pointer.prev;
        for (int i = 0; i < index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        return getRecursiveHelper(pointer.prev, index);
    }

    private T getRecursiveHelper(LinkNode current, int index) {
        if (index == 0) {
            return current.item;
        }
        return getRecursiveHelper(current.next, index - 1);
    }

    public void printDeque() {
        LinkNode node = pointer.prev;
        for (int i = 0; i < size; i++) {
            System.out.println(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private LinkNode currentNode = pointer.prev;
            private int idx = 0;

            @Override
            public boolean hasNext() {
                //循环一圈后回到头节点
                return idx != size;
            }

            @Override
            public T next() {
                T item = currentNode.item;
                idx++;
                currentNode = currentNode.next;
                return item;
            }
        };
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

    private class LinkNode {
        T item = null;
        LinkNode prev;
        LinkNode next;

        LinkNode(T item, LinkNode prev, LinkNode next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        LinkNode() {
        }
    }
}
