package ISP;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayOutputStream;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class ISPBusinessTest {

	private ISPBusiness test;
    private Town testTown;
    private ByteArrayOutputStream outputStream; // To capture the output

    @BeforeEach
    public void setUp() {
        // Initialize a Town object for testing
    	test = new ISPBusiness();
        testTown = new Town(4, 4);

        // Redirects System.out to capture the output
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }
	
    @SuppressWarnings("static-access")
	@Test
    public void testUpdatePlain() {
        // Tests the updatePlain method for the ISPBusiness class
    	testTown.randomInit(10);
    	/*
    	 *  O R O R
    	 *  E E C O
    	 *  E S O S
    	 *  E O R R
    	 */
    	String s = "O R O R \nE E C O \nE S O S \nE O R R \n";
    	assertEquals(testTown.toString(), s);
    	testTown = test.updatePlain(testTown);
    	/*
    	 *  E E E E
    	 *  C C O E
    	 *  C O E O
    	 *  C E E E
      	 */
    	String s2 = "E E E E \nC C O E \nC O E O \nC E E E \n";
    	assertEquals(testTown.toString(), s2);
    }

    @SuppressWarnings("static-access")
	@Test
    public void testGetProfit() {
        // Tests the getProfit method for the ISPBusiness class
    	testTown.randomInit(10);
    	/*
    	 *  O R O R
    	 *  E E C O
    	 *  E S O S
    	 *  E O R R
    	 */
    	assertEquals(test.getProfit(testTown), 1);
    }

    @Test
    public void testMainInputTwo() {
        // Test the main method for the ISPBusiness class with the input of 2 and row col and seed numbers.
        String inputString = "2\n4 \n4 \n10 \n";

        // Creates an InputStream from the inputString
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        // Redirects System.in to use the custom inputStream
        System.setIn(inputStream);

        // Calls the main method
        ISPBusiness.main(null);

        // Retrieves the captured output and trim it to remove leading/trailing whitespace
        String capturedOutput = outputStream.toString().trim();

        assertEquals("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed\r\n"
        		+ "Provide rows, cols and seed integer separated by spaces:\r\n27.60%", capturedOutput);
    }
    
    @Test
    public void testMainInputOne() {
        // Test the main method for the ISPBusiness class with the input 1 and followng with the text file path.
        String inputString = "1\n./ISP4x4.txt\n";

        // Creates an InputStream from the inputString
        InputStream inputStream = new ByteArrayInputStream(inputString.getBytes());

        // Redirects System.in to use the custom inputStream
        System.setIn(inputStream);

        // Calls the main method
        ISPBusiness.main(null);

        // Retrieves the captured output and trim it to remove leading/trailing whitespace
        String capturedOutput = outputStream.toString().trim();

        assertEquals("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed\r\n"
        		+ "Please enter file path:\r\n27.60%", capturedOutput);
    }
}