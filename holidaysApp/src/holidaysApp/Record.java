package holidaysApp;

import java.util.ArrayList;

public class Record {
	private String placeName;
	private float price;
	private String[] activities;
	private Date startDate;
	private Date endDate;

	public Record() {}
	
	public Record(String placeName, float price, String[] activities, Date startDate, Date endDate) {
		this.placeName = placeName;
		this.price = price;
		this.activities = activities;
		this.startDate = startDate;
		this.endDate = endDate;
	}
	
	private String concatenateActivities() {
		String result = "";
		int len = activities.length - 1;
		
		for (int i = 0; i < len; ++i)
			result += activities[i] + ", ";
		result += activities[len];
		
		return result;
	}
	
	@Override
	public String toString() {
		return placeName + "/" +
				Float.toString(price) + "/" +
				concatenateActivities() + "/" +
				startDate.toString() + "-" +
				endDate.toString();
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String[] getActivities() {
		return activities;
	}

	public void setActivities(String[] activities) {
		this.activities = activities;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
