// --------------------------------------------------------------
// 2 -> Open Closed Principle (OCP):

// Software entities (classes, modules, functions, etc.) should be open for extension,
//  but closed for modification
// We can use the abstraction and polymorphism to help us apply this principle.

public class Rectangle {
 
    private int width;
    private int height;
 
    // getter and setter methods...
}

public class Square {
 
    private int side;
 
    // getter and setter methods...
}

public class ShapePrinter {
 
    public void drawShape(Object shape) {
 
        if (shape instanceof Rectangle) {
            // Draw Rectangle...
        } else if (shape instanceof Square) {
            // Draw Square...
        }
    }
}

// We can see that every time we want to draw a distinct shape we will have to 
// modify the drawShape method of the ShapePrinter to accept a new shape.

// Therefore the ShapePrinter class is not closed for modification.

// Solution 
// 1 - Make shape abstract

public abstract class Shape {
    abstract void draw();
}

public class Rectangle extends Shape {
 
    private int width;
    private int height;
 
    // getter and setter methods...
 
    @Override
    public void draw() {
        // Draw the Rectangle...
    }
}


public class Square extends Shape {
 
    private int side;
    // getter and setter methods...
 
    @Override
    public void draw() {
        // Draw the Square
    }
}

public class ShapePrinter {
    public void drawShape(Shape shape) {
        shape.draw();
    }
}

// Now the ShapePrinter class remains intact when we add a new shape type.
// The existing code is not modified.

// Solution 
// 2 - Change shape into interface

public interface Shape {
    void draw();
}

public class Rectangle implements Shape {
 
    private int width;
    private int height;
 
    // getter and setter methods...
 
    @Override
    public void draw() {
        // Draw the Rectangle...
    }
}

public class Square implements Shape {
 
    private int side;
 
    // getter and setter methods...
 
    @Override
    public void draw() {
        // Draw the Square
    }
}

public class ShapePrinter {
 
    public void drawShape(Shape shape) {
        shape.draw();
    }
}