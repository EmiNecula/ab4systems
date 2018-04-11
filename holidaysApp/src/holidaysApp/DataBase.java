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
	
	private class DetailedLocation {
		private Record record;
		private City city;
		private County county;
		private Country country;
		
		public DetailedLocation(Record record, City city, County county, Country country) {
			this.record = record;
			this.city = city;
			this.country = country;
			this.county = county;
		}
		
		public Record getRecord() {
			return record;
		}
		
		public void setRecord(Record record) {
			this.record = record;
		}
		
		public City getCity() {
			return city;
		}
		
		public void setCity(City city) {
			this.city = city;
		}
		
		public County getCounty() {
			return county;
		}
		
		public void setCounty(County county) {
			this.county = county;
		}
		
		public Country getCountry() {
			return country;
		}
		
		public void setCountry(Country country) {
			this.country = country;
		}
	}

	/*
	 * Adds in the topLocations list a new record only if it is in already
	 * checked top 5 cheapest locations. If the size of topLocations
	 * exceeds 5, its last element is removed.
	 */
	private void addLocation(ArrayList<DetailedLocation> topLocations, Record rec, City city, County county,
			Country country) {
		int len = topLocations.size();
		DetailedLocation newLoc;
		
		for(int i = 0; i < len; ++i)
			if (rec.getPrice() < topLocations.get(i).getRecord().getPrice()) {
				newLoc = new DetailedLocation(rec, city, county, country);
				topLocations.add(i, newLoc);
				
				if (len >= 5)
					topLocations.remove(5);
				return;
			}
		
		if (len < 5) {
			newLoc = new DetailedLocation(rec, city, county, country);
			topLocations.add(newLoc);
		}
	}
	
	/*
	 * Parses the topLocations list into a string.
	 */
	private String parseLocations(ArrayList<DetailedLocation> topLocations) {
		if (topLocations.size() == 0)
			return null;
		
		String result = "";
		int len = topLocations.size();
		DetailedLocation actLocation;
		for (int i = 0; i < len; ++i) {
			actLocation = topLocations.get(i);
			result += actLocation.getCountry().getName() + ": " +
					actLocation.getCounty().getName() + ": " +
					actLocation.getCity().getName() + ": " +
					actLocation.getRecord().toString() + "\n";
		}
		
		return result;
	}
	
	public String getTopLocationsInCountry(String location, MyDate startDate, MyDate endDate) {
		ArrayList<DetailedLocation> topLocations = new ArrayList<DetailedLocation>();
		
		for (Country country: countries) {
			if (country.getName().equals(location)) {
				for (County county: country.getCounties()) {
					for (Location loc: county.getLocations()) {
						City city = (City) loc.getLocality();
						for (Record rec: city.getRecords()) {
							if (rec.containsPeriod(startDate, endDate)) {
								addLocation(topLocations, rec, city, county, country);
							}
						}
					}
				}
			}
		}
		
		return parseLocations(topLocations);
	}

	public String getTopLocationsInCounty(String location, MyDate startDate, MyDate endDate) {
		ArrayList<DetailedLocation> topLocations = new ArrayList<DetailedLocation>();
		
		for (Country country: countries) {
			for (County county: country.getCounties()) {
				if (county.getName().equals(location)) {
					for (Location loc: county.getLocations()) {
						City city = (City) loc.getLocality();
						for (Record rec: city.getRecords()) {
							if (rec.containsPeriod(startDate, endDate)) {
								addLocation(topLocations, rec, city, county, country);
							}	
						}
					}
				}
			}
		}
		
		return parseLocations(topLocations);
	}

	public String getTopLocationsInCity(String location, MyDate startDate, MyDate endDate) {
		ArrayList<DetailedLocation> topLocations = new ArrayList<DetailedLocation>();
		
		for (Country country: countries) {
			for (County county: country.getCounties()) {
				for (Location loc: county.getLocations()) {
					City city = (City) loc.getLocality();
					if (city.getName().equals(location)) {
						for (Record rec: city.getRecords()) {
							if (rec.containsPeriod(startDate, endDate)) {
								addLocation(topLocations, rec, city, county, country);
							}
						}
					}
				}
			}
		}
		
		return parseLocations(topLocations);
	}
}
