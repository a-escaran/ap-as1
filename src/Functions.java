import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.lang.Math;

public class Functions {
private ArrayList<Post>post;


public Functions() {
	post = new ArrayList<>();
}

public void populatePosts() {
    post.add(new Post("Come and meet us at Building 14 of RMIT.", "SD2C5", 10, 24, "12/5/2023 10:10"));
    post.add(new Post("Check out this epic film.", "A567VF", 1000, 1587, "1/6/2023 14:25"));
    post.add(new Post("Check out this epic film.", "A567VF", 1000, 1587, "1/6/2023 14:25"));
    post.add(new Post("What a miracle!", "38726I", 2775, 13589, "12/2/2023 18:18"));
    post.add(new Post("Fantastic day today. Congratulations to all winners.", "1258XE", 230, 1214, "6/6/2023 21:00"));
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

public void menu() {
	printMenu();
	
	Scanner mn = new Scanner(System.in);
	System.out.println(" > Selection:  ");
	int choice = mn.nextInt();
	mn.nextLine();
	
	switch(choice) {
	case 1:
		System.out.println("Add post \n");
		addPost();
		menu();
		break;
	case 2:
		System.out.println("Remove Post ");
		remPost();
		menu();
		break;
	case 3:
		System.out.println("View Post ");
		viewPost();
		menu();
		break;
	case 4:
		System.out.println("Retrieve post with most Likes /n");
		likeMenu();
		menu();
		break;
	case 5:
		System.out.println("Retrieve post with most Shares ");
		shareMenu();
		menu();
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

public void addPost() {
    Scanner ap = new Scanner(System.in);
    System.out.println(" The Post Creator!  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" Content: ");
    System.out.println(" > ");
    String ct = ap.nextLine();
    System.out.println(" Author name: ");
    System.out.println(" > ");
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
    Post pt = new Post(ct, au, likes, shares, formattedDateTime);
    post.add(pt);

    System.out.println(" Post uploaded! ");
    System.out.println(" Post ID: " + pt.getPostID());

    System.out.println(" --------------------------------------------------------------------------------  ");
    menu();
}


public void remPost() {
	Scanner rp = new Scanner(System.in);
	System.out.println(" The Post Remover!  ");
    System.out.println(" --------------------------------------------------------------------------------  ");
    System.out.println(" Post ID: ");
    System.out.println(" > ");
    int pi = rp.nextInt();
    rp.nextLine();
    
    for (int i = 0; i < post.size(); i++) {
        Post p = post.get(i);
        if (p.getPostID() == pi) {
            post.remove(i);
            System.out.println("Post with ID " + pi + " has been removed.");
            return; // Exit the method after removing the post
        }
    }

    // If no post with the specified Post ID is found, display an error message
    System.out.println("Post with ID " + pi + " not found.");
}

public void viewPost() {
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
   
    boolean postFound = false;
    for(Post p : post) {
    	    switch(choice) {
			    case 1:
			    	System.out.println(p.toString() + "\n");
			    	break;
			    	
			    case 2:
			    	System.out.println(" Welcome to The Post Finder! ");
		            System.out.println(" --------------------------------------------------------------------------------  ");
		            System.out.println(" > POST: " + p.getPostID());
			    	System.out.print("Post ID: ");
	                int pid = vp.nextInt();
	                vp.nextLine();
	            	
	                if (p.getPostID() == pid) {
	                    System.out.println(" Welcome to The Post Finder! ");
		            	System.out.println(" --------------------------------------------------------------------------------  ");
		            	System.out.println(" > POST: " + p.getPostID());
		            	System.out.println(" > AUTHOR: " + p.getAuthor());
		            	System.out.println(" --------------------------------------------------------------------------------  ");
		            	System.out.println(" CONTENT: ");
		            	System.out.println(" " + p.getContent() + "\n\n");
		            	System.out.println(" --------------------------------------------------------------------------------  ");
		            	System.out.println(" Number of Likes: " + p.getLikes() + " ");
		            	System.out.println(" Number of Likes: " + p.getShares() + " ");	            	
		            	System.out.println(" --------------------------------------------------------------------------------  \n");
	                    postFound = true; // Set the flag to true as the post is found.
	                }
			    	break;
			    	
			    case 3:
			    	menu();
			    	break;
			    	
			    default:
			    	
			    	break;
			    }
    }

}

public void likeMenu() {
	Scanner lm = new Scanner(System.in);
	printLike();
	System.out.println(">Choice: ");
	int choice = lm.nextInt();
	lm.nextLine();
	
	switch(choice) {
	case 1:
		desLikes();
		menu();
		break;
		
	case 2:
		mostLikes();
		menu();
		break;
	
	default:
		System.out.println("Try again");
		break;
	}
	menu();
}

public void shareMenu() {
	Scanner sm = new Scanner(System.in);
	printLike();
	System.out.println(">Choice: ");
	int choice = sm.nextInt();
	sm.nextLine();
	
	switch(choice) {
	case 1:
		desShares();
		menu();
		break;
		
	case 2:
		mostShares();
		menu();
		break;
	
	default:
		System.out.println("Try again");
		break;
	}
	menu();
}

public void mostLikes() {
    int maxLikes = 0;
    int maxLikesPostID = -1;

    for (Post p : post) {
        int likes = p.getLikes();
        if (likes > maxLikes) {
            maxLikes = likes;
            maxLikesPostID = p.getPostID();
        }
    }

    if (maxLikesPostID != -1) {
        System.out.println("\nThe Most Liked Post!");
        System.out.println("--------------------------------------------------------------------------------");
        for (Post p : post) {
            if (p.getPostID() == maxLikesPostID) {
                System.out.println("> POST: " + p.getPostID());
                System.out.println("> AUTHOR: " + p.getAuthor());
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("CONTENT:");
                System.out.println(" " + p.getContent() + "\n\n");
                System.out.println("--------------------------------------------------------------------------------");
                System.out.println("Number of Likes: " + p.getLikes());
                System.out.println("Number of Shares: " + p.getShares());
                System.out.println("--------------------------------------------------------------------------------");
            }
        }
    } else {
        System.out.println("No posts found.");
    }
}

public void desLikes() {
    // Sort the posts in descending order of likes using a custom comparator
    Collections.sort(post, new Comparator<Post>() {
        @Override
        public int compare(Post p1, Post p2) {
            return Integer.compare(p2.getLikes(), p1.getLikes());
        }
    });

    for (Post p : post) {
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


public void mostShares() {
	 int maxShares = 0;
	 int maxSharesPostID = -1;

	    for (Post p : post) {
	        int shares = p.getShares();
	        if (shares > maxShares) {
	            maxShares = shares;
	            maxSharesPostID = p.getPostID();
	        }
	    }

	    if (maxSharesPostID != -1) {
	        System.out.println("\nThe Most Shared Post!");
	        System.out.println("--------------------------------------------------------------------------------");
	        for (Post p : post) {
	            if (p.getPostID() == maxSharesPostID) {
	                System.out.println("> POST: " + p.getPostID());
	                System.out.println("> AUTHOR: " + p.getAuthor());
	                System.out.println("--------------------------------------------------------------------------------");
	                System.out.println("CONTENT:");
	                System.out.println(" " + p.getContent() + "\n\n");
	                System.out.println("--------------------------------------------------------------------------------");
	                System.out.println("Number of Likes: " + p.getLikes());
	                System.out.println("Number of Shares: " + p.getShares());
	                System.out.println("--------------------------------------------------------------------------------");
	            }
	        }
	    } else {
	        System.out.println("No posts found.");
	    }
	}


public void desShares() {
	 // Sort the posts in descending order of likes using a custom comparator
    Collections.sort(post, new Comparator<Post>() {
        @Override
        public int compare(Post p1, Post p2) {
            return Integer.compare(p2.getShares(), p1.getShares());
        }
    });

    for (Post p : post) {
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
/*BIG GAMER PLAN
 * just fix the switch and the look of each post then youre ag
 * PRINTMENU
 * SCANNER/SWITCH
 * C1 = DESCENING CALLS (MOSTLIKE DES)
 * C2 = SPECIFIC MOST SHARE DESCENDING
 */



}
