
public class Statements 
{
	public static final String query1 = 
			"SELECT v.title"
				+ ",v.synopsys"
				+ ",s.fname"
				+ ",s.lname"
				+ ",s.dob"
			+ "FROM Videos v "
			+ "INNER JOIN Credit c ON v.ID = c.IDVideo "
			+ "INNER JOIN Staff s ON c.IDStaff = s.ID "
			+ "WHERE s.dob = ("
				+ "SELECT MIN(s.dob) "
				+ "FROM Staff s "
				+ "WHERE s.roleName = \"producer\""
			+ ") "
			+ "GROUP BY v.title;";
	public static final String query2 = 
			"SELECT v.title"
				+ ",v.synopsys,"
				+ ",COUNT(s.ID) AS NumberOfStaff," 
				+ ",se.league,"
				+ ",se.teams AS NumberOfStaff "
			+ "FROM Videos v "
			+ "INNER JOIN SportingEvents se ON v.ID = se.IDVideo "
			+ "INNER JOIN Credit c ON v.ID = c.IDVideo "
			+ "INNER JOIN Staff s ON c.IDStaff = s.ID "
			+ "GROUP BY v.title "
			+ "HAVING se.league = \"football\"";
	public static final String query3 = 
			"SELECT v.title"
				+ ",v.synopsys"
				+ ",AVG(r.grade) AS AverageGrade "
			+ "FROM Videos v "
			+ "LEFT OUTER JOIN Review r ON v.ID = r.IDVideo "
			+ "LEFT OUTER JOIN User u ON u.username = r.username "
			+ "WHERE v.title IN ("
				+ "SELECT v.title "
				+ "FROM Videos v "
				+ "INNER JOIN Review r ON v.ID = r.IDVideo "
				+ "WHERE r.grade IS NOT NULL "
				+ "GROUP BY v.title "
				+ "HAVING AVG(r.grade) > 2"
			+ ") "
			+ "GROUP BY v.title"
				+ ",v.synopsys;";
	
	// Videos is a parent of 
	public static final String delete = 
			"DELETE FROM Videos v "
			+ "WHERE v.title=\"Basketball games\" AND "
			+ "v.synopsys=\"team2 vs opponent2 tonight who will win to acceed to the final versus LA Clippers\";";
	
	// Sporting event is a child of videos
	public static final String insert = 
			"INSERT INTO SportingEvents ("
				+ "IDVideo"
				+ ",teams"
				+ ",league"
				+ ") "
			+ "VALUES ("
				+ "9"
				+ ",\"team6 vs opponent6\""
				+ ",\"football\""
				+ ");";
}
