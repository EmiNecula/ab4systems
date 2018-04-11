package holidaysApp;

import java.util.ArrayList;

public class Country {
	private ArrayList<County> counties;
	private String name;
	
	public Country(String name) {
		this.name = name;
		counties = new ArrayList<County>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<County> getCounties() {
		return this.counties;
	}
}
