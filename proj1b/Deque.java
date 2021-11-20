public interface Deque<T> {
    public void addFirst(T item);
    public void addLast(T item);
    default public boolean isEmpty(){
        if(size() == 0) {
            return true;
        }
        return false;
    }
    public int size();
    default public void printDeque() {
        int i;
        for(i=0; i<size()-1; i+=1) {
            System.out.print(get(i)+" ");
        }
        System.out.println(get(i));
    }
    public T removeFirst();
    public T removeLast();
    public T get(int index);
}
