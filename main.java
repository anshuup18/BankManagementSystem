
import java.util.Scanner;

public class main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int choice = 0;
        int accountNo;
        int receiverNo;
        double amount;

        AccountDAO dao = new AccountDAO();
        BankService service = new BankService();
        Account account;
        Account receiver;

        while (choice != 10) {

            System.out.println("\n===== BANK MANAGEMENT SYSTEM =====");
            System.out.println("1. Create Account");
            System.out.println("2. Check Balance");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. Transfer Money");
            System.out.println("6. Transaction History");
            System.out.println("7. Search Account");
            System.out.println("8. Display Account Details");
            System.out.println("9. Update Account Details");
            System.out.println("10. Exit");

            System.out.print("Enter your choice: ");
            if (sc.hasNextInt()) {
                choice = sc.nextInt();
            } else {
                System.out.println("Please enter a valid menu choice!");
                sc.next();
                continue;
            }

            switch (choice) {

                case 1:
                	 sc.nextLine();

                	    System.out.print("Enter account holder name: ");
                	    String name = sc.nextLine();

                	    System.out.print("Enter initial deposit: ");
                	    Double initialBalance;
                	    if (sc.hasNextDouble()) {
                	        initialBalance = sc.nextDouble();
                	    } else {
                	        System.out.println("Please enter a valid amount!");
                	        sc.next();
                	        break;
                	    }
                	    
                	    if (name.trim().isEmpty() || initialBalance <= 0) {
                	        System.out.println("Invalid name or initial deposit!");
                	        break;
                	    }

                	    Account newAccount = new Account(0, name, initialBalance);

                	    int generatedAccountNo = dao.createAccount(newAccount);

                	    if (generatedAccountNo != -1) {
                	        System.out.println("Account created successfully!");
                	        System.out.println("Your Account Number: " + generatedAccountNo);
                	    } else {
                	        System.out.println("Account creation failed!");
                	    }
                    break;

                case 2:
                    System.out.print("Enter account number: ");
                    if (sc.hasNextInt()) {
                        accountNo = sc.nextInt();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }

                    account = dao.getAccountByNumber(accountNo);

                    if (account != null) {
                        System.out.println("Current Balance: "
                                + account.getbalance());
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter account number: ");
                    if (sc.hasNextInt()) {
                        accountNo = sc.nextInt();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }

                    System.out.print("Enter deposit amount: ");
                    if (sc.hasNextDouble()) {
                        amount = sc.nextDouble();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }
                    
                    account = dao.getAccountByNumber(accountNo);

                    if (account != null) {
                        boolean result = service.deposit(account, amount);

                        if (result) {
                            System.out.println("Deposit successful!");
                        } else {
                            System.out.println("Deposit failed!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 4:
                    System.out.print("Enter account number: ");
                    if (sc.hasNextInt()) {
                        accountNo = sc.nextInt();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }
                    
                    System.out.print("Enter withdrawal amount: ");
                    
                    if (sc.hasNextDouble()) {
                        amount = sc.nextDouble();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }

                    account = dao.getAccountByNumber(accountNo);

                    if (account != null) {
                        boolean result = service.withdraw(account, amount);

                        if (result) {
                            System.out.println("Withdrawal successful!");
                        } else {
                            System.out.println("Withdrawal failed!");
                        }
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 5:
                    System.out.print("Enter sender account number: ");
                    if (sc.hasNextInt()) {
                        accountNo = sc.nextInt();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }

                    System.out.print("Enter receiver account number: ");
                    if (sc.hasNextInt()) {
                        receiverNo = sc.nextInt();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }

                    System.out.print("Enter transfer amount: ");
                    if (sc.hasNextDouble()) {
                        amount = sc.nextDouble();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }

                    account = dao.getAccountByNumber(accountNo);
                    receiver = dao.getAccountByNumber(receiverNo);

                    if (account != null && receiver != null) {
                        boolean result = service.transfer(
                                account, receiver, amount);

                        if (result) {
                            System.out.println("Transfer successful!");
                        } else {
                            System.out.println("Transfer failed!");
                        }
                    } else {
                        System.out.println("Sender or receiver account not found!");
                    }
                    break;

                case 6:
                    System.out.print("Enter account number: ");
                    if (sc.hasNextInt()) {
                        accountNo = sc.nextInt();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }

                    TransactionDAO tdao = new TransactionDAO();
                    tdao.getTransactionHistory(accountNo);
                    break;

                case 7:
                    System.out.println("Enter account number to search: ");
                    if (sc.hasNextInt()) {
                        accountNo = sc.nextInt();
                    } else {
                        System.out.println("Please enter a valid account number!");
                        sc.next(); // Consume invalid input
                        break;
                    }
                    account = dao.getAccountByNumber(accountNo);

                    if (account != null) {
                        System.out.println("Account found!");
                        account.displayAccount();
                    } else {
                        System.out.println("Account not found!");
                    }
                    break;

                case 8:
                	 System.out.print("Enter account number: ");
                	 if (sc.hasNextInt()) {
                         accountNo = sc.nextInt();
                     } else {
                         System.out.println("Please enter a valid account number!");
                         sc.next(); // Consume invalid input
                         break;
                     }

                	    account = dao.getAccountByNumber(accountNo);

                	    if (account != null) {
                	        System.out.println("===== ACCOUNT DETAILS =====");
                	        account.displayAccount();
                	    } else {
                	        System.out.println("Account not found!");
                	    }
                    break;

                case 9:
                	 System.out.print("Enter account number: ");
                	 if (sc.hasNextInt()) {
                         accountNo = sc.nextInt();
                     } else {
                         System.out.println("Please enter a valid account number!");
                         sc.next(); // Consume invalid input
                         break;
                     }

                	    account = dao.getAccountByNumber(accountNo);

                	    if (account != null) {
                	        sc.nextLine(); // Consume leftover newline
                	        
                	        System.out.print("Enter new account holder name: ");
                	                        	        
                	        String newName = sc.nextLine();
                	        
                	        if (newName.trim().isEmpty()) {
                	            System.out.println("Account name cannot be empty!");
                	            break;
                	        }

                	        boolean updated = dao.updateAccountName(accountNo, newName);

                	        if (updated) {
                	            System.out.println("Account details updated successfully!");
                	        } else {
                	            System.out.println("Account update failed!");
                	        }

                	    } else {
                	        System.out.println("Account not found!");
                	    }
                    break;

                case 10:
                    System.out.println("Thank you for using our bank!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        sc.close();
    }
}
