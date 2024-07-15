package com.mysql.books.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class DynamicSelectBooksUsingPrepStmt {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	static int book_price = 0; // declaring as a global variable.

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver"); // this line is optional
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);

		System.out.println("Enter book price");
		Scanner sc = new Scanner(System.in);
		String userInputString = sc.nextLine(); // always accept user input as String, later you can check whether it is
												// Integer or not.
		Boolean boolean1 = tryParsingIntegerValue(userInputString);
		StringBuilder selectSql = new StringBuilder("SELECT * FROM books");
		if (boolean1) {
			selectSql.append(" WHERE book_price <= ?");
			PreparedStatement prepStmt = con.prepareStatement(selectSql.toString());
			prepStmt.setInt(1, book_price);
			ResultSet rSet = prepStmt.executeQuery();
			System.out.println("Books having price <= " + book_price + " = ");
			while (rSet.next()) {
				System.out.println(rSet.getInt(1) + "--" + rSet.getString(2) + "--" + rSet.getInt(3));
			}
		} else {
			System.out.println("User didn't enter valid input. So, printing all the records.");
			Statement stmt = con.createStatement();
			ResultSet rSet = stmt.executeQuery(selectSql.toString());
			while (rSet.next()) {
				System.out.println(rSet.getInt(1) + "--" + rSet.getString(2) + "--" + rSet.getInt(3));
			}
		}

		con.close();
	}

	private static boolean tryParsingIntegerValue(String userInputString) {
		try {
			book_price = Integer.parseInt(userInputString);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
