package com.mysql.books.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DeleteBook {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String DELETE_SQL = "DELETE FROM books WHERE book_price>4000";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		Statement stmt = conn.createStatement();
		int rows = stmt.executeUpdate(DELETE_SQL);
		conn.close();
	}
}
