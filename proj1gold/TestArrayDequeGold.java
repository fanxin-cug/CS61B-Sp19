import static org.junit.Assert.*;
import org.junit.Test;

public class TestArrayDequeGold {

    @Test
    public void test() {
        StudentArrayDeque<Integer> sad=new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> ads=new ArrayDequeSolution<>();

        ArrayDequeSolution<String> msg=new ArrayDequeSolution<>();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                sad.addLast(i);
                msg.addLast("addLast("+i+")\n");
                ads.addLast(i);
            } else {
                sad.addFirst(i);
                msg.addLast("addFirst("+i+")\n");
                ads.addFirst(i);
            }
        }

        //System.out.println(ads);
        //sad.printDeque();

        for (int i = 0; i < 10; i += 1) {
            double numberBetweenZeroAndOne = StdRandom.uniform();

            if (numberBetweenZeroAndOne < 0.5) {
                Integer expected=ads.removeFirst();
                Integer actual=sad.removeFirst();
                msg.addLast("removeFirst()\n");
                String s="";
                for(int j=0;j<msg.size();j++){
                    s+=msg.get(j);
                }
                assertEquals(s,expected,actual);
            } else {
                Integer expected=ads.removeLast();
                Integer actual=sad.removeLast();
                msg.addLast("removeLast()\n");
                String s="";
                for(int j=0;j<msg.size();j++){
                    s+=msg.get(j);
                }
                assertEquals(s,expected,actual);
            }
        }
    }
}
