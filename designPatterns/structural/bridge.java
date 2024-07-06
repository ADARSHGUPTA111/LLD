/**
 * Bridge pattern helps in separating out orthogonal properties into separate classes so as to 
 * reduce the number of combinations.
 * In this example, if Type is added as a property in Card class, for every new Type, a new 
 * class would have to be created for every Card.
 * Similarly, for every new Card, a new class would have to created for every Type.
 * This would lead to creation of N(number of cards) * M(number of types) classes
 * However, using this pattern, a new Type or Card can be created independently.
 * Hence, there will be a total of N+M classes only.
 */


 // Implementation Layer
interface NavigationImpl {
    void navigateTo(String destination);
}

// Abstraction Layer
interface NavigationSystem {
    void navigate(String destination);
}

// Concrete Abstraction: UberRide
class UberRide implements NavigationSystem {
    private String driverName;
    private NavigationImpl navigationImpl;

    public UberRide(String driverName) {
        this.driverName = driverName;
    }

    public void setNavigationImpl(NavigationImpl impl) {
        this.navigationImpl = impl;
    }

    public void navigate(String destination) {
        System.out.print("Uber ride with " + driverName + " to " + destination + " using ");
        navigationImpl.navigateTo(destination);
    }
}

// Concrete Abstraction: UberEats
class UberEats implements NavigationSystem {
    private String restaurantName;
    private NavigationImpl navigationImpl;

    public UberEats(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public void setNavigationImpl(NavigationImpl impl) {
        this.navigationImpl = impl;
    }

    public void navigate(String destination) {
        System.out.print("Uber Eats delivery from " + restaurantName + " to " + destination + " using ");
        navigationImpl.navigateTo(destination);
    }
}

// Concrete Implementation: GoogleMaps
class GoogleMaps implements NavigationImpl {
    public void navigateTo(String destination) {
        System.out.println("Google Maps.");
        // Actual navigation logic using Google Maps API
    }
}

// Concrete Implementation: AppleMaps
class AppleMaps implements NavigationImpl {
    public void navigateTo(String destination) {
        System.out.println("Apple Maps.");
        // Actual navigation logic using Apple Maps API
    }
}

public class UberDemo {
    public static void main(String[] args) {
        // Create an UberRide with a driver
        UberRide uber = new UberRide("Keerti");

        // Create an UberEats delivery
        UberEats uberEats = new UberEats("Pizza Palace");

        // Create different navigation implementations
        GoogleMaps googleMaps = new GoogleMaps();
        AppleMaps appleMaps = new AppleMaps();

        // Set the navigation implementation for UberRide
        uber.setNavigationImpl(googleMaps);

        // Request an Uber ride with Google Maps navigation
        uber.navigate("Central Park");

        // Switch to Apple Maps navigation for UberEats
        uberEats.setNavigationImpl(appleMaps);

        // Request an Uber Eats delivery with Apple Maps navigation
        uberEats.navigate("123 HSR");
    }
}