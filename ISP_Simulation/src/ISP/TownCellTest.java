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
public class TownCellTest {
	
	private int[] nCensus = new int[5];
	
    @Test
    public void testCensus() throws FileNotFoundException {
    	Town testTown = new Town("./S2.txt");
    	/*
    	 *  C C C
    	 *  C S C
    	 *  C E E
    	 */
    	testTown.grid[1][1].census(nCensus); //Counts and checks all adjacent neighbors excluding the center cell
    	//There has to be 6 Casual and 2 Empty adjacents of the Cell at (1,1) location.
    	assertEquals(nCensus[2], 6);
    	assertEquals(nCensus[1], 2);
    }

    @Test
    public void testWho() {
    	Town testTown = new Town(4, 4);
        TownCell casual = new Casual(testTown, 0, 0); // Creates a Casual TownCell object
        assertEquals(State.CASUAL, casual.who());  
        TownCell empty = new Empty(testTown, 0, 1); // Creates an Empty TownCell object
        assertEquals(State.EMPTY, empty.who());
        TownCell outage = new Outage(testTown, 0, 2); // Creates an Outage TownCell object
        assertEquals(State.OUTAGE, outage.who());
        TownCell reseller = new Reseller(testTown, 0, 3); // Create a Reseller TownCell object
        assertEquals(State.RESELLER, reseller.who());
        Streamer streamer = new Streamer(testTown, 1, 0); // Creates a Streamer TownCell object
        assertEquals(State.STREAMER, streamer.who());
    }

    @Test
    public void testNext() throws FileNotFoundException {
    	Town testTown = new Town(1, 3);
    	TownCell empty1 = new Empty(testTown, 0, 0);
    	TownCell streamer = new Streamer(testTown, 0, 1);
    	TownCell empty2 = new Empty(testTown, 0, 2);
    	testTown.grid[0][0] = empty1;
    	testTown.grid[0][1] = streamer;
    	testTown.grid[0][2] = empty2;
    	/*
    	 *  E S E
    	 */
    	assertEquals(testTown.grid[0][1].who(), State.STREAMER);   
        //Streamer -> Streamer
    	// Because the TownCell in the location (0,1) applies none of the rules
    	// it remains as Streamer.
    	assertEquals(testTown.grid[0][1].next(testTown).who(), State.STREAMER);  
    	// More Conditions in Sub Classes...
    }
}