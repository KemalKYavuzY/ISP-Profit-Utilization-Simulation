package ISP;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class Casual extends TownCell {
	
	public Casual(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * @return Casual
	 */
	public State who() {
		return State.CASUAL;
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus); //Counts all adjacent neighbors excluding the center cell.
		
		if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
			//Any cell that (1) is not a Reseller or Outage and 
			//(2) and has (Number of Empty + Number of Outage neighbors less than or equal to 
			//Converts to Reseller.
			return new Reseller(tNew, row, col);
		} else if (nCensus[RESELLER] >= 1) {
			//If there is any reseller in its neighborhood, then the reseller causes outage in the
			//casual user cell. Thus, the state of the cell changes from C (Casual) to O
			//(Outage).
			return new Outage(tNew, row, col);
		} else if (nCensus[STREAMER] >= 1) {
			//Otherwise, if there is any neighbor who is a streamer, then the casual user also
			//becomes a streamer, in the hopes of making it big on the internet.
			return new Streamer(tNew, row, col);
		} else if (nCensus[CASUAL] >= 5) {
			//If none of the above rules apply, any cell with 5 or more casual neighbors
			//becomes a Streamer.
			return new Streamer(tNew, row, col);
		} else {
			//If none of the rules apply, then the cell state remains unchanged for the next iteration.
			return new Casual(tNew, row, col);
		}	
	}
}