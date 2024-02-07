package ISP;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * 
 * @author Kemal Yavuz
 * 
 *
 */
public class Town {
	
	private int length, width;  //Row and col (first and second indices)
	public TownCell[][] grid;
	
	/**
	 * Constructor to be used when user wants to generate grid randomly, with the given seed.
	 * This constructor does not populate each cell of the grid (but should assign a 2D array to it).
	 * @param length
	 * @param width
	 */
	public Town(int length, int width) {
		
		this.length = length;
		this.width = width;
		grid = new TownCell[length][width];
	}
	
	/*
	 * Constructor to be used when user wants to populate grid based on a file.
	 * Please see that it simple throws FileNotFoundException exception instead of catching it.
	 * Ensure that you close any resources (like file or scanner) which is opened in this function.
	 * @param inputFileName
	 * @throws FileNotFoundException
	 */
	public Town(String inputFileName) throws FileNotFoundException {
		
		File file = new File(inputFileName);
		Scanner fileScnr = new Scanner(file);
		this.length = fileScnr.nextInt(); //Returns the first number inputed to this length variable
		this.width = fileScnr.nextInt();  //Returns the second number inputed to this width variable
		grid = new TownCell[length][width]; //Creates a grid with the boundries of given inputs.
		
		while(fileScnr.hasNext()) {
			for (int r = 0; r < length; r++) {
				for (int c = 0; c < width; c++) {
					String s = fileScnr.next();
					//Scans the letters inside the file and Creates the right
					//SubClass of TownCell at that point.
					
					if (s.equals("R")) {
						grid[r][c] = new Reseller(this, r, c);
					} else if (s.equals("E")) {
						grid[r][c] = new Empty(this, r, c);;
					} else if (s.equals("C")) {
						grid[r][c] = new Casual(this, r, c);
					} else if (s.equals("O")) {
						grid[r][c] = new Outage(this, r, c);
					} else if (s.equals("S")) {
						grid[r][c] = new Streamer(this, r, c);
					}
				}
			}
		}
		
		fileScnr.close(); //Closes Scanner and File
	}
	
	/**
	 * Returns width of the grid.
	 * @return
	 */
	public int getWidth() {
		return width;
	}
	
	/**
	 * Returns length of the grid.
	 * @return
	 */
	public int getLength() {
		return length;
	}

	/**
	 * Initialize the grid by randomly assigning cell with one of the following class object:
	 * Casual, Empty, Outage, Reseller OR Streamer
	 */
	public void randomInit(int seed) {
		Random rand = new Random(seed);
		int numRand;

		for (int r = 0; r < length; r++) {
			for (int c = 0; c < width; c++) {
				numRand = rand.nextInt(seed) % 5; //Generates a random number with the given seed.
				//Between 0 and 4
				
				if (numRand == 0) {
					TownCell Reseller = new Reseller(this, r, c);
					grid[r][c] = Reseller;
				} else if (numRand == 1) {
					TownCell Empty = new Empty(this, r, c);
					grid[r][c] = Empty;
				} else if (numRand == 2) {
					TownCell Casual = new Casual(this, r, c);
					grid[r][c] = Casual;
				} else if (numRand == 3) {
					TownCell Outage = new Outage(this, r, c);
					grid[r][c] = Outage;
				} else if (numRand == 4) {
					TownCell Streamer = new Streamer(this, r, c);
					grid[r][c] = Streamer;
				}	
			}
		}
	}
	
	/**
	 * Output the town grid. For each square, output the first letter of the cell type.
	 * Each letter should be separated either by a single space or a tab.
	 * And each row should be in a new line. There should not be any extra line between 
	 * the rows.
	 */
	@Override
	public String toString() {
		String s = "";

		for (int r = 0; r < length; r++) {
			for (int c = 0; c < width; c++) {
				if (grid[r][c].who() == State.RESELLER) {
					s += "R ";
				} else if (grid[r][c].who() == State.EMPTY) {
					s += "E ";
				} else if (grid[r][c].who() == State.CASUAL) {
					s += "C ";
				} else if (grid[r][c].who() == State.OUTAGE) {
					s += "O ";
				} else if (grid[r][c].who() == State.STREAMER) {
					s += "S ";
				}
			}
			
			s += "\n"; //Next Row
		}
		
		return s;
	}
}
