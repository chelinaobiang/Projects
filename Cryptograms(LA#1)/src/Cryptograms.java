
//Chelina Obiang
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Cryptograms {
	private String alphaLower = "abcdefghijklmnopqrstuvwxyz";
	private String answer;
	private CryptogramModel model;

	/*
	 * Constructor that initializes CryptogramModel and loads the quote.
	 */
	public Cryptograms(String txtFilePath) {
		this.model = new CryptogramModel(this);
		this.selectQuote(txtFilePath); // Load the quote and set it in CryptogramModel
	}

	/*
	 * This method is used to gather a random line of text from the txt file.
	 * 
	 */
	private void selectQuote(String txtFilePath) {
		try {
			Scanner scan = new Scanner(new File(txtFilePath));
			Random ran = new Random();
			int lineNum = 0;

			// Count the number of lines in the file
			while (scan.hasNextLine()) {
				scan.nextLine();
				lineNum++;
			}
			scan.close();
			int ranNum = ran.nextInt(lineNum);
			scan = new Scanner(new File(txtFilePath));

			for (int i = 0; i < ranNum; i++) {
				scan.nextLine();
			}
			answer = scan.nextLine();
			scan.close();

			// Set quote in CryptogramModel
			model.setQuote(answer);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	/*
	 * This is used to create a cipher mapping of the alphabet with HashMap &
	 * Shuffle then returns the encrypter map of the letters.
	 */
	public HashMap<Character, Character> getAlphabet() {
		List<Character> shuffledAlphaList = new ArrayList<>();
		for (char c : alphaLower.toCharArray()) {
			shuffledAlphaList.add(c);
		}
		Collections.shuffle(shuffledAlphaList); // Shuffle the alphabet for the value of the HashMap
		HashMap<Character, Character> encrypter = new HashMap<>();
		for (int i = 0; i < alphaLower.length(); i++) {
			encrypter.put(alphaLower.charAt(i), shuffledAlphaList.get(i));
		}

		return encrypter;
	}

}