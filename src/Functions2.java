import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.Math;
import java.util.HashMap;
import java.util.Map;

//probs going to need to chang this shit above

public class Functions2 {
private ArrayList<Post>post;
HashMap<Integer, Postv2> posts = new HashMap<>();

public void populateData(HashMap<Integer, Postv2> posts) {
	
	String line = "";  
	String splitBy = ",";  
	try   {  
		//parsing a CSV file into BufferedReader class constructor  
		BufferedReader br = new BufferedReader(new FileReader("posts.csv"));  
		boolean isFirstLine = true;  // Flag to skip the header line
		while ((line = br.readLine()) != null){  
			  if (isFirstLine) {
                    isFirstLine = false;
                    continue;  // Skip the header line
			  }
			String[] postData = line.split(splitBy);    // use comma as separator  
			int id = Integer.parseInt(postData[0]);
			String content = postData[1];
			String author = postData[2];
			int likes = Integer.parseInt(postData[3]);
			int shares = Integer.parseInt(postData[4]);
			String dateTime = postData[5];
			
			addLineToMap(posts, id, content, author, likes, shares, dateTime);
		}  
	} catch (IOException e) {  
		e.printStackTrace();  
	}  
}

public static void addLineToMap(HashMap<Integer, Postv2> map, int id, String content, String author, int likes,
        int shares, String dateTime) {
    Postv2 post = new Postv2(id, content, author, likes, shares, dateTime);
    map.put(id, post);
}


public void printMenu() {
	System.out.println(" Welcome to Social Media Analyzer!  ");
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

public void menu(HashMap<Integer, Postv2> posts) {
	printMenu();
	
	Scanner mn = new Scanner(System.in);
	System.out.println(" > Selection:  ");
	int choice = mn.nextInt();
	mn.nextLine();
	
	switch(choice) {
	case 1:
		System.out.println("Add post \n");
		addPost(posts);
		menu(posts);
		break;
	case 2:
		System.out.println("Remove Post ");
		remPost(posts);
		menu(posts);
		break;
	case 3:
		System.out.println("View Post ");
		viewPost(posts);
		menu(posts);
		break;
	case 4:
		System.out.println("Retrieve post with most Likes /n");
		likeMenu(posts);
		menu(posts);
		break;
	case 5:
		System.out.println("Retrieve post with most Shares ");
		shareMenu(posts);
		menu(posts);
		break;
	case 6:
		System.out.println("Exit ");
		System.exit(0);
		break;
	default:
		System.out.println("Try again ");
		break;
	}
}

//literally the same thing but with hashmap
// probs just fix the end?
public void addPost(HashMap<Integer, Postv2> posts) {
    Scanner ap = new Scanner(System.in);
    System.out.println(" The Post Creator!  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" Content: ");
    System.out.print(" > ");
    String ct = ap.nextLine();
    System.out.println(" Author name: ");
    System.out.print(" > ");
    String au = ap.nextLine();

    // Get the current date and time as a LocalDateTime
    LocalDateTime dateTime = LocalDateTime.now();

    // Generate random values for likes and shares
    int likes = (int) (Math.random() * 100); // Generate a random value between 0 and 99
    int shares = (int) (Math.random() * 100); // Generate a random value between 0 and 99

    // Format the LocalDateTime object to the desired format "dd/MM/yyyy HH:mm"
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    String formattedDateTime = dateTime.format(formatter);

 // Create the Post object with the entered content, author, generated likes, shares, and the current date-time
    Postv2 post = new Postv2(ct, au, likes, shares, formattedDateTime);
    posts.put(post.getPostID(), post);

    System.out.println(" Post uploaded! ");
    System.out.println(" Post ID: " + post.getPostID());

    System.out.println(" --------------------------------------------------------------------------------  ");
    menu(posts);
}


//change the finder so that it finds the key (pid)
public void remPost(HashMap<Integer, Postv2> posts) {
	Scanner rp = new Scanner(System.in);
	System.out.println(" The Post Remover!  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" Post ID: ");
    System.out.println(" > ");
    int pi = rp.nextInt();
    rp.nextLine();
    
    if (posts.containsKey(pi)) {
        posts.remove(pi);
        System.out.println("Post with ID " + pi + " has been removed.");
    } else {
        System.out.println("Post with ID " + pi + " not found.");
    }
}

//same shit lol
public void viewPost(HashMap<Integer, Postv2> posts) {
    Scanner vp = new Scanner(System.in);
    System.out.println(" The Post Finder!  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" 1) View all social media post  ");
    System.out.println(" 2) Find specific social media post  ");
    System.out.println(" 2) Exit  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" > ");
    int choice = vp.nextInt();
    vp.nextLine();

    switch (choice) {
        case 1:
        	for (Map.Entry<Integer, Postv2> entry : posts.entrySet()) {
                System.out.println(entry.getValue().toString() + "\n");
            }
            break;

        case 2:
        	 System.out.print("Post ID: ");
        	    int pid = vp.nextInt();
        	    vp.nextLine();

        	    if (posts.containsKey(pid)) {
        	        Postv2 p = posts.get(pid);
        	        System.out.println(" Welcome to The Post Finder! ");
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
            menu(posts); // Assuming this is your menu method.
            break;

        default:
            // Handle invalid choice.
            break;
    }
}


public void likeMenu(HashMap<Integer, Postv2> posts) {
	Scanner lm = new Scanner(System.in);
	printLike();
	System.out.println(">Choice: ");
	int choice = lm.nextInt();
	lm.nextLine();
	
	switch(choice) {
	case 1:
		desLikes(posts);
		menu(posts);
		break;
		
	case 2:
		mostLikes(posts);
		menu(posts);
		break;
	
	default:
		System.out.println("Try again");
		break;
	}
	menu(posts);
}

public void shareMenu(HashMap<Integer, Postv2> posts) {
	Scanner sm = new Scanner(System.in);
	printLike();
	System.out.println(">Choice: ");
	int choice = sm.nextInt();
	sm.nextLine();
	
	switch(choice) {
	case 1:
		desShares(posts);
		menu(posts);
		break;
		
	case 2:
		mostShares(posts);
		menu(posts);
		break;
	
	default:
		System.out.println("Try again");
		break;
	}
	menu(posts);
}

// all below is fucky wucky. just fix it lol
public void mostLikes(HashMap<Integer, Postv2> posts) {
    int maxLikes = 0;
    int maxLikesPostID = -1;

    for (Map.Entry<Integer, Postv2> entry : posts.entrySet()) {
        Postv2 p = entry.getValue();
        int likes = p.getLikes();
        if (likes > maxLikes) {
            maxLikes = likes;
            maxLikesPostID = p.getPostID();
        }
    }

    if (maxLikesPostID != -1) {
        System.out.println("\nThe Most Liked Post!");
        System.out.println("--------------------------------------------------------------------------------");
        Postv2 mostLikedPost = posts.get(maxLikesPostID);
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
}

public void desLikes(HashMap<Integer, Postv2> posts) {
    List<Postv2> sortedPosts = new ArrayList<>(posts.values());

    // Sort the posts in descending order of likes using a custom comparator
    Collections.sort(sortedPosts, new Comparator<Postv2>() {
        @Override
        public int compare(Postv2 p1, Postv2 p2) {
            return Integer.compare(p2.getLikes(), p1.getLikes());
        }
    });

    for (Postv2 p : sortedPosts) {
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



public void mostShares(HashMap<Integer, Postv2> posts) {
    int maxShares = 0;
    int maxSharesPostID = -1;

    for (Map.Entry<Integer, Postv2> entry : posts.entrySet()) {
        Postv2 p = entry.getValue();
        int shares = p.getShares();
        if (shares > maxShares) {
            maxShares = shares;
            maxSharesPostID = p.getPostID();
        }
    }

    if (maxSharesPostID != -1) {
        System.out.println("\nThe Most Shared Post!");
        System.out.println("--------------------------------------------------------------------------------");
        Postv2 mostSharedPost = posts.get(maxSharesPostID);
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
}

public void desShares(HashMap<Integer, Postv2> posts) {
    List<Postv2> sortedPosts = new ArrayList<>(posts.values());

    // Sort the posts in descending order of shares using a custom comparator
    Collections.sort(sortedPosts, new Comparator<Postv2>() {
        @Override
        public int compare(Postv2 p1, Postv2 p2) {
            return Integer.compare(p2.getShares(), p1.getShares());
        }
    });

    for (Postv2 p : sortedPosts) {
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


public void printLike() {
	System.out.println(" The Post Finder! (Likes) ");
    System.out.println(" --------------------------------------------------------------------------------  ");
	System.out.println(" 1) View all social media post  ");
	System.out.println(" 2) Find Top Liked social media post  ");
	System.out.println(" 2) Exit  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" > ");

}

public void printShare() {
	System.out.println(" The Post Finder! (Shares) ");
    System.out.println(" --------------------------------------------------------------------------------  ");
	System.out.println(" 1) View all social media post  ");
	System.out.println(" 2) Find Top Shared social media post  ");
	System.out.println(" 2) Exit  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" > ");
}

public void divider() {
	 System.out.println("\n -------------------------------------------------------------------------------- ");
	 System.out.println("--------------------------------END OF PROCESS----------------------------------\n ");
}
}
