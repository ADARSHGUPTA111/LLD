// Liskov Substitution Principle

// It’s the ability to replace any object of a parent class with any 
// object of one of its child classes without affecting the correctness 
// of the program.

// Applying this principle we can validate that our abstractions are correct.

// Violation

// Bird is a class which has the two methods eat() and fly(). 
// It represents a base class that any type of bird can extend.

public class Bird {
    
    public void eat() {
        System.out.println("I can eat.");
    }
    
    public void fly() {
        System.out.println("I can fly.");
    }
}

// Swan is a type of bird that can eat and fly. 
public class Swan extends Bird {

    @Override
    public void eat() {
	System.out.println("OMG! I can eat pizza!");
    }

    @Override
    public void fly() {
	System.out.println("I believe I can fly!");
    }
}

// Main is the main class of our program which contains its logic. It has 
// two methods, letBirdsFly(List<Bird> birds) and main(String[] args). 
// The first method takes a list of birds as a parameter and invokes their 
// fly methods. The second one creates the list and passes it to the first.

public class Main { 
  
    public static void letBirdsFly(List<Bird> birds) {
        for(Bird bird: birds) { 
            bird.fly(); 
        } 
    } 
    
    public static void main(String[] args) { 
        List<Bird> birds = new ArrayList<Bird>();
        birds.add(new Bird()); 
        birds.add(new Swan()); 
        letBirdsFly(birds);
    }
}

// I can fly.
// I believe I can fly!

// Till now everything was fine. Output will be alright!

// Let's add Penguin. It can't fly!
public class Penguin extends Bird {
    @Override
    public void eat() {
        System.out.println("Can I eat taco?");
    } 
    @Override 
    public void fly() {
        throw new UnsupportedOperationException("Help! I cannot fly!"); 
    } 
}


// Inside Main, now
public static void main(String[] args) { 
    List<Bird> birds = new ArrayList<Bird>();
    birds.add(new Swan());
    birds.add(new Penguin());
    letBirdsFly(birds);
}

// Output - 
// I believe I can fly! 
// Exception in thread "main" java.lang.UnsupportedOperationException: Help! I cannot fly!

// Ordinary fix -
public static void letBirdsFly(List<Bird> birds) { 
    for(Bird bird: birds) { 
        if(!(bird instanceof Penguin)) { 
            bird.fly(); 
        } 
    } 
}

// But this solution is considered a bad practice, and it violates the 
// Open-Closed Principle. Imagine if we add another three types of birds 
// that cannot fly.

// Better solution

// Separate the flying logic
// The parent class must have the most generic form

public class Bird { 
    public void eat() { 
        System.out.println("I can eat."); 
    } 
}
public class FlyingBird extends Bird { 
    public void fly() { 
        System.out.println("I can fly."); 
    }
}
public class Swan extends FlyingBird { 
    @Override
    public void eat() { 
        System.out.println("OMG! I can eat pizza!"); 
    }
    @Override
    public void fly() { 
        System.out.println("I believe I can fly!");
    } 
}
public class Penguin extends Bird { 
    @Override 
    void eat() {
        System.out.println("Can I eat taco?");
    }
}

// Now main will be
public class Main {
    public static void letBirdsFly(List<FlyingBird> flyingBirds) {
        for(FlyingBird flyingBird: flyingBirds) { 
            flyingBird.fly();
        } 
    }
    public static void main(String[] args) {
        List<FlyingBird> flyingBirds = new ArrayList<FlyingBird>();
        flyingBirds.add(new Swan());
        letBirdsFly(flyingBirds); 
    }
}

// So, here we have reduced the chances of getting an error by keeping
// the most generic functions only in the parent!