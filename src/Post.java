import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Post {
    private int postID;
    private String content;
    private String author;
    private int likes;
    private int shares;
    private Date dateTime;
    private static int nextID = 1;

    public Post() {
        postID = nextID;
        nextID++;
    }

    public Post(int postID) {
        this.postID = postID;
    }

    public Post(int postID, String content) {
        this.postID = postID;
        this.content = content;
    }

    public Post(int postID, String content, String author) {
        this.postID = postID;
        this.content = content;
        this.author = author;
    }

    public Post(int postID, String content, String author, int likes) {
        this.postID = postID;
        this.content = content;
        this.author = author;
        this.likes = likes;
    }

    public Post(int postID, String content, String author, int likes, int shares) {
        this.postID = postID;
        this.content = content;
        this.author = author;
        this.likes = likes;
        this.shares = shares;
    }

    public Post(int postID, String content, String author, int likes, int shares, String dateTimeString) {
        try {
            this.postID = postID;
            this.content = content;
            this.author = author;
            this.likes = Math.max(0, likes);
            this.shares = Math.max(0, shares);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            this.dateTime = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            // Handle the exception here (e.g., print an error message)
            System.err.println("Error parsing date-time: " + dateTimeString);
        }
    }

    public Post(String content, String author, int likes, int shares, String dateTimeString) {
        try {
            postID = nextID;
            nextID++;
            this.content = content;
            this.author = author;
            this.likes = Math.max(0, likes);
            this.shares = Math.max(0, shares);
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            this.dateTime = dateFormat.parse(dateTimeString);
        } catch (ParseException e) {
            // Handle the exception here (e.g., print an error message)
            System.err.println("Error parsing date-time: " + dateTimeString);
        }
    }
    public int getPostID() {
        return postID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getShares() {
        return shares;
    }

    public void setShares(int shares) {
        this.shares = shares;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Post " + postID + "\nContent: " + content + "\nAuthor: " + author + "\nLikes: " + likes + "\nShares: "
                + shares + "\nDateTime: " + dateTime;
    }
}
