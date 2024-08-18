package deque;

public class LinkedListDeque<T> {
    class LinkNode{
        T item = null;
        LinkNode prev;
        LinkNode next;
        LinkNode(T item, LinkNode prev,LinkNode next){
            this.item = item;
            this.prev = prev;
            this.next = next;
        }

        LinkNode(){}
    }
    private int size;
    //哨兵，prev尾，next头
    private LinkNode pointer;

    public LinkedListDeque(){
        size = 0;
        pointer = new LinkNode(null,null,null);
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void addFirst(T item){
        LinkNode beforeFirst = pointer.prev;
        LinkNode beforeLast = pointer.next;
        LinkNode newFirst = new LinkNode(item,beforeLast,beforeFirst);
        if(pointer.prev == null){
            pointer.next = newFirst;
        }
        pointer.prev = newFirst;
        if(beforeFirst != null){
            beforeFirst.prev = newFirst;
        }
        if(beforeLast != null){
            beforeLast.next = newFirst;
        }
        size++;
    }

    public void addLast(T item){
        LinkNode beforeFirst = pointer.prev;
        LinkNode beforeLast = pointer.next;
        LinkNode newLast = new LinkNode(item,beforeLast,beforeFirst);
        if(pointer.prev == null){
            pointer.prev = newLast;
        }
        pointer.next = newLast;
        if(beforeFirst != null){
            beforeFirst.prev = newLast;
        }
        if(beforeLast != null){
            beforeLast.next = newLast;
        }
        size++;
    }

    public T removeFirst(){
        if(pointer.prev == null){
            return null;
        }
        LinkNode beforeFirst = pointer.prev;
        LinkNode beforeLast = pointer.next;

        if(beforeFirst.next == null){
            size = 0;
            pointer = new LinkNode(null,null,null);
            return beforeFirst.item;
        }else{
            beforeFirst.next.prev = beforeLast;
        }

        pointer.prev = beforeFirst.next;
        beforeLast.next = beforeFirst.next;

        size--;
        return beforeFirst.item;
    }

    public T removeLast(){
        if(pointer.next == null){
            return null;
        }
        LinkNode beforeFirst = pointer.prev;
        LinkNode beforeLast = pointer.next;

        if(beforeLast.prev == null){
            //即元素只有一个，beforeFirst/Last指向同一个结点，该结点prev/next为空
            size = 0;
            pointer = new LinkNode(null,null,null);
            return beforeLast.item;
        }else{
            beforeLast.prev.next = beforeFirst;
        }

        pointer.next = beforeLast.prev;
        beforeFirst.prev = beforeLast.prev;

        size--;
        return beforeLast.item;
    }

    public void printDeque(){
        LinkNode node = pointer.prev;
        for (int i = 0; i < size; i++) {
            System.out.println(node.item + " ");
            node = node.next;
        }
        System.out.println();
    }

}