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
public class EmptyTest {

	@Test
	void constructorEmpty() {
		Town testTown = new Town(4, 4);
		Empty empty = new Empty(testTown, 0, 0);
		
		assertEquals(empty.col, 0); // Checks whether the column value input in the constructor is received correctly.
		assertEquals(empty.row, 0); // Checks whether the row value input in the constructor is received correctly.
		assertEquals(empty.plain, testTown); // Checks whether the Town object input in the constructor is received correctly.
	}
	
    @Test
    public void testWho() {
    	Town testTown = new Town(4, 4);
        Empty empty = new Empty(testTown, 0, 0); // Creates an Empty object
        assertEquals(State.EMPTY, empty.who());
    }

    @Test
    public void testNext() throws FileNotFoundException {
    	Town testTown = new Town("./E.txt");
    	/*
       	 *  E(E)E E
       	 *  C C O E
       	 *  C O E O
       	 *  C E E E
         */
       	assertEquals(testTown.grid[0][1].who(), State.EMPTY); 
       	//Empty -> Casual
       	/*
       	 *  R(C)C C
       	 *  C C E C
       	 *  C E C E
       	 *  C C C C
       	 */
       	// TownCell in the location (0,1) becomes Casual because it applies the rule 
       	// "If the cell was empty, then a Casual user takes it and it becomes a C"
       	assertEquals(testTown.grid[0][1].next(testTown).who(), State.CASUAL);   
    }
    
    @Test
       public void testNextOneOrLessEmptyOrOutageNeighbors() throws FileNotFoundException {
       	// Test the next method for the Empty class with one or less Empty or Outage neighbors
    	Town testTown = new Town("./E.txt");
       	/*
       	 * (E)E E E
       	 *  C C O E
       	 *  C O E O
       	 *  C E E E
         */
       	assertEquals(testTown.grid[0][0].who(), State.EMPTY); 
       	//Empty -> Reseller
       	/*
       	 * (R)C C C
       	 *  C C E C
       	 *  C E C E
       	 *  C C C C
       	 */
       	// Because the TownCell in the location (0,0) applies the rule of having 1 or less Empty
       	// or Outage neighbors it becomes a Reseller.
       	assertEquals(testTown.grid[0][0].next(testTown).who(), State.RESELLER);       
       }
}