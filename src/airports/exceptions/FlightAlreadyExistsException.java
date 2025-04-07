package airports.exceptions;

public class FlightAlreadyExistsException extends RuntimeException{
  public FlightAlreadyExistsException (String msg) {
        super(msg);
    }

}
