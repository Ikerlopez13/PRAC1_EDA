package airports;

import airports.exceptions.FlightScheduleException;
import flyingObjects.Aircraft;

import java.util.Date;

import airports.exceptions.FlightScheduleException;
import flyingObjects.Aircraft;

import java.util.Date;

public class Flight implements ScheduledTravel{

    private String id;
    private String origin;
    private String destination;
    private Aircraft aircraft;
    private Date departure;
    private Date arrival;

    public Flight(String id, Aircraft aircraft, String origin, String destination, Date departure, Date arrival){
        if(id == null){throw new IllegalArgumentException("El id no pot ser null");}
        if(aircraft == null){throw new IllegalArgumentException("L'objecte aircraft no pot ser null");}
        if(arrival == null || departure == null){throw new FlightScheduleException("Ni la arrival ni el departure date poden ser null");}
        if(origin == null || destination == null){throw new FlightScheduleException("Ni l'origin ni la destination poden ser null");}
        if(departure.compareTo(arrival)<0){throw new FlightScheduleException("L'arrival no pot ser avans de la departure time");}

        this.id = id;
        this.aircraft = aircraft;
        this.origin = origin;
        this.destination = destination;
        this.departure = departure;
        this.arrival = arrival;
    }

    public String getId(){
        return this.id;
    }
    public String getOrigin(){
        return this.origin;
    }
    public String getDestination(){
        return this.destination;
    }
    public Aircraft getAircraft(){
        return this.aircraft;
    }

    @Override
    public boolean equals(Object o){
        if(!(o instanceof Flight)){
            return false;
        }
        Flight other = (Flight) o;
        return this.id.equals(other.getId());
    }

    @Override
    public Date getDepartureTime() {
        return this.departure;
    }

    @Override
    public Date getArrivalTime() {
        return this.arrival;
    }
}
