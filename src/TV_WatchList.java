import java.sql.*;
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
	
	static final String DB_URL = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql367377";//hostname
	static final String USER = "sql367377";//username
	static final String PASS = "jT4!iX8!";//password
	
	public static void main(String[] args) 
	{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			stmt = conn.createStatement();
			String sql;
			sql = "SELECT username FROM User";
			rs = stmt.executeQuery(sql);

			while(rs.next())
			{
				String name = rs.getString("username");
				System.out.println(name);
			}
			rs.close();
			conn.close();
			stmt.close();
		}
		catch(SQLException se)
		{
			se.printStackTrace();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(rs!= null) 
					rs.close();
			}
			catch(SQLException se){}
			try
			{
				if(stmt!= null) 
					stmt.close();
			}
			catch(SQLException se){}
			try
			{
				if(conn!= null) 
					conn.close();
			}
			catch(SQLException se){}
		}
	}
}
