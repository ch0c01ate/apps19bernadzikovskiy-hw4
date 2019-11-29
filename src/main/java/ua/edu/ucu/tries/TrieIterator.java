package ua.edu.ucu.tries;

import ua.edu.ucu.utils.Queue;
import ua.edu.ucu.utils.immutable.ImmutableArrayList;

import java.util.Iterator;

public class TrieIterator implements Iterator<String> {
    private static int R = 26;
    Queue queue;

    public TrieIterator(RWayTrie.Node node) {
        queue = new Queue();
        queue.enqueue(node);
    }

    @Override
    public boolean hasNext() {
        Object[] lst = queue.getImmutableArrayList().toArray();
        while (!queue.getImmutableArrayList().isEmpty()){
            RWayTrie.Node value = (RWayTrie.Node) queue.dequeue();
            if(value.val != null){
                queue = new Queue(new ImmutableArrayList(lst));
                return true;
            }
            addChildrenToQueue(value);
        }
        queue = new Queue(new ImmutableArrayList(lst));
        return false;
    }

    @Override
    public String next() {
        RWayTrie.Node nextValue = (RWayTrie.Node)queue.dequeue();
        while(nextValue.val == null){
            addChildrenToQueue(nextValue);
            nextValue = (RWayTrie.Node) queue.dequeue();
        }

        addChildrenToQueue(nextValue);
        return nextValue.val.term;
    }

    private void addChildrenToQueue(RWayTrie.Node node){
        for(int i = 0; i < R; i++){
            if(node.next[i] != null){
                queue.enqueue(node.next[i]);
            }
        }
    }
}
