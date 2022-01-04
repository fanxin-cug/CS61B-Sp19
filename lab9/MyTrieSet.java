import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyTrieSet implements TrieSet61B {

    private Node root;    // root of trie

    private static class Node {
        private char ch;
        private boolean isKey;
        private HashMap<Character, Node> map;

        private Node(char c, boolean blue) {
            ch = c;
            isKey = blue;
            map = new HashMap<>();
        }
    }

    public MyTrieSet() {
        root = new Node('^', false);
    }

    @Override
    public void clear() {
        root.map.clear();
    }

    @Override
    public boolean contains(String key) {
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                return false;
            }
            curr = curr.map.get(c);
        }
        return curr.isKey;
    }

    @Override
    public void add(String key) {
        if (key == null || key.length() < 1) {
            return;
        }
        Node curr = root;
        for (int i = 0, n = key.length(); i < n; i++) {
            char c = key.charAt(i);
            if (!curr.map.containsKey(c)) {
                curr.map.put(c, new Node(c, false));
            }
            curr = curr.map.get(c);
        }
        curr.isKey = true;
    }

    @Override
    public List<String> keysWithPrefix(String prefix) {
        Node curr = root;
        for (int i = 0, n = prefix.length(); i < n; i++) {
            char c = prefix.charAt(i);
            if (!curr.map.containsKey(c)) {
                return null;
            }
            curr = curr.map.get(c);
        }
        List<String> lst = new ArrayList<>();
        collect(curr,prefix,lst);
        return lst;
    }

    private void collect(Node x, String pre, List<String> lst) {
        if(x.isKey) {
            lst.add(pre);
        }
        for(char c : x.map.keySet()) {
            collect(x.map.get(c),pre+c,lst);
        }
    }

    @Override
    public String longestPrefixOf(String key) {
        throw new UnsupportedOperationException();
    }
}
