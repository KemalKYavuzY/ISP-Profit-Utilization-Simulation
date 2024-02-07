package ISP;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class Streamer extends TownCell {

	public Streamer(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * @return Streamer
	 */
	public State who() {
		return State.STREAMER; //Returns Streamer
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
			//If there is any reseller in the neighborhood, the reseller causes outage for the
			//streamer as well.
			return new Outage(tNew, row, col);
		} else if (nCensus[OUTAGE] >= 1) {
			//Otherwise, if there is any Outage in the neighborhood, then the streamer leaves
			//and the cell becomes Empty.
			return new Empty(tNew, row, col);
		} else if (nCensus[CASUAL] >= 5) {
			//If none of the above rules apply, any cell with 5 or more casual neighbors
			//becomes a Streamer.
			return new Streamer(tNew, row, col);
		} else {
			//If none of the rules apply, then the cell state remains unchanged for the next iteration.
			return new Streamer(tNew, row, col);
		}
	}
}