/**
 * This is a simple-minded and inefficient implementation of the
 * PriorityQueue<T> inteferface.  It is based on linked lists.
 */
public class LinkedListPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T> {
  /*** Private data & methods ***/
  private class Node {
    public T data;
    public Node next;
    public Node(T d, Node n) { data = d; next = n; }
  }  
  private Node head = null;
  private int count = 0;

  private Node findSmallest() {
    Node smallest = head;
    for(Node tmp = head; tmp != null; tmp = tmp.next)
      if (tmp.data.compareTo(smallest.data) < 0)
	smallest = tmp;
    return smallest;
  }

  /*** Public methods ***/
  public boolean empty() { return head == null; }

  public int size() { return count; }

    public T peek() { return findSmallest().data; }

  public void enqueue(T s) { head = new Node(s,head); count++;  }

  public T dequeue() {
    Node smallest = findSmallest();
    if (smallest == head)
      head = smallest.next;
    else {
      Node pred = head;
      while(pred.next != smallest)
	pred = pred.next;
      pred.next = smallest.next;
    }
    count--;
    return smallest.data;
  }
}