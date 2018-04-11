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
		return Integer.toString(day) + "." +
				Integer.toString(month) + "." +
				Integer.toString(year);
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
