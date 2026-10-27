/**
 * MIDN 2/C Soren Patel
 * Event.java
 * Event class
 * represents a do-it once event
 * Has a time and a message
 */

public class Event implements Comparable<Event> {
    
    //time and message fields
    private int time;
    private String message;
    //priority measuring fields
    private static int nextOrder = 0;
    private int order;

    //constructor
    public Event(int time, String message) {
        this.time = time;
        this.message = message;
        this.order = nextOrder++;
    }
    //constructor that lets future ocurrences keep their original order number
    public Event(int time, String message, int order) {
        this.time = time;
        this.message = message;
        this.order = order;
    }


    //accessors
    public int getTime() {
        return time;
    }
    public String getMessage() {
        return message;
    }
    public int getOrder() {
        return order;
    }


    /**
     * compareTo implementation
     * uses Integer class's native compare(x,y) method
     * does a max of 3 checks
     */
    @Override
    public int compareTo(Event other) {
        //first check: time comparison
        int cmp = Integer.compare(this.time, other.time);
        if (cmp != 0)
            return cmp;

        //second check: tie priority
        cmp = Integer.compare(this.tiePriority(), other.tiePriority());
        if (cmp != 0)
            return cmp;

        //third check: order 
        return Integer.compare(this.order, other.order);
    }


    /**
     * message events will print out their messages
     * designed to be overridden in ExitEvent and CountdownEvent, to exit
     * default true tells sim to continue
     */
    public boolean process(){
        System.out.print("[" + message + "]");
        return true;
    }

    /**
     * returns the next occurrence of this event if it has one
     * designed to be overridden by RepeatingEvent and CountdownEvent
     */
    public Event nextOccurrence(){
        return null;
    }

    /**
     * returns a number indicating priority in case of tie
     * 0 - normal event
     * 1 - exit event
     * 0 > 1 in terms of priority
     * overridden in ExitEvent.java
     * other types of events just inherit from here
     */
    public int tiePriority(){
        return 0;
    }
}