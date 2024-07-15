package com.mysql.books.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class SelectBooks_ScrollableResultSet2 {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String SELECT_SQL = "SELECT * FROM books";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection connection = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rSet = statement.executeQuery(SELECT_SQL);
		rSet.absolute(2);
		System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
		Thread.sleep(2000);
		while (rSet.next()) {
			System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
		}
		connection.close();
	}

}
