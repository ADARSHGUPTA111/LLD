// 1 ->  Single responsibility

// A class should have only one reason to change.

// We have a Customer class that has more than one responsibility:
// It has multiple reasons to change 

public class Customer {
 
    private String name;
 
    // getter and setter methods...
 
    // This is a Responsibility
    public void storeCustomer(String customerName) {
        // store customer into a database...
    }
 
    // This is another Responsibility
    public void generateCustomerReport(String customerName) {
        // generate a report...
    }
}

// Better solution
// Now each class will have just one reason to change

// Customer class
public class Customer {
    private String name;
    // getter and setter methods...
}

// CustomerDB class
public class CustomerDB {
    public void storeCustomer(String customerName) {
        // store customer into a database...
    }
}

// CustomerReportGenerator class
public class CustomerReportGenerator {
    public void generateReport(String customerName) {
        // generate a report...
    }
}

