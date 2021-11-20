public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> dc = new ArrayDeque<>();
        for(int i=0; i<word.length(); i++){
            dc.addLast(word.charAt(i));
        }
        return dc;
    }

    public boolean isPalindrome(String word) {
        Deque d=wordToDeque(word);
        return isPalindromeHelper(d);
    }

    private boolean isPalindromeHelper(Deque d) {
        int s=d.size();
        if(s==1 || s==0){
            return true;
        }
        if(d.removeFirst()==d.removeLast()){
            return isPalindromeHelper(d);
        }
        return false;
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque d=wordToDeque(word);
        return isPalindromeHelper2(d, cc);
    }

    private boolean isPalindromeHelper2(Deque d, CharacterComparator cc) {
        int s=d.size();
        if(s==1 || s==0){
            return true;
        }
        if(cc.equalChars((char) d.removeFirst(), (char) d.removeLast())){
            return isPalindromeHelper2(d, cc);
        }
        return false;
    }
}
