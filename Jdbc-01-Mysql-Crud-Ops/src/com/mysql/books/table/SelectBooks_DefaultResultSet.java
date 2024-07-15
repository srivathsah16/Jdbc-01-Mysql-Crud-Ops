package com.mysql.books.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectBooks_DefaultResultSet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String SELECT_SQL = "SELECT * FROM books";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		Statement stmt = connection.createStatement();
		ResultSet rs = stmt.executeQuery(SELECT_SQL);
		while(rs.next()) {
			System.out.println(rs.getInt(1));
			System.out.println(rs.getString(2));
			System.out.println(rs.getString(3));
		}
		connection.close();
	}
}
