package holidaysApp;

public class Test {

	public static void main(String[] args) {
		DataBaseReader reader = new DataBaseReader();
		DataBase dataBase = reader.readInput("test1.txt");
		
		for (Country c: dataBase.getCountries()) {
			System.out.println(c.getName());
			
			for (County co: c.getCounties()) {
				System.out.println(co.getName());
				
				for (Location l: co.getLocations()) {
					City ci = (City) l.getLocality();
					System.out.println(ci.getName());
					
					for (Record r: ci.getRecords()) {
						System.out.println(r.toString());
					}
				}
			}
		}
	}

}
