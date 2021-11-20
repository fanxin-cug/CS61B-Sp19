public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dc = new ArrayDeque<>();
        for(int i=0; i<word.length(); i++){
            dc.addLast(word.charAt(i));
        }
        return dc;
    }
}
