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
	
	/*
	 * Determines the type of line read from input file.
	 */
	private lineType getLineType(String line) {
		String token;
		
		if (line.charAt(0) == '*')
			return lineType.RECORD;
		
		token = line.split(" ", 2)[0];
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

	/*
	 * Given a record in the following form:
	 * "*Name/Price/List_of_activities_separated_by_comma/date1-date2"
	 * where date1 and date2 have the following format:
	 * "dd.mm.yyyy"
	 * this function returns a Record object with the given data.
	 */
	private Record parseRecord(String line) {
		Record record;
		String[] tokens;
		String[] period;

		line = line.substring(1);
		tokens = line.split("/");
		period = tokens[3].split("-");
		record = new Record(tokens[0],
				Float.parseFloat(tokens[1]),
				tokens[2].split(","),
				Date.toDate(period[0]),
				Date.toDate(period[1]));
		
		return record;
	}
	
	/*
	 * Reads the records that belong to the given city.
	 * It returns the first line (or null) that doesn't contain
	 * a record.
	 */
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
	
	/*
	 * Reads the locations' data from a given county according to
	 * the type of location.
	 * It returns the first line (or null) that contains data which
	 * doesn't belong to the given location.
	 */
	private String readLocation(County county) {
		String line;
		String[] tokens;
		String locationType, locationName;

		try {
			line = buffReader.readLine();
			while (line != null) {
				if (getLineType(line) != lineType.LOCATION)
					return line;
				
				tokens = line.split(" ", 3);
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
	
	/*
	 * Reads the given countie's data from the input file.
	 * It returns the first line (or null) that contains data which
	 * doesn't belong to the given county.
	 */
	private String readCounty(Country country) {
		String line;

		try {
			line = buffReader.readLine();
			while (line != null) {
				if (getLineType(line) != lineType.COUNTY)
					return line;

				String countyName = line.split(" ", 2)[1];
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
	
	/*
	 * Reads all data for a given country.
	 * It returns the first line (or null) that contains data which
	 * doesn't belong to the given country.
	 */
	private String readCountry(String country, DataBase dataBase) {
		if (country == null || country.equals("")) {
			return null;
		}
		
		String countryName = country.split(" ", 2)[1];
		Country newCountry = new Country(countryName);

		dataBase.getCountries().add(newCountry);
		return readCounty(newCountry);
	}
	
	/*
	 * Reads data from the file given by parameter.
	 * @param fileName The path to the input file.
	 * @return A DataBase object containing data from the given input file,
	 * or null in case that the given file cannot be opened.
	 */
	public DataBase readInput(String fileName) {
		DataBase dataBase = null;
		
		try {
			FileReader fileReader = new FileReader(fileName);
			buffReader = new BufferedReader(fileReader);
			
			dataBase = new DataBase();
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
