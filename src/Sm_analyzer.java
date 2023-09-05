import java.util.HashMap;

public class Sm_analyzer {

    public static void main(String[] args) {
        HashMap<Integer, Post> posts = new HashMap<>();  // Create the HashMap
        SMfunctions f = new SMfunctions();  // Create an instance of Functions2
        f.populateData(posts);  // Call populateData using the instance
        f.menu(posts);  // Call menu with the HashMap as an argument using the instance
    }

}
