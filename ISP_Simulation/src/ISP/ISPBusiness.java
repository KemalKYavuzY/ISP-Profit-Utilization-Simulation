package ISP;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * @author Kemal Yavuz
 *
 * The ISPBusiness class performs simulation over a grid 
 * plain with cells occupied by different TownCell types.
 *
 */
public class ISPBusiness {

	/**
	 * Returns a new Town object with updated grid value for next billing cycle.
	 * @param tOld: old/current Town object.
	 * @return: New town object.
	 */
	public static Town updatePlain(Town tOld) {
		Town tNew = new Town(tOld.getLength(), tOld.getWidth());
		
			//Updates every location of the grid.
			for (int r = 0; r < tOld.getLength(); r++) {
				for (int c = 0; c < tOld.getWidth(); c++) {
					tNew.grid[r][c] = tOld.grid[r][c].next(tNew);
				}
			}
			
			tOld = tNew;
			
		return tNew;
	}
	
	/**
	 * Returns the profit for the current state in the town grid.
	 * @param town
	 * @return
	 */
	public static int getProfit(Town town) {
		int numCasual = 0;
		
		//Checks and Counts every Casual TownCell Object in the grid.
		for (int r = 0; r < town.getLength(); r++) {
			for (int c = 0; c < town.getWidth(); c++) {
				if (town.grid[r][c].who() == State.CASUAL) {
					numCasual++;
				}
			}
		}
		
		return numCasual; //Returns the number of Casual objects in one cycle.
	}

	/**
	 *  Main method. Interact with the user and ask if user wants to specify elements of grid
	 *  via an input file (option: 1) or wants to generate it randomly (option: 2).
	 *  
	 *  Depending on the user choice, create the Town object using respective constructor and
	 *  if user choice is to populate it randomly, then populate the grid here.
	 *  
	 *  Finally: For 12 billing cycle calculate the profit and update town object (for each cycle).
	 *  Print the final profit in terms of %. You should print the profit percentage
	 *  with two digits after the decimal point:  Example if profit is 35.5600004, your output
	 *  should be:
	 *
	 *	35.56%
	 *  
	 * Note that this method does not throw any exception, so you need to handle all the exceptions
	 * in it.
	 * 
	 * @param args
	 * 
	 */
	public static void main(String []args) {
		int input; //Variable to store user input.
		int totalProfit = 0; //Variable to get the sum of each cycle's profit.
		double result = 0;
		
		
		System.out.println("How to populate grid (type 1 or 2): 1: from a file. 2: randomly with seed");
		Scanner scnr = new Scanner(System.in);
		input = scnr.nextInt();
		
		if (input == 1) { 
			System.out.println("Please enter file path:");
			String path = scnr.next(); 
			try {
				Town fileTown = new Town(path); //Creates a Town object using a file path if the user inputs 1.
				int i = 1;
				
				System.out.println("  Start:\n");
				while (i <= 12) { //While loop of the 12 cycles
					System.out.print(fileTown.toString());
					System.out.println("Profit: " + getProfit(fileTown) + "\n");
					totalProfit += getProfit(fileTown);
					fileTown = updatePlain(fileTown);
					if (i < 12) {
						System.out.println(" After itr: " + i + "\n");
					}
					i++;
				}

				//Final Calculation
				//((100 * Casual) / ((Length of the Grid)*(Width of the Grid))) / 12 Casted to Double
				result = (100* totalProfit) / (double)(fileTown.getLength() * fileTown.getWidth()) / 12;

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			
		} else if (input == 2) {
			System.out.println("Provide rows, cols and seed integer separated by spaces:");
			int rows = scnr.nextInt();
			int cols = scnr.nextInt();
			int seed = scnr.nextInt();
			Town randomTown = new Town(rows, cols); //Creates a Town object by using 2 integer values (length and width) if the user inputs 2.
			randomTown.randomInit(seed); //And uses the 3rd integer input as the seed of the randomization.
			int i = 1;
			
			System.out.println("  Start:\n");
			while (i <= 12) { //While loop of the 12 cycles
				System.out.print(randomTown.toString());
				System.out.println("Profit: " + getProfit(randomTown) + "\n");
				totalProfit += getProfit(randomTown);
				randomTown = updatePlain(randomTown);
				if (i < 12) {
				System.out.println(" After itr: " + i + "\n");
				}
				i++;
			}
			
			//Final Calculation
			//((100 * Casual) / ((Length of the Grid)*(Width of the Grid))) / 12 Casted to Double
			result = (100* totalProfit) / (double)(randomTown.getLength() * randomTown.getWidth()) / 12;
		}	
		
		//Printing the Profit Utilization in 2 Decimals
		System.out.printf("%.2f", result);
		System.out.print("%");
		
		scnr.close(); //Closes Scanner
	}
}