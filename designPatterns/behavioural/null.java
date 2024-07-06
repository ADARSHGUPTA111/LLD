// The Null object pattern is a design pattern that simplifies the use of dependencies that can be 
// undefined. This is achieved by using instances of a concrete class that implements a known 
// interface, instead of null references.



interface Car {
	void drive();
	void stop();
}

class SUV implements Car {
	public void drive() {
		System.out.println("Driving an SUV");
	}

	public void stop() {
		System.out.println("Stopping an SUV");
	}
}

class Sedan implements Car {
	public void drive() {
		System.out.println("Driving a Sedan");
	}

	public void stop() {
		System.out.println("Stopping a Sedan");
	}
}

// Null class has the default values
class NullCar implements Car {
	public void drive() {
		// Do nothing
	}

	public void stop() {
		// Do nothing
	}
}

class CarRentalService {
	private Car car;

	public CarRentalService(Car car) {
		this.car = car;
	}

	public void rentCar() {
		car.drive();
		car.stop();
	}
}

public class Main {
	public static void main(String[] args) {
		Car suv = new SUV();
		Car sedan = new Sedan();
		Car nullCar = new NullCar();

		CarRentalService rentalService1 = new CarRentalService(suv);
		CarRentalService rentalService2 = new CarRentalService(sedan);
		CarRentalService rentalService3 = new CarRentalService(nullCar);

		rentalService1.rentCar(); // Output: Driving an SUV, Stopping an SUV
		rentalService2.rentCar(); // Output: Driving a Sedan, Stopping a Sedan
		rentalService3.rentCar(); // No output
        // No output is better than Null Pointer Exception
	}
}
