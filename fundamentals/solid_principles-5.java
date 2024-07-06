// Dependency Inversion Principle

// High-level modules should not depend on low-level modules. Both should 
// depend on abstractions.

// Abstractions should not depend on details. Details should depend on 
// abstractions.

// The Dependency Inversion Principle means that a particular class should not
//  depend directly on another class, but on an abstraction (interface) 
//  of this class.


// DeliveryDriver class that represents a driver that works for company
public class DeliveryDriver {
    public void deliverProduct(Product product){
        // deliver product...
    }
}

// DeliveryCompany that handles shipments:
public class DeliveryCompany {
    public void sendProduct(Product product) {
        DeliveryDriver deliveryDriver = new DeliveryDriver();
        deliveryDriver.deliverProduct(product);
    }
}


// Note that DeliveryCompany creates and uses DeliveryDriver concretions. 
// Therefore DeliveryCompany which is a high-level class is dependent on a 
// lower level class and this is a violation of Dependency Inversion Principle.



// Solution
// We create the DeliveryService interface to have an abstraction:

public interface DeliveryService {
    void deliverProduct(Product product);
}

// Refactor DeliveryDriver class to implements DeliveryService
public class DeliveryDriver implements DeliveryService {
    @Override
    public void deliverProduct(Product product) {
        // deliver product...
    }
}

// Refactor DeliveryCompany that now depends on an abstraction 
// and not off a concretion:

public class DeliveryCompany {
 
    private DeliveryService deliveryService;
 
    public DeliveryCompany(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }
 
    public void sendProduct(Product product) {
        this.deliveryService.deliverProduct(product);
    }
}