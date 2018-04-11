package holidaysApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

enum lineType {
	COUNTRY, COUNTY, LOCATION, RECORD, UNDEFINED
}

public class DataBaseReader {
	private BufferedReader buffReader = null;
	
	public DataBaseReader() {}
	
	private lineType getLineType(String line) {
		String token;
		
		if (line.charAt(0) == '*')
			return lineType.RECORD;
		
		token = line.split(" ")[0];
		switch (token.replaceAll("[^.]", "").length()) {
		case 0:
			return lineType.COUNTRY;
		case 1:
			return lineType.COUNTY;
		case 2:
			return lineType.LOCATION;
		default:
			return lineType.UNDEFINED;
		}
	}

	private Record parseRecord(String line) {
		Record record;
		String[] tokens;
		String[] period;

		line = line.substring(1);
		tokens = line.split("/");
		period = tokens[3].split("-");
		record = new Record(tokens[0],
				Float.parseFloat(tokens[1]),
				tokens[2].split("/"),
				Date.toDate(period[0]),
				Date.toDate(period[1]));
		
		return record;
	}
	
	private String readCity(City city) {
		String line;
		ArrayList<Record> cityRecords = city.getRecords();

		try {
			line = buffReader.readLine();
			while (line != null) {
				if (getLineType(line) == lineType.RECORD) {
					cityRecords.add(parseRecord(line));
				} else {
					return line;
				}

				line = buffReader.readLine();
			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
			e.printStackTrace();
		}

		return null;
	}
	
	private String readLocation(County county) {
		String line;
		String[] tokens;
		String locationType, locationName;

		try {
			line = buffReader.readLine();
			while (line != null) {
				if (getLineType(line) != lineType.LOCATION)
					return line;
				
				tokens = line.split(" ");
				locationType = tokens[1];
				locationName = tokens[2];
				
				if (locationType.equals("City")) {
					Location location = new Location(LocationType.CITY, locationName);
					county.getLocations().add(location);
					line = readCity((City) location.getLocality());
				} else {
					return null;
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
			e.printStackTrace();
		}

		return null;
	}
	
	private String readCounty(Country country) {
		String line;

		try {
			line = buffReader.readLine();
			while (line != null) {
				if (getLineType(line) != lineType.COUNTY)
					return line;

				String countyName = line.split(" ")[1];
				County newCounty = new County(countyName);
				country.getCounties().add(newCounty);
				
				line = readLocation(newCounty);
			}
		} catch (IOException e) {
			System.out.println("Error reading file!");
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String readCountry(String country, DataBase dataBase) {
		if (country == null || country.equals("")) {
			return null;
		}
		
		String countryName = country.split(" ")[1];
		Country newCountry = new Country(countryName);

		dataBase.getCountries().add(newCountry);
		return readCounty(newCountry);
	}
	
	public DataBase readInput(String fileName) {
		DataBase dataBase = new DataBase();
		
		try {
			FileReader fileReader = new FileReader(fileName);
			buffReader = new BufferedReader(fileReader);
			
			String line = buffReader.readLine();
			while (line != null) {
				line = readCountry(line, dataBase);
			}
			
			buffReader.close();
			buffReader = null;
		} catch (FileNotFoundException e) {
			System.out.println("File \"" + fileName + "\" not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error reading file \"" + fileName + "\"!");
			e.printStackTrace();
		}
		
		return dataBase;
	}
}
