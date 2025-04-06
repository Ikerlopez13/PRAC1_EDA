package airports;

import airports.exceptions.*;
import flyingObjects.Aircraft;

import java.util.*;

public class ListBasedMinimalAirport  {

	/*COMPLETE - Ha d'implementar interface MinimalAirport*/

	private List<Aircraft> infrastructure;
	private List<Flight> scheduledFlights;
	private String airportId;
	
	public ListBasedMinimalAirport ()  {
		airportId = "BCN";
		/*COMPLETE*/
	}

	/* COMPLETE */

	
}

class AircraftByNameComparator implements Comparator<Aircraft> {
	
	public AircraftByNameComparator () {}

	public int compare(Aircraft a0, Aircraft a1) {
		/*COMPLETE*/
		return -1; //Change as appropriate
	}
}

