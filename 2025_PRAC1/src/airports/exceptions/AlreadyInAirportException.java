package airports.exceptions;

public class AlreadyInAirportException extends RuntimeException {
	
	public AlreadyInAirportException (String msg) {
		super(msg);
	}

}
