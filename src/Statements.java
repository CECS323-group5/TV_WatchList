
public class Statements 
{
	public static final String query1 = 
			"SELECT v.title"
				+ ",v.synopsys"
				+ ",s.fname"
				+ ",s.lname"
				+ ",s.dob "
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
				+ ",v.synopsys"
				+ ",COUNT(s.ID) AS NumberOfStaff" 
				+ ",se.league"
				+ ",se.teams AS teams " // Change to Teams 
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
	
	public static final String query4 = "SELECT * FROM sql367377.TVShow;";
	public static final String query5 = "SELECT * FROM sql367377.Staff;";
	// Videos is a parent of 
	public static String delete(String f, String l)
	{ 
			return "DELETE FROM sql367377.Staff "
			+ "WHERE fname =\""
			+ f
			+ "\" AND "
			+ "lname =\""
			+ l
			+ "\";";
	}
	
	// Sporting event is a child of videos
	public static String insert(String t, String d, String s, String n, String r)
	{ 
				return	"INSERT INTO sql367377.TVShow ("
						+ "title"
						+ ",totalDuration"
						+ ",synopsys"
						+ ",network"
						+ ",pg_rating"
						+ ") "
						+ "VALUES ("
						+ "\"" + t + "\","
						+ d
						+ ",\"" + s + "\","
						+ "\"" + n + "\","
						+ "\"" + r + "\""
						+ ");";
	}
}
