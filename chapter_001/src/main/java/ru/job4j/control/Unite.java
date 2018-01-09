package ru.job4j.control;

/**
 * This class contain method which combines two arrays in ascending order.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version %Id%.
 *@since 0.1.
 */
public class Unite {
    /**
     * This method combines two arrays in ascending order.
     *@param first - first integer array.
     *@param second - second integer array.
     *@return united array.
     */
    public int[] uniteArray(int[] first, int[] second) {
        int len = first.length + second.length;
        int[] array = new int[len];

        int indexFirst = 0;
        int indexSecond = 0;

        for (; indexFirst < first.length && indexSecond < second.length;) {
            if (first[indexFirst] < second[indexSecond]) {
                array[indexFirst + indexSecond] = first[indexFirst];
                indexFirst++;
            } else {
                array[indexFirst + indexSecond] = second[indexSecond];
                indexSecond++;
            }
        }

        for (; indexFirst < first.length;) {
            array[indexFirst + indexSecond] = first[indexFirst];
            indexFirst++;
        }

        for (; indexSecond < second.length;) {
            array[indexFirst + indexSecond] = second[indexSecond];
            indexSecond++;
        }

        return array;
    }
}
