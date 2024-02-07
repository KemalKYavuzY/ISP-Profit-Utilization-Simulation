package ISP;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.io.FileNotFoundException;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class TownTest {

    private Town testTown;

    @BeforeEach
    public void setUp() {
        // Initialize a Town object for testing
        testTown = new Town(4, 4);
    }
    
    @Test
    public void testTownConstructor() {
    	Town constructorTown = new Town(4, 4);
    	assertEquals(4, constructorTown.getWidth());
    	assertEquals(4, constructorTown.getLength());
    }

    @Test
    public void testGetWidth() {
        assertEquals(4, testTown.getWidth());
    }

    @Test
    public void testGetLength() {
        assertEquals(4, testTown.getLength());
    }

    @Test
    public void testRandomInit() {
        // Test the randomInit method for the Town class
        testTown.randomInit(10); // Provide a specific seed for testing
        // Checking if the grid is correctly initialized after getting the seed input
        String s = "O R O R \nE E C O \nE S O S \nE O R R \n";
    	assertEquals(testTown.toString(), s);
    	assertEquals(testTown.grid[0][0].who(), State.OUTAGE);
    	//...
    	assertEquals(testTown.grid[3][3].who(), State.RESELLER);
    }
    
    @Test
    public void testConstructorFromFile() throws FileNotFoundException {
        Town townFromFile = new Town("./ISP4x4.txt");
        // Checking if the grid is correctly initialized from the file
        String s = "O R O R \nE E C O \nE S O S \nE O R R \n";
    	assertEquals(townFromFile.toString(), s);
    	assertEquals(townFromFile.grid[0][0].who(), State.OUTAGE);
    	//...
    	assertEquals(townFromFile.grid[3][3].who(), State.RESELLER);
    }
    
    @Test
    public void testToString() {
        // Test the toString method for the Town class
    	testTown.randomInit(10);
    	String s = "O R O R \nE E C O \nE S O S \nE O R R \n";
    	assertEquals(testTown.toString(), s);
    }
}