package ISP;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class Reseller extends TownCell {

	public Reseller(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * @return Reseller
	 */
	public State who() {
		return State.RESELLER;
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus); //Counts all adjacent neighbors excluding the center cell.
		
		if (nCensus[CASUAL] <= 3) {
			//If there are 3 or fewer casual users in the neighborhood, then Reseller finds
			//it unprofitable to maintain the business and leaves, making the cell Empty.
			return new Empty(tNew, row, col);
		} else if (nCensus[EMPTY] >= 3) {
			//Also, if there are 3 or more empty cells in the neighborhood, then the Reseller
			//leaves, making the cell Empty. Resellers do not like unpopulated regions.
			return new Empty(tNew, row, col);
		} else if (nCensus[CASUAL] >= 5) {
			//If none of the above rules apply, any cell with 5 or more casual neighbors
			//becomes a Streamer.
			return new Streamer(tNew, row, col);
		} else {
			//If none of the rules apply, then the cell state remains unchanged for the next iteration.
			return new Reseller(tNew, row, col);
		}
	}
}