
//Chelina Obiang
import java.util.HashMap;

public class CryptogramModel {
	private String quote;
	private String decrypt = "";
	private HashMap<Character, Character> crypt;

	/*
	 * Constructor that takes Cryptograms as a parameter to get the alphabet mapping
	 */
	public CryptogramModel(Cryptograms cryptograms) {
		this.crypt = cryptograms.getAlphabet();
	}

	/*
	 * Method to set the quote in CryptogramModel
	 * 
	 */
	public void setQuote(String quote) {
		this.quote = quote;

		// Initialize decrypter that changes as guesses are made
		StringBuilder decryptBuilder = new StringBuilder();
		for (char c : quote.toCharArray()) {
			if (Character.isLetter(c)) {
				decryptBuilder.append(' ');
			} else {
				decryptBuilder.append(c);
			}
		}
		decrypt = decryptBuilder.toString();
	}

	/*
	 * Method that will replace their chosen encrypted characters with the user's
	 * char guess.
	 */
	public void setReplacement(char encryptedChar, char guess) {
		if (crypt.containsValue(encryptedChar)) {
			if (crypt.get(guess) != null && crypt.get(guess) == encryptedChar) {
				StringBuilder updatedDecrypt = new StringBuilder(decrypt);

				// Replace all of the same encrypted characters with the guess one if correlate.
				for (int i = 0; i < decrypt.length(); i++) {
					if (decrypt.charAt(i) == ' ' && getEncryptedString().charAt(i) == encryptedChar) {
						updatedDecrypt.setCharAt(i, guess);
					}
				}
				decrypt = updatedDecrypt.toString();
				this.getCurrentGuess();
			} else {
				System.out.println("Sorry, this is the wrong guess!");
			}
		} else {
			System.out.println("This isn't a valid encrypted letter.");
		}
	}

	/*
	 * Method to generate and return the encrypted version of the quote using
	 * mapping.
	 */
	public String getEncryptedString() {
		StringBuilder encrypt = new StringBuilder();
		String quoteLower = quote.toLowerCase();

		for (int i = 0; i < quoteLower.length(); i++) {
			char currentChar = quoteLower.charAt(i);
			if (crypt.containsKey(currentChar)) {
				encrypt.append(crypt.get(currentChar));
				decrypt += " "; // Placeholder
			} else {
				encrypt.append(currentChar); // Non-alphabet characters are left unchanged
				decrypt += currentChar;
			}
		}
		return encrypt.toString();
	}

	// Returns the current state of guesses
	public String getCurrentGuess() {
		return decrypt;
	}

	// Returns the real quote
	public String getAnswer() {
		return quote;
	}
}