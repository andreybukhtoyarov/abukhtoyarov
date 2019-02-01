package ru.job4j.bytesteramcheck;

import org.junit.Test;

import java.io.ByteArrayInputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class EvenCheckerTest {

    private EvenChecker checker = new EvenChecker();

    @Test
    public void whenByteArrayInputStreamZeroZeroOneTenThenTrue() {
        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{0, 0, 1, 10});
        assertThat(checker.isEvenByte(stream), is(true));
    }

    @Test
    public void whenByteArrayInputStreamZeroZeroOneElevenThenFalse() {
        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{0, 0, 1, 11});
        assertThat(checker.isEvenByte(stream), is(false));
    }

    @Test
    public void whenIsEvenCharacterStubInputOneTwoFourIsTrue() {
        StubInput input = new StubInput(new char[]{'1', '2', '4'});
        assertThat(checker.isEvenCharacter(input), is(true));
    }

    @Test
    public void whenIsEvenCharacterStubInputOneTwoThreeIsFalse() {
        StubInput input = new StubInput(new char[]{'1', '2', '3'});
        assertThat(checker.isEvenCharacter(input), is(false));
    }

    @Test
    public void whenUseIsEvenWithByteArrayInputStreamZeroZeroOneTenThenTrue() {
        ByteArrayInputStream stream = new ByteArrayInputStream(new byte[]{0, 0, 1, 10});
        assertThat(checker.isEven(stream), is(true));
    }

    @Test
    public void whenUseIsEvenWithCharacterStubInputOneTwoThreeIsFalse() {
        StubInput input = new StubInput(new char[]{'1', '2', '3'});
        assertThat(checker.isEven(input), is(false));
    }
}