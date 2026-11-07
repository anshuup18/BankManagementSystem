import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class TransactionDAO {
	
	public boolean addTransaction (Connection con, Account account, String type, double amount) {
		
		String sql = "INSERT INTO transactions (account_no, transaction_type, amount) VALUES (?, ?, ?)";
		
		
			try(PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, account.getAccountno());
			ps.setString(2, type);
			ps.setDouble(3, amount);
			
			int rows = ps.executeUpdate();
			
			 return rows > 0;
			 
		}catch(SQLException e) {
			e.printStackTrace();
			return false ;
		}

	}

	public void getTransactionHistory(int accountNo) {
		
		String sql = "SELECT * FROM transactions WHERE account_no = ?";
		
		Connection con = DBconnection.getconnection();
		
		try(PreparedStatement ps = con.prepareStatement(sql)){
			
			ps.setInt(1, accountNo);
			
			ResultSet rs = ps.executeQuery(); 
			
			boolean found = false;
			
		    while (rs.next()) {
		         found = true;

		        System.out.println("Transaction ID: " + rs.getInt("transaction_id"));
		        System.out.println("Account No: " + rs.getInt("account_no"));
		        System.out.println("Type: " + rs.getString("transaction_type"));
		        System.out.println("Amount: " + rs.getDouble("amount"));
		        System.out.println("Date: " + rs.getTimestamp("transaction_date"));

		        System.out.println("----------------------");
		    }
		    
		    if (!found) {
		        System.out.println("No transactions found for this account.");
		    }
		}catch(SQLException e) {
			e.printStackTrace();
			
	    }
	}
}
