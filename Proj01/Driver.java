/**
 * MIDN 2/C Soren Patel
 * Driver.java
 * Main driver file for project
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * Driver class
 * Reads in file, initializes empty PriorityQueue, enqueues all events, and dequeues based on time.
 */
public class Driver {

    public static void main(String[] args) {
        boolean cFlag = false;
        String filename;

        //handle command-line input
        //just filename
        if (args.length == 1) {
            filename = args[0];
        }
        //filename -c
        else if (args.length == 2 && args[0].equals("-c")) {
            cFlag = true;
            filename = args[1];
        }
        //other input -> error
        else{
            System.out.println("usage: java Driver [-c] <file>");
            return;
        }

        //read in file, init filereader Scanner
        File file = new File(filename);
        Scanner input;
        //try: read in file
        try{
            input = new Scanner(file);
        }
        //catch: throw FNFException
        catch (FileNotFoundException e) {
            System.out.println("Error: File '" + filename + "' not found!");
            return;
        }

        //initialize empty LLPQ
        PriorityQueue<Event> queue = new LinkedListPriorityQueue<Event>();

        //file reading / enqueueing loop
        while (input.hasNextLine()) {
            //read line and clean whitespace
            String line = input.nextLine().trim();

            //skip empty lines
            if(line.isEmpty())
                continue;

            //init temp linereader Scanner obj
            Scanner sc = new Scanner(line);

            //read time and event type
            int time = sc.nextInt();
            String eventType = sc.next();

            //handle doit-once event
            if (eventType.equals("doit-once")) {
                //extract the message and enqueue Event
                sc.next();
                String message = sc.nextLine().trim();
                queue.enqueue(new Event(time, message));
            }
            //handle exit event
            else if(eventType.equals("exit")) {
                queue.enqueue(new ExitEvent(time));
            }
            //handle repeating event
            else if (eventType.equals("repeating")){
                //read in interval and message, and enqueue
                int interval = sc.nextInt();
                sc.next();
                String message = sc.nextLine().trim();
                queue.enqueue(new RepeatingEvent(time, interval, message));
            }
            else if (eventType.equals("countdown")){
                //read interval, count fields and msg, enqueue
                int interval = sc.nextInt();
                int count = sc.nextInt();
                sc.next();
                String message = sc.nextLine().trim();
                queue.enqueue(new CountdownEvent(time, interval, count, message));
            }

            //close temp linereader Scanner
            sc.close();
        }

        //close filereader Scanner
        input.close();

        //start sim and clock
        System.out.println("Simulation");
        int clock = 1;
        boolean running = true;

        //dequeue loop, until queue is empty
        while (queue.size() > 0 && running) {
            //flag to check if current clock time has been printed
            boolean printedTime = false;

            //cycle through all events matching current time
            while (queue.size() > 0 && queue.peek().getTime() == clock) {

                //Get and remove the next event
                Event next = queue.dequeue();
                
                //switch flag once time is printed
                if (!printedTime) {
                    System.out.printf("%03d: ", clock);
                    printedTime = true;
                }

                //polymorphic call
                //returns true for all events except ExitEvents
                running = next.process();

                //break on exit
                if (!running)
                    break;

                //get next occurence if it exists
                //if so, enqueue for later dequeuement
                Event following = next.nextOccurrence();
                if(following != null){
                    queue.enqueue(following);
                }
            }

            //print newline
            if(printedTime)
                System.out.println();
            
            //print empty times if c mode is off
            if (!printedTime && !cFlag)
                System.out.printf("%03d: %n", clock);

            //update clock
            if (running)
                clock++;
        }

        System.out.println("Goodbye");
    }
}