package holidaysApp;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String result, location;
		int action, action2;
		MyDate startDate, endDate;
		DataBaseReader reader = new DataBaseReader();
		DataBase dataBase = reader.readInput("test1.txt");
		
		Scanner sc = new Scanner(System.in);
		
		boolean stop = false;
		while (!stop) {
			System.out.println("Choose action:");
			System.out.println("Type 1 for information about a given location;");
			System.out.println("Type 2 for information about a given period;");
			System.out.println("Type 3 for information about a given activity;");
			System.out.println("Type 4 to exit application.");
			
			action = sc.nextInt();
			sc.nextLine();
			switch (action) {
			case 1:
				System.out.print("Type location: ");
				location = sc.nextLine();
				result = dataBase.getInfoAboutLocation(location);
				
				if (result == null)
					System.out.println("No record with given location was found!");
				else
					System.out.println(result);
				break;
			case 2:
				System.out.println("Choose target place:");
				System.out.println("Type 1 for a country;");
				System.out.println("Type 2 for a county;");
				System.out.println("Type 3 for a city.");
				
				action2 = sc.nextInt();
				sc.nextLine();
				
				switch (action2) {
				case 1:
					System.out.println("Type country name:");
					break;
				case 2:
					System.out.println("Type county name:");
					break;
				case 3:
					System.out.println("Type city name:");
					break;
				}
				
				location = sc.nextLine();
				
				System.out.println("Type start date in format dd.mm.yyyy:");
				startDate = MyDate.toDate(sc.nextLine());
				
				System.out.println("Type end date in format dd.mm.yyyy:");
				endDate = MyDate.toDate(sc.nextLine());
				
				result = null;
				switch (action2) {
				case 1:
					result = dataBase.getTopLocationsInCountry(location, startDate, endDate);
					break;
				case 2:
					result = dataBase.getTopLocationsInCounty(location, startDate, endDate);
					break;
				case 3:
					result = dataBase.getTopLocationsInCity(location, startDate, endDate);
					break;
				}
				
				if (result == null)
					System.out.println("No suitable records was found.");
				else
					System.out.println(result);
				
				break;
			case 3:
				System.out.print("Type activity: ");
				String activity = sc.nextLine();
				result = dataBase.getInfoAboutActivity(activity, 10);
				
				if (result == null)
					System.out.println("No record with given activity was found!");
				else
					System.out.println(result);
				break;
			case 4:
				stop = true;
				break;
			}
		}
		
	}

}
