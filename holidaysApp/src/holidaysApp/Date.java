package holidaysApp;

public class Date {
	private int day;
	private int month;
	private int year;
	
	public Date() {}
	
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}
	
	public static Date toDate(String s) {
		return new Date(Integer.parseInt(s.substring(0, 2)),
				Integer.parseInt(s.substring(3, 5)),
				Integer.parseInt(s.substring(6, 10)));
	}
	
	@Override
	public String toString() {
		String dayS, monthS, yearS;
		
		if (day < 10)
			dayS = "0" + Integer.toString(day);
		else
			dayS = Integer.toString(day);
		
		if (month < 10)
			monthS = "0" + Integer.toString(month);
		else
			monthS = Integer.toString(month);
		
		yearS = Integer.toString(year);
		
		return dayS + "." + monthS + "." + yearS;
	}
	
	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
}
