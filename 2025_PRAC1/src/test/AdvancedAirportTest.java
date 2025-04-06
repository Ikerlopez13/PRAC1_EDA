package test;

import java.util.Arrays;

import airports.exceptions.AlreadyInAirportException;
import airports.exceptions.NotInAirportException;
import identifiers.*;
import flyingObjects.*;
import airports.*;
import static utilsProva.UtilsProva.*;

public class AdvancedAirportTest {

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

		iniciar("test 1 de  ListBasedMinimalAirport");

		MinimalAirport ai = new ListBasedMinimalAirport();

		// let the planes land...
		provar("Aterratge d'avions -sense repeticions-");
		try {
			for (Aircraft a : planes) {
				ai.land(a);
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		informar("aterratge aparentment exitós");

		// try to land some "repeated" plane...
		provar("Aterratge d'avions repetits");
		for (Aircraft a : planes) {
			try {
				ai.land(copy(a)); // això hauria de fer saltar una excepció
				notificarError("No s'ha detectat avio repetit", SORTIR);
			}
			catch (AlreadyInAirportException ex) {
				// ok. aquí és on volem arribar
			}
			catch(Exception e) {
				notificarExcepcio(e, SORTIR);
			}
		}
		informar("comportament aparentment correcte davant d'aterratge d'avions repetits");

		// get all the aircrafts and check (shallow check) if it's well sorted or not...
		provar("comportament del mètode allAircrafts");
		Aircraft [] all = null;
		try {
			all = ai.allAircrafts();
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}

		if (Arrays.equals(all, s_planes)) {
			informar("El resultat proporcionat per allAircrafts sembla correcte");
		}
		else {
			notificarError("El resultat proporcionat per allAircrafts NO ÉS L'ESPERAT",SORTIR);
		}

		provar("comportament del mètode takeOff amb paràmetre Aircraft i avions existents");
		try {
			for (int i=0; i<s_removePlanes.length; i++) {
				ai.takeOff(s_removePlanes[i]);
			}
		}
		catch(Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		informar("Enlairment aparentment exitós");

		// get all the aircrafts and see that those that must remain DO REMAIN

		provar("contingut després de l'enlairament");
		try {
			all = ai.allAircrafts();
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}

		if (Arrays.equals(all, s_retainPlanes)) {
			informar("El resultat proporcionat per allAircrafts (després enlairament) sembla correcte");
		}
		else {
			notificarError("El resultat proporcionat per allAircrafts (després enlairament) NO ÉS L'ESPERAT",SORTIR);
		}


		provar("comportament del mètode takeOff amb paràmetre aircraft i avions NO existents");
		try {
			try {
				for (int i=0; i<s_removePlanes.length; i++) {
					ai.takeOff(s_removePlanes[i]);
					notificarError("takeoff amb aircraft inexistent no ha repost de la manera esperada", SORTIR);
				}
			}
			catch (NotInAirportException ex) {
				// ok. Aquí és on volem estar
			}
		}
		catch(Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		informar("Comportament de takeoff amb aircraft inexistent aparentment correcte");


		provar("re-aterratge d'avions previament enlairats");
		try {
			for (Aircraft a : s_removePlanes) {
				ai.land(a);
			}
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}
		informar("re-aterratge aparentment exitós");

		provar("comportament del mètode allAircrafts després del re-aterratge");
		try {
			all = ai.allAircrafts();
		}
		catch (Exception e) {
			notificarExcepcio(e, SORTIR);
		}

		if (Arrays.equals(all, s_planes)) {
			informar("El resultat proporcionat per allAircrafts (després re-aterratge) sembla correcte");
		}
		else {
			notificarError("El resultat proporcionat per allAircrafts (després re-aterratge NO ÉS L'ESPERAT",SORTIR);
		}

		finalitzar();

		//----


	} // end of main

	private static Aircraft copy (Aircraft a) {
		return new Aircraft (copy(a.getName()),
				copy(a.getOwner()),
				copy(a.getTag())
		);

	}

	private static CountryIdentifier copy (CountryIdentifier c) {
		return new CountryIdentifier(copy(c.getCountryCode()));
	}

	private static RegTag copy (RegTag r) {
		return new RegTag(r.getYear(), r.getNumber(), copy(r.getCountryId()));
	}

	private static String copy (String s) {
		if (s==null) return null;
		else return new String (s);
	}

	private static void shuffle (Object [] t1, Object [] t2, Object [] t3) {
		java.util.Random rand = new java.util.Random();
		int a, b;
		Object inter;
		for (int i=1; i<=t1.length; i++) {
			a = rand.nextInt(t1.length);
			b = rand.nextInt(t1.length);
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
