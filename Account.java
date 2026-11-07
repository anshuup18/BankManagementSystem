
public class Account {
	
	private int accountno;
	private String accountname;
	private double balance;
	
	public Account(int accountno , String accountname , double balance ) {
		
		this.accountno = accountno;
		this.accountname= accountname;
		this.balance = balance;
	}
	
	public void setBalance(double balance) {
		this.balance = balance;
	}

	public int getAccountno() {
		return accountno;
	}

	public String getaccountname() {
		return accountname;
	}

	public double getbalance() {
		return this.balance;
	}

	public void displayAccount() {
	    System.out.println("Account No: " + this.accountno);
	    System.out.println("Name: " + this.accountname);
	    System.out.println("Balance: " + this.balance);
	    System.out.println();
	}
	
	public boolean withdraw(double amount) {
		if(this.balance >= amount && amount>0) {
			this.balance -= amount;
			System.out.println("Withdrawal successful of " + amount);
			return true; 
		}else {
			System.out.println("Insufficient balance");
			return false;
		}
	}
	
	public boolean deposit(double amount) {
		if(amount > 0 ) {
			this.balance += amount;
			System.out.println("Succesfully Deposit of "+ amount);
			return true;
		}else {
			System.out.println("Amount not valid");
			return false;
		}
	}
	
	public boolean transfer(Account receiver, double amount) {
		
		
		if(amount > 0 && amount<= this.balance && receiver != null &&
				this.accountno != receiver.accountno) {
			
			this.balance -= amount;
			receiver.balance += amount;
			
			System.out.println("you have succesfully transfer the amount to "+ receiver.accountname);
			return true;
		}else {
			System.out.println("Amount not valid");
			return false;
		}

	}
	
	

}
