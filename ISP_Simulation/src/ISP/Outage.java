package ISP;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class Outage extends TownCell {

	public Outage(Town p, int r, int c) {
		super(p, r, c);
	}

	@Override
	/**
	 * @return Outage
	 */
	public State who() {
		return State.OUTAGE;
	}

	@Override
	public TownCell next(Town tNew) {
		census(nCensus); //Counts all adjacent neighbors excluding the center cell.
		
		//An Outage cell becomes an Empty cell, meaning internet access is restored
		//on the billing cycle after an outage.
		return new Empty(tNew, row, col);
	}
}