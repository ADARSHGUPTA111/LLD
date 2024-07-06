/**
 * This pattern is used when we want to include all data in an object required to perform 
 * an action.
 * Hence, the executor need not know the details of how to perform the action. It can simply 
 * call the execute method of the object.
 * In this example, multiple ArithmeticOperation objects are created and each of them contains 
 * all details required to perform the action.
 * The executor simply picks up an object and execute it using the operate() method.
 * This is useful while building an architecture of message queues with multiple worker threads 
 * which can pick up tasks when possible and execute them without needing to know how to perform it.
 */


 // Receiver: RideService
class RideService {
    public void requestRide(String passenger, String srcLoc, String destLoc) {
        System.out.println("Requesting a ride for passenger: " + passenger +
                " from " + srcLoc + " to " + destLoc);
        // Additional ride request processing logic here
    }

    public void cancelRide(String passenger) {
        System.out.println("Canceling the ride for passenger: " + passenger);
        // Additional cancellation logic here
    }
}

// Abstract Command interface
interface Command {
    void execute();
}

// Concrete Command: RideRequestCommand
class RideRequestCommand implements Command {
    private RideService receiver;
    private String passenger;
    private String srcLoc;
    private String destLoc;

    public RideRequestCommand(RideService receiver, String passenger, String srcLoc, String destLoc) {
        this.receiver = receiver;
        this.passenger = passenger;
        this.srcLoc = srcLoc;
        this.destLoc = destLoc;
    }

    public void execute() {
        receiver.requestRide(passenger, srcLoc, destLoc);
    }
}

// Concrete Command: CancelRideCommand
class CancelRideCommand implements Command {
    private RideService receiver;
    private String passenger;

    public CancelRideCommand(RideService receiver, String passenger) {
        this.receiver = receiver;
        this.passenger = passenger;
    }

    public void execute() {
        receiver.cancelRide(passenger);
    }
}

// Invoker: RideRequestInvoker
class RideRequestInvoker {
    public void processRequest(Command command) {
        command.execute();
    }
}

public class UberRidesDemo {
    public static void main(String[] args) {
        // Create a receiver
        RideService rideService = new RideService();

        // Create an invoker
        RideRequestInvoker rideRequestInvoker = new RideRequestInvoker();

        // Execute ride request and cancellation commands directly
        Command request1 = new RideRequestCommand(rideService, "Keerti", "Sarjapur", "Koramangala");
        Command request2 = new RideRequestCommand(rideService, "Amit", "Koramangala", "Indiranagar");
        Command cancel1 = new CancelRideCommand(rideService, "Keerti");

        // Process the ride requests and cancellations
        rideRequestInvoker.processRequest(request1);
        rideRequestInvoker.processRequest(request2);
        rideRequestInvoker.processRequest(cancel1);

        // Clean up (not required in Java)
    }
}

 