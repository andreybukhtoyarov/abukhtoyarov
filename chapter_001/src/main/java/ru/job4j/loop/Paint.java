package ru.job4j.loop;

/**
 * This class .
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com)..
 *@version %Id%.
 *@since 0.1.
 */
public class Paint {
	/**
	 * This method retunr a line that contains a pyramid drawing in the pseudo-graphic .
	 *@param height - height of the pyramid.
	 *@return line - full pyramide.
	 */
	public static String pyramid(int height) {
		StringBuilder screen = new StringBuilder();
		// the width = (height * 2) - 1.
		int width = (height * 2) - 1;
		//move on the row.
		for (int row = 0; row < height; ++ row) {
			//move on column.
			for (int column = 0; column < width; ++column) {
				if (row >= height - column - 1 && row + height - 1 >= column ) {
					screen.append("^");
				} else {
					screen.append(" ");
				}
			}
			screen.append(System.lineSeparator());
		}
		return screen.toString();
	}
	
	/**
	 * This method retunr a line that contains a right side of pyramide drawing in the pseudo-graphic .
	 *@param height - height of a the pyramide.
	 *@return line - right side of pyramide.
	 */
	public String rightTrl(int height) {
		StringBuilder screen = new StringBuilder();
		// the width will be = the height;
		int width = height;
		// move on the lines (row).
		for (int row = 0; row < height; ++row) {
			// move on the column.
			for (int column = 0; column < width; ++ column) {
				// if row index >= column index then append "^".
				if (row >= column) {
					screen.append("^");
				} else {
					screen.append(" ");
				}
			}
			// append transfer string.
			screen.append(System.lineSeparator());
		}
		return screen.toString();
	}
	
	/**
	 * This method retunr a line that contains a left side of pyramide drawing in the pseudo-graphic .
	 *@param height - height of a the pyramide.
	 *@return line - left side of pyramide.
	 */
	public String leftTrl(int height) {
		StringBuilder screen = new StringBuilder();
		int width = height;
		
		for (int row = 0; row < height; ++ row) {
			for (int column = 0; column < width; ++column) {
				// if row >= width - colunm index then append "^".
				if (row >= width - column -1) {
					screen.append("^");
				} else {
					screen.append(" ");
				}
			}
			screen.append(System.lineSeparator());
		}
	}
}
