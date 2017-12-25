package ru.job4j.calculator;

/**
 *This class is simple calculator.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version 1.0.
 *@since 25.12.2017.
 */
public class Calculator {
	/** Field result. */
	private double result;

	/**
	 *This method is getter, return value of field result.
	 *@return double result.
	 */
	public double getResult() {
		return this.result;
	}

	/**
	 *This method add together two number.
	 *@param first - first number.
	 *@param second - second number.
	 */
	void add(double first, double second) {
		this.result = first + second;
	}

	/**
	 *This method subtract numbers.
	 *@param first - first number.
	 *@param second - second number.
	 */
	void subtract(double first, double second) {
		this.result = first - second;
	}

	/**
	 *This method division numbers.
	 *@param first - first number.
	 *@param second - second number.
	 */
	void div(double first, double second) {
		this.result = first / second;
	}

	/**
	 *This method multiple numbers.
	 *@param first - first number.
	 *@param second - second number.
	 */
	void multiple(double first, double second) {
		this.result = first * second;
	}
}