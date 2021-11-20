public class ArrayDeque<T> implements Deque<T>{

    private T[] items;
    private int front;
    private int tail;
    private int size;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        front = 4;
        tail = 5;
        size = 0;
    }

    public ArrayDeque(ArrayDeque other) {
        this();
        for(int i=0; i<other.size(); i+=1){
            addLast((T) other.get(i));
        }
    }

    private int minusOne(int index) {
        int next;
        if(index == 0) {
            next = items.length-1;
        }else {
            next = index-1;
        }
        return next;
    }

    private int plusOne(int index) {
        int next;
        if(index == items.length-1) {
            next = 0;
        }else {
            next = index+1;
        }
        return next;
    }

    private void expand(int firstindex, int lastindex){
        T[] newitems = (T[]) new Object[items.length*2];
        if(firstindex>lastindex) {
            int start1=firstindex;
            int end1=items.length-1;
            int num1=end1-start1+1;
            System.arraycopy(items,start1,newitems,items.length/2, num1);
            int start2=0;
            int end2=lastindex;
            int num2=end2-start2+1;
            System.arraycopy(items,start2,newitems,items.length/2+num1, num2);
        }else{
            //firstindex=0 lastindex=items.length-1
            System.arraycopy(items,firstindex,newitems,items.length/2, items.length);
        }
        front = minusOne(items.length/2);
        tail = plusOne(front+size); //plusOne(items.length/2+size-1)
        items = newitems;
    }

    @Override
    public void addFirst(T item) {
        items[front] = item;
        size += 1;
        if(size==items.length) {
            int first=front;
            int last=minusOne(tail);
            expand(first,last);
        }else{
            front = minusOne(front);
        }
    }

    @Override
    public void addLast(T item) {
        items[tail] = item;
        size += 1;
        if(size==items.length) {
            int first=plusOne(front);
            int last=tail;
            expand(first, last);
        }else{
            tail = plusOne(tail);
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void shrink(int firstindex, int lastindex) {
        T[] newitems = (T[]) new Object[items.length/2];
        if(firstindex<lastindex){
            System.arraycopy(items,firstindex,newitems,items.length/8, size);
        }else{
            int start1=firstindex;
            int end1=items.length-1;
            int num1=end1-start1+1;
            System.arraycopy(items,start1,newitems,items.length/8, num1);
            int start2=0;
            int end2=lastindex;
            int num2=end2-start2+1;
            System.arraycopy(items,start2,newitems,items.length/8+num1, num2);
        }
        front = minusOne(items.length/8);
        tail = plusOne(front+size); //plusOne(newlength/4+size-1)
        items = newitems;
    }

    @Override
    public T removeFirst() {
        if(size == 0){
            return null;
        }
        front = plusOne(front);
        T res = items[front]; //fix bug
        size -= 1;
        if(size*4 == items.length && items.length>8) {
            int first=plusOne(front);
            int last=minusOne(tail);
            shrink(first, last);
        }
        return res;
    }

    @Override
    public T removeLast() {
        if(size == 0){
            return null;
        }
        tail = minusOne(tail);
        T res = items[tail]; //fix bug
        size -= 1;
        if(size*4 == items.length && items.length>8) {
            int first=plusOne(front);
            int last=minusOne(tail);
            shrink(first, last);
        }
        return res;
    }

    @Override
    public T get(int index) {
        if(index<0 || index>=size) {
            return null;
        }
        int i=plusOne(front);
        i += index;
        if(i >= items.length) {
            i -= items.length;
        }
        return items[i];
    }
}
