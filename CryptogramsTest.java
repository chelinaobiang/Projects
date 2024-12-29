import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class CryptogramsTest {
	private Cryptograms cryptograms;
	private CryptogramModel model;
	private List<String> fileQuotes;

	@Before
	public void setUp() {
		// Use the input.txt file from the current directory or specify the relative
		// path
		File inputFile = new File("input.txt");
		cryptograms = new Cryptograms(inputFile.getPath());
		model = new CryptogramModel(cryptograms);

		// Load all quotes from the file into a list
		fileQuotes = loadQuotesFromFile(inputFile);
	}

	// Helper method to load quotes from the text file
	private List<String> loadQuotesFromFile(File inputFile) {
		List<String> quotes = new ArrayList<>();
		try (Scanner scanner = new Scanner(inputFile)) {
			while (scanner.hasNextLine()) {
				quotes.add(scanner.nextLine().trim());
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return quotes;
	}

//    @Test
//    public void testRandomQuoteSelection() {
//        // Since the quote is chosen randomly, verify it is one of the possible quotes from the file
//        String selectedQuote = model.getAnswer();
//        assertTrue("Selected quote should be from the provided text file.", fileQuotes.contains(selectedQuote));
//    }

	@Test
	public void testAlphabetCipherGeneration() {
		// Verify the cipher is correctly generated
		HashMap<Character, Character> cipher = cryptograms.getAlphabet();
		assertEquals(26, cipher.size()); // There should be 26 letters in the cipher
		// Ensure all values are unique
		assertEquals(26, new HashSet<>(cipher.values()).size());
	}

	@Test
	public void testEncryptQuote() {
		model.setQuote("Life is like a box of chocolates.");
		String encryptedQuote = model.getEncryptedString();
		assertNotNull(encryptedQuote);
		assertNotEquals("Life is like a box of chocolates.", encryptedQuote); // Should be encrypted
	}

//    @Test
//    public void testCorrectReplacement() {
//        model.setQuote("Great minds think alike.");
//        // Assume the encrypted string maps 'G' to 'X'
//        model.setReplacement('X', 'G');
//        assertTrue(model.getCurrentGuess().contains("G"));
//    }

	@Test
	public void testIncorrectReplacement() {
		model.setQuote("Great minds think alike.");
		model.setReplacement('X', 'A'); // Assuming 'X' is mapped to 'G'
		assertFalse(model.getCurrentGuess().contains("A"));
	}

//    @Test
//    public void testInvalidEncryptedCharacter() {
//        model.setQuote("Great minds think alike.");
//        model.setReplacement('!', 'A'); // Invalid encrypted character
//        assertEquals("                      ", model.getCurrentGuess()); // No changes made
//    }
	@Test
	public void testDecrypt() {
		model.setQuote("This -- former president.");
		assertEquals("     --                 .", model.getCurrentGuess());
	}
}