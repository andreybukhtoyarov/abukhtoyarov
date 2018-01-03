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
			/* Берем первый символ из массива sub и сравниваем его с первым символо из массива ori.
			 *Если они равны, берем второй символ из sub и ori массивов соответственно,
			 *инкрементируем счетчкик совпавших символов и оба индекса итераций. И так далее.
			 *Если символы (на любой итерации) не совпадают, мы обнуляем счетчик совпавших символов count = 0,
			 *а так же индекс итерации массива Sub(что бы начать проверку с начала слова), а так же, если не совпали
			 *первый символ массива Sub и любой символ второго массива - инкрементируем индекс итерации indexOri++,
			 *т.к. с него не начинается слово.
			 */
			for (int indexSub = 0; indexSub < sub.length && indexOri < ori.length;) {
				if (sub[indexSub] == ori[indexOri]) {
					count++;
					indexOri++;
					indexSub++;
				} else {
					if (indexSub == 0) {
						indexOri++;
					}
					count = 0;
					indexSub = 0;
				}
			}
			// Проверяем совпадает ли счетчик совпавших подряд символов с длиной второго слова.
			if (count == sub.length) {
				contains = true;
			}
		}
		return contains;
	}
}
