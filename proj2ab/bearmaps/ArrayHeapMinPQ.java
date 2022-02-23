package bearmaps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.NoSuchElementException;

public class ArrayHeapMinPQ<T> implements ExtrinsicMinPQ<T> {

    private ArrayList<PriorityNode> items;
    private HashMap<T, Integer> itemMapIndex;
    private int size;

    public ArrayHeapMinPQ() {
        items = new ArrayList<>();
        itemMapIndex = new HashMap<>();
        size = 0;
    }

    @Override
    public void add(T item, double priority) {
        if (contains(item)) {
            throw new IllegalArgumentException();
        }
        items.add(new PriorityNode(item, priority));
        size += 1;
        itemMapIndex.put(item, size - 1);
        swim(size-1);
    }

    @Override
    public boolean contains(T item) {
        return itemMapIndex.containsKey(item);
    }

    @Override
    public T getSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        return items.get(0).getItem();
    }

    @Override
    public T removeSmallest() {
        if (size == 0) {
            throw new NoSuchElementException("PQ is empty");
        }
        T min = items.get(0).getItem();
        Collections.swap(items,0, size-1);
        itemMapIndex.put(items.get(0).getItem(), 0);
        itemMapIndex.put(items.get(size-1).getItem(), size-1);
        items.remove(size-1);
        size -= 1;
        itemMapIndex.remove(min);
        sink(0);

        return min;
    }

    @Override
    public int size() {
        return size;
    }

    /*
    private int indOf(T elem) {
        return items.indexOf(new PriorityNode(elem, 0));
    }
    */

    @Override
    public void changePriority(T item, double priority) {
        if (!contains(item)) {
            throw new NoSuchElementException("PQ does not contain " + item);
        }
        //int idx = indOf(item);
        int idx = itemMapIndex.get(item);
        double oldPriority = items.get(idx).getPriority();
        items.get(idx).setPriority(priority);
        /*
        int i = parent(idx);

        int j = leftchild(idx);
        if(j<size-1 && items.get(j).compareTo(items.get(j+1)) > 0) j++;

        if(i>=0 && items.get(i).compareTo(items.get(idx)) > 0) {
            swim(idx);
        }else if(j<=size-1 && items.get(idx).compareTo(items.get(j)) > 0) {
            sink(idx);
        }
        */
        if (oldPriority < priority) {
            sink(idx);
        } else {
            swim(idx);
        }
    }

    /*递归
    private void swim(int k) {
        if(k == 0) {
            return;
        }
        if(items.get(parent(k)).compareTo(items.get(k)) > 0) {
            Collections.swap(items,k,parent(k));
            swim(parent(k));
        }
    }

    private void sink(int k) {
        if(leftchild(k) > size-1) {
            return;
        }
        int j = leftchild(k);
        if(j<size-1 && items.get(j).compareTo(items.get(j+1)) > 0) j++;
        if(items.get(k).compareTo(items.get(j)) <= 0) return;
        Collections.swap(items,k,j);
        sink(j);
    }
    */

    private void swim(int k) {
        while(k>0 && items.get(parent(k)).compareTo(items.get(k)) > 0) {
            Collections.swap(items,k,parent(k));
            itemMapIndex.put(items.get(k).getItem(), k);
            itemMapIndex.put(items.get(parent(k)).getItem(), parent(k));
            k = parent(k);
        }
    }

    private void sink(int k) {
        while(leftchild(k) <= size-1) {
            int j = leftchild(k);
            if(j<size-1 && items.get(j).compareTo(items.get(j+1)) > 0) j++;
            if(items.get(k).compareTo(items.get(j)) <= 0) break;
            Collections.swap(items,k,j);
            itemMapIndex.put(items.get(k).getItem(), k);
            itemMapIndex.put(items.get(j).getItem(), j);
            k = j;
        }
    }

    private int parent(int k) {
        return (k-1) / 2;
    }

    private int leftchild(int k) {
        return 2*k + 1;
    }

    private class PriorityNode implements Comparable<PriorityNode> {
        private T item;
        private double priority;

        PriorityNode(T e, double p) {
            this.item = e;
            this.priority = p;
        }

        T getItem() {
            return item;
        }

        double getPriority() {
            return priority;
        }

        void setPriority(double priority) {
            this.priority = priority;
        }

        @Override
        public int compareTo(PriorityNode o) {
            return Double.compare(this.getPriority(), o.getPriority());
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || o.getClass() != this.getClass()) {
                return false;
            } else {
                return ((PriorityNode) o).getItem().equals(this.getItem());
            }
        }

        @Override
        public int hashCode() {
            return item.hashCode();
        }
    }
}
