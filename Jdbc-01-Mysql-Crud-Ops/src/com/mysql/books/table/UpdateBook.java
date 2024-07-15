package com.mysql.books.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class UpdateBook {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String UPDATE_SQL = "UPDATE books SET book_name='Javascript' WHERE book_id=2";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		Statement statement = connection.createStatement();
		int noOfRows = statement.executeUpdate(UPDATE_SQL);
		System.out.println(noOfRows);
		connection.close();
	}
}
