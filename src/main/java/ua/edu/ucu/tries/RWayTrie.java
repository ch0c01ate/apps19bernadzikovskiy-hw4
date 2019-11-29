package ua.edu.ucu.tries;

import ua.edu.ucu.utils.Queue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class RWayTrie implements Trie, Iterable<String> {
    private static int R = 26;
    private static boolean DELETED = false;
    private Node root;

    public Node get(String key) {
        return get(root, key.toLowerCase(), 0);
    }

    private Node get(Node x, String key, int d) {
        if (x == null) return null;
        if (d == key.length()) return x;
        char c = key.charAt(d);
        return get(x.next[c-97], key, d + 1);
    }

    private Node put(Node x, String key, int val, int d) {
        if (x == null) x = new Node();
        if (d == key.length()) {
            x.val = new Tuple(key, val);
            return x;
        }
        char c = key.charAt(d);
        x.next[c-97] = put(x.next[c-97], key, val, d + 1);
        return x;
    }

    @Override
    public void add(Tuple t) {
        root = put(root, t.term.toLowerCase(), t.weight, 0);
    }

    @Override
    public boolean contains(String word) {
        return get(word).val != null;
    }

    @Override
    public boolean delete(String word) {
        root = delete(root, word.toLowerCase(), 0);
        boolean result = DELETED;
        DELETED = false;
        return result;
    }

    private Node delete(Node x, String key, int d)
    {
        if (x == null) return null;
        if (d == key.length()) {
            x.val = null;
            DELETED = true;
        }
        else
        {
            char c = key.charAt(d);
            x.next[c-97] = delete(x.next[c-97], key, d+1);
        }
        if (x.val != null) return x;
        for (char c = 0; c < R; c++)
            if (x.next[c] != null) return x;
        return null;
    }

    @Override
    public Iterable<String> words() {
        return wordsWithPrefix("");
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s) {
        RWayTrie trie = new RWayTrie();
        trie.root = get(s);
        return trie;
    }

    @Override
    public Iterable<String> wordsWithPrefix(String s, int k) {
        Queue q = new Queue();
        List<Integer> lst = new ArrayList<Integer>();
        Node node = get(s);
        if (node.val != null){
            lst.add(node.val.weight);
            q.enqueue(node.val);
        }
        collect(node, q, lst, k);
        RWayTrie rWayTrie = new RWayTrie();

        while (!q.getImmutableArrayList().isEmpty()){
            rWayTrie.add((Tuple) q.dequeue());
        }

        return rWayTrie;
    }

    private void collect(Node node, Queue queue, List<Integer> kArray, int k){
        for(int i=0; i < R; i++){
            if(node.next[i] != null && node.next[i].val != null){
                Node element = node.next[i];
                queue.enqueue(element.val);

                if(!kArray.contains(node.next[i].val.weight)){
                    kArray.add(element.val.weight);
                }

                if(kArray.size() < k){
                    collect(element, queue, kArray, k);
                }
            }
        }
    }

    @Override
    public int size()
    {  return size(root);  }

    private int size(Node x)
    {
        if (x == null) return 0;
        int cnt = 0;
        if (x.val != null) cnt++;
        for (char c = 0; c < R; c++)
            cnt += size(x.next[c]);
        return cnt; }

    @Override
    public Iterator<String> iterator() {
        return new TrieIterator(root);
    }

    protected static class Node {
        protected Tuple val;
        protected Node[] next = new Node[R];
    }

}
