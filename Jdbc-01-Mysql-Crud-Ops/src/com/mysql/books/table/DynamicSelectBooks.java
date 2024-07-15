package com.mysql.books.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class DynamicSelectBooks {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		System.out.println("Enter book price");
		Scanner sc = new Scanner(System.in);
		String bookPriceString = sc.nextLine();
		int bookPrice = parseIntValue(bookPriceString);

		StringBuilder selectSql = new StringBuilder("SELECT * FROM books ");
		if (bookPrice >= 0) {
			selectSql.append("WHERE book_price <= ?");
		}
		PreparedStatement pStmt = con.prepareStatement(selectSql.toString());
		if (bookPrice >= 0) {
			pStmt.setInt(1, bookPrice);
			System.out.println("Books having price <= " + bookPrice + " =>");
		}
		ResultSet rSet = pStmt.executeQuery();
		while (rSet.next()) {
			System.out.println(rSet.getInt(1) + "--" + rSet.getString(2) + "--" + rSet.getInt(3));
		}
		con.close();
	}

	private static int parseIntValue(String bookPriceString) {
		int bookPrice = 0;
		try {
			bookPrice = Integer.parseInt(bookPriceString);
		} catch (NumberFormatException e) {
			System.out.println("Invalid input.");
		}
		return bookPrice;
	}
}
