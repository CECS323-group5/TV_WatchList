import java.sql.*;

public class DBConn 
{
	static final String DB_URL = "jdbc:mysql://sql3.freemysqlhosting.net:3306/sql367377";//hostname
	static final String USER = "sql367377";//username
	static final String PASS = "jT4!iX8!";//password
	
	private Connection conn;
	
	public DBConn() 
	{
		conn = null;
		
		try {
			conn = getConnection();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void query(String sql)
	{	
		Statement stmt;
		try {
			stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			
			printResult(rs);
			
			if(stmt!= null) 
				stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
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
	
	private static void printResult(ResultSet rs)
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
		
		return DriverManager.getConnection(DB_URL, USER, PASS);
	}
}
