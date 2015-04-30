import java.sql.SQLException;
import java.util.Scanner;

public class TV_WatchList 
{
	/*
	 * Ask About gracefullness
	 * format
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
					+ "\n4.) Commit."
					+ "\n5.) RollBack."
					+ "\n6.) Quit.");
			
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
				String ans = "";
				do
				{
					ans = scan.nextLine();
					if(ans.toUpperCase().equals("Y"))
					{
						conn.transaction(Statements.delete);
					}
					else if(ans.toUpperCase().equals("N"))
					{
						break;
					}
					System.out.println("Warning deleting a row of data can effect the row it is referenced by. Continue... (Y/N)");
				}while(!(ans.toUpperCase().equals("N") || ans.toUpperCase().equals("Y")));
				break;
			case 4:
				conn.com();
				System.out.println("Changes commited.");
				break;
			case 5:
				conn.roll();
				System.out.println("RollBack changes.");
				break;
			case 6:
				String exitAns;
				do
				{
					exitAns = scan.nextLine();
					if(exitAns.toUpperCase().equals("Y"))
					{
						choice = 0;
						break;
					}
					else if(exitAns.toUpperCase().equals("N"))
					{
						break;
					}
					System.out.println("Before exiting do you want any previous work to be commited or rolled back... (Y/N)");
				}while(!(exitAns.toUpperCase().equals("N") || exitAns.toUpperCase().equals("Y")));
				break;
			}
		} while (choice != 6);
	}

	private static void showSubMenu()
	{
		int choice = 0;
		
		do
		{
			System.out.println("1.) Retrieve all the videos title, synopsys and the staff name and lastname of the guy which is the olsdest producer."
					+ "\n2.) Retrieve all the videos which is a SportingEvents and the league is football and show the information about title, sysnopsis, number of staff working on it."
					+ "\n3.) Retrieve all the movie title, synopsys and average grade of each video which have a grade and this grade have to be superior than 2."
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