package com.mysql.books.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class BatchOpsUsingStmt {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		Statement stmt = con.createStatement();
		stmt.addBatch("INSERT INTO books VALUES (7,'AI',5000)");
		stmt.addBatch("INSERT INTO books VALUES (8,'ML',6000)");
		stmt.addBatch("INSERT INTO books VALUES (9,'DS',7000)");
		stmt.addBatch("INSERT INTO books VALUES (10,'Design',8000)");

		int[] count = stmt.executeBatch();
		for (int i : count) {
			System.out.println(i);
		}
		System.out.println("Execution completed..");
		con.close();
	}
}
