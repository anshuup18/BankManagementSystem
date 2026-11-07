import java.sql.Connection;
import java.sql.SQLException;

public class BankService {
	
	public boolean deposit(Account account, double amount) {
		
		double oldBalance = account.getbalance();
		AccountDAO accountDAO = new AccountDAO();
		TransactionDAO transactionDAO = new TransactionDAO();
		
		if (!account.deposit(amount)) {
		    return false;
		}
		Connection con = DBconnection.getconnection();
		
		if (con == null) {
		    account.setBalance(oldBalance);
		    return false;
		}
		
		
		try {
		    con.setAutoCommit(false);

		    boolean balanceUpdated = accountDAO.updateBalance(
		    	    con, account.getAccountno(), account.getbalance()
		    	);

		    	boolean transactionAdded = transactionDAO.addTransaction(
		    	    con, account, "DEPOSIT", amount
		    	);

		    	if (balanceUpdated && transactionAdded) {
		    	    con.commit();
		    	    return true;
		    	} else {
		    	    con.rollback();
		    	    account.setBalance(oldBalance);
		    	    return false;
		    	}
		    
		} catch (SQLException e) {
			  try {
			        con.rollback();
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }

			    account.setBalance(oldBalance);
			    e.printStackTrace();
			    return false;
			    
		} finally {
		    try {
		        if (con != null) {
		            con.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		}

	}

	public boolean withdraw(Account account, double amount) {

		double oldBalance = account.getbalance();

		AccountDAO accountDAO = new AccountDAO();
		TransactionDAO transactionDAO = new TransactionDAO();
		
		if(! account.withdraw(amount)) {
			return false;
		}
		
		Connection con = DBconnection.getconnection();
		
		if (con == null) {
		    account.setBalance(oldBalance);
		    return false;
		}
		
		try {
			con.setAutoCommit(false);
			
			boolean balanceupdate = accountDAO.updateBalance(con, account.getAccountno(),account.getbalance());
			
			boolean transactionAdded = transactionDAO.addTransaction(con, account, "WITHDRAW", amount);
			
			if(balanceupdate && transactionAdded ) {
				con.commit();	
				return true;
			}
			else {
				con.rollback();
				account.setBalance(oldBalance);
				return false ; 
				
			}
		}catch (SQLException e) {
				  try {
				        con.rollback();
				    } catch (SQLException ex) {
				        ex.printStackTrace();
				    }

				    account.setBalance(oldBalance);
				    e.printStackTrace();
				    return false;
			}finally {
			    try {
			        if (con != null) {
			            con.close();
			        }
			    } catch (SQLException e) {
			        e.printStackTrace();
			    
			}		    
		}
	}
	
	public boolean transfer(Account sender, Account receiver, double amount) {

		double oldbalanceSender = sender.getbalance();
		double oldbalanceReceiver = receiver.getbalance();
		
		AccountDAO accountDAO = new AccountDAO();
		TransactionDAO transactionDAO = new TransactionDAO();
		
		if (sender.getAccountno() == receiver.getAccountno()) {
		    System.out.println("Cannot transfer money to the same account!");
		    return false;
		}
		
		if(!sender.transfer(receiver, amount)) {
			return false;
		}
						
		Connection con = DBconnection.getconnection();
		
		if(con==null) {
			sender.setBalance(oldbalanceSender);
			receiver.setBalance(oldbalanceReceiver);
			return false;
		}
		
		try {
			con.setAutoCommit(false);
			
			boolean updatebalancedS = accountDAO.updateBalance(con, sender.getAccountno(), sender.getbalance());
					
			boolean updatebalanceR = accountDAO.updateBalance(con, receiver.getAccountno(), receiver.getbalance());
			
			boolean transactitonupdateS = transactionDAO.addTransaction(con, sender, "TRANSFERED", amount);		
		
			boolean transactionupdateR = transactionDAO.addTransaction(con, receiver, "RECEIVED", amount);		
			
			if(updatebalancedS && updatebalanceR && transactitonupdateS && transactionupdateR) {
				con.commit();
				return true; 
			}else {
				con.rollback();
				sender.setBalance(oldbalanceSender);
				receiver.setBalance(oldbalanceReceiver);
				return false;
			}
		}catch (SQLException e) {
			  try {
			        con.rollback();
			    } catch (SQLException ex) {
			        ex.printStackTrace();
			    }

			sender.setBalance(oldbalanceSender);
			receiver.setBalance(oldbalanceReceiver);
			    e.printStackTrace();
			    return false;
		}finally {
		    try {
		        if (con != null) {
		            con.close();
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    
		}		    
	}
	}
}

