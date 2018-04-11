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
	
	public String getInfoAboutLocation(String location) {
		String result = "";
		
		for (Country country: countries) {
			for (County county: country.getCounties()) {
				for (Location loc: county.getLocations()) {
					City city = (City) loc.getLocality();
					for (Record rec: city.getRecords()) {
						if (rec.getPlaceName().equals(location)) {
							result += country.getName() + ": " +
									county.getName() + ": " +
									city.getName() + ": " +
									rec.toString() + "\n";
						}
					}
				}
			}
		}
		
		if (result.equals(""))
			return null;
		return result;
	}
	
	public String getInfoAboutActivity(String activity, int daysNum) {
		Record recTarget = null;
		Country countryTarget = null;
		County countyTarget = null;
		City cityTarget = null;
		float min = Float.MAX_VALUE;
		
		for (Country country: countries) {
			for (County county: country.getCounties()) {
				for (Location loc: county.getLocations()) {
					City city = (City) loc.getLocality();
					for (Record rec: city.getRecords()) {
						if (rec.hasActivity(activity)) {
							if (rec.duration() >= daysNum && min > rec.getPrice()) {
								recTarget = rec;
								countryTarget = country;
								countyTarget = county;
								cityTarget = city;
								min = rec.getPrice();
							}
						}
					}
				}
			}
		}
		
		if (recTarget == null)
			return null;
		return (countryTarget.getName() + ": " +
				countyTarget.getName() + ": " +
				cityTarget.getName() + ": " +
				recTarget.toString() + "\n");
	}
}
