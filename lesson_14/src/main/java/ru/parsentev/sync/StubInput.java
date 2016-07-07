package ru.parsentev.sync;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.parsentev.interact.Validator;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/**
 * TODO: comment
 * @author parsentev
 * @since 04.05.2016
 */
public class StubInput extends Validator {
	private static final Logger log = LoggerFactory.getLogger(StubInput.class);

	private final Iterator<String> answers;

	public StubInput(final String[] answers) {
		super(null);
		this.answers = Arrays.asList(answers).iterator();
	}

	@Override
	public double getDouble(String message) {
		return Double.valueOf(answers.next());
	}

	@Override
	public int getInt(String message) {
		return Integer.valueOf(answers.next());
	}

	@Override
	public boolean compare(String msg, String answer) {
		return answer.equals(answers.next());
	}

	@Override
	public int getIntFromList(String msg, Collection<Integer> keys) {
		return getInt(msg);
	}

	@Override
	public String getString(String message) {
		return answers.next();
	}
}
