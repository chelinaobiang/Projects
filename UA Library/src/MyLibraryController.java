/**
 * The MyLibraryController class manages a few of the operations for a library system,
 * interacting with the Model and handling functionalities related to books in the library.
 *
 * Attributes:
 * - satisfied (boolean): Indicates whether the user is satisfied with the library commands.
 * - model (MyLibraryModel): The model that represents the library's data.
 * - book (Book): A temporary Book object used for recommendations or additions.
 * - input (Scanner): A Scanner for user input from the console for interactions.
 *
 * Methods:
 * - isSatisfied: Returns the current satisfaction status of the user.
 * - setSatisfaction: Sets the satisfaction status to true to end program.
 * - getAllUnreadBooks: Returns a sorted list of all unread books in the library.
 * - getAllreadBooks: Returns a sorted list of all read books in the library.
 * - suggestRead: Suggests a random unread book from the library.
 * - addBooks: Adds books to the library from a specified text file, skipping headers
 *   and parsing each book's title and author gathered from each line.
 *
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class MyLibraryController{
	
	private boolean satisfied = false;
	private MyLibraryModel model;
	private Book book;
	
	public MyLibraryController() {
        model = new MyLibraryModel();
    }
	
	/*
	 * Checks if the user is satisfied with the library experience. Then returns true if 
	 * the user is satisfied; false otherwise.
	 */
	public boolean isSatisfied() {
	      return this.satisfied;
	}
	/*
	 * Converts the satisfaction to true.
	 */
	public void setSatisfaction() {
		this.satisfied = true;
	}
	/*
	 * Retrieves all unread books from the library.
	 * 
	 * This method iterates through the provided list of books, collecting 
	 * those that have not been read. It then sorts the unread books by title 
	 * before returning them as a new ArrayList.
	 * 
	 * Parameter: library An ArrayList of Book objects representing the library.
	 * Returns An ArrayList of unread Book objects, sorted by title.
	 */
	public ArrayList<Book> getAllUnreadBooks(ArrayList<Book> library) {
		ArrayList<Book> unread = new ArrayList<>();
		for(Book b: library) {
			if(!(b.isRead())) {
				unread.add(b);
			}
		}
		Collections.sort(unread, Comparator.comparing(Book::getTitle));
		return unread;
	}
	/*
	 * Retrieves all read books from the library.
	 * 
	 * This method iterates through the provided list of books, collecting 
	 * those that have been read. It then sorts the read books by title 
	 * before returning them as a new ArrayList.
	 * 
	 * Parameter: library An ArrayList of Book objects representing the library.
	 * Returns An ArrayList of read Book objects, sorted by title.
	 */
	public ArrayList<Book> getAllreadBooks(ArrayList<Book> library) {
		ArrayList<Book> readBooks = new ArrayList<>();
		for(Book b: library) {
			if(b.isRead() == true) {
				readBooks.add(b);
			}
		}
		Collections.sort(readBooks, Comparator.comparing(Book::getTitle));
		return readBooks;
	}
	
	public void printList(ArrayList<Book> books) {
	    if (books.isEmpty()) {
	        System.out.println("No books to display.");
	    }
	    else {
	    for (Book b : books) {
	        System.out.println(b.toString());
	    }}
	}
	/*
	 * Suggests a random unread book from the library.
	 * 
	 * This method first retrieves the list of unread books using 
	 * the getAllUnreadBooks method. It then randomly selects one 
	 * unread book and prints its details as a recommendation to the user.
	 */
	public void suggestRead() {
		ArrayList<Book> library = model.getLibrary();
		ArrayList<Book> unread = getAllUnreadBooks(library);
		
		if (unread.size() == 0) {
			System.out.println("There are no available books.");
			return;
		} else {
		Random rand = new Random();
		int randomInt = rand.nextInt(unread.size());
		book = unread.get(randomInt);

		System.out.println("The book I recommend is : " + book.toString());
		}
		return;
	}
	
	/*
	 * Adds multiple books to the library from a specified text file.
	 * 
	 * This method prompts the user for a text file name, reads the file line by line, 
	 * and parses each line to extract book titles and authors. Each book is created 
	 * as a new Book object with a default rating of 0 and a read status of false, 
	 * and then added to the library's collection.
	 * 
	 * The method skips the first line of the file, which is assumed to contain headers.
	 * If a line does not contain exactly two pieces of information (title and author), 
	 * that line is ignored. 
	 * 
	 * If the specified file cannot be found, an error message is displayed.
	 * 
	 * Parameter: fileName The name of the text file containing book data. The expected format 
	 *                 for each line is "title;author".
	 */
	public void addBooks() {
		ArrayList<Book> library = model.getLibrary();
		
		Scanner inputScanner = new Scanner(System.in);
        System.out.println("Type the name of the text file that contains the books you would like to add: ");
        String fileName = inputScanner.nextLine();
		
		try {
			File file = new File(fileName); 
			Scanner fileScanner = new Scanner(file);
			while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] details = line.split(";");
                if (details.length == 2) {
                    String title = details[0].toLowerCase().trim();
                    String author = details[1].toLowerCase().trim();
                    book = new Book(title, author, 0, false);
            		library.add(book);
                }
                System.out.println("Book added successfully.");
            } 
			
		} catch (FileNotFoundException e) {
                System.out.println("File not found: " + fileName);
            }
	}
}