package ru.parsentev.engineer;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * @author parsentev
 * @since 20.08.2015
 */
public class EngineerCalcTest {
    @Test
    public void percent() {
        try {
            assertThat(new EngineerCalc().percent(100, 1), is(1d));
        } catch (UserException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void percentUniversal() {
        assertThat(new EngineerCalc().percentUniversal(100, 1), is(new Option<Double>(1d)));
    }
}