package airports;

import flyingObjects.Aircraft;
import identifiers.RegTag;

public interface MinimalAirport {

	public String getAirportId();
	public int getCapacity ();
	public int size();
	public boolean isFull();
	public boolean isEmpty();
	public int getNumFlights();
	
	public void land (Aircraft a);
	/* When an aircraft lands, it is stored in the airport. This method throws
	   - a NullPointerException if the parameter is null
	   - an AlreadyInAirportException  if the airport already contains the same aircraft
	   - a FullAirportException if the airport is full 
	 */
	
	
	public void takeOff (Aircraft a);
	/* When an aircraft takes off, it is removed from the airport. This method throws 
	   - NullPointerException if the parameter is null
	   - NotInAirportException if the airport does not contain the aircraft
	 */


	public void addFlight(Flight f);
	/* Adds a flight to the airport. This method throws
	   - NullPointerException if the parameter is null
	   - FlightScheduleException if the flight does not depart from or arrives at the airport
	   - FlightAlreadyExistsException if the airport already contains the flight
	 */

	public void takeOff (Flight f);
	/* When a flight takes off, it is removed from the airport. The flight's aircraft is also removed from the airport. This method throws:
	  - NullPointerException if the parameter is null
	  - NotInAirportException if flight has not been registered in the airport
	  - FlightScheduleException if the flight does not depart (the flight's origin) from the current airport
	  - FlightScheduleException if the aircraft cannot be removed from the airport
	 */

	public void land (Flight f);
	/* When a flight lands, it is removed from the airport. The flight's aircraft is added to the airport. This method throws:
	   - a NullPointerException if the parameter is null
	   - NotInAirportException if flight has not been registered in the airport
	   - FlightScheduleException if the flight does not arrive (destination) at the current airport
	   - FlightScheduleException if the aircraft cannot be added to the airport
	 */

	public Flight [] byFlightDepartureTime();
	/* Returns an array containing all the flights in the airport.
	   This array:
	   - has the exact length (no empty –null- positions)
	   - has length 0 if there are no flights
	   - is sorted by flight departure time
	 */

	public Aircraft [] byAircraftName();
	/* Returns an array containing all the aircrafts in the airport.
	   This array:
	   - has the exact length (no empty –null- positions)
	   - has length 0 if there are no aircrafts
	   - is sorted by aircraft name (ascending, lexicographically, case sensitive). Aircrafts without name go first
	 */

	
	public Aircraft [] allAircrafts ();
	/* Returns an array containing all the aircrafts in the airport
	   This array:
	   - has the exact length (no empty –null- positions)
	   - has length 0 if the airport is empty
	   - is sorted according to the natural ordering of the aircrafts (ascending)
	 */
 	
}
