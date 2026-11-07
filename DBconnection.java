
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	
	private static final String URL= "jdbc:mysql://localhost:3306/bank_management";  
	 
	private static final String USERNAME= System.getenv("DB_USERNAME");
	
	private static final String PASSWORD= System.getenv("DB_PASSWORD");
	
	public static Connection getconnection() {
		
		try {
			Connection con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			
			System.out.println("Database connected successfully!");
			
			return con ;
		} catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
}
