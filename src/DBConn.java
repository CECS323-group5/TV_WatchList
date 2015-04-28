import java.sql.*;

public class DBConn 
{
	// Figure out how to format prints for different results
	// Insert and Delete table.
	static final String DB_URL = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql367377";//hostname
	static String user = "sql367377";//username= "sql367377"
	static String pass = "jT4!iX8!";//password= "jT4!iX8!"
	
	private Connection conn;
	
	public DBConn(String u, String p) 
	{
		conn = null;
		//user  = u;
		//pass = p;
		try {
			conn = getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void query(String sql, int num)
	{	
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			switch(num)
			{
				case 1:
					printResult1(rs);
					break;
				case 2:
					printResult2(rs);
					break;
				case 3:
					printResult3(rs);
					break;
			}
			
			if(stmt!= null) 
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void transaction(String sql) 
	{	
		Statement stmt = null;
		try {
			conn.setAutoCommit(false);
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
			conn.commit();
			conn.rollback(); // Ask about me
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		finally
		{
			if(stmt != null)
				try {
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	
	public void closeConnection()
	{
		if(conn!= null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}
	
	private static void printResult1(ResultSet rs)
	{
		String[] columns = new String[5];
		columns[0] = "title";
		columns[1] = "synopys";
		columns[2] = "fname";
		columns[3] = "lname";
		columns[4] = "dob";
		
		System.out.println();
		
		for (int i = 0; i < 5; i++)
		{
			System.out.println(columns[i] + "\t");
		}
		
		
		
		try {
			while(rs.next())
			{
				String name = rs.getString("username");
				System.out.println(name);
			}
			
			if(rs!= null) 
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println();
	}
	
	private static void printResult2(ResultSet rs)
	{
		System.out.println();
		try {
			while(rs.next())
			{
				String name = rs.getString("username");
				System.out.println(name);
			}
			
			if(rs!= null) 
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println();
	}
	
	private static void printResult3(ResultSet rs)
	{
		System.out.println();
		try {
			while(rs.next())
			{
				String name = rs.getString("username");
				System.out.println(name);
			}
			
			if(rs!= null) 
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println();
	}
	
	private static Connection getConnection() throws SQLException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return DriverManager.getConnection(DB_URL, user, pass);
	}
}
