import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * Create a HashMap to store Post object
 */
public class SMfunctions {
HashMap<Integer, Post> posts = new HashMap<>();

/**
 * This method populates a HashMap with Post objects by reading data from a CSV file.
 * Each line in the CSV file corresponds to a Post, containing information like ID, content,
 * author, likes, shares, and date-time. The CSV file is expected to have a header line which is skipped.
 * The parsed data is added to the HashMap using the addLineToMap method.
 */
public void populateData(HashMap<Integer, Post> posts) {
    String line = "";  
    String splitBy = ",";  // The separator used in the CSV file

    try {
        // Read the CSV file using BufferedReader
        BufferedReader br = new BufferedReader(new FileReader("posts.csv"));

        boolean isFirstLine = true;  

        while ((line = br.readLine()) != null) {  
            if (isFirstLine) {
                isFirstLine = false;
                continue; 
            }

            String[] postData = line.split(splitBy);

            int id = Integer.parseInt(postData[0]);
            String content = postData[1];
            String author = postData[2];
            int likes = Integer.parseInt(postData[3]);
            int shares = Integer.parseInt(postData[4]);
            String dateTime = postData[5];
            
            addLineToMap(posts, id, content, author, likes, shares, dateTime);
        }
        
        br.close(); 
    } catch (IOException e) {
        e.printStackTrace();  
    }
}

/**
 * Adds a Post object to the specified map using the provided data.
 */
public static void addLineToMap(HashMap<Integer, Post> map, int id, String content, String author, int likes, int shares, String dateTime) {
    Post post = new Post(id, content, author, likes, shares, dateTime);
    map.put(id, post);
}


/**
 * Displays a menu, takes user input for menu choice, and invokes corresponding methods.
 * Continuously prompts the user until a valid choice is made.
 *
 * @param posts The HashMap containing posts.
 */

public void menu(HashMap<Integer, Post> posts) {
	printMenu();
	
	Scanner mn = new Scanner(System.in);
	System.out.print(" > Selection:  ");
try {
	int choice = mn.nextInt();
	mn.nextLine();
	
	switch(choice) {
	case 1:
		System.out.println("\n");
		addPost(posts);
		menu(posts);
		break;
	case 2:
		System.out.println("\n");
		remPost(posts);
		menu(posts);
		break;
	case 3:
		System.out.println("\n");
		viewPost(posts);
		menu(posts);
		break;
	case 4:
		System.out.println("\n");
		likeMenu(posts);
		menu(posts);
		break;
	case 5:
		System.out.println("\n");
		shareMenu(posts);
		menu(posts);
		break;
	case 6:
		System.out.println("Exiting  \n");
		System.exit(0);
		break;
	default:
		System.out.println("Try again \n");
		break;
	}
}catch(InputMismatchException e) {
	 System.err.println("Invalid input. Please enter a valid integer.");
}
	
}

/**
 * This method allows users to create a new Post and add it to the provided HashMap of posts.
 * Users are prompted to enter the content and author name for the new Post.
 * The current date and time are automatically generated and added to the Post.
 * Random values for likes and shares are also generated and included in the Post.
 * The Post object is then added to the HashMap using its ID as the key.
 */
public void addPost(HashMap<Integer, Post> posts) {
    Scanner ap = new Scanner(System.in);
    System.out.println(" The Post Creator!  ");
    System.out.println(" --------------------------------------------------------------------------------  ");

    try {
        System.out.println(" Content: ");
        System.out.print(" > ");
        String ct = ap.nextLine();
        System.out.println(" Author name: ");
        System.out.print(" > ");
        String au = ap.nextLine();

        // Get the current date and time as a LocalDateTime
        LocalDateTime dateTime = LocalDateTime.now();

        // Generate random values for likes and shares between 1 and 100
        int likes = (int) (Math.random() * 100);
        int shares = (int) (Math.random() * 100);

        // Format the LocalDateTime object to the desired format "dd/MM/yyyy HH:mm"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        String formattedDateTime = dateTime.format(formatter);

        // Create the Post object with the entered content, author, generated likes, shares, and the current date-time
        Post post = new Post(ct, au, likes, shares, formattedDateTime);
        posts.put(post.getPostID(), post);

        System.out.println(" Post uploaded! ");
        System.out.println(" Post ID: " + post.getPostID());

    } catch (Exception e) {
        System.err.println("An error occurred: " + e.getMessage());
        System.out.println(" --------------------------------------------------------------------------------  ");
    } finally {
        divider();
        menu(posts);
    }
}


/**
 * This method allows users to remove a Post from the provided HashMap of posts based on its ID.
 * Users are prompted to enter the ID of the Post they want to remove.
 * If the specified Post ID exists in the HashMap, the corresponding Post is removed.
 * If the specified Post ID is not found, a message is displayed indicating that the Post was not found.
 * If the user enters invalid input (non-integer), an error message is displayed.
 */

public void remPost(HashMap<Integer, Post> posts) {
	Scanner rp = new Scanner(System.in);
	System.out.println(" The Post Remover!  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" Post ID: ");
    System.out.print(" > ");
try {
	 int pi = rp.nextInt();
    rp.nextLine();
    
    if (posts.containsKey(pi)) {
        posts.remove(pi);
        System.out.println("Post with ID " + pi + " has been removed.");
    } else {
        System.out.println("Post with ID " + pi + " not found.");
    }
}catch (InputMismatchException e){
	 System.err.println("Invalid input. Please enter a valid integer.");
}
  divider();
}


/**
 * This method allows users to view social media posts based on their choice.
 * Users are presented with options to either view all posts or find a specific post by ID.
 * After making a choice, users are prompted to enter necessary inputs.
 * If the input is invalid (non-integer), an error message is displayed.
 * Depending on the user's choice, the method either displays all posts or a specific post's details.
 * If a specific post ID is not found, a message is displayed indicating that the post was not found.
 */
public void viewPost(HashMap<Integer, Post> posts) {
    Scanner vp = new Scanner(System.in);
    System.out.println(" The Post Finder!  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" 1) View all social media post  ");
    System.out.println(" 2) Find specific social media post  ");
    System.out.println(" 2) Exit  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.print(" > ");
		try {
			int choice = vp.nextInt();
		    vp.nextLine();
		
		    switch (choice) {
		        case 1:
		        	for (Map.Entry<Integer, Post> entry : posts.entrySet()) {
		                System.out.println(entry.getValue().toString() + "\n");
		            }
		            break;
		
		        case 2:
		        	 System.out.print("Post ID: ");
		        	    int pid = vp.nextInt();
		        	    vp.nextLine();
		
		        	    if (posts.containsKey(pid)) {
		        	        Post p = posts.get(pid);
		        	        System.out.println(" \nWelcome to The Post Finder! ");
		        	        System.out.println(" --------------------------------------------------------------------------------  ");
		        	        System.out.println(" > POST: " + p.getPostID());
		        	        System.out.println(" > AUTHOR: " + p.getAuthor());
		        	        System.out.println(" --------------------------------------------------------------------------------  ");
		        	        System.out.println(" CONTENT: ");
		        	        System.out.println(" " + p.getContent() + "\n\n");
		        	        System.out.println(" --------------------------------------------------------------------------------  ");
		        	        System.out.println(" Number of Likes: " + p.getLikes() + " ");
		        	        System.out.println(" Number of Shares: " + p.getShares() + " ");                    
		        	        System.out.println(" --------------------------------------------------------------------------------  \n");
		        	    } else {
		        	        System.out.println("Post with ID " + pid + " not found.");
		        	    }
		            break;
		
		        case 3:
		        	divider();
		            menu(posts); // Assuming this is your menu method.
		            break;
		
		        default:
		            // Handle invalid choice.
		            break;
		    }
		}catch(InputMismatchException e) {
			 System.err.println("Invalid input. Please enter a valid integer.");
		}
	divider();
}

/**
 * Finds and returns the post with the highest number of likes among the given social media posts.
 * If no posts are available, it displays a message indicating that no posts are found.
 * Prints the details of the most liked post, including post ID, author, content, likes, and shares.
 */
public Post mostLikes(HashMap<Integer, Post> posts) {
    int maxLikes = 0;
    int maxLikesPostID = -1;

    for (Map.Entry<Integer, Post> entry : posts.entrySet()) {
        Post p = entry.getValue();
        int likes = p.getLikes();
        if (likes > maxLikes) {
            maxLikes = likes;
            maxLikesPostID = p.getPostID();
        }
    }

    if (maxLikesPostID != -1) {
        System.out.println("\nThe Most Liked Post!");
        System.out.println("--------------------------------------------------------------------------------");
        Post mostLikedPost = posts.get(maxLikesPostID);
        System.out.println("> POST: " + mostLikedPost.getPostID());
        System.out.println("> AUTHOR: " + mostLikedPost.getAuthor());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("CONTENT:");
        System.out.println(" " + mostLikedPost.getContent() + "\n\n");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Number of Likes: " + mostLikedPost.getLikes());
        System.out.println("Number of Shares: " + mostLikedPost.getShares());
        System.out.println("--------------------------------------------------------------------------------");
    } else {
        System.out.println("No posts found.");
    }
    divider();
    return posts.get(maxLikesPostID);
}

/**
 * Displays the details of all social media posts sorted in descending order of likes.
 * Prints post details including post ID, author, content, likes, and shares for each post.
 */
public void desLikes(HashMap<Integer, Post> posts) {
    List<Post> sortedPosts = new ArrayList<>(posts.values());

    // Sort the posts in descending order of likes using a custom comparator
    Collections.sort(sortedPosts, new Comparator<Post>() {
        @Override
        public int compare(Post p1, Post p2) {
            return Integer.compare(p2.getLikes(), p1.getLikes());
        }
    });

    for (Post p : sortedPosts) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("> POST: " + p.getPostID());
        System.out.println("> AUTHOR: " + p.getAuthor());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("CONTENT:");
        System.out.println(" " + p.getContent() + "\n\n");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Number of Likes: " + p.getLikes());
        System.out.println("Number of Shares: " + p.getShares());
        System.out.println("--------------------------------------------------------------------------------\n\n");
    }
}


/**
 * Finds and displays the details of the post with the most shares.
 * Prints post details including post ID, author, content, likes, and shares for the most shared post.
 */
public Post mostShares(HashMap<Integer, Post> posts) {
    int maxShares = 0;
    int maxSharesPostID = -1;

    for (Map.Entry<Integer, Post> entry : posts.entrySet()) {
        Post p = entry.getValue();
        int shares = p.getShares();
        if (shares > maxShares) {
            maxShares = shares;
            maxSharesPostID = p.getPostID();
        }
    }

    if (maxSharesPostID != -1) {
        System.out.println("\nThe Most Shared Post!");
        System.out.println("--------------------------------------------------------------------------------");
        Post mostSharedPost = posts.get(maxSharesPostID);
        System.out.println("> POST: " + mostSharedPost.getPostID());
        System.out.println("> AUTHOR: " + mostSharedPost.getAuthor());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("CONTENT:");
        System.out.println(" " + mostSharedPost.getContent() + "\n\n");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Number of Likes: " + mostSharedPost.getLikes());
        System.out.println("Number of Shares: " + mostSharedPost.getShares());
        System.out.println("--------------------------------------------------------------------------------");
    } else {
        System.out.println("No posts found.");
    }
    divider();
    return posts.get(maxSharesPostID);
   
}

/**
 * Displays the details of all posts in descending order based on the number of shares.
 * Prints post details including post ID, author, content, likes, and shares for each post.
 * The posts are sorted in descending order based on the number of shares.
 */
public void desShares(HashMap<Integer, Post> posts) {
    List<Post> sortedPosts = new ArrayList<>(posts.values());

    Collections.sort(sortedPosts, new Comparator<Post>() {
        @Override
        public int compare(Post p1, Post p2) {
            return Integer.compare(p2.getShares(), p1.getShares());
        }
    });

    for (Post p : sortedPosts) {
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("> POST: " + p.getPostID());
        System.out.println("> AUTHOR: " + p.getAuthor());
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("CONTENT:");
        System.out.println(" " + p.getContent() + "\n\n");
        System.out.println("--------------------------------------------------------------------------------");
        System.out.println("Number of Likes: " + p.getLikes());
        System.out.println("Number of Shares: " + p.getShares());
        System.out.println("--------------------------------------------------------------------------------\n\n");
    }
    divider();
}

//switch for like menu
public void likeMenu(HashMap<Integer, Post> posts) {
	Scanner lm = new Scanner(System.in);
	printLike();
	System.out.print(">Choice: ");
try {
	int choice = lm.nextInt();
	lm.nextLine();
	
	switch(choice) {
	case 1:
		desLikes(posts);
		divider();
		menu(posts);
		break;
		
	case 2:
		mostLikes(posts);
		divider();
		menu(posts);
		break;
	
	default:
		System.out.println("Try again");
		break;
	}
}catch(InputMismatchException e) {
	 System.err.println("Invalid input. Please enter a valid integer.");
}
divider();
	menu(posts);
}

//prints menu for share function
public void shareMenu(HashMap<Integer, Post> posts) {
	Scanner sm = new Scanner(System.in);
	printShare();
	System.out.print(">Choice: ");
try {
	int choice = sm.nextInt();
	sm.nextLine();
	
	switch(choice) {
	case 1:
		desShares(posts);
		divider();
		menu(posts);
		break;
		
	case 2:
		mostShares(posts);
		divider();
		menu(posts);
		break;
	
	default:
		System.out.println("Try again");
		break;
	}
}catch(InputMismatchException e) {
	 System.err.println("Invalid input. Please enter a valid integer.");
}
divider();
	menu(posts);
}


//Welcome to the Menu Section

//prints main menu
public void printMenu() {
	System.out.println("\n Welcome to Social Media Analyzer!  ");
	System.out.println(" --------------------------------------------------------------------------------  ");
	System.out.println(" > Select from main menu  ");
	System.out.println(" --------------------------------------------------------------------------------  ");
	System.out.println(" 1) Add a social media post  ");
	System.out.println(" 2) Delete an existing social media post  ");
	System.out.println(" 3) Retrieve a social media post  ");
	System.out.println(" 4) Retrieve the top N posts with most likes  ");
	System.out.println(" 5) Retrieve the top N posts with most shares  ");
	System.out.println(" 6) Exit ");
	System.out.println(" --------------------------------------------------------------------------------  ");
}


//prints like main menu
public void printLike() {
	System.out.println(" The Post Finder! (Likes) ");
    System.out.println(" --------------------------------------------------------------------------------  ");
	System.out.println(" 1) View all social media post  ");
	System.out.println(" 2) Find Top Liked social media post  ");
	System.out.println(" 2) Exit  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" > ");

}

//prints share menu
public void printShare() {
	System.out.println(" The Post Finder! (Shares) ");
    System.out.println(" --------------------------------------------------------------------------------  ");
	System.out.println(" 1) View all social media post  ");
	System.out.println(" 2) Find Top Shared social media post  ");
	System.out.println(" 2) Exit  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" > ");
}

//divider i made to make end of process more easy to see 
public void divider() {
	 System.out.println("\n -------------------------------------------------------------------------------- ");
	 System.out.println(" --------------------------------END OF PROCESS----------------------------------\n ");
}
}
