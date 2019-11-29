package ua.edu.ucu.autocomplete;

import ua.edu.ucu.tries.RWayTrie;
import ua.edu.ucu.tries.Trie;
import ua.edu.ucu.tries.Tuple;
import ua.edu.ucu.utils.Queue;

/**
 *
 * @author andrii
 */
public class PrefixMatches {

    private Trie trie;

    public PrefixMatches(Trie trie) {
        this.trie = trie;
    }

    public int load(String... strings) {
        int numberOfAddedStrings = 0;
        for (String string: strings
             ) {
            String[] wordsFromString = string.split(" ");
            for (String word: wordsFromString
                 ) {
                int l = string.length();
                if(l > 2) {
                    trie.add(new Tuple(string, string.length()));
                    numberOfAddedStrings++;
                }
            }
        }

        return numberOfAddedStrings;
    }

    public boolean contains(String word) {
        return trie.contains(word);
    }

    public boolean delete(String word) {
        return trie.delete(word);
    }

    public Iterable<String> wordsWithPrefix(String pref) {
        return trie.wordsWithPrefix(pref);
    }

    public Iterable<String> wordsWithPrefix(String pref, int k) {
        return trie.wordsWithPrefix(pref, k);
    }

    public int size() {
        return trie.size();
    }
}
