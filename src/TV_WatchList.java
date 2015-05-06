import java.sql.SQLException;
import java.util.Scanner;

public class TV_WatchList 
{	
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
		String choice = "";
		
		do
		{
			System.out.println("1.) Make a query."
					+ "\n2.) Insert new TV."
					+ "\n3.) Delete row Staff."
					+ "\n4.) Commit."
					+ "\n5.) RollBack."
					+ "\n6.) Quit.");
			
			choice = scan.nextLine();
			
			switch (Integer.valueOf(choice))
			{
			case 1:
				showSubMenu();
				break;
			case 2:
				System.out.println("Enter title: ");
				String title = scan.nextLine();
				System.out.println("Enter duration: ");
				String duration = scan.nextLine();
				System.out.println("Enter synopsys: ");
				String synopsys = scan.nextLine();
				System.out.println("Enter network: ");
				String network = scan.nextLine();
				System.out.println("Enter rating: ");
				String rating = scan.nextLine();
				conn.transaction(Statements.insert(title, duration, synopsys, network, rating));
				conn.query(Statements.query4, 4);
				break;
			case 3:
				String ans = "";
				do
				{
					System.out.println("Warning deleting a row of data can effect the row it is referenced by. Continue... (Y/N)");
					ans = scan.nextLine();
					conn.query(Statements.query5, 5);
					if(ans.toUpperCase().equals("Y"))
					{
						System.out.println("Enter first name: ");
						String first = scan.nextLine();
						System.out.println("Enter last name: ");
						String last = scan.nextLine();
						conn.transaction(Statements.delete(first, last));
					}
					else if(ans.toUpperCase().equals("N"))
					{
						break;
					}
				}while(!(ans.toUpperCase().equals("N") || ans.toUpperCase().equals("Y")));
				break;
			case 4:
				conn.com();
				System.out.println("Changes commited.");
				break;
			case 5:
				conn.roll();
				conn.query(Statements.query4, 4);
				System.out.println("RollBack changes.");
				break;
			case 6:
				String exitAns;
				do
				{
					System.out.println("Before exiting do you want any previous work to be commited or rolled back... (Y/N)");
					exitAns = scan.nextLine();
					if(exitAns.toUpperCase().equals("Y"))
					{
						choice = "0";
						break;
					}
					else if(exitAns.toUpperCase().equals("N"))
					{
						break;
					}
				}while(!(exitAns.toUpperCase().equals("N") || exitAns.toUpperCase().equals("Y")));
				break;
			}
		} while (Integer.valueOf(choice) != 6);
	}

	private static void showSubMenu()
	{
		String choice = "";
		
		do
		{
			System.out.println("1.) Retrieve the first name, last name and date of birth of the oldest producer and display the titles and synopses of his videos."
					+ "\n2.) Retrieve all the football sporting event videos and display its following information: title, sysopsis, and the number of staff that worked on it."
					+ "\n3.) Retrieve all the movies with a rating higher than two and display their title, synopsis and average grade."
					+ "\n4.) Back.");
			
			choice = scan.nextLine();
			
			switch (Integer.valueOf(choice))
			{
			case 1:
				conn.query(Statements.query1, 1);
				break;
			case 2:
				conn.query(Statements.query2, 2);
				break;
			case 3:
				conn.query(Statements.query3, 3);
				break;
			case 4:
				return;
			}
			
		} while (Integer.valueOf(choice) != 4);
	}
}