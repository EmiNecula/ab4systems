package holidaysApp;

public class Location {
	private Locality locality;
	
	public Location(LocationType type, String name) {
		if (type == LocationType.CITY) {
			locality = new City(name);
		} else {
			locality = null;
		}
	}
	
	public Locality getLocality() {
		return this.locality;
	}
}
