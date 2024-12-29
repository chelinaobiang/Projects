/**
 * The Book class represents a book with a title, author, rating, and read status.
 * It implements the Comparable interface to allow for comparison between Book objects
 * based on their titles.
 *
 * Attributes:
 * - title (String): The title of the book.
 * - author (String): The author of the book.
 * - rating (integer): The rating of the book (assumed to be from 0 to 5).
 * - read (boolean): A flag indicating whether the book has been read.
 *
 * Methods:
 * - Constructor: Initializes a new Book instance with title, author, rating, and read status.
 * - Getters: Methods to retrieve the title, author, rating, and read status.
 * - setRead: Marks the book as read.
 * - setRate: Updates the book's rating.
 * - toString: Returns a string representation of the book including title, author, and rating.
 * - getStrRating: Returns a string representation of the rating using asterisks.
 * - titleCompareTo: Compares this book's title with another book's title.
 * - authorCompareTo: Compares this book's author with another book's author.
 * - ratingCompareTo: Compares this book's rating with another book's rating.
 * - compareTo: Implements the Comparable interface to compare books based on title.
 */
public class Book {
	private String title;
	private String author;
	private int rating;
	public boolean read;

	// Constructor
	public Book(String title, String author, int rating, boolean read) {
		this.title = title;
		this.author = author;
		this.rating = rating;
		this.read = read;
	}

	// Getters
	public String getTitle() {
		return title;
	}
	public String toString() {
		String str = "";
		str += title + " by: " + author + " ( " + getStrRating() + " )";
		return str;
	}
	
	public String getStrRating(){
		return ("*".repeat(rating));
	}

	public String getAuthor() {
		return author;
	}

	public int getRating() {
		return rating;
	}

	public boolean isRead() {
		return this.read;
	}

	public void setRead() {
		this.read = true;
		
	}

	public void setRate(int rating2) {
		this.rating = rating2;
		
	}

}
