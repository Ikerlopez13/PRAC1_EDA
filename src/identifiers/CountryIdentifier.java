package identifiers;

import java.util.Arrays;

public class CountryIdentifier implements Comparable<CountryIdentifier> {
	
	private static final String [] knownIdentifiers = {
		"UK", "RU", "CAT", "CA", "JP", "FR", "POL", "USA", "MEX", "AR", "ICE", "CH", "QT"
	}; 
	
	static {
		// static initializer
		Arrays.sort(knownIdentifiers);
	}
	
	private String countryCode;
	
	public CountryIdentifier (String countryCode) {
		countryCode = countryCode.toUpperCase();
		if (Arrays.binarySearch(knownIdentifiers, countryCode)<0 ) {
			throw new IllegalArgumentException("Unknown country code: +countryCode");
		}
		this.countryCode = countryCode;
	}
	
	public String getCountryCode () {return this.countryCode;}
	
	public String toString () {return "cc="+this.countryCode;}
	
	public int compareTo (CountryIdentifier countryId) {
		/* this is how country identifiers are sorted:
		   - shorter identifiers (2 letters) go first
		   - if they have the same length then they are sorted lexicographicaly
		 */
		
		/* COMPLETE */
		int cmp;
		
		// shorter countrycodes go first
		cmp = this.countryCode.length()-countryId.countryCode.length();
		if (cmp!=0) return cmp;
		
		// if length is the same, use lexicographic ordering
		return this.countryCode.compareTo(countryId.countryCode);
	}
	
	public boolean equals (Object o) {
		try {
			CountryIdentifier ci = (CountryIdentifier) o;
			return this.compareTo(ci) == 0;
		}
		catch (ClassCastException e) {
			return false;
		}
	}

}
