import edu.princeton.cs.algs4.Queue;

public class MergeSort {
    /**
     * Removes and returns the smallest item that is in q1 or q2.
     *
     * The method assumes that both q1 and q2 are in sorted order, with the smallest item first. At
     * most one of q1 or q2 can be empty (but both cannot be empty).
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      The smallest item that is in q1 or q2.
     */
    private static <Item extends Comparable> Item getMin(
            Queue<Item> q1, Queue<Item> q2) {
        if (q1.isEmpty()) {
            return q2.dequeue();
        } else if (q2.isEmpty()) {
            return q1.dequeue();
        } else {
            // Peek at the minimum item in each queue (which will be at the front, since the
            // queues are sorted) to determine which is smaller.
            Comparable q1Min = q1.peek();
            Comparable q2Min = q2.peek();
            if (q1Min.compareTo(q2Min) <= 0) {
                // Make sure to call dequeue, so that the minimum item gets removed.
                return q1.dequeue();
            } else {
                return q2.dequeue();
            }
        }
    }

    /** Returns a queue of queues that each contain one item from items. */
    private static <Item extends Comparable> Queue<Queue<Item>>
            makeSingleItemQueues(Queue<Item> items) {
        Queue<Queue<Item>> queues = new Queue<>();
        for (Item item : items) {
            Queue<Item> queue = new Queue<>();
            queue.enqueue(item);
            queues.enqueue(queue);
        }
        return queues;
    }

    /**
     * Returns a new queue that contains the items in q1 and q2 in sorted order.
     *
     * This method should take time linear in the total number of items in q1 and q2.  After
     * running this method, q1 and q2 will be empty, and all of their items will be in the
     * returned queue.
     *
     * @param   q1  A Queue in sorted order from least to greatest.
     * @param   q2  A Queue in sorted order from least to greatest.
     * @return      A Queue containing all of the q1 and q2 in sorted order, from least to
     *              greatest.
     *
     */
    private static <Item extends Comparable> Queue<Item> mergeSortedQueues(
            Queue<Item> q1, Queue<Item> q2) {
        Queue<Item> mergeQueue = new Queue<>();
        while (!q1.isEmpty() || !q2.isEmpty()) {
            mergeQueue.enqueue(getMin(q1, q2));
        }
        return mergeQueue;
    }

    /** Returns a Queue that contains the given items sorted from least to greatest. */
    public static <Item extends Comparable> Queue<Item> mergeSort(
            Queue<Item> items) {
        if (items.size() <= 1) {
            return items;
        }
        Queue<Queue<Item>> tempoQueue = makeSingleItemQueues(items);
        Queue<Item> q1 = new Queue<>();
        Queue<Item> q2 = new Queue<>();
        for (int i = 0; i < items.size() / 2; i += 1) {
            q1.enqueue(tempoQueue.dequeue().dequeue());
        }
        for (int i = items.size() / 2; i < items.size(); i += 1) {
            q2.enqueue(tempoQueue.dequeue().dequeue());
        }
        Queue<Item> ql = mergeSort(q1);
        Queue<Item> qr = mergeSort(q2);
        return mergeSortedQueues(ql, qr);
    }

    public static void main(String[] args) {
        Queue<String> friends = new Queue<String>();
        friends.enqueue("Christopher-Robin");
        friends.enqueue("Winnie-the-Pooh");
        friends.enqueue("Yicheng Xia");
        friends.enqueue("Tigger");
        friends.enqueue("Rabbit");
        friends.enqueue("Eeyore");
        friends.enqueue("Roo");
        friends.enqueue("Owl");
        friends.enqueue("Tinge");
        System.out.println("original queue: " + friends.toString());
        Queue<String> sortedFriends = mergeSort(friends);
        System.out.println("sorted queue: " + sortedFriends.toString());
    }
}








