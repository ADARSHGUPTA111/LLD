/**
 * This pattern is used when we need to change an object's behavior when its state changes.
 * Instead of accomplising this by maintaining a state variable and adding a lot of conditional statements,
 * we can store a different class instance (called state) inside our concerned class.
 * All information and logic required for object's behavior will be stored in the state object.
 * In this example, state stores the logic for phone ring/vibration/silence.
 * PhoneRinger stores an instance of state.
 * Whenever state changes, the state object is also changed inside PhoneRinger.
 */


 // Define the RideState interface.
interface RideState {
    void handleRequest();
    void handleCancel();
}

// Implement concrete states.

class RideRequestedState implements RideState {
    @Override
    public void handleRequest() {
        System.out.println("Ride has been requested.");
    }

    @Override
    public void handleCancel() {
        System.out.println("Ride request has been canceled.");
    }
}

class DriverAssignedState implements RideState {
    @Override
    public void handleRequest() {
        System.out.println("Ride is already assigned to a driver.");
    }

    @Override
    public void handleCancel() {
        System.out.println("Ride request has been canceled. The driver is notified.");
    }
}

class EnRouteState implements RideState {
    @Override
    public void handleRequest() {
        System.out.println("Ride is already en route.");
    }

    @Override
    public void handleCancel() {
        System.out.println("Ride request cannot be canceled while en route.");
    }
}

// Context

class Ride {
    private RideState state;

    public Ride() {
        // Initial state is RideRequestedState.
        state = new RideRequestedState();
    }

    public void setState(RideState newState) {
        state = newState;
    }

    public void requestRide() {
        state.handleRequest();
    }

    public void cancelRide() {
        state.handleCancel();
    }
}

public class StateDemo {
    public static void main(String[] args) {
        Ride ride = new Ride();
    
        ride.requestRide();  // Output: Ride has been requested.
        ride.cancelRide();   // Output: Ride request has been canceled.

        System.out.println("--------------------------------------------------");
        System.out.println("Setting state to Driver Assigned.");

        ride.setState(new DriverAssignedState());
        ride.requestRide();  // Output: Ride is already assigned to a driver.
        ride.cancelRide();   // Output: Ride request has been canceled. The driver is notified.

        System.out.println("--------------------------------------------------");
        System.out.println("Setting state to EnRoute.");
        
        ride.setState(new EnRouteState());
        ride.requestRide();  // Output: Ride is already en route.
        ride.cancelRide();   // Output: Ride request cannot be canceled while en route.
    }
}