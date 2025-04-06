package flyingObjects;

import identifiers.RegTag;

public class Aircraft implements Comparable<Aircraft> {

	private String name; // Beware! may be null
	private String owner; // Beware! may be null
	private RegTag tag; // cannot be null
	
	public Aircraft (String name, String owner, RegTag tag) {
		if (tag==null) throw new IllegalArgumentException ("Wrong tag: "+tag) ;
		this.name = name;
		this.owner = owner;
		this.tag = tag;
	}
	
	public String getName () {return this.name;}
	public String getOwner () {return this.owner;}
	public RegTag getTag () {return this.tag;}
	
	public String toString () {
		String n, o, t;
		String result;
		if (this.name==null) n = "unknown name";
		else n=this.name;
		if (owner==null) o= "unknown owner";
		else o = "owned by: "+this.owner;
		t = "tag: "+this.tag;
		result = "AIRCRAFT "+n+"\n"+"   "+o+"\n"+"   "+t;
		return result;
	}
	
	public int compareTo (Aircraft aircraft) {
		/* Aircrafts are sorted according to their register tags: aircrafts with lower tags go first */
		return this.tag.compareTo(aircraft.tag);
	}
	
	public boolean equals (Object o) {
		try {
			Aircraft aircraft = (Aircraft) o;
			return  this.compareTo(aircraft) == 0;
		}
		catch (ClassCastException e) {
			return false;
		}
	}
	
}
