import java.util.*;

public class MyHashMap<K,V> implements Map61B<K,V> {

    private int size;

    private int bucketSize;
    private double loadFactor;
    private LinkedList<Node>[] st;

    private class Node {
        K key;
        V val;
        Node(K k, V v) {
            key = k;
            val = v;
        }
    }

    public MyHashMap() {
        this(16,0.75);
    }


    public MyHashMap(int initialSize) {
        this(initialSize, 0.75);
    }

    public MyHashMap(int initialSize, double loadFactor) {
        this.bucketSize = initialSize;
        this.loadFactor = loadFactor;
        st = (LinkedList<Node>[]) new LinkedList[bucketSize];
        for(int i=0; i<bucketSize; i++) {
            st[i] = new LinkedList<>();
        }
    }

    private int hash(K key) {
        return (key.hashCode() & 0x7fffffff) % bucketSize;
    }

    @Override
    public void clear() {
        size = 0;
        for(int i=0; i<bucketSize; i++) {
            st[i].clear();
        }
    }

    @Override
    public boolean containsKey(K key) {
        int id = hash(key);
        for(Node node : st[id]) {
            if(key.equals(node.key)){
                return true;
            }
        }
        return false;
    }

    @Override
    public V get(K key) {
        int id = hash(key);
        for(Node node : st[id]) {
            if(key.equals(node.key)){
                return node.val;
            }
        }
        return null;
    }

    @Override
    public int size() {
        return size;
    }

    private void resize() {
        ArrayList<Node> al = new ArrayList<>();
        for(int i=0; i<bucketSize; i++) {
            for(Node node : st[i]) {
                al.add(node);
            }
        }
        clear();
        bucketSize*=2;
        st = (LinkedList<Node>[]) new LinkedList[bucketSize];
        for(int i=0; i<bucketSize; i++) {
            st[i] = new LinkedList<>();
        }
        for(Node node : al) {
            put(node.key, node.val);
        }
    }

    @Override
    public void put(K key, V value) {
        int id = hash(key);
        for(Node node : st[id]) {
            if(node.key == key){
                node.val = value;
                return;
            }
        }
        st[id].add(new Node(key,value));
        size += 1;
        //resize
        if(size > loadFactor * bucketSize) {
            resize();
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> s = new HashSet<>();
        for(int i=0; i<bucketSize; i++) {
            for(Node node : st[i]) {
                s.add(node.key);
            }
        }
        return s;
    }

    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIter();
    }

    private class HashMapIter implements Iterator<K> {

        private Iterator<K> iter;

        HashMapIter() {
            Set<K> s = keySet();
            iter = s.iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public K next() {
            return iter.next();
        }
    }
}
