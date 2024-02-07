package ISP;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public abstract class TownCell {

	protected Town plain;
	protected int row;
	protected int col;
	
	// constants to be used as indices.
	protected static final int RESELLER = 0;
	protected static final int EMPTY = 1;
	protected static final int CASUAL = 2;
	protected static final int OUTAGE = 3;
	protected static final int STREAMER = 4;
	
	public static final int NUM_CELL_TYPE = 5;
	
	//Use this static array to take census.
	public static final int[] nCensus = new int[NUM_CELL_TYPE];

	public TownCell(Town p, int r, int c) {
		plain = p;
		row = r;
		col = c;
	}
	
	/**
	 * Checks all neigborhood cell types in the neighborhood.
	 * Refer to homework pdf for neighbor definitions (all adjacent
	 * neighbors excluding the center cell).
	 * Use who() method to get who is present in the neighborhood
	 *  
	 * @param counts of all customers
	 */
	public void census(int nCensus[]) {
		// zero the counts of all customers
		nCensus[RESELLER] = 0; 
		nCensus[EMPTY] = 0; 
		nCensus[CASUAL] = 0; 
		nCensus[OUTAGE] = 0; 
		nCensus[STREAMER] = 0; 

		State[] adjacent = new State[8];
		
		//Arrays that hold the values that will be added to the row and column values as the variable i increases.
	    int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
		int[] dy = {-1, 0, 1, -1, 1, -1, 0, 1};
		
		for (int i = 0; i < 8; i++) {
			//It checks each neighboring coordinate by updating the Row and Column values with the increment of the variable "i".
		    int newRow = row + dx[i];
		    int newCol = col + dy[i];
		    
		    //Checsk if the location that is going to be checked is in the boundaries first.
		    if (newRow >= 0 && newCol >= 0 && newRow < plain.getLength() && newCol < plain.getWidth()) {
		    	//Checks if there is actually an object at that location.
			    if (plain.grid[newRow][newCol] != null) {
			    	//Counts it as a neighbor if it meets all the conditions.
			        adjacent[i] = plain.grid[newRow][newCol].who();
			    }
		    }
		}
		
		for (int i = 0; i < 8; i++) {
			//It checks all eight of its neighbors and increases the value of 
			//that array index, which keeps track of the number of neighbor species, 
			//accordingly.
			if (adjacent[i] == State.RESELLER) {
				nCensus[RESELLER]++;
			} else if (adjacent[i] == State.EMPTY) {
				nCensus[EMPTY]++;
			} else if (adjacent[i] == State.CASUAL) {
				nCensus[CASUAL]++;
			} else if (adjacent[i] == State.OUTAGE) {
				nCensus[OUTAGE]++;
			} else if (adjacent[i] == State.STREAMER) {
				nCensus[STREAMER]++;
			}
		}		
	}

	/**
	 * Gets the identity of the cell.
	 * 
	 * @return State
	 */
	public abstract State who();

	/**
	 * Determines the cell type in the next cycle.
	 * 
	 * @param tNew: town of the next cycle
	 * @return TownCell
	 */
	public abstract TownCell next(Town tNew);
}