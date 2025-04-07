package airports;

import airports.Flight;
import airports.exceptions.AlreadyInAirportException;
import flyingObjects.Aircraft;
import identifiers.RegTag;
import java.util.Arrays;
import java.util.Comparator;

public interface MinimalAirport {

	public String getAirportId();
	public int getCapacity ();
	public int size();
	public boolean isFull();
	public boolean isEmpty();
	public int getNumFlights();
	
	public void land(Aircraft a) {
        if(a == null) { throw new NullPointerException("L'aircraft no pot ser null"); }
        if(infrastructure.contains(a)) { throw new AlreadyInAirportException("L'aircraft ja es a la lista"); }
        if(isFull()) { throw new FullAirportException("L'aeroport esta ple"); }

        infrastructure.add(a);
    }
	
	
	public void takeOff (Aircraft a){
		if(a == null){throw new NullPointerException("L'aircraft no pot ser null");};
		if(!ListBasedMinimalAirport.infraestructure.contains(a)){throw new NotInAirportException("L'aircraft no esta a la lista");};

        ListBasedMinimalAirport.infrastructure.remove(a);
	}
	/* When an aircraft takes off, it is removed from the airport. This method throws 
	   - NullPointerException if the parameter is null
	   - NotInAirportException if the airport does not contain the aircraft
	 */


	public void addFlight(Flight f) {
        if(f == null) {
            throw new NullPointerException("El flight no pot ser null");
        }
        if(!f.getOrigin().equals(getAirportId()) && !f.getDestination().equals(getAirportId())) {
            throw new FlightScheduleException("El flight no esta relacionat amb aquest aeroport");
        }
        if(scheduledFlights.contains(f)) {
            throw new FlightAlreadyExistsException("El flight ja existeix");
        }
        scheduledFlights.add(f);
    }
		
	/* Adds a flight to the airport. This method throws
	   - NullPointerException if the parameter is null
	   - FlightScheduleException if the flight does not depart from or arrives at the airport
	   - FlightAlreadyExistsException if the airport already contains the flight
	 */

	 public void takeOff(Flight f) {
        if(f == null) {
            throw new NullPointerException("El flight no pot ser null");
        }
        if(!scheduledFlights.contains(f)) {
            throw new NotInAirportException("Aquest flight no ha estat registrat a l'airport");
        }
        if(!f.getOrigin().equals(getAirportId())) {
            throw new FlightScheduleException("L'avió no surt de aquest airport");
        }
        if(!infrastructure.contains(f.getAircraft())) {
            throw new FlightScheduleException("L'aircraft no pot ser borrat de l'aeroport");
        }

        scheduledFlights.remove(f);
        infrastructure.remove(f.getAircraft());
    }
	/* When a flight takes off, it is removed from the airport. The flight's aircraft is also removed from the airport. This method throws:
	  - NullPointerException if the parameter is null
	  - NotInAirportException if flight has not been registered in the airport
	  - FlightScheduleException if the flight does not depart (the flight's origin) from the current airport
	  - FlightScheduleException if the aircraft cannot be removed from the airport
	 */

	 public void land(Flight f) {
        if(f == null) {
            throw new NullPointerException("El flight no pot ser null");
        }
        if(!scheduledFlights.contains(f)) {
            throw new NotInAirportException("Aquest flight no ha estat registrat a l'airport");
        }
        if(!f.getDestination().equals(getAirportId())) {
            throw new FlightScheduleException("L'avió no arriba a aquest airport");
        }

        if(isFull()) {
            throw new FlightScheduleException("L'aircraft no pot ser afegit a l'aeroport perque esta ple");
        }

        scheduledFlights.remove(f);
        infrastructure.add(f.getAircraft());
    }
	/* When a flight lands, it is removed from the airport. The flight's aircraft is added to the airport. This method throws:
	   - a NullPointerException if the parameter is null
	   - NotInAirportException if flight has not been registered in the airport
	   - FlightScheduleException if the flight does not arrive (destination) at the current airport
	   - FlightScheduleException if the aircraft cannot be added to the airport
	 */

	 public Flight[] byFlightDepartureTime() {
        Flight[] flights = scheduledFlights.toArray(new Flight[0]);
        Arrays.sort(flights, new DepartureTimesComparator());
        return flights;
    }

	/* Returns an array containing all the flights in the airport.
	   This array:
	   - has the exact length (no empty �null- positions)
	   - has length 0 if there are no flights
	   - is sorted by flight departure time
	 */

    public Aircraft[] byAircraftName() {
        Aircraft[] aircrafts = infrastructure.toArray(new Aircraft[0]);
        Arrays.sort(aircrafts, new AircraftByNameComparator());
        return aircrafts;
    }

	/* Returns an array containing all the aircrafts in the airport.
	   This array:
	   - has the exact length (no empty �null- positions)
	   - has length 0 if there are no aircrafts
	   - is sorted by aircraft name (ascending, lexicographically, case sensitive). Aircrafts without name go first
	 */

    public Aircraft[] allAircrafts() {
        Aircraft[] aircrafts = infrastructure.toArray(new Aircraft[0]);
        Arrays.sort(aircrafts);
        return aircrafts;
    }
	/* Returns an array containing all the aircrafts in the airport
	   This array:
	   - has the exact length (no empty �null- positions)
	   - has length 0 if the airport is empty
	   - is sorted according to the natural ordering of the aircrafts (ascending)
	 */
 	
}
