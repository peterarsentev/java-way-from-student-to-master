package lesson_8;

/**
 * TODO: comment
 * @author parsentev
 * @since 20.08.2015
 */
public class UserException extends Exception {
	public UserException(String message) {
		super(message);
	}

	public UserException(Throwable cause) {
		super(cause);
	}
}
