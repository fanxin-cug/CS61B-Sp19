import edu.princeton.cs.algs4.Queue;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestSortAlgs {

    @Test
    public void testQuickSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        Queue<String> res = QuickSort.quickSort(tas);
        assertTrue(isSorted(res));

        Queue<Integer> num = new Queue<>();
        num.enqueue(1);
        num.enqueue(5);
        num.enqueue(4);
        num.enqueue(2);
        num.enqueue(3);
        Queue<Integer> ans = QuickSort.quickSort(num);
        assertTrue(isSorted(ans));
    }

    @Test
    public void testMergeSort() {
        Queue<String> tas = new Queue<String>();
        tas.enqueue("Joe");
        tas.enqueue("Omar");
        tas.enqueue("Itai");
        Queue<String> res = MergeSort.mergeSort(tas);
        assertTrue(isSorted(res));

        Queue<Integer> num = new Queue<>();
        num.enqueue(1);
        num.enqueue(5);
        num.enqueue(4);
        num.enqueue(2);
        num.enqueue(3);
        Queue<Integer> ans = MergeSort.mergeSort(num);
        assertTrue(isSorted(ans));
    }

    /**
     * Returns whether a Queue is sorted or not.
     *
     * @param items  A Queue of items
     * @return       true/false - whether "items" is sorted
     */
    private <Item extends Comparable> boolean isSorted(Queue<Item> items) {
        if (items.size() <= 1) {
            return true;
        }
        Item curr = items.dequeue();
        Item prev = curr;
        while (!items.isEmpty()) {
            prev = curr;
            curr = items.dequeue();
            if (curr.compareTo(prev) < 0) {
                return false;
            }
        }
        return true;
    }
}
