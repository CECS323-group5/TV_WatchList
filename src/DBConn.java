import java.sql.*;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

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
			conn.setAutoCommit(false);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	}
	
	public void com()
	{
		try {
			conn.commit();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void roll()
	{
		try {
			conn.rollback();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			stmt = conn.createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
					e1.printStackTrace();
			}
			if(e instanceof MySQLIntegrityConstraintViolationException)
				System.out.println("Warning Violates the referential Integrity Constraint. Please choose something else.");
			else
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
		StringBuilder sb=new StringBuilder();
		String[] columns = new String[5];
		columns[0] = "title";
		columns[1] = "synopys";
		columns[2] = "fname";
		columns[3] = "lname";
		columns[4] = "dob";
		
		sb.append(String.format("| %-30s",columns[0].toUpperCase()));
		sb.append(String.format("| %-70s",columns[1].toUpperCase()));
		sb.append(String.format("| %-10s",columns[2].toUpperCase()));
		sb.append(String.format("| %-10s",columns[3].toUpperCase()));
		sb.append(String.format("| %-10s",columns[4].toUpperCase()));
		sb.append("\n");
		
		try {
			while(rs.next())
			{
				String title = rs.getString("title");
				sb.append(String.format("| %-30s",title));
				String synopys = rs.getString("synopsys");
				sb.append(String.format("| %-70s",synopys));
				String fname = rs.getString("fname");
				sb.append(String.format("| %-10s",fname));
				String lname = rs.getString("lname");
				sb.append(String.format("| %-10s",lname));
				String dob = rs.getString("dob");
				sb.append(String.format("| %-10s",dob + "\n"));
			}
			System.out.println(sb);
			if(rs!= null) 
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println();
	}
	
	private static void printResult2(ResultSet rs)
	{
		StringBuilder sb=new StringBuilder();
		String[] columns = new String[5];
		columns[0] = "title";
		columns[1] = "synopsys";
		columns[2] = "NumberOfStaff";
		columns[3] = "league";
		columns[4] = "teams";
		
		sb.append(String.format("| %-30s",columns[0].toUpperCase()));
		sb.append(String.format("| %-70s",columns[1].toUpperCase()));
		sb.append(String.format("| %-20s",columns[2].toUpperCase()));
		sb.append(String.format("| %-10s",columns[3].toUpperCase()));
		sb.append(String.format("| %-10s",columns[4].toUpperCase()));
		sb.append("\n");
		
		try {
			while(rs.next())
			{
				String c1 = rs.getString(columns[0]);
				sb.append(String.format("| %-30s",c1));
				String c2 = rs.getString(columns[1]);
				sb.append(String.format("| %-70s",c2));
				String c3 = rs.getString(columns[2]);
				sb.append(String.format("| %-20s",c3));
				String c4 = rs.getString(columns[3]);
				sb.append(String.format("| %-10s",c4));
				String c5 = rs.getString(columns[4]);
				sb.append(String.format("| %-10s",c5 + "\n"));
			}
			System.out.println(sb);
			
			if(rs!= null) 
				rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println();
	}
	
	private static void printResult3(ResultSet rs)
	{
		StringBuilder sb=new StringBuilder();
		String[] columns = new String[3];
		columns[0] = "title";
		columns[1] = "synopsys";
		columns[2] = "AverageGrade";
		sb.append(String.format("| %-30s",columns[0].toUpperCase()));
		sb.append(String.format("| %-70s",columns[1].toUpperCase()));
		sb.append(String.format("| %-10s",columns[2].toUpperCase()));
		sb.append("\n");
			
		try {
			while(rs.next())
			{
				String c1 = rs.getString(columns[0]);
				sb.append(String.format("| %-30s",c1));
				String c2 = rs.getString(columns[1]);
				sb.append(String.format("| %-70s",c2));
				String c3 = rs.getString(columns[2]);
				sb.append(String.format("| %-10s",c3));
				sb.append("\n");
			}
			System.out.println(sb);
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
