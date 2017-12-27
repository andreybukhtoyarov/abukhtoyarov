package ru.job4j.condition;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DummyBotTest {
    @Test
    public void whenGreetBot() {
        DummyBot dummyBot = new DummyBot();
        assertThat(dummyBot.answer(
                "Привет, Бот!"),
                is("Привет, умник.")
        );
    }

    @Test
    public void whenByuBot() {
        DummyBot dummyBot = new DummyBot();
        assertThat(dummyBot.answer(
                "Пока."),
                is("До скорой встречи.")
        );
    }

    @Test
    public void whenUnKnownBot() {
        DummyBot dummyBot = new DummyBot();
        assertThat(dummyBot.answer(
                "Как твое самочувствие, Бот?"),
                is("Это ставит меня в тупик. Спросите другой вопрос.")
        );
    }
}
