package airports;

import airports.exceptions.*;
import flyingObjects.Aircraft;

import java.util.*;

public class ListBasedMinimalAirport implements MinimalAirport {
	private List<Aircraft> infrastructure;
	private List<Flight> flights;
	private String airportId;

	public ListBasedMinimalAirport() {
		airportId = "BCN";
		infrastructure = new ArrayList<>();
		flights = new ArrayList<>();
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
		return infrastructure.size() >= getCapacity();
	}

	@Override
	public boolean isEmpty() {
		return infrastructure.isEmpty();
	}

	@Override
	public int getNumFlights() {
		return flights.size();
	}

	@Override
	public void land(Aircraft a) {
		if (a == null) {
			throw new NullPointerException("L'aircraft no pot ser null");
		}
		if (infrastructure.contains(a)) {
			throw new AlreadyInAirportException("L'aircraft ja es a la lista");
		}
		if (isFull()) {
			throw new FullAirportException("L'aeroport esta ple");
		}
		infrastructure.add(a);
	}

	@Override
	public void takeOff(Aircraft a) {
		if (a == null) {
			throw new NullPointerException("L'aircraft no pot ser null");
		}
		if (!infrastructure.contains(a)) {
			throw new NotInAirportException("L'aircraft no esta a la lista");
		}
		infrastructure.remove(a);
	}

	@Override
	public void addFlight(Flight f) {
		if (f == null) {
			throw new NullPointerException("El flight no pot ser null");
		}
		if (!f.getOrigin().equals(getAirportId()) && !f.getDestination().equals(getAirportId())) {
			throw new FlightScheduleException("El flight no esta relacionat amb aquest aeroport");
		}
		if (flights.contains(f)) {
			throw new FlightAlreadyExistsException("El flight ja existeix");
		}
		flights.add(f);
	}

	@Override
	public void takeOff(Flight f) {
		if (f == null) {
			throw new NullPointerException("El flight no pot ser null");
		}
		if (!flights.contains(f)) {
			throw new NotInAirportException("Aquest flight no ha estat registrat a l'airport");
		}
		if (!f.getOrigin().equals(getAirportId())) {
			throw new FlightScheduleException("L'avió no surt de aquest airport");
		}
		if (!infrastructure.contains(f.getAircraft())) {
			throw new FlightScheduleException("L'aircraft no pot ser borrat de l'aeroport");
		}

		flights.remove(f);
		infrastructure.remove(f.getAircraft());
	}

	@Override
	public void land(Flight f) {
		if (f == null) {
			throw new NullPointerException("El flight no pot ser null");
		}
		if (!flights.contains(f)) {
			throw new NotInAirportException("Aquest flight no ha estat registrat a l'airport");
		}
		if (!f.getDestination().equals(getAirportId())) {
			throw new FlightScheduleException("L'avió no arriba a aquest airport");
		}

		if (isFull()) {
			throw new FlightScheduleException("L'aircraft no pot ser afegit a l'aeroport perque esta ple");
		}

		flights.remove(f);
		infrastructure.add(f.getAircraft());
	}

	@Override
	public Flight[] byFlightDepartureTime() {
		Flight[] flights = this.flights.toArray(new Flight[0]);
		Arrays.sort(flights, new DepartureTimesComparator());
		return flights;
	}

	@Override
	public Aircraft[] byAircraftName() {
		Aircraft[] aircrafts = infrastructure.toArray(new Aircraft[0]);
		Arrays.sort(aircrafts, new AircraftByNameComparator());
		return aircrafts;
	}

	@Override
	public Aircraft[] allAircrafts() {
		Aircraft[] aircrafts = infrastructure.toArray(new Aircraft[0]);
		Arrays.sort(aircrafts);
		return aircrafts;
	}

	class AircraftByNameComparator implements Comparator<Aircraft> {

		public AircraftByNameComparator() {
		}

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
}
