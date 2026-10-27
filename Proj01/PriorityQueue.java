/**
 * A generic interface for a (min) Priority Queue data structure.
 * Elements are prioritized based on their natural ordering based
 * on the Comparable interface, which they must implement. Note:
 * the <T extends Comparable<T>> business means that the type
 * argument used in PriorityQueue<???> has to be a class that 
 * implements the Comparable interface.
 */
public interface PriorityQueue<T extends Comparable<T>> {
    /**
     * Returns the number of elements currently in the queue.
     */
    public int size();

    /**
     * Inserts the specified element into the queue.
     */
    public void enqueue(T s);

    /**
     * Retrieves, but does not remove, the lowest priority
     * element in the queue.
     */
    public T peek();

    /**
     * Retrieves and removes the lowest priority element
     * from the queue.
     */
    public T dequeue();
}