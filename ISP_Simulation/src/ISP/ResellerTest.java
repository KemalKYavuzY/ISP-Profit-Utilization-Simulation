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
public class ResellerTest {

	@Test
	void constructorReseller() {
		Town testTown = new Town(4, 4);
		Reseller reseller = new Reseller(testTown, 0, 0);
		
		assertEquals(reseller.col, 0); // Checks whether the column value input in the constructor is received correctly.
		assertEquals(reseller.row, 0); // Checks whether the row value input in the constructor is received correctly.
		assertEquals(reseller.plain, testTown); // Checks whether the Town object input in the constructor is received correctly.
	}
    
    @Test
    public void testWho() {
    	Town testTown = new Town(4, 4);
        Reseller reseller = new Reseller(testTown, 0, 0); // Creates a Reseller object
        assertEquals(State.RESELLER, reseller.who());
    }

    @Test
    public void testNextResellerLeavesOne() throws FileNotFoundException {
    	Town testTown = new Town("./CtoO.txt");
    	/*
    	 *  O(R)O R
    	 *  E E C O
    	 *  E S O S
    	 *  E O R R
    	 */
    	assertEquals(testTown.grid[0][1].who(), State.RESELLER);
    	//Reseller -> Empty
    	/*
    	 *  E(E)E E
    	 *  C C O E
    	 *  C O E O
    	 *  C E E E
      	 */
    	// Because the TownCell in the location (0,1) applies the rule of having 3 or less Casual
    	// neighbors it becomes Empty.
    	assertEquals(testTown.grid[0][1].next(testTown).who(), State.EMPTY);
    }
    
    @Test
    public void testNextResellerLeavesTwo() throws FileNotFoundException {
		Town testTown = new Town("./RtoE.txt");
	   	/*
	   	 *  E(R)E E
	   	 *  E E E E
	   	 *  E E E E
	   	 *  E E E E
	   	 */
	   	assertEquals(testTown.grid[0][1].who(), State.RESELLER);
	   	//Reseller -> Empty
	   	/*
	   	 *  C(E)C C
	   	 *  C C C C
	   	 *  C C C C
	   	 *  C C C C
	     */
	   	// Because the TownCell in the location (0,1) applies the rule of having 3 or more Empty
	   	// neighbors it becomes Empty.
	   	assertEquals(testTown.grid[0][1].next(testTown).who(), State.EMPTY);
    }
    
    @Test
    public void testNextCasualNeighbors() throws FileNotFoundException {
		Town testTown = new Town("./RtoS.txt");
		/*
		 *  C(R)C 
		 *  C C C
		 */
		assertEquals(testTown.grid[0][1].who(), State.RESELLER);
		//Reseller -> Streamer
		/*
		 *  R(S)R
		 *  R R R
		 */
		// Because the TownCell in the location (0,1) applies the rule of having 5 or more Casual
		// neighbors it becomes a Streamer.
		assertEquals(testTown.grid[0][1].next(testTown).who(), State.STREAMER);
    }
    
    @Test
    public void testNextUnchanged() throws FileNotFoundException {  
    	Town testTown = new Town("./R.txt");
    	/*
    	 *  C(R)C
    	 *  C C E
      	 */
    	assertEquals(testTown.grid[0][1].who(), State.RESELLER);
    	//Reseller -> Reseller
    	// Because the TownCell in the location (0,1) not apply any of the rules it remains as Reseller.
    	assertEquals(testTown.grid[0][1].next(testTown).who(), State.RESELLER);       
    }
}