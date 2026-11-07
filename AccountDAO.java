import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AccountDAO {
	
	
	public int createAccount(Account account) {
		
		String sql = "INSERT INTO accounts (holder_name, balance) VALUES (?, ?)" ;
		
       try (Connection con = DBconnection.getconnection();
    		   
             PreparedStatement ps = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {

			ps.setString(1,account.getaccountname());
			ps.setDouble(2,account.getbalance());
			
			int rows = ps.executeUpdate();
			
			if (rows > 0) {
			    ResultSet rs = ps.getGeneratedKeys();

			    if (rs.next()) {
			        return rs.getInt(1);
			    }
			}
			return -1;
			
		}catch(SQLException e) {
			e.printStackTrace();
			return -1 ;
		}

	}
	
	public Account getAccountByNumber(int accountNo) {
		
		String sql = " SELECT * FROM accounts WHERE account_no = ?";
		
		try(Connection con = DBconnection.getconnection();  
				PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, accountNo);
			 ResultSet rs = ps.executeQuery();
			 
			 while(rs.next()) {
           	  Account as= new Account(
                         rs.getInt("account_no"),
                         rs.getString("holder_name"),
                         rs.getDouble("balance")
                         );
           	  return as;
			 }return null; 
		}catch(SQLException e) {
			e.printStackTrace();
			return null ;
		}
	}
	
	public boolean updateBalance(Connection con, int accountNo, double newBalance) {
	    String sql = "UPDATE accounts SET balance = ? WHERE account_no = ?";

	    try (PreparedStatement ps = con.prepareStatement(sql)) {
	        ps.setDouble(1, newBalance);
	        ps.setInt(2, accountNo);

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}

	public boolean updateAccountName(int accountNo, String newName) {

	    String sql = "UPDATE accounts SET holder_name = ? WHERE account_no = ?";

	    try (Connection con = DBconnection.getconnection();
	         PreparedStatement ps = con.prepareStatement(sql)) {

	        ps.setString(1, newName);
	        ps.setInt(2, accountNo);

	        int rows = ps.executeUpdate();
	        return rows > 0;

	    } catch (SQLException e) {
	        e.printStackTrace();
	        return false;
	    }
	}
}

