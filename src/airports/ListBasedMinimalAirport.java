package airports;

import airports.exceptions.*;
import flyingObjects.Aircraft;
import java.util.*;

public class ListBasedMinimalAirport implements MinimalAirport {
	private List<Aircraft> infrastructure;
	private List<Flight> scheduledFlights;
	private String airportId;

	public ListBasedMinimalAirport() {
		airportId = "BCN";
		infrastructure = new ArrayList<>();
		scheduledFlights = new ArrayList<>();
	}

	@Override
	public String getAirportId() {
		return airportId;
	}

	@Override
	public int getCapacity() {
		return Integer.MAX_VALUE;
	}

	@Override
	public int size() {
		return infrastructure.size();
	}

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return infrastructure.isEmpty();
	}

	@Override
	public int getNumFlights() {
		return scheduledFlights.size();
	}

}

class AircraftByNameComparator implements Comparator<Aircraft> {

	public AircraftByNameComparator() {}

	public int compare(Aircraft a0, Aircraft a1) {
		if (a0 == null && a1 == null) return 0;
		if (a0 == null) return -1;
		if (a1 == null) return 1;

		if (a0.getName() == null && a1.getName() == null) return 0;
		if (a0.getName() == null) return -1;
		if (a1.getName() == null) return 1;

		return a0.getName().compareTo(a1.getName());
	}
}

