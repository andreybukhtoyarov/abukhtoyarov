package ru.job4j;

/**
*Class Класс Calculate для вывода текста самой простой прграммы на экран в консоли.
*@autor abukhtoyarov
*@since 20.12.2017
*@version 1.0
*/
public class Calculate {
	
	/**
	*Точка входа в программу. Печатает "Привет Мир".
	*@param args типа массив строк.
	*@return ничего не возвращает.
	*/
	public static void main(String[] args) {
		System.out.println("Hello world");
	}
	
	/**
	*Method echo.
	*@param name Your name.
	*@return Echo plus your name.
	*/
	public String echo(String name) {
		return "Echo, echo, echo : " + name;
	}
}