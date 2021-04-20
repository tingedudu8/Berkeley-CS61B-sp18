/* Linked-list based double ended queue, which accepts generic types.
@Rule: All the method should follow "Deque API" described in 
https://sp18.datastructur.es/materials/proj/proj1a/proj1a#the-deque-api
@Rule: The amount of memory that this program uses at any given time
must be proportional to the number of items */

public class LinkedListDeque<T> implements Deque<T> {
    
    private class Node {
        T item;
        Node prev;
        Node next;

        Node(T i, Node p, Node n) {
            item = i;
            prev = p;
            next = n;
        }
    }

    private Node sentinel; // A circular sentinel~
    private int size;

    // Creates an empty linked list deque.
    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    //Returns true if deque is empty, false otherwise.
    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    //Returns the number of items in the deque
    @Override
    public int size() {
        return size;
    }

    //Prints the items in the deque from first to last, separated by a space. 
    //Once all the items have been printed, print out a new line.

    // error:
    // public void printDeque() {
    //     while(sentinel.next != sentinel) {
    //         System.out.print(sentinel.next.item + " ");
    //         sentinel = sentinel.next;
    //     }
    //     System.out.println();
    // }
    @Override
    public void printDeque() {
        Node currentNode = sentinel;
        while (currentNode.next != sentinel) {
            System.out.print(currentNode.next.item + " ");
            currentNode = currentNode.next;
        }
        System.out.println();
    }

/*     Adds an item of type T to the front of the deque
    @Rule: A single operation should be executed in constant time. */
    @Override
    public void addFirst(T item) {
        sentinel.next = new Node(item, sentinel, sentinel.next);
        sentinel.next.next.prev = sentinel.next;
        size += 1;
    }

    //Adds an item of type T to the back of the deque.
    @Override
    public void addLast(T item) {
        sentinel.prev = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.prev.next = sentinel.prev;
        size += 1;
    }

    //Removes and returns the item at the front of the deque. If no such item exists, returns null.
    @Override
    public T removeFirst() {
        if (sentinel.next == sentinel) {
            return null;
        }

        T removed = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return removed;
    }

    // Removes and returns the item at the back of the deque. If no such item exists, returns null.
    @Override
    public T removeLast() {
        if (sentinel.prev == sentinel) {
            return null;
        }
        T removed = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return removed;
    }    

    //Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth.
    //If no such item exists, returns null. Must not alter the deque!
    @Override
    public T get(int index) {
        if (index >= size) {
            return null;
        }

        Node back = sentinel.next;
        while (index != 0) {
            back = back.next;
            index--;
        }
        return back.item;
    }

    // Same as get, but uses recursion.
    private T getRecursiveHelper(Node currentNode, int index) {
        if (index == 0) {
            return currentNode.item;
        }
        return getRecursiveHelper(currentNode.next, index - 1);
    }

    public T getRecursive(int index) {
        if (index >= size) {
            return null;
        }

        return getRecursiveHelper(sentinel.next, index);
    }

}































