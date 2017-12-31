package ru.job4j.loop;

import java.util.function.BiPredicate;

/**
 *This class is refactoring  of class Paint.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class PaintRefactor {
	/**
	 *This method return a line that contains a right side of pyramid drawing in the pseudo-graphic.
	 *@param height - height of a the pyramid.
	 *@return line - right side of pyramid.
	 */
	public String rightTrl(int height) {
		return this.loopBy(
					height,
					height,
					(row, column) -> row >= column
		);
	}
	
	/**
	 *This method return a line that contains a left side of pyramid drawing in the pseudo-graphic.
	 *@param height - height of a the pyramid.
	 *@return line - left side of pyramid.
	 */
	public String leftTrl(int height) {
		return this.loopBy(
					height,
					height,
					(row, column) -> row >= height - column - 1
		);
	}
	
	/**
	 *This method return a line that contains a pyramid drawing in the pseudo-graphic.
	 *@param height - height of the pyramid.
	 *@return line - full pyramid.
	 */
	public String pyramid(int height) {
		return this.loopBy(
					height,
					2 * height - 1,
					(row, column) -> row >= height - column - 1 && row + height - 1 >= column
		);
	}
	
	private String loopBy(int height, int weight, BiPredicate<Integer, Integer> predict) {
		StringBuilder screen = new StringBuilder();
		for (int row = 0; row != height; ++row) {
			for (int column = 0; column != weight; ++column) {
				if (predict.test(row, column)) {
					screen.append("^");
				} else {
					screen.append(" ");
				}
			}
			screen.append(System.lineSeparator());
		}
		return screen.toString();
	}
}
	