/**
 * Mediator pattern is used when we want to reduce chaotic communication between
 *  various objects in a system.
 * In such a situation, we want all communication to be handled by a central object called
 *  the mediator; no object communicates directly to each other.
 * This helps remove dependencies and enables reuse of objects.
 * In this example, the submit button and loginOrRegister checkbox don't communicate with
 *  each other, and neither do they communicate with the title.
 * All state changes of any object are notified to the mediator which in turn changes states
 * of other objects as required.
 * By doing this, we didn't have to write code of updating title in loginOrRegister checkbox, 
 * which means this checkbox can be reused somewhere else which doesn't need title related logic.
 */


//  https://www.baeldung.com/java-mediator-pattern (nice explanation in this blog post)

// Colleague Interface/abstract class
public abstract class User
{
    private IChatRoom mediator;
    
    private String id;
    private String name;
    
    public User(IChatRoom room, String id, String name){
        this.mediator = room;
        this.name = name;
        this.id = id;
    }
    
    public abstract void send(String msg, String userId);
    public abstract void receive(String msg);

    public IChatRoom getMediator() {
        return mediator;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}


// Colleague class
public class ChatUser extends User {
    
    public ChatUser(IChatRoom room, String id, String name) {
        super(room, id, name);
    }

    @Override
    public void send(String msg, String userId) {
        System.out.println(this.getName() + " :: Sending Message : " + msg);
        getMediator().sendMessage(msg, userId);
    }

    @Override
    public void receive(String msg) {
        System.out.println(this.getName() + " :: Received Message : " + msg);
    }

}

// Mediator Interface
public interface IChatRoom 
{
    public void sendMessage(String msg, String userId);
    public void addUser(User user);
}

// Concrete Mediator
public class ChatRoom implements IChatRoom {
    private Map<String, User> usersMap = new HashMap<>();

    @Override
    public void sendMessage(String msg, String userId) 
    {
        User u = usersMap.get(userId);
        u.receive(msg);
    }
    @Override
    public void addUser(User user) {
        this.usersMap.put(user.getId(), user);
    }
}

// Main class
public class Main 
{
    public static void main(String[] args) 
    {
        IChatRoom chatroom = new ChatRoom();
        
        User user1 = new ChatUser(chatroom,"1", "Alex");
        User user2 = new ChatUser(chatroom,"2", "Brian");
        User user3 = new ChatUser(chatroom,"3", "Charles");
        User user4 = new ChatUser(chatroom,"4", "David");
        
        chatroom.addUser(user1);
        chatroom.addUser(user2);
        chatroom.addUser(user3);
        chatroom.addUser(user4);

        user1.send("Hello brian", "2");
        user2.send("Hey buddy", "1");

        // Output
        // Alex :: Sending Message : Hello brian
        // Brian :: Received Message : Hello brian

        // Brian :: Sending Message : Hey buddy
        // Alex :: Received Message : Hey buddy
    }
}