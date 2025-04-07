package airports;

import java.util.Comparator;

public class DepartureTimesComparator implements Comparator<ScheduledTravel> {
    @Override
    public int compare(ScheduledTravel travel1, ScheduledTravel travel2) {
        return travel1.getDepartureTime().compareTo(travel2.getDepartureTime());
    }
}
