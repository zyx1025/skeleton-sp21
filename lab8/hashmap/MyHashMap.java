package hashmap;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {
    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    //can be instantated by many different types of data structures,e.g. LinkedList<Node> or ArrayList<Node>
    private Collection<Node>[] buckets= new Collection[16];

    private int numberOfElement;

    private int sizeOfBucket = 16;

    //
    private double loadFactor = 0.75;

    private HashSet<K> keySet = new HashSet<>();

    /** Constructors */
    public MyHashMap() {}

    public MyHashMap(int initialSize) {
        buckets = new Collection[initialSize];
        sizeOfBucket = initialSize;
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        buckets = new Collection[initialSize];
        sizeOfBucket = initialSize;
        loadFactor = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key,value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     */
    protected Collection<Node> createBucket(){
        return new ArrayList<>();
    };

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    @Override
    public void clear() {
        buckets = new Collection[16];
        sizeOfBucket = 16;
        numberOfElement = 0;
        keySet.clear();
    }

    @Override
    public boolean containsKey(K key) {
        return keySet.contains(key);
    }

    @Override
    public V get(K key) {
        if(!keySet.contains(key)){
            return null;
        }
        int idx = getIdx(key,sizeOfBucket);
        for (Node item : buckets[idx]){
            if(item.key.equals(key)){
                return item.value;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return numberOfElement;
    }

    @Override
    public void put(K key, V value) {
        if (keySet.contains(key)){
            int idx = getIdx(key,sizeOfBucket);
            for (Node item : buckets[idx]){
                if(item.key.equals(key)){
                    item.value = value;
                }
            }
            return;
        }
        //add new key
        numberOfElement ++;
        if((double)numberOfElement/sizeOfBucket>loadFactor){
            resize(2*sizeOfBucket);
        }
        keySet.add(key);

        int idx = getIdx(key,sizeOfBucket);
        if (buckets[idx] == null){
            buckets[idx] = createBucket();
        }
        buckets[idx].add(createNode(key, value));
    }

    private int getIdx(K key,int limit) {
        return Math.floorMod(key.hashCode(),limit);
    }

    @Override
    public Set<K> keySet() {
        return keySet;
    }

    @Override
    public V remove(K key) {
        if (!keySet.contains(key)){
            return null;
        }
        numberOfElement--;
        int idx = getIdx(key,sizeOfBucket);
        V result = get(key);
        keySet.remove(key);
        buckets[idx].remove(key);
        return result;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }

    private void resize(int newSize){
        Collection<Node>[] newBuckets = new Collection[newSize];
        //不考虑size down的情况
        for (int i = 0; i < sizeOfBucket; i++) {
            if (buckets[i] == null){
                continue;
            }
            for (Node node:buckets[i]){
                int newIdx = getIdx(node.key,newSize);
                if (newBuckets[newIdx] == null){
                    newBuckets[newIdx] = createBucket();
                }
                newBuckets[newIdx].add(node);
            }
        }
        buckets = newBuckets;
        sizeOfBucket = newSize;
    }

}
