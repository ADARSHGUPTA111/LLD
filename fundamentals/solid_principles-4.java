// Interface segregation principle
// many client-specific interfaces are better than one 
// general-purpose interface


// This principle defines that a class should never implement an 
// interface that does not go to use.

public interface Car {
    void startEngine();
    void accelerate();
}

public class Mustang implements Car {
 
    @Override
    public void startEngine() {
        // start engine...
    }
 
    @Override
    public void accelerate() {
        // accelerate...
    }
}

// Mustang implements Car
// Now, we have MustangPlus which has more features, so we will 
// modify the Car Interface
public interface Car {
    void startEngine();
    void accelerate();
    void backToThePast();
    void backToTheFuture();
}

// Now MustangPlus implements Car
public class MustangPlus implements Car {
 
    @Override
    public void startEngine() {
        // start engine...
    }
 
    @Override
    public void accelerate() {
        // accelerate...
    }
 
    @Override
    public void backToThePast() {
        // back to the past...
    }
 
    @Override
    public void backToTheFuture() {
        // back to the future...
    }
}

// But now mustang is forced to implement Car Interface forcefully now

public class Mustang implements Car {
 
    @Override
    public void startEngine() {
        // start engine...
    }
 
    @Override
    public void accelerate() {
        // accelerate...
    }
 
    @Override
    public void backToThePast() {
        // because a Mustang can not back to the past!
        throw new UnsupportedOperationException();
    }
 
    @Override
    public void backToTheFuture() {
        // because a Mustang can not back to the future!
        throw new UnsupportedOperationException();
    }
}

// Here, ISP is violated

// Solution

// Break Car Interface in smaller pieces
public interface Car {
    void startEngine();
    void accelerate();
}
public interface TimeMachine {
    void backToThePast();
    void backToTheFuture();
}

// Now Mustang implements just Car
public class Mustang implements Car {
 
    @Override
    public void startEngine() {
        // start engine...
    }
 
    @Override
    public void accelerate() {
        // accelerate...
    }
}

// MustangPlus implements both

public class MustangPlus implements Car, TimeMachine {
 
    @Override
    public void startEngine() {
        // start engine...
    }
 
    @Override
    public void accelerate() {
        // accelerate...
    }
 
    @Override
    public void backToThePast() {
        // back to de past...
    }
 
    @Override
    public void backToTheFuture() {
        // back to de future...
    }
}

// Now ISP is implemented!