/**
 * MIDN 2/C Soren Patel
 * CountdownEvent.java
 * extends Event
 * handles "countdown" events
 */

public class CountdownEvent extends Event{
    //repeating interval and count field
    private int interval;
    private int count;

    //constructor
    //super time and msg, new interval and count
    public CountdownEvent(int time, int interval, int count, String message) {
        super(time, message);
        this.interval = interval;
        this.count = count;
    }
    //accepts super order
    public CountdownEvent(int time, int interval, int count, String message, int order) {
        super(time, message, order);
        this.interval = interval;
        this.count = count;
    }


    //returns the next occurrence in a new CountdownEvent (count -1)
    @Override
    public Event nextOccurrence() {
        if(count <= 1)
            return null;
        
        return new CountdownEvent(getTime()+interval, interval, count-1, getMessage(), getOrder());
    }

    //print curr countdown num and msg, ret true to continue sim
    @Override
    public boolean process(){
        System.out.print("[" + count + " : " + getMessage() + "]");
        return true;
    }
}