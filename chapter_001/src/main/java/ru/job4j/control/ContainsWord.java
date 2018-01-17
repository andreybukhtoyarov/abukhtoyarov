package ru.job4j.control;

/**
 * This class contain method which determines whether the given first word contains the second word.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class ContainsWord {
	/**
	 * This method determines whether the first word contains the second word.
	 *@param origin - first word.
	 *@param subword - second word.
	 *@return true if first word contains the second word, else return false.
	 */
	boolean contains(String origin, String subword) {
		boolean contains = false;
		// Создаем массивы символов из аргументов метода.
		char[] ori = origin.toCharArray();
		char[] sub = subword.toCharArray();
		// Проверяем длинне второе слово или нет. Если нет - то можно начинать.
		if (ori.length >= sub.length) {
			// count счетчик совпавших подряд символов.
			int count = 0;
			// indexOri индекс элементов массива ori.
			// indexSub индекс элементов массива sub.
			int indexOri = 0;
			int indexSub = 0;
			while (indexOri < ori.length && count < sub.length && indexSub < sub.length) {
				if (sub[indexSub] == ori[indexOri]) {
					count++;
					indexOri++;
					indexSub++;
				} else {
					count = 0;
					indexOri++;
					indexSub = 0;
				}
			}
			if (count == sub.length) {
				contains = true;
			}
		}
		return contains;
	}
}
