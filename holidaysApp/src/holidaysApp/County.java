package holidaysApp;

import java.util.ArrayList;

public class County {
	private ArrayList<Location> locations;
	private String name;
	
	public County(String name) {
		this.name = name;
		locations = new ArrayList<Location>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Location> getLocations() {
		return this.locations;
	}
}
