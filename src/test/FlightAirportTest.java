package test;

import airports.Flight;
import airports.ListBasedMinimalAirport;
import airports.MinimalAirport;
import airports.exceptions.FlightScheduleException;
import flyingObjects.Aircraft;
import identifiers.CountryIdentifier;
import identifiers.RegTag;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import static utilsProva.UtilsProva.*;

public class FlightAirportTest {

    public static void main (String [] args) {

        String [] s_names = {
                "Birdy", //Removed planes
                "Red arrow",
                null,
                "Flying Dutchman",
                "Fatima",
                null,
                null,
                "Fatima plus",
                "Nights of Arabia",
                null,
                "Moonlight shadow", //Retained planes
                null,
                "Millenium Falcon",
                null,
                "Moby dick",
                "Martona",
                null,
                null,
                "Eagle one",
                "Eagle two"

        };

        String [] s_owners = {
                null,
                null,
                null,
                "Xeic Ahmed al Qatari",
                "Xeic Ahmed al Qatari",
                "Xeic Ahmed al Qatari",
                "Xeic Ahmed al Qatari",
                "Xeic Ahmed al Qatari",
                "Xeic Ahmed al Qatari",
                "Xeic Ahmed al Qatari",
                null,
                null,
                "British Airways",
                "British Airways",
                "British Airways",
                "Clickair",
                "Clickair",
                "Bird Airlines",
                "Bird Airlines",
                null
        };

        RegTag[] sortedTags = {
                new RegTag(2010, 450, new CountryIdentifier("QT")),
                new RegTag(2010, 1000, new CountryIdentifier("QT")),
                new RegTag(2011, 123, new CountryIdentifier("QT")),
                new RegTag(2011, 2000, new CountryIdentifier("QT")),
                new RegTag(2012, 333, new CountryIdentifier("QT")),
                new RegTag(2012, 1000, new CountryIdentifier("QT")),
                new RegTag(2013, 1001, new CountryIdentifier("QT")),
                new RegTag(2013, 2000, new CountryIdentifier("QT")),
                new RegTag(2014, 77, new CountryIdentifier("QT")),
                new RegTag(2014, 1000, new CountryIdentifier("QT")),
                new RegTag(2015, 999, new CountryIdentifier("QT")),
                new RegTag(2015, 2000, new CountryIdentifier("QT")),
                new RegTag(2010, 1000, new CountryIdentifier("UK")),
                new RegTag(2015, 1000, new CountryIdentifier("UK")),
                new RegTag(2015, 1020, new CountryIdentifier("UK")),
                new RegTag(2009, 1000, new CountryIdentifier("CAT")),
                new RegTag(2010, 1000, new CountryIdentifier("CAT")),
                new RegTag(2010, 1000, new CountryIdentifier("POL")),
                new RegTag(2015, 1000, new CountryIdentifier("POL")),
                new RegTag(2015, 5025, new CountryIdentifier("POL"))
        };

        // create sorted version of planes (s_planes)
        Aircraft[] s_planes = new Aircraft[s_names.length];
        for(int i = 0; i<s_planes.length; i++){
            s_planes[i] = new Aircraft(s_names[i], s_owners[i], sortedTags[i]);
        }

        //Date format
        DateFormat dateFormatter= new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date1Dep = dateFormatter.parse("2025-03-01 10:05");
            Date date1Arr = dateFormatter.parse("2025-03-01 11:55");

            Date date2Dep = dateFormatter.parse("2025-03-01 12:40");
            Date date2Arr = dateFormatter.parse("2025-03-01 15:40");

            Date date3Dep = dateFormatter.parse("2025-03-05 21:05");
            Date date3Arr = dateFormatter.parse("2025-03-06 00:15");

            Date date4Dep = dateFormatter.parse("2025-04-10 12:40");
            Date date4Arr = dateFormatter.parse("2025-04-10 15:15");

            Date date5Dep = dateFormatter.parse("2025-02-28 12:40");
            Date date5Arr = dateFormatter.parse("2025-02-28 15:15");

            Date date6Dep = dateFormatter.parse("2025-01-25 10:40");
            Date date6Arr = dateFormatter.parse("2025-01-25 11:40");

            //Flight creation
            Flight [] flights = new Flight[] {
                    new Flight("IB01",s_planes[0],"BCN","LHR", date1Dep , date1Arr), //0
                    new Flight("IB02",s_planes[0],"LHR","BCN", date2Dep , date2Arr), //1
                    new Flight("IB03",s_planes[1],"BCN","TFN", date3Dep , date3Arr), //2
                    new Flight("IB04",s_planes[1],"TFN","BCN", date4Dep , date4Arr), //3
                    new Flight("BA01",s_planes[12], "LGW","BCN", date5Dep, date5Arr), //4
                    new Flight("BA02",s_planes[13], "BCN","MAD", date6Dep, date6Arr), //5
            };

            Flight [] s_flights = new Flight[] {
                    new Flight("BA02",s_planes[13], "BCN","MAD", date6Dep, date6Arr), //5
                    new Flight("BA01",s_planes[12], "LGW","BCN", date5Dep, date5Arr), //4
                    new Flight("IB01",s_planes[0],"BCN","LHR", date1Dep , date1Arr), //0
                    new Flight("IB02",s_planes[0],"LHR","BCN", date2Dep , date2Arr), //1
                    new Flight("IB03",s_planes[1],"BCN","TFN", date3Dep , date3Arr), //2
                    new Flight("IB04",s_planes[1],"TFN","BCN", date4Dep , date4Arr), //3
            };

            //We create the airport
            MinimalAirport ai = new ListBasedMinimalAirport();

            //We add the aircrafts
            for (int i=0; i<s_planes.length;i++){
                ai.land(s_planes[i]);
            }

            //We add the flights
            for (int i=0; i<flights.length;i++){
                ai.addFlight(flights[i]);
            }

            iniciar("Test de vols a ListBasedMinimalAirport");

            provar("Afegim vol null a l'aerport");
            try{
                ai.addFlight(null);
                notificarError("No poden afegir-se vols nulls", SORTIR);
            }
            catch(Exception e){
                informar("Resultat aparentment correcte");
            }

            provar("Metode land(flight) quan l'avio del vol ja es a l'aeroport");
            try {
                ai.land(flights[4]);
                ai.land(flights[3]);
                notificarError("Han aterrat avions que haurien d'estar a l'aeroport",SORTIR);
            }
            catch (Exception e){
                informar("Resultat aparentment correcte. ");
            }

            provar("Fem que s'enlairin vols amb origen diferent al nostre aeroport");
            try{
                ai.takeOff(flights[1]);
                ai.takeOff(flights[4]);
                notificarError("S'han enlairat vols que no tenen BCN com a sortida",SORTIR);
            }
            catch (Exception e){
                informar("Resultat aparentment correcte. " + e.getMessage());
            }

            provar("Ordenem vols per data i hora de sortida");
            Flight[] reOrderedFlights = ai.byFlightDepartureTime();

            if (Arrays.equals(reOrderedFlights,s_flights)){
                informar("Vols ordenats correctament");
            }
            else{
                notificarError("ERROR: Vols no s'han ordenat correctament ",SORTIR);
            }

            provar("Enlairem vols");
            try{
                ai.takeOff(flights[0]);
                ai.takeOff(flights[2]);
                ai.takeOff(flights[5]);
                if(ai.size() == 17 && ai.getNumFlights()==3){
                    informar("Vols enlairats correctament");
                }
                else {
                    notificarError("Discrepancia entre vols enlairats i avions/vols que consten a l'aeroport",SORTIR);
                }
            }
            catch (Exception e){
                notificarExcepcio(e,SORTIR);
            }

            provar("Aterrem vols");
            try{
                ai.land(flights[1]);
                ai.land(flights[3]);
                if(ai.size()==19 && ai.getNumFlights()==1){
                    informar("Vols aterrats correctament");
                }
                else{
                    notificarError("Discrepancia entre vols aterrats i avions/vols que consten a l'aeroport", SORTIR);
                }
            }
            catch(Exception e){

            }

        } catch (ParseException e) {
            //We should never get to this point
            notificarExcepcio(e,SORTIR);
        } catch (FlightScheduleException fse) {
            notificarExcepcio(fse, SORTIR);
        }

        finalitzar();

    }
}