package bstmap;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>,V> implements Map61B<K,V>{
    private BSTNode root = null;
    private HashSet<K> keySet = new HashSet<>();
    private int size = 0;
    private class BSTNode{
        private K key;
        private V value;

        public BSTNode left;
        public BSTNode right;

        private BSTNode parent;

        BSTNode(K key,V value,BSTNode left,BSTNode right){
            this.key = key;
            this.value = value;
            this.left = left;
            this.right = right;
        }

        public void setParent(BSTNode parent) {
            this.parent = parent;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }
    @Override
    public void clear() {
        root = null;
        size = 0;
        keySet = new HashSet<>();
    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        BSTNode keyNode = root;
        while (keyNode!=null){
            int delta = key.compareTo(keyNode.key);
            if(delta==0){
                return keyNode.value;
            }
            if(delta>0){
                keyNode = keyNode.right;
            }else{
                keyNode = keyNode.left;
            }
        }
        //不存在该key对应的点
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        root = putNode(root,key,value,null);
        keySet.add(key);
        size ++;
    }

    private BSTNode putNode(BSTNode node,K key,V value,BSTNode parent){
        if(node == null){
            BSTNode bstNode = new BSTNode(key,value,null,null);
            bstNode.setParent(parent);
            return bstNode;
        }
        if(node.key.compareTo(key)<0){
            node.right = putNode(node.right,key,value,node);
        }else{
            node.left = putNode(node.left,key,value,node);
        }
        return node;
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    //不必实现
    @Override
    public V remove(K key) {
        removedValue = null;
        if(keySet.contains(key)){
            keySet.remove(key);
            size--;
            root = removeNode(root,key);
        }
        return removedValue;
    }

    private V removedValue;
    private BSTNode removeNode(BSTNode node,K key){
        if (node!=null){
            int delta = key.compareTo(node.key);
            if(delta==0){
                removedValue = node.value;
                return deleteNode(node);
            }
            if(delta>0){
                node.right = removeNode(node.right,key);
            }else{
                node.left = removeNode(node.left,key);
            }
        }
        return node;
    }

    private BSTNode deleteNode(BSTNode node){
        //no children
        if(node.left == null && node.right == null){
            return null;
        }
        //one children
        if(node.left == null || node.right == null){
            return node.left!=null?node.left:node.right;
        }
        //two children
        BSTNode predecessor = node.left;
        while (predecessor.right!=null){
            predecessor = predecessor.right;
        }
        node.setKey(predecessor.key);
        node.setValue(predecessor.value);
        if(predecessor.left != null){
            predecessor.left.parent = null;
        }
        if(predecessor == node.left){
            predecessor.parent.left = predecessor.left;
        }else{
            predecessor.parent.right = predecessor.left;
        }
        return node;
    }
    //不必实现
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    //不必实现
    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    //prints out your BSTMap in order of increasing Key
    public void printInOrder(){

    }
}
