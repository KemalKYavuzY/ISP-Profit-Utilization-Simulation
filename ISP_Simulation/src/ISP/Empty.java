package ISP;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class Empty extends TownCell {

	public Empty(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * @return Empty
	 */
	public State who() {
		return State.EMPTY; 
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus); //Counts all adjacent neighbors excluding the center cell.
		
		if (nCensus[EMPTY] + nCensus[OUTAGE] <= 1) {
			//Any cell that (1) is not a Reseller or Outage and 
			//(2) and has (Number of Empty + Number of Outage neighbors less than or equal to 
			//Converts to Reseller.
			return new Reseller(tNew, row, col);
		} else {
			//If the cell was empty, then a Casual user takes it and it becomes a C.
			return new Casual(tNew, row, col);
		}
	}
}