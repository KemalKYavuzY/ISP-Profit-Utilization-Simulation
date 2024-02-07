package ISP;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileNotFoundException;
import org.junit.jupiter.api.Test;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class OutageTest {

	@Test
	void constructorOutage() {
		Town testTown = new Town(4, 4);
		Outage outage = new Outage(testTown, 0, 0);
		
		assertEquals(outage.col, 0); // Checks whether the column value input in the constructor is received correctly.
		assertEquals(outage.row, 0); // Checks whether the row value input in the constructor is received correctly.
		assertEquals(outage.plain, testTown); // Checks whether the Town object input in the constructor is received correctly.
	}
    
    @Test
    public void testWho() {
    	Town testTown = new Town(4, 4);
        Outage outage = new Outage(testTown, 0, 0); // Creates an Outage object
        assertEquals(State.OUTAGE, outage.who());
    }

    @Test
    public void testNext() throws FileNotFoundException {
    	Town testTown = new Town("./E.txt");
    	/*
       	 *  E E E E
       	 *  C C(O)E
       	 *  C O E O
       	 *  C E E E
         */
       	assertEquals(testTown.grid[1][2].who(), State.OUTAGE); 
       	//Outage -> Empty
       	/*
       	 *  R C C C
       	 *  C C(E)C
       	 *  C E C E
       	 *  C C C C
       	 */
       	// TownCell in the location (1,2) becomes Empty because it applies the rule 
       	// "An Outage cell becomes an Empty cell, meaning internet access is restored
       	// on the billing cycle after an outage."
       	assertEquals(testTown.grid[1][2].next(testTown).who(), State.EMPTY);   
    }
}