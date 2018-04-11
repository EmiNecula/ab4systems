package holidaysApp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Record {
	private String placeName;
	private float price;
	private String[] activities;
	private MyDate startDate;
	private MyDate endDate;

	public Record() {}
	
	public Record(String placeName, float price, String[] activities, MyDate startDate, MyDate endDate) {
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
			result += activities[i] + ",";
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
	
	public boolean hasActivity(String activity) {
		for (int i = 0; i < activities.length; ++i)
			if (activity.equals(activities[i]))
				return true;
		return false;
	}
	
	/*
	 * Returns the duration of current record.
	 */
	public int duration() {
		SimpleDateFormat myFormat = new SimpleDateFormat("dd.mm.yyyy");
		
		try {
			Date date1 = myFormat.parse(startDate.toString());
			Date date2 = myFormat.parse(endDate.toString());
			long diff = date2.getTime() - date1.getTime();
			return (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
		} catch (ParseException e) {
			System.out.println("Date convert error!");
			e.printStackTrace();
		}

		return -1;
	}
	
	/*
	 * Checks whether given as parameters period is contained in
	 * current record's period.
	 */
	public boolean containsPeriod(MyDate startDate, MyDate endDate) {
		if (this.startDate.isBefore(startDate) && endDate.isBefore(this.endDate))
			return true;
		return false;
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

	public MyDate getStartDate() {
		return startDate;
	}

	public void setStartDate(MyDate startDate) {
		this.startDate = startDate;
	}

	public MyDate getEndDate() {
		return endDate;
	}

	public void setEndDate(MyDate endDate) {
		this.endDate = endDate;
	}
}
