/**
 * This pattern lets you pass requests along a chain of processors until it reaches an 
 * appropriate processor which can handle it.
 * User doesn't need to know about all the available processors.
 * Only the head of the chain needs to be known to the users, and each processor can know its 
 * succeeding one.
 * In this example, user is provided the head of the chain that is Leave Request processor.
 * If the request provided by user is a leave request, it is handled immediately, else it is 
 * passed down the chain until an appropriate processor is found.
 * If no processor can process it, an appropriate message is returned to the user.
 * If new processors come in, they can be attached to the chain without any change required on 
 * the user side.
 */



// Define the abstract base class for order handlers.
abstract class OrderHandler {
    protected OrderHandler nextHandler;

    public OrderHandler(OrderHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void processOrder(String order);
}

// Concrete handler for order validation.
class OrderValidationHandler extends OrderHandler {
    public OrderValidationHandler(OrderHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void processOrder(String order) {
        System.out.println("Validating order: " + order);
        // Perform order validation logic here

        // If the order is valid, pass it to the next handler
        if (nextHandler != null) {
            nextHandler.processOrder(order);
        }
    }
}

// Concrete handler for payment processing.
class PaymentProcessingHandler extends OrderHandler {
    public PaymentProcessingHandler(OrderHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void processOrder(String order) {
        System.out.println("Processing payment for order: " + order);
        // Perform payment processing logic here

        // If payment is successful, pass it to the next handler
        if (nextHandler != null) {
            nextHandler.processOrder(order);
        }
    }
}

// Concrete handler for order preparation.
class OrderPreparationHandler extends OrderHandler {
    public OrderPreparationHandler(OrderHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void processOrder(String order) {
        System.out.println("Preparing order: " + order);
        // Perform order preparation logic here

        // If preparation is complete, pass it to the next handler
        if (nextHandler != null) {
            nextHandler.processOrder(order);
        }
    }
}

// Concrete handler for delivery assignment.
class DeliveryAssignmentHandler extends OrderHandler {
    public DeliveryAssignmentHandler(OrderHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void processOrder(String order) {
        System.out.println("Assigning delivery for order: " + order);
        // Perform delivery assignment logic here

        // If delivery is assigned, pass it to the next handler
        if (nextHandler != null) {
            nextHandler.processOrder(order);
        }
    }
}

// Concrete handler for order tracking.
class OrderTrackingHandler extends OrderHandler {
    public OrderTrackingHandler(OrderHandler nextHandler) {
        super(nextHandler);
    }

    @Override
    public void processOrder(String order) {
        System.out.println("Tracking order: " + order);
        // Perform order tracking logic here
    }
}

public class SwiggyOrder {
    public static void main(String[] args) {
        // Create a chain of responsibility for order processing
        OrderHandler orderProcessingChain = new OrderValidationHandler(
            new PaymentProcessingHandler(
                new OrderPreparationHandler(
                    new DeliveryAssignmentHandler(
                        new OrderTrackingHandler(null))))); 
                        // The last handler has no next handler

        // Simulate an order being placed
        String order = "Pizza";
        orderProcessingChain.processOrder(order);
    }
}