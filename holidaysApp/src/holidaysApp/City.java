package holidaysApp;

import java.util.ArrayList;

public class City implements Locality{
	private String name;
	private ArrayList<Record> records;
	
	public City(String name) {
		this.name = name;
		records = new ArrayList<Record>();
	}
	
	public String getName() {
		return this.name;
	}
	
	public ArrayList<Record> getRecords() {
		return this.records;
	}
}
