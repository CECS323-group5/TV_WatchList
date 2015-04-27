import java.util.Scanner;

public class TV_WatchList 
{
	/*
	 * 1.) Make a query
	 * 		a.) sql query
	 * 		b.) sql query
	 * 		c.) sql query
	 * 2.) Insert
	 * 3.) Delete row
	 */
	
	public static Scanner scan;
	public static DBConn conn;
	
	public static void main(String[] args) 
	{
		conn = new DBConn();
		scan = new Scanner(System.in);
		
		showMenu();
		
		scan.close();
		conn.closeConnection();
	}
	
	public static void showMenu()
	{
		int choice = 0;
		
		do
		{
			System.out.println("1.) Make a query."
					+ "\n2.) Insert Data."
					+ "\n3.) Delete row."
					+ "\n4.) Quit.");
			
			choice = scan.nextInt();
			
			switch (choice)
			{
			case 1:
				showSubMenu();
				break;
			case 2:
				//TODO insert data
				break;
			case 3:
				//TODO delete row
				break;
			case 4:
				return;
			}
			
			
		} while (choice != 4);
	}

	private static void showSubMenu()
	{
		int choice = 0;
		
		do
		{
			System.out.println("1.) Query1."
					+ "\n2.) Query2."
					+ "\n3.) Query3"
					+ "\n4.) Back.");
			
			choice = scan.nextInt();
			
			switch (choice)
			{
			case 1:
				conn.query(Statements.query1);
				break;
			case 2:
				//TODO query2
				break;
			case 3:
				//TODO query3
				break;
			case 4:
				return;
			}
			
		} while (choice != 4);
	}
}