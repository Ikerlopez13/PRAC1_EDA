package identifiers;


public class RegTag implements Comparable<RegTag> {
	
	private int year;
	private int number;
	private CountryIdentifier countryId;
	
	public RegTag (int year, int number, CountryIdentifier countryId) {
		if (year<1990 || year>2025) throw new IllegalArgumentException("Wrong year "+year);
		if (number <=0) throw new IllegalArgumentException("Wrong number "+number);
		if (countryId==null) throw new IllegalArgumentException("Wrong country identifier "+countryId);
		
		this.year = year;
		this.number = number;
		this.countryId = countryId;
	}
	
	public int getYear () {return this.year;}
	public int getNumber () {return this.number;}
	public CountryIdentifier  getCountryId() {return this.countryId;}
	
	public String toString () {
		return "["+this.countryId.toString()
				+" (y:"+this.year+") - "+this.number
				+"]";
	}
	
	public int compareTo (RegTag regTag) {
		/* this is how register tags are sorted:
		   - tags with lower country identifier go first
		   - if country identifiers are equal, older tags go first
		   - Finally, numbers are taken into account: tags with lower number go first
		 */
		
		/* COMPLETE */ 

		int cmp;
		
		cmp = this.countryId.compareTo(regTag.countryId);
		if (cmp!=0) return cmp;
		
		cmp = this.year - regTag.year;
		if (cmp!=0) return cmp;
		
		return this.number-regTag.number;
	}
	
	public boolean equals (Object o) {
		try {
			RegTag rt = (RegTag) o;
			return this.compareTo(rt) == 0;
		}
		catch (ClassCastException e) {
			return false;
		}
	}

}
