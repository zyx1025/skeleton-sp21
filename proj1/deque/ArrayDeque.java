package deque;

import org.junit.Test;

public class ArrayDeque<Item> {
    private Item[] items;
    //元素个数上限
    private int limit;

    //当前数组元素个数
    private int size;
    private int first;
    private int last;
    public ArrayDeque() {
        items = (Item[]) new Object[8];
        limit = 8;
        size = 0;
        first = 3;
        last = 4;
    }

    private void resize(int capacity) {
        Item[] a = (Item[]) new Object[capacity];
        int idx = (first+1) % limit;
        for (int i = 0; i < limit; i += 1) {
            a[i] = items[idx];
            idx = (idx+1) % limit;
        }
        first = capacity-1;
        last = limit;
        limit = capacity;

        items = a;
    }

    public void addLast(Item x) {
        if (size == limit) {
            resize(size * 2);
        }

        items[last] = x;
        last = (last + 1) % limit;
        size++;
    }

    public void addFirst(Item x) {
        if (size == limit) {
            resize(size * 2);
        }

        items[first] = x;
        first = (first - 1 + limit) % limit;
        size++;
    }
    public Item getLast() {
        return items[(last-1+limit)%limit];
    }

    public Item getFirst(){
        return items[(first+1) %limit];
    }

    public Item removeFirst(){
        if(size==0){
            return null;
        }
        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 4);
        }
        first = (first+1) % limit;
        Item result = items[first];
        items[first] = null;
        size--;
        return result;
    }

    public Item get(int i) {
        return items[(first+1+i)%limit];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty(){
        return size==0;
    }

    public void printDeque(){
        int tempIdx = (first+1) % limit;
        for (int i = 0; i < size; i++) {
            System.out.println(items[tempIdx] + " ");
            tempIdx = (tempIdx+1) % limit;
        }
        System.out.println();
    }

    public Item removeLast() {
        if(size==0){
            return null;
        }
        if ((size < items.length / 4) && (size > 4)) {
            resize(items.length / 4);
        }
        last = (last-1+limit) % limit;
        Item result = items[last];
        items[last] = null;
        size--;
        return result;
    }
}
