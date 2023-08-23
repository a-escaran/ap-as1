import java.util.HashMap;

public class Sm_analyzer {

    public static void main(String[] args) {
        HashMap<Integer, Postv2> posts = new HashMap<>();  // Create the HashMap
        Functions2 f = new Functions2();  // Create an instance of Functions2
        f.populateData(posts);  // Call populateData using the instance
        f.menu(posts);  // Call menu with the HashMap as an argument using the instance
    }

}
