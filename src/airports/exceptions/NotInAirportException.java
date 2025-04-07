package airports.exceptions;

public class NotInAirportException extends RuntimeException {
	public NotInAirportException (String msg) {
		super(msg);
	}
}
