import java.util.Scanner;
	import java.util.ArrayList;
	import java.util.List;
	import java.util.Collections;
	import java.util.Comparator;
	import java.util.Date;
	import java.time.LocalDateTime;
	import java.time.format.DateTimeFormatter;
	import java.util.HashMap;
	import java.util.Map;
	import java.io.BufferedReader;  
	import java.io.FileReader;  
	import java.io.IOException;  
	
public class Test {
	private ArrayList<Post>post;


	public static void main(String[] args) {
		HashMap<Integer, Postv2> posts = new HashMap<>();
		
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
		  for (Postv2 post : posts.values()) {
	        System.out.println(post.toString());
		  }
	}

	public static void addLineToMap(HashMap<Integer, Postv2> map, int id, String content, String author, int likes,
	        int shares, String dateTime) {
	    Postv2 post = new Postv2(id, content, author, likes, shares, dateTime);
	    map.put(id, post);
	}
	
}
