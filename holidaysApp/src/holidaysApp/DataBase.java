package holidaysApp;

import java.util.ArrayList;

public class DataBase {
	private ArrayList<Country> countries;
	
	public DataBase() {
		countries = new ArrayList<Country>();
	}
	
	public ArrayList<Country> getCountries() {
		return this.countries;
	}
}
