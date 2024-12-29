
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class MyLibraryModel {

	private Scanner scanner;
	private Book book;
	private ArrayList<Book> library = new ArrayList<>();

	public MyLibraryModel() {
        library = new ArrayList<>();
    }
	
	public ArrayList<Book> getLibrary() {
        return library;
    }

	/*
	 * The user will search for a book based on book title, author, or rating. We
	 * can make this interactive to where there can be questions asked about what
	 * you will want to search for instead of confusing an invalid input.
	 */
	public Book searchBooks() {
		System.out.println("How would you like to search for your book? (Title, Author, or Rating): ");
	    scanner = new Scanner(System.in);
	    String searchType = scanner.nextLine().toLowerCase().trim();

	    Book result = null;
	    
	    switch (searchType) {
	        case "title":
	            result = searchBookByTitle();
	            break;
	        case "author":
	            result = searchBookByAuthor();
	            break;
	        case "rating":
	            result = searchBookByRating();
	            break;
	        default:
	            System.out.println("Error: Invalid search type. Please try again.");
	            result = searchBooks();  // Call the search method again for a valid input
	    }

	    scanner.close();
	    return result;

	}

	Book searchBookByTitle() {
		// Get book title from user
		System.out.println("What is the title of the book you'd like to search for?");
		scanner = new Scanner(System.in);
		String title = scanner.nextLine().toLowerCase().trim();

		ArrayList<Book> booksFound = new ArrayList<>(); // Create empty array list to store books with matching data

		// Search through entire library for books with given title and add to
		// booksFound list
		for (Book book : library) {
			if (title.equals(book.getTitle().toLowerCase().trim())) {
				booksFound.add(book);
			}
		}

		// If booksFound list is empty, then print message and return null
		if (booksFound.isEmpty()) {
			System.out.println("No books found with the title: " + title);
			return null;
		}

		// If size of booksFound list is 1, then return found book
		if (booksFound.size() == 1) {
			System.out.println("Found one book: " + booksFound.get(0).toString());
			return booksFound.get(0);
		}

		// If there are multiple books with same title, print books as a numbered list
		System.out.println("Multiple books found with the title '" + title + "':");
		for (int i = 0; i < booksFound.size(); i++) {
			Book bookFound = booksFound.get(i);
			System.out.println((i + 1) + ". " + bookFound.getTitle() + " by " + bookFound.getAuthor());
		}

		// Narrow search by getting book author
		System.out.println("Who is the author of the book you are searching for?");
		System.out.println("Please enter their name to narrow search:");
		String author = scanner.nextLine().toLowerCase().strip();

		// Look through the list of books found
		for (Book book : booksFound) {
			// Check if there is a book with the matching author
			if (author.equals(book.getAuthor().toLowerCase().strip())) {
				System.out.println("Found the book: " + book);
				return book;
			}
		}

		// Return null if there is no book with given author and book title
		System.out.println("No book found with the title '" + title + "' and author '" + author + "'.");
		return null;
	}

	Book searchBookByAuthor() {
		// Get author name from user
		System.out.println("Who is the author of the book you'd like to search for?");
		scanner = new Scanner(System.in);
		String author = scanner.nextLine().toLowerCase().trim();

		ArrayList<Book> booksFound = new ArrayList<>(); // Create empty array list to store books with matching data

		// Search through entire library for books by given author and add to booksFound
		// list
		for (Book book : library) {
			if (author.equals(book.getAuthor().toLowerCase().trim())) {
				booksFound.add(book);
			}
		}

		// If booksFound list is empty, then print message and return null
		if (booksFound.isEmpty()) {
			System.out.println("No books found with the author: " + author);
			return null;
		}

		// If size of booksFound list is 1, then return found book
		if (booksFound.size() == 1) {
			System.out.println("Found one book: " + booksFound.get(0).toString());
			return booksFound.get(0);
		}

		// If there are multiple books by same author, print books as a numbered list
		System.out.println("Multiple books found with the author '" + author + "':");
		for (int i = 0; i < booksFound.size(); i++) {
			Book bookFound = booksFound.get(i);
			System.out.println((i + 1) + ". " + bookFound.getTitle() + " by " + bookFound.getAuthor());
		}

		// Narrow search by getting book title
		System.out.println("What is the of the book you are searching for?");
		System.out.println("Please enter the title to narrow search:");
		String title = scanner.nextLine().toLowerCase().strip();

		// Look through the list of books found
		for (Book book : booksFound) {
			// Check if there is a book with a matching title
			if (title.equals(book.getTitle().toLowerCase().strip())) {
				System.out.println("Found the book: " + book);
				return book;
			}
		}

		// Return null if there is no book with given author and book title
		System.out.println("No book found with the author '" + author + "' and title '" + title + "'.");
		return null;
	}

	Book searchBookByRating() {
		// Get rating from user
		System.out
				.println("How many stars has the book been rated? Enter a digit between 0-5 (0 is for unrated books)");
		scanner = new Scanner(System.in);
		int rating;

		// Validate input and make sure integer is between 0 and 5
		while (true) {
			try {
				rating = Integer.parseInt(scanner.nextLine().trim());
				if (rating < 0 || rating > 5) {
					System.out.println("Invalid input. Please enter a rating between 0 and 5.");
				} else {
					break; // Exit loop if input is valid
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a number between 0 and 5.");
				scanner.next(); // Clear invalid input
			}

		}

		ArrayList<Book> booksFound = new ArrayList<>(); // Create empty array list to store books with matching data

		// Search through entire library for books with given rating and add to
		// booksFound list
		for (Book book : library) {
			if (rating == book.getRating()) {
				booksFound.add(book);
			}
		}

		// If booksFound list is empty, then print message and return null
		if (booksFound.isEmpty()) {
			System.out.println("No books found with " + Integer.toString(rating) + " stars.");
			return null;
		}

		// If size of booksFound list is 1, then return found book
		if (booksFound.size() == 1) {
			System.out.println("Found one book: " + booksFound.get(0).toString());
			return booksFound.get(0);
		}

		// If there are multiple books with same rating, print books as a numbered list
		System.out.println("Multiple books found with " + Integer.toString(rating) + " stars:");
		for (int i = 0; i < booksFound.size(); i++) {
			Book bookFound = booksFound.get(i);
			System.out.println((i + 1) + ". " + bookFound.getTitle() + " by " + bookFound.getAuthor());
		}

		// Narrow search by getting book title
		System.out.println("What is the of the book you are searching for?");
		System.out.println("Please enter the title to narrow search:");
		String title = scanner.nextLine().toLowerCase().strip();

		// Also narrow search by getting book author
		System.out.println("Who is the author of the book you'd like to search for?");
		System.out.println("Please enter the author's name to narrow search:");
		String author = scanner.nextLine().toLowerCase().strip();

		// Look through the list of books found
		for (Book book : booksFound) {
			// Check if there is a book with a matching title
			if (title.equals(book.getTitle().toLowerCase().trim())
					&& author.equals(book.getAuthor().toLowerCase().trim())) {
				System.out.println("Found the book: " + book.toString());
				return book;
			}
		}

		// Return null if there is no book with given rating, author or book title
		System.out.println("No book found with the given data.");
		return null;
	}

	/*
	 * This is where the user will add a book to the library list with the required
	 * information : title, author, rating (optional).
	 */
	public void addBook() {
	    System.out.println("Book to add in format (Title:Author): ");
	    Scanner scanner = new Scanner(System.in);
	    String form = scanner.nextLine();
	    
	    if (!form.contains(":")) {
	        System.out.println("Error, wrong input form.");
	    }
	    String[] parts = form.split(":");

	    if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
	        System.out.println("Error, wrong input form.");
	    }
	    book = new Book(parts[0].trim(), parts[1].trim(), 0, false);
	    library.add(book);

	    System.out.println("Book added: " + book);
	    return;
	}

	/*
	 * Not sure if a String is appropriate-- but set the book that they Search() for
	 * to read , but there could be more than one book listed, so then another
	 * search is made to get one book. Then you display the text they want to read
	 * based on the book and close it at the end.
	 */
	public void readBook(Book book) {		
		book.setRead(); 

	}

	/*
	 * This function is used to search(), once book is found. Ask user for the
	 * rating 1-5 stars (check for valid input).
	 */
	public void rateBook(Book book) {
		System.out.println("What rating would you give it (1-5) stars: ");
		scanner = new Scanner(System.in);
		String rate = scanner + "";
		int rating = Integer.parseInt(rate);
		book.setRate(rating);
	}

	/*
	 * This uses the array list of books and displays the books sorted by title, then
	 * author, then if read or not. I think everything should be printed and each
	 * book in a new line.
	 */
	public void getBooks() {
	    MyLibraryController controller = new MyLibraryController();
	    System.out.println("How do you want to access your books -> Title, Author, Rating, Read or Unread");
	    Scanner scanner = new Scanner(System.in);
	    String form = scanner.nextLine();  // Read user input
	    
	    if (form.equalsIgnoreCase("Title")) {
	    	Collections.sort(library, Comparator.comparing(Book::getTitle));
	        controller.printList(library);
	    } else if (form.equalsIgnoreCase("Author")) {
	    	Collections.sort(library, Comparator.comparing(Book::getAuthor));
	    	controller.printList(library);
	    } else if (form.equalsIgnoreCase("Rating")) {
	    	Collections.sort(library, Comparator.comparing(Book::getRating));
	    	controller.printList(library);
	    } else if (form.equalsIgnoreCase("Read")) {
	        controller.printList(controller.getAllreadBooks(library));
	    } else if (form.equalsIgnoreCase("Unread")) {
	        controller.printList(controller.getAllUnreadBooks(library));
	    } else {
	        System.out.println("Error: You didn't type the given options.");
	    }
	    return;
	}


}

