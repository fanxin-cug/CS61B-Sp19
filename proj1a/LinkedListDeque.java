public class LinkedListDeque<T> {

    public class DequeNode {
        public T item;
        public DequeNode prev;
        public DequeNode next;
        public DequeNode(T i, DequeNode p, DequeNode n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private DequeNode sentinel;
    private DequeNode first;
    private DequeNode last;
    private int size;

    public LinkedListDeque() {
        sentinel = new DequeNode(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        first = sentinel;
        last = sentinel;
        size = 0;
    }

    public LinkedListDeque(LinkedListDeque other) {
        this();
        for(int i=0; i<other.size(); i+=1){
            addLast((T) other.get(i));
        }
    }

    public void addFirst(T item) {
        DequeNode temp = first;
        first = new DequeNode(item, null, null);
        sentinel.next = first;
        first.prev = sentinel;
        first.next = temp;
        temp.prev = first;
        if(last == sentinel) {
            last = first;
        }
        size += 1;
    }

    public void addLast(T item) {
        DequeNode temp = last;
        last = new DequeNode(item, null, null);
        temp.next = last;
        last.prev = temp;
        last.next = sentinel;
        sentinel.prev = last;
        if(first == sentinel) {
            first = last;
        }
        size += 1;
    }

    public boolean isEmpty() {
        if(size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        DequeNode p = first;
        for(int i=0; i<size-1; i+=1) {
            System.out.print(p.item+" ");
            p = p.next;
        }
        System.out.println(p.item);
    }

    public T removeFirst() {
        if(size==0){
            return null;
        }
        T res = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;
        first = first.next;
        if(first == sentinel) {
            last = sentinel;
        }
        size-=1;
        return res;
    }

    public T removeLast() {
        if(size==0){
            return null;
        }
        T res = last.item;
        last.prev.next = last.next;
        last.next.prev = last.prev;
        last = last.prev;
        if(last == sentinel) {
            first = sentinel;
        }
        size-=1;
        return res;
    }

    public T get(int index) {
        if(index<0 || index>=size) {
            return null;
        }else if(index==0) {
            return first.item;
        }else if(index==size-1) {
            return last.item;
        }
        DequeNode p=first;
        for(int i=0; i<index; i+=1) {
            p=p.next;
        }
        T res=p.item;
        return res;
    }

    public T getRecursive(int index) {
        if(index<0 || index>=size) {
            return null;
        }
        return getRecursiveHelper(index, first);
    }

    private T getRecursiveHelper(int index, DequeNode n){
        if(index==0){
            return n.item;
        }
        return getRecursiveHelper(index-1, n.next);
    }

    public static void main(String[] args){
        //test
        LinkedListDeque<Integer> lld = new LinkedListDeque<>();
        lld.addLast(3);
        lld.addFirst(2);
        lld.addFirst(1);
        lld.printDeque();
        System.out.println(lld.removeFirst());
        lld.printDeque();
        System.out.println(lld.removeLast());
        lld.printDeque();
        lld.removeFirst();
        //lld.removeLast();
        System.out.println(lld.isEmpty());
        lld.printDeque();
        lld.addFirst(2);
        lld.addFirst(1);
        lld.addLast(3);
        lld.printDeque();
        System.out.println(lld.get(2));
        System.out.println(lld.getRecursive(2));
        LinkedListDeque<Integer> lld2 = new LinkedListDeque<>(lld);
        lld2.printDeque();
    }
}