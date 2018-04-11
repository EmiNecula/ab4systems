package holidaysApp;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		String result;
		DataBaseReader reader = new DataBaseReader();
		DataBase dataBase = reader.readInput(args[0]);
		
		Scanner sc = new Scanner(System.in);
		
		boolean stop = false;
		while (!stop) {
			System.out.println("Choose action:");
			System.out.println("Type 1 for information about a given location;");
			System.out.println("Type 2 for information about a given period;");
			System.out.println("Type 3 for information about a given activity;");
			System.out.println("Type 4 to exit application.");
			
			int action = sc.nextInt();
			sc.nextLine();
			switch (action) {
			case 1:
				System.out.print("Type location: ");
				String location = sc.nextLine();
				result = dataBase.getInfoAboutLocation(location);
				
				if (result == null)
					System.out.println("No record with given location was found!");
				else
					System.out.println(result);
				break;
			case 2:
				
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
