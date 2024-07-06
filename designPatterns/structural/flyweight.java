/**
 * This pattern is used when we need to create a lot of objects of same class with very minimal 
 * differences in properties.
 * It is used if we want to reduce memory usage or prevent memory leakage.
 * It helps redcing memory by sharing memory over multiple objects.
 * Each object requires memory, so it is efficient to create one object for a certain set of 
 * properties and reuse is wherever possible.
 * Ofcourse these objects should be immutable so as to avoid any unintentional behavior anywhere 
 * they get used.
 * In this example, say we are creating a terrain for a game. It can be viewed as a grid with 
 * different elements in each cell.
 * Most of these elements will be immutable.
 * So it is way more efficient to create 1 instance of each element and render it multiple times on the terrain.`
 */

//  Example taken from shrayansh jain

 import java.util.HashMap;
 import java.util.Map;
 import java.util.ArrayList;
 

//  driver method

//  interface
public interface IRobot {

    public void display(int x, int y);
}

 
//  concrete classes implementing interface
// private variables + only setters = immutable class
// Making a class immutable is a step for flyweight
// The resultant class is known as the flyweight class

public class HumanoidRobot implements IRobot {

    private String type;
    private Sprites body; //small 2d bitmap (graphic element)

    HumanoidRobot(String type, Sprites body){
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public Sprites getBody() {
        return body;
    }

    @Override
    public void display(int x, int y) {

        //use the humanoid sprites object
        // and X and Y coordinate to render the image.

    }
}

// Assume the implementation of Sprites is done here
public class Sprites {
}


// factory where the caching is done
public class RoboticFactory {

    private static Map<String, IRobot> roboticObjectCache = new HashMap<>();

    public static IRobot createRobot(String robotType){

        if(roboticObjectCache.containsKey(robotType)){
            return roboticObjectCache.get(robotType);
        }
        else {
            if(robotType == "HUMANOID"){
                Sprites humanoidSprite = new Sprites();
                IRobot humanoidObject = new HumanoidRobot(robotType, humanoidSprite);
                roboticObjectCache.put(robotType, humanoidObject);
                return humanoidObject;
            }
            else if(robotType == "ROBOTICDOG"){
                Sprites roboticDogSprite = new Sprites();
                IRobot roboticDogObject = new RoboticDog(robotType, roboticDogSprite);
                roboticObjectCache.put(robotType, roboticDogObject);
                return roboticDogObject;
            }
        }
        return null;
    }
}




public class RoboticDog implements IRobot{

    private String type;
    private Sprites body; //small 2d bitmap (graphic element)

    RoboticDog(String type, Sprites body){
        this.type = type;
        this.body = body;
    }

    public String getType() {
        return type;
    }

    public Sprites getBody() {
        return body;
    }

    @Override
    public void display(int x, int y) {

        //use the Robotic Dog sprites object
        // and X and Y coordinate to render the image.

    }
}





 
//  the main class where variables are made private 
// This class has only getter methods (private + only getters = immutable)

public class Main {

    public static void main(String args[]){

        IRobot humanoidRobot1 = RoboticFactory.createRobot("HUMANOID");
        humanoidRobot1.display(1,2);


        IRobot humanoidRobot2 = RoboticFactory.createRobot("HUMANOID");
        humanoidRobot2.display(10,30);

        IRobot roboDog1 = RoboticFactory.createRobot("ROBOTICDOG");
        roboDog1.display(2,9);

        IRobot roboDog2 = RoboticFactory.createRobot("ROBOTICDOG");
        roboDog2.display(11,19);

    }
}


