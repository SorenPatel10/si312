/**
 * MIDN 2/C Soren Patel
 * RepeatingEvent.java
 * extends Event
 * handles "repeating" events
 */

public class RepeatingEvent extends Event{
    //repeating interval field
    private int interval;

    //constructors
    //super time and msg, new interval
    public RepeatingEvent(int time, int interval, String message) {
        super(time, message);
        this.interval = interval;
    }
    //also accepts super order
    public RepeatingEvent(int time, int interval, String message, int order) {
        super(time, message, order);
        this.interval = interval;
    }



    //returns the next occurrence in a new RepeatingEvent
    @Override
    public Event nextOccurrence() {
        return new RepeatingEvent(getTime() + interval, interval, getMessage(), getOrder());
    }
}