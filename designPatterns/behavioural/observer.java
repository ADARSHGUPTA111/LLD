/**
 * This pattern is used when multiple objects needs to be updated (called subscribers) when a certain change occurs in an object's state (called observable).
 * Instead of updating all subscribers of the change, or letting subscribers poll the publisher continuously,
 * the subscribers can subscribe to a system (called publisher) which will notify them whenever the observable's state changes.
 * In this example, channels are the observables.
 * YoutubeNotification class is the publisher which stores a list of all Subscribers for each channel.
 * User class implements Subscriber so that its notify() method can be used to notify it.
 * Whenever a new video is added to the channel, publisher notifies all subscribers.
 */


import java.util.*;

interface Observer {
    void update(Order order);
}

class Customer implements Observer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    @Override
    public void update(Order order) {
        System.out.println("Hello, " + name + "! Order #" + order.getId() + " is now " + order.getStatus() + ".");
    }
}

class Restaurant implements Observer {
    private String restaurantName;

    public Restaurant(String name) {
        this.restaurantName = name;
    }

    @Override
    public void update(Order order) {
        System.out.println("Restaurant " + restaurantName + ": Order #" + order.getId() + " is now " + order.getStatus() + ".");
    }
}

class DeliveryDriver implements Observer {
    private String driverName;

    public DeliveryDriver(String name) {
        this.driverName = name;
    }

    @Override
    public void update(Order order) {
        System.out.println("Driver " + driverName + ": Order #" + order.getId() + " is now " + order.getStatus() + ".");
    }
}

class CallCenter implements Observer {
    @Override
    public void update(Order order) {
        System.out.println("Call center: Order #" + order.getId() + " is now " + order.getStatus() + ".");
    }
}

class Order {
    private int id;
    private String status;
    private List<Observer> observers = new ArrayList<>();

    public Order(int id) {
        this.id = id;
        this.status = "Order Placed";
    }

    public int getId() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String newStatus) {
        status = newStatus;
        notifyObservers();
    }

    public void attach(Observer observer) {
        observers.add(observer);
    }

    public void detach(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this);
        }
    }
}

public class OrderStatus {
    public static void main(String[] args) {
        // Create an order
        Order order1 = new Order(123);

        // Create customers, restaurants, drivers, and a call center to track the order
        Customer customer1 = new Customer("Customer 1");
        Restaurant restaurant1 = new Restaurant("Rest 1");
        DeliveryDriver driver1 = new DeliveryDriver("Driver 1");
        CallCenter callCenter = new CallCenter();

        // Attach observers to the order
        order1.attach(customer1);
        order1.attach(restaurant1);
        order1.attach(driver1);
        order1.attach(callCenter);

        // Simulate order status updates
        order1.setStatus("Out for Delivery");

        // Detach an observer (if needed)
        order1.detach(callCenter);

        // Simulate more order status updates
        order1.setStatus("Delivered");
    }
}