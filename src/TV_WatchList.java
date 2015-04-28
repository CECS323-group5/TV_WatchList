import java.sql.SQLException;
import java.util.Scanner;

public class TV_WatchList 
{
	/*
	 * Three print statements
	 * update SQL
	 * Delete and Insert
	 * ask for username and password
	 */
	
	public static Scanner scan;
	public static DBConn conn;
	
	public static void main(String[] args) 
	{
		scan = new Scanner(System.in);	
		connectionMenu();
		
		showMenu();
		
		scan.close();
		conn.closeConnection();
	}
	public static void connectionMenu()
	{
		String username = "";
		String password = "";
		System.out.println("Enter Database Username:");
		username = scan.nextLine();
		System.out.println("Enter Database Password:");
		password = scan.nextLine();
		conn = new DBConn(username, password);
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
				conn.transaction(Statements.insert);
				break;
			case 3:
				conn.transaction(Statements.delete);
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
			System.out.println("1.) Retrieve all the videos title, synopsys and the staff name and lastname of the guy which is the olsdest producer."
					+ "\n2.) Retrieve all the videos which is a SportingEvents and the league is football and show the information about title, sysnopsis, number of staff working on it."
					+ "\n3.) Query3"
					+ "\n4.) Back.");
			
			choice = scan.nextInt();
			
			switch (choice)
			{
			case 1:
				conn.query(Statements.query1, choice);
				break;
			case 2:
				conn.query(Statements.query2, choice);
				break;
			case 3:
				conn.query(Statements.query3, choice);
				break;
			case 4:
				return;
			}
			
		} while (choice != 4);
	}
}