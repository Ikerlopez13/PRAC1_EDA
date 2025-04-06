package airports;

import java.util.Date;

public interface ScheduledTravel {

    /**
     * Gets departure date and time of any scheduled travel (e.g. flight)
     * @return Arrival date
     */
    public Date getDepartureTime();

    /**
     * Gets arrival date and time of any scheduled travel (e.g. flight)
     * @return Arrival date
     */
    public Date getArrivalTime();

}
