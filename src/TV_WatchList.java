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
	
	public static void main(String[] args) 
	{
		DBConn conn = new DBConn();
		
		conn.query("SELECT username FROM User");
	}
}
