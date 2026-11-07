# BankManagementSystem

A console-based Bank Management System developed using **Core Java, JDBC, and MySQL**.

This project demonstrates Object-Oriented Programming, database connectivity, CRUD operations, and transaction management in Java.

## 🚀 Features

- Create a new bank account
- Check account balance
- Deposit money
- Withdraw money
- Transfer money between accounts
- View transaction history
- Search and display account details
- Update account holder information
- Input validation and error handling
- Database transaction management using Commit and Rollback

## 🛠️ Technologies Used

| Technology | Purpose |
|---|---|
| Java | Application development |
| JDBC | Database connectivity |
| MySQL | Database management |
| Eclipse IDE | Development environment |
| MySQL Connector/J | JDBC driver |

## 📂 Project Structure

```text
BankManagementSystem/
│
└── src/
    ├── main.java
    ├── Account.java
    ├── AccountDAO.java
    ├── TransactionDAO.java
    ├── BankService.java
    └── DBconnection.java
```

## 🗄️ Database Structure

Database name: `bank_management`

### Accounts Table

Stores account numbers, account holder names, and balances.

### Transactions Table

Stores transaction IDs, account numbers, transaction types, amounts, and transaction dates.

## ⚙️ Setup and Installation

1. Install Java JDK.
2. Install MySQL Server.
3. Create a MySQL database named `bank_management`.
4. Create the `accounts` and `transactions` tables.
5. Add MySQL Connector/J to the Java project's Build Path.
6. Configure the following environment variables on your system:

   - `DB_USERNAME`
   - `DB_PASSWORD`

7. Open the project in Eclipse.
8. Run `main.java`.

## 💡 Concepts Implemented

- Object-Oriented Programming (OOP)
- Encapsulation and constructors
- JDBC connectivity
- PreparedStatement and ResultSet
- DAO (Data Access Object) architecture
- SQL CRUD operations
- Database transactions
- Commit and Rollback
- Exception handling
- Input validation

## 👨‍💻 Author

**Anshu**

BCA Student | Java Developer Learner

[GitHub Profile](https://github.com/anshuup18)

---

*Built as a hands-on project to strengthen Java programming and database management skills.*
