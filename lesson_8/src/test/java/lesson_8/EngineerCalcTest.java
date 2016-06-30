package lesson_8;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * TODO: comment
 * @author parsentev
 * @since 20.08.2015
 */
public class EngineerCalcTest {
	@Test
	public void percent() throws UserException{
		final EngineerCalc calc = new EngineerCalc();
		final double result = calc.percent(100, 1);
		assertThat(result, is(1d));
	}

	@Test(expected = UserException.class)
	public void whenPercentIsLessZeryShouldThrowException() throws UserException {
		new EngineerCalc().percent(-1, 1);
	}

	@Test
	public void percentUniversal() {
		assertThat(new EngineerCalc().percentUniversal(100, 1), is(new Option<Double>(1d)));
	}
}