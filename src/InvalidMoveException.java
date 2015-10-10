/**
 * An error that is used to specify when an illegal play attempt was made.
 * @author Ludde
 *
 */
public class InvalidMoveException extends Throwable {
	private static final long serialVersionUID = 3996184698849509500L;

	/**
	 * Creates a new InvalidMoveException
	 * @param string the type of invalid move that was made. Currently any string is valid.
	 */
	public InvalidMoveException(String string) {
		super(string);
	}

}
