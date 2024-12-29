
/* Genesis Benedith & Chelina Obiang
 * Course: CSC 335 - Fall 2024
 * This file contains a main method that runs a text-based 
 * user interface where the user can enter commands to get 
 * or add information to the library
 */
import java.util.Scanner;

public class MyLibrary {
	private static MyLibraryModel model;
	private static MyLibraryController controller;

	public MyLibrary() {
		
	}

	public static void main(String[] args) {
		controller = new MyLibraryController();
		model = new MyLibraryModel();
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Welcome to the UA Library!\n");
		
		while(!controller.isSatisfied()) {
			System.out.println("What would you like to do? Enter a number from 1-7 for the following commands:");
			System.out.println("1- search, 2- rate, 3- add a book, 4- add books, 5- read book, 6- get list of books, 7- get suggested book");
			int answer = scanner.nextInt();
			switch (answer) {
				case 1: // Call searchBooks to search library by various search types
					
					Book book = model.searchBooks();
					
					if (book == null) {
						System.out.println("Would you like to search again? (y/n)");
						scanner = new Scanner(System.in);
					    String searchAgain = scanner.nextLine().toLowerCase().trim(); 
					    if (searchAgain.equals("y")) {
                            // Call the search again
                            book = model.searchBookByTitle();
                        }
                    }

                    if (book != null) {
                    	System.out.println(book.toString()); // Print string format of string details
                    } else {
                        System.out.println("No book selected. Returning to the main menu.");
                    }
                    break;
					
					
				
				case 2: // Rate's a book, if found
					
					book = model.searchBookByTitle();
					if (book == null) {
						System.out.println("Would you like to search again? (y/n)");
						scanner = new Scanner(System.in);
					    String searchAgain = scanner.nextLine().toLowerCase().trim(); 
					    if (searchAgain.equals("y")) {
                            // Call the search again
                            book = model.searchBookByTitle();
                        }
                    }

                    if (book != null) {
                        model.rateBook(book);  // If a book is found, proceed with rating book
                    } else {
                        System.out.println("No book selected. Returning to the main menu.");
                    }
                    break;
				
				case 3:
					model.addBook();
					break;
				case 4: 
					controller.addBooks();
					break;
				
				case 5: // Changes a book's status to read if found
					
					book = model.searchBooks();
					if (book == null) {
						System.out.println("Would you like to search again? (y/n)");
						scanner = new Scanner(System.in);
					    String searchAgain = scanner.nextLine().toLowerCase().trim(); 
					    if (searchAgain.equals("y")) {
                            // Call the search again
                            book = model.searchBookByTitle();
                        }
                    }

                    if (book != null) {
                        model.readBook(book);  // If a book is found, proceed with changing book status to read
                     
                    } else {
                        System.out.println("No book selected. Returning to the main menu.");
                    }
                    break;
				case 6:
					model.getBooks();
					break;
				case 7:
					controller.suggestRead();
					break;
				default:
                    System.out.println("Invalid command. Try again.");
                    
			}
			System.out.println("Is there anything else you would like to do? (y/n)");
			scanner = new Scanner(System.in);
			String answer2 = scanner.nextLine().toLowerCase();
			
			if (answer2.equals("n")) {
				controller.setSatisfaction(); // Sets satisfaction to true b/c they don't want to do anything else
			}
		}
		scanner.close();
	}
}