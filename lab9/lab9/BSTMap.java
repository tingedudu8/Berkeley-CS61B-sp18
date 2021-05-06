package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.HashSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author tinge
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (key.compareTo(p.key) == 0) {
            return p.value;
        } else if (key.compareTo(p.key) < 0) {
            return getHelper(key, p.left);
        } else {
            return getHelper(key, p.right);
        }
    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            size += 1;
            return new Node(key, value);
        } else if (key.compareTo(p.key) == 0) {
            p.value = value;
        } else if (key.compareTo(p.key) < 0) {
            p.left = putHelper(key, value, p.left);
        } else {
            p.right = putHelper(key, value, p.right);
        }
        return p;
    }


    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        root = putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private void keySetHelper(Set<K> keySet, Node p) {
        if (p == null) {
            return;
        }
        keySet.add(p.key);
        keySetHelper(keySet, p.left);
        keySetHelper(keySet, p.right);
    }

    @Override
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<>();
        keySetHelper(keySet, root);
        return keySet;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private int deleteCase(Node p) {
        if (p.left == null && p.right == null) {
            return 0;
        } else if (p.right == null) {
            return 1;
        } else if (p.left == null) {
            return 2;
        }
        return 3;
    }

    private Node minNode(Node p) {
        if (p.left == null) {
            return p;
        }
        return minNode(p.left);
    }

    private Node removeHelper(K key, Node p) {
        if (key.compareTo(p.key) == 0) {
            switch (deleteCase(p)) {
                case 1:
                    return p.left;
                case 2:
                    return p.right;
                case 3:
                    Node minNodeOnRight =  minNode(p.right);
                    K minKeyOnRight = minNodeOnRight.key;
                    V minValueOnRight = minNodeOnRight.value;
                    p.key = minKeyOnRight;
                    p.value = minValueOnRight;
                    p.right = removeHelper(minKeyOnRight, p.left);
                    return p;
                default:
                    return null;
            }
        }
        if (key.compareTo(p.key) < 0) {
            p.left = removeHelper(key, p.left);
        } else {
            p.right = removeHelper(key, p.right);
        }
        return p;
    }

    @Override
    public V remove(K key) {
        V removed = get(key);
        if (removed == null) {
            return null;
        }
        root = removeHelper(key, root);
        size -= 1;
        return removed;
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        V removed = get(key);
        if (removed == null || removed != value) {
            return null;
        }
        root = removeHelper(key, root);
        size -= 1;
        return removed;
    }

    @Override
    public Iterator<K> iterator() {
        return keySet().iterator();
    }
}
