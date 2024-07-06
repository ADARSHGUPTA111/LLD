/**
 * Strategy pattern is used when we need to change the behavior of an algorithm at runtime.
 * We create an interface with a method which is implemented by all concrete classes.
 * This method contains the logic of the algorithm and can be implemented differently in each class.
 * The caller class stores an instance of this interface which can be set to required class at runtime.
 * In this example, LoadBalancer class contains an instance of Strategy which is implemented by RoundRobin and Random strategy.
 * setStrategy method of the LoadBalancer class is used to set/modify the required concrete class at runtime.
 */



// PaymentStrategy interface
interface PaymentStrategy {
    void processPayment(double amount);
}

// Concrete PaymentStrategy classes
class CreditCardPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        System.out.println("Processing credit card payment of $" + amount);
    }
}

class PayPalPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        System.out.println("Processing PayPal payment of $" + amount);
    }
}

class CryptocurrencyPayment implements PaymentStrategy {
    public void processPayment(double amount) {
        System.out.println("Processing cryptocurrency payment of $" + amount);
    }
}

// PaymentProcessor
class PaymentProcessor {
    private PaymentStrategy paymentStrategy;

    public PaymentProcessor() {
        paymentStrategy = null;
    }

    public void setPaymentStrategy(PaymentStrategy strategy) {
        if (paymentStrategy != null) {
            // Clean up the previous strategy
            paymentStrategy = null;
        }
        paymentStrategy = strategy;
    }

    public void processPayment(double amount) {
        if (paymentStrategy != null) {
            paymentStrategy.processPayment(amount);
        } else {
            System.err.println("Payment strategy not set.");
        }
    }

    public void finalize() {
        if (paymentStrategy != null) {
            // Clean up the strategy instance
            paymentStrategy = null;
        }
    }
}

public class PaymentDemo {
    public static void main(String[] args) {
        PaymentProcessor processor = new PaymentProcessor();

        // Select and set the payment strategy at runtime
        PaymentStrategy strategy = new CreditCardPayment();
        processor.setPaymentStrategy(strategy);

        // Process the payment
        processor.processPayment(100.0);

        // Change the payment strategy
        strategy = new PayPalPayment();
        processor.setPaymentStrategy(strategy);

        // Process another payment using the new strategy
        processor.processPayment(50.0);
    }
}