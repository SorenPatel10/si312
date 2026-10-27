/**
 * MIDN 2/C Soren Patel
 * ExitEvent.java
 * extends Event
 * handles "exit" events
 */

public class ExitEvent extends Event{

    //constructor (only takes time since msg always EXIT)
    public ExitEvent(int time) {
        super(time, "EXIT");
    }

    //override process, tells sim to stop by returning false
    @Override
    public boolean process() {
        super.process();
        return false;
    }

    @Override
    public int tiePriority(){
        return 1;
    }
}