
//Chelina Obiang
import java.util.Scanner;

public class CryptogramController {
	private CryptogramModel model;
	private boolean status = true;

	// Constructor that takes the model and cryptograms as parameters
	public CryptogramController(CryptogramModel model) {
		this.model = model;
	}

	// Method to check if the game is over
	public boolean isGameOver() {
		if (model.getCurrentGuess().equalsIgnoreCase(model.getAnswer())) {
			status = false;
			System.out.println("Yay, you did it!");
			return true; // Game is over
		}
		return false; // Game is not over
	}

	// Method to make a letter replacement
	public void makeReplacement() {
		char letterToReplace = chooseLetter();
		char guess = chooseGuess();
		model.setReplacement(letterToReplace, guess);
	}

	/*
	 * Helper method to get the letter to replace
	 * 
	 */
	@SuppressWarnings("resource")
	private char chooseLetter() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("What letter would you like to replace: ");
		return scanner.next().charAt(0);
	}

	/*
	 * Helper method to get the replacement guess
	 * 
	 */
	@SuppressWarnings("resource")
	private char chooseGuess() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("What is your letter replacement guess: ");
		return scanner.next().charAt(0);
	}

	// Method to get the encrypted quote
	public void getEncryptedQuote() {
		System.out.println(model.getEncryptedString());
	}

	// Method to get the user's progress
	public void getUsersProgress() {
		System.out.println(model.getCurrentGuess());
	}
}
