/**
 * The model for John Conway's Game of Life. This class has all needed methods
 * as method stubs. The preceding comments for each are the specification for
 * the method explaining what each method does.
 *
 * @author Rick Mercer and Chelina Obiang
 */
public class GameOfLife {

	// Use this data structure to represent the existence of cells.
	// A true value means a cell exists at that location.
	private boolean[][] society;

	/*-
	 * - Write the constructor so it takes two integer arguments to represent the
	 * number of rows and columns in the game of life. The constructor creates a
	 * society with no cells but space to store rows*cols cells.
	 *
	 * @param rows The height of the grid that shows the cells.
	 * @param cols The width of the grid that shows the cells.
	 * 
	 *             Precondition rows and cols are in the range of 5 through 50
	 */
	public GameOfLife(int rows, int cols) {
		society = new boolean[rows][cols];
	}

	/**
	 * Return the number of rows, which can be indexed from 0..numberOfRows()-1.
	 *
	 * @return The height of the society.
	 */
	public int numberOfRows() {
		return society.length;
	}

	/**
	 * The number of columns, which can be indexed from 0..numberOfColumns()-1.
	 *
	 * @return The height of the society.
	 */
	public int numberOfColumns() {
		return society[0].length;
	}

	/**
	 * Place a new cell in the society.
	 * 
	 * @param row The row to grow the cell.
	 * 
	 * @param col The column to grow the cell.
	 *
	 *            Precondition: row and col are in range of the 2D array.
	 */
	public void growCellAt(int row, int col) {
		society[row][col] = true;
	}

	/*
	 * Return true if there is a cell at the given row and column. Return false if
	 * there is no cell at the specified location.
	 *
	 * @param row The row to check.
	 * 
	 * @param col The column to check.
	 * 
	 * @return True if there is a cell at the given row or false if none
	 *
	 * Precondition: row and col are in range.
	 */
	public boolean cellAt(int row, int col) {
		return society[row][col];
	}

	/*
	 * Note this will not be tested for a grade. But you might find this useful
	 * while implementing GameOfLife.
	 * 
	 * Return one big string of cells to represent the current state of the society
	 * of cells (see output below where '.' represents an empty space and 'O' is a
	 * live cell. There is no need to test toString. Simply use it to visually
	 * inspect if needed. Here is one sample output from toString:
	 *
	 * GameOfLife society = new GameOfLife(4, 14); society.growCellAt(1, 2);
	 * society.growCellAt(2, 3); society.growCellAt(3, 4);
	 * System.out.println(society.toString());
	 *
	 * @return A textual representation of this society of cells.
	 *
	 * Sample Output: .............. ..O........... ...O.......... ....O.........
	 */
	@Override
	public String toString() {
		String str = "";
		for (int col = 0; col < society[0].length; col++) {
			if (society[0][col] == true) {
				str += "O";
			} else
				str += ".";
		}
		for (int row = 1; row < society.length; row++) {
			str += "\n";
			for (int coll = 0; coll < society[row].length; coll++) {
				if (society[row][coll] == true) {
					str += "O";
				} else
					str += ".";
			}
		}
		return str;
	}

	/*-
	 * The return values should always be in the range of 0 through 8.
	 *
	 * @return The number of neighbors around any cell using wrap around.
	 * 
	 *
	 * Count the neighbors around the given location. Use wrap around. A cell in row
	 * 0 has neighbors in the last row if a cell is in the same column, or the
	 * column to the left or right. In this example, cell 0,5 has two neighbors in
	 * the last row, cell 2,8 has four neighbors, cell 2,0 has four neighbors, cell
	 * 1,0 has three neighbors. The cell at 3,8 has 3 neighbors. 
	 *
	 * .....O..O
	 * O........
	 * O.......O
	 * O.......O
	 * ....O.O..
	 *
	 * Precondition: row and col are in range of the 2D array
	 *   
	 * @param row
	 * @param col
	 * @return how many neighbors are around the given location, alsways 0..8
	 */
	public int neighborCount(int row, int col) {
		int count = 0;
		for (int irow = row - 1; irow <= row + 1; irow++) {
			for (int icol = col - 1; icol <= col + 1; icol++) {
				if (irow == row && icol == col) {
					continue;
				}
				int nCol = (icol + numberOfColumns()) % numberOfColumns();
				int nRow = (irow + numberOfRows()) % numberOfRows();
				if (society[nRow][nCol]) {
					count++;
				}
			}
		}
		return count;
	}

	/*
	 * Update the state to represent the next society. Typically, some cells will
	 * die off while others are born.
	 */
	public void update() {
		boolean[][] temp = new boolean[numberOfRows()][numberOfColumns()];
		for (int row = 0; row < numberOfRows(); row++) {
			for (int col = 0; col < numberOfColumns(); col++) {
				int nums = neighborCount(row, col);
				if (cellAt(row, col)) {
					if (nums == 3 || nums == 2) {
						temp[row][col] = true;
					}
				}
				if (nums == 1) {
					temp[row][col] = false;
				}
				if (!cellAt(row, col)) {
					if (nums == 3) {
						temp[row][col] = true;
					}
				}
			}
		}
		society = temp;
	}
}