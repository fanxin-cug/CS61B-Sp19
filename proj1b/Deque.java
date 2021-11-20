public interface Deque<T> {
    void addFirst(T item);
    void addLast(T item);
    default boolean isEmpty(){
        if(size() == 0) {
            return true;
        }
        return false;
    }
    int size();
    default void printDeque() {
        int i;
        for(i=0; i<size()-1; i+=1) {
            System.out.print(get(i)+" ");
        }
        System.out.println(get(i));
    }
    T removeFirst();
    T removeLast();
    T get(int index);
}
