import java.sql.SQLException;

public class main 
{
	public main() {}
	
	public static void testJDBC()
	{
		try 
		{
			SQLiteJDBC test = new SQLiteJDBC();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("testJDBC(), in main.");
	}
	
	public static void main(final String[] args)
	{
		testJDBC();
		
		System.out.println("Hello Java for JDBC of SQLite.");
	}

}
