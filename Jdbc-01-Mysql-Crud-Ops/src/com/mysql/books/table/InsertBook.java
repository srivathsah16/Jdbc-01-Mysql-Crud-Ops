package com.mysql.books.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class InsertBook {

	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String INSERT_BOOK1_SQL = "INSERT INTO BOOKS VALUES(1,'Java',2000)";
	private static final String INSERT_BOOK2_SQL = "INSERT INTO BOOKS VALUES(2,'JavaScript',3000)";
	private static final String INSERT_BOOK3_SQL = "INSERT INTO BOOKS VALUES(3,'ReactJS',6000)";

	public static void main(String[] args) throws Exception {

		// 1. Load Driver class
		Class.forName("com.mysql.cj.jdbc.Driver");
		// 2. Get DB Connection
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		// 3. Create Statement
		Statement stmt = con.createStatement();
		// 4. Execute query
		int rowsAffected1 = stmt.executeUpdate(INSERT_BOOK1_SQL);
		int rowsAffected2 = stmt.executeUpdate(INSERT_BOOK2_SQL);
		int rowsAffected3 = stmt.executeUpdate(INSERT_BOOK3_SQL);
		
		// 5. Process Result.
		System.out.println("Number of Records Inserted = " + rowsAffected1 + "\n" + rowsAffected2 + "\n" + rowsAffected3);
		// 6. Close Connection
		con.close();
	}
}
