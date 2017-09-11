import java.sql.*;
class Jdbc
{
	public static Connection jconn()
	{
		Connection conn=null;
		try
		{
		Class.forName("org.postgresql.Driver");
	    conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/akghospital","postgres","123456"); 
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return conn; 
	}
}