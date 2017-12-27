package ru.job4j.condition;

/**
 *This is simple (dummy) chat bot.
 *@author Andrey Bukhtoyarov (andreymedoed@gmail.com).
 *@version 1.0.
 *@since 27.12.2017.
 */
public class DummyBot {
    /**
     * This method answers questions.
     * @param question - question for bot.
     * @return result - answer of bot.
     */
    public String answer(String question) {
        String result = "Это ставит меня в тупик. Спросите другой вопрос.";

        if (question.equals("Привет, Бот!")) {
            result = "Привет, умник.";
        } else if (question.equals("Пока.")) {
            result = "До скорой встречи.";
        }
        return result;
    }
}
