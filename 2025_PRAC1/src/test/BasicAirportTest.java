package test;

import identifiers.*;
import flyingObjects.*;
import airports.*;
import static utilsProva.UtilsProva.*;

public class BasicAirportTest {

	public static void main (String [] args) {
		
		/* TEST DATA CREATION STARTS HERE */
		
		final int PLANES_TO_REMOVE = 10;
		
		String [] s_names = {
				 new String ("Birdy"),
				 new String ("Red arrow"),
				 null,
				 new String ("Flying Dutchman"),
				 new String ("Fatima"),
				 null,
				 null,
				 new String ("Fatima plus"),
				 new String ("Nights of Arabia"),
				 null,
				 new String ("Moonlight shadow"),
				 null,
				 new String ("Millenium Falcon"),
				 null,
				 new String ("Moby dick"),
				 new String ("Martona"),
				 null,
				 null,
				 new String ("Eagle one"),
				 new String ("Eagle two")
				 
			};
		
		String [] s_owners = {
			 null, 
			 null, 
			 null,
			 new String("Xeic Ahmed al Qatari"),
			 new String("Xeic Ahmed al Qatari"),
			 new String("Xeic Ahmed al Qatari"),
			 new String("Xeic Ahmed al Qatari"),
			 new String("Xeic Ahmed al Qatari"),
			 new String("Xeic Ahmed al Qatari"),
			 new String("Xeic Ahmed al Qatari"), 
			 null, 
			 null,
			 new String ("British Airways"),
			 new String ("British Airways"),
			 new String ("British Airways"),
			 new String ("Clickair"),
			 new String ("Clickair"),
			 new String ("Bird Airlines"),
			 new String ("Bird Airlines"),
			 null 
		};
		
		RegTag [] sortedTags = {
				new RegTag(2010, 450, new CountryIdentifier(new String("QT"))),
				new RegTag(2010, 1000, new CountryIdentifier(new String("QT"))),
				new RegTag(2011, 123, new CountryIdentifier(new String("QT"))),
				new RegTag(2011, 2000, new CountryIdentifier(new String("QT"))),
				new RegTag(2012, 333, new CountryIdentifier(new String("QT"))),
				new RegTag(2012, 1000, new CountryIdentifier(new String("QT"))),
				new RegTag(2013, 1001, new CountryIdentifier(new String("QT"))),
				new RegTag(2013, 2000, new CountryIdentifier(new String("QT"))),
				new RegTag(2014, 77, new CountryIdentifier(new String("QT"))),
				new RegTag(2014, 1000, new CountryIdentifier(new String("QT"))),
				new RegTag(2015, 999, new CountryIdentifier(new String("QT"))),
				new RegTag(2015, 2000, new CountryIdentifier(new String("QT"))),
				new RegTag(2010, 1000, new CountryIdentifier(new String("UK"))),
				new RegTag(2015, 1000, new CountryIdentifier(new String("UK"))),
				new RegTag(2015, 1020, new CountryIdentifier(new String("UK"))),
				new RegTag(2009, 1000, new CountryIdentifier(new String("CAT"))),
				new RegTag(2010, 1000, new CountryIdentifier(new String("CAT"))),
				new RegTag(2010, 1000, new CountryIdentifier(new String("POL"))),
				new RegTag(2015, 1000, new CountryIdentifier(new String("POL"))),
				new RegTag(2015, 5025, new CountryIdentifier(new String("POL")))
		};
		
		RegTag [] tags = new RegTag [sortedTags.length];
		String [] owners = new String [s_owners.length];
		String [] names = new String [s_names.length];
		for (int i=0; i<tags.length; i++) {
			tags [i] = sortedTags[i];
			names [i] = s_names[i];
			owners [i] = s_owners[i];
		}
		shuffle(tags, owners, names);
		
		
		// create sorted version of planes (s_planes)
		Aircraft [] s_planes = new Aircraft[s_names.length];
		for (int i = 0; i<s_planes.length; i++) {
			s_planes[i] = new Aircraft(s_names[i], s_owners[i], sortedTags[i]);
		}
		
		// create unsorted version of planes (plane)
		Aircraft [] planes = new Aircraft[names.length];
		for (int i = 0; i<planes.length; i++) {
			planes[i] = new Aircraft(names[i], owners[i], tags[i]);
		}
		
		// planes to be removed in removal op.
		Aircraft [] s_removePlanes = new Aircraft[PLANES_TO_REMOVE];
		for (int i=0; i<PLANES_TO_REMOVE; i++) {
			s_removePlanes[i] = s_planes[i];
		}
		utilsProva.UtilsProva.shuffle(s_removePlanes);
		
		// planes to retain after removal op.
		Aircraft [] s_retainPlanes = new Aircraft[s_planes.length - PLANES_TO_REMOVE];
		int index = 0;
		for (int i=PLANES_TO_REMOVE; i<s_planes.length; i++) {
			s_retainPlanes[index] = s_planes[i];
			index++;
		}
		
		
		/* END OF TEST DATA CREATION */

		iniciar("test 0 de  ListBasedMinimalAirport");
		
		MinimalAirport ai = new ListBasedMinimalAirport();
		
		provar("mètode getCapacity. Aeroport buit");
		try {
			if (ai.getCapacity()!=Integer.MAX_VALUE) {
				notificarError("getCapacity no ha proporcionat el resultat esperat", SORTIR);
			}
			else {
				informar("getCapacity ha proporcionat el resultat esperat");
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		
		provar("mètode size. Aeroport buit");
		try {
			if (ai.size()!=0) {
				notificarError("size no ha proporcionat el resultat esperat", SORTIR);
			}
			else {
				informar("size ha proporcionat el resultat esperat");
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		
		provar("mètode isFull. Aeroport buit");
		try {
			if (ai.isFull()) {
				notificarError("isFull no ha proporcionat el resultat esperat", SORTIR);
			}
			else {
				informar("isFull ha proporcionat el resultat esperat");
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		
		provar("mètode isEmpty. Aeroport buit");
		try {
			if (!ai.isEmpty()) {
				notificarError("isEmpty no ha proporcionat el resultat esperat", SORTIR);
			}
			else {
				informar("isEmpty ha proporcionat el resultat esperat");
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		
		// let a null plane try to land
		provar("land amb null");
		try {
			ai.land((Aircraft) null);
			notificarError("land amb null ha generat una NullPointerException com s'esperava", SORTIR);
		}
		catch(NullPointerException npe) {
			informar("comportament de land amb null ok");
		}
		catch(Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		
		// let 5 planes land
		provar("Aterratge d'avions (5) -sense repeticions-");
		try {
			for (int i=0; i<5; i++) {
				ai.land(planes[i]);
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		informar("aterratge aparentment exitós");


		// let a null plane try to take off
		provar("takeoff amb aircraft null");
		try {
			ai.takeOff((Aircraft) null);
			notificarError(
					"TakeOff amb aircraft null no ha generat una NullPointerException com s'esperava",
					SORTIR);
		} catch (NullPointerException npe) {
			informar("comportament de takeOff amb null aparentment ok");
		} catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
				
		
		
		
		provar("mètode size. Aeroport amb avions");
		try {
			if (ai.size()!=5) {
				notificarError("size no ha proporcionat el resultat esperat", SORTIR);
			}
			else {
				informar("size ha proporcionat el resultat esperat");
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		
		provar("mètode isFull");
		try {
			if (ai.isFull()) {
				notificarError("isFull no ha proporcionat el resultat esperat", SORTIR);
			}
			else {
				informar("isFull ha proporcionat el resultat esperat");
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		
		provar("mètode isEmpty. Aeroport amb avions");
		try {
			if (ai.isEmpty()) {
				notificarError("isEmpty no ha proporcionat el resultat esperat", SORTIR);
			}
			else {
				informar("isEmpty ha proporcionat el resultat esperat");
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		
		
		
		finalitzar();
		
		//----
		
	
	} // end of main
	
	
	
	
	
	
	private static void shuffle (Object [] t1, Object [] t2, Object [] t3) {
		java.util.Random alea = new java.util.Random();
		int a, b; 
		Object inter;
		for (int i=1; i<=t1.length; i++) {
			a = alea.nextInt(t1.length);
			b = alea.nextInt(t1.length);
			inter = t1[a];
			t1[a] = t1[b];
			t1[b] = inter;
			
			inter = t2[a];
			t2[a] = t2[b];
			t2[b] = inter;
			
			inter = t3[a];
			t3[a] = t3[b];
			t3[b] = inter;
		}
	}
}
