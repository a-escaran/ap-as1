import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.InputMismatchException;

import org.junit.Test;

public class SMtest {
	        

	@Test
	public void addtest() {
		HashMap<Integer, Post> posts = new HashMap<>();  // Create the HashMap
        SMfunctions f = new SMfunctions();  // Create an instance of Functions2
        f.populateData(posts);  // Call populateData using the instance
        
		assertNotNull(posts);
	}

	@Test
    public void addtestex() {
        HashMap<Integer, Post> posts = new HashMap<>();
        SMfunctions f = new SMfunctions();
        
        System.setIn(new java.io.ByteArrayInputStream("Author name: \n".getBytes()));
        assertThrows(java.util.NoSuchElementException.class, () -> f.addPost(posts));
    }
	
	@Test
	public void remtest() {
		HashMap<Integer, Post> posts = new HashMap<>();  // Create the HashMap
        SMfunctions f = new SMfunctions();  // Create an instance of Functions2
        f.populateData(posts);  // Call populateData using the instance
        
        posts.remove(10);
        assertFalse(posts.containsKey(10));
		assertNull(posts.get(10));
	}
	
	@Test
    public void remtestec() {
		HashMap<Integer, Post> posts = new HashMap<>();
        SMfunctions f = new SMfunctions();

        // Simulate entering an invalid choice (non-integer input)
        String input = "-1\n"; 
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        f.remPost(posts);

    }

	
	@Test
	public void viewtest() {
		assertNotNull("Not yet implemented");
		HashMap<Integer, Post> posts = new HashMap<>();  // Create the HashMap
        SMfunctions f = new SMfunctions();  // Create an instance of Functions2
        f.populateData(posts);  // Call populateData using the instance
        
        
        assertTrue(posts.containsKey(10));
        assertNotNull(posts.get(10)); 
	}
	
    @Test
    public void viewtestec() {
        HashMap<Integer, Post> posts = new HashMap<>();
        SMfunctions f = new SMfunctions();

        // Simulate entering an invalid choice (non-integer input)
        String input = "-1\n"; // For example, -1 as an invalid choice
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        // Call the method
        f.viewPost(posts);

        // Perform assertions based on the expected behavior
        // For example, you can capture console output or check other behavior
    }

	

	
	@Test
	public void comtest() {
		HashMap<Integer, Post> posts = new HashMap<>();
        // Populate 'posts' with test data
        
        SMfunctions f = new SMfunctions();
        f.populateData(posts);  // Populate 'posts' with actual data
        
        assertNotNull(f.mostLikes(posts));
        assertEquals(2775, f.mostLikes(posts).getLikes());
        
        assertNotNull(f.mostShares(posts));
        assertEquals(13589, f.mostShares(posts).getShares());
    }
	}
	