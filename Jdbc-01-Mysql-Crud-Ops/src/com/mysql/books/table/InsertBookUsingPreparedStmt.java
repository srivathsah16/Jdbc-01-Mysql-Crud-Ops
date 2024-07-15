package com.mysql.books.table;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class InsertBookUsingPreparedStmt {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String INSERT_SQL = "INSERT INTO books VALUES(?,?,?);";
	private static final String SELECT_SQL = "SELECT book_id FROM books WHERE book_id=?;";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		PreparedStatement pStmtInsertQuery = con.prepareStatement(INSERT_SQL);
		PreparedStatement pStmtSelectQuery = con.prepareStatement(SELECT_SQL);

		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Enter book id");
			int book_id = sc.nextInt();
			pStmtSelectQuery.setInt(1, book_id);
			ResultSet resultSet = pStmtSelectQuery.executeQuery();
			if (resultSet.next()) {
				System.out.println(
						"Duplicate book_id entered. Try with unique book_id as it is PRIMARY KEY in books Table");
			} else {
				System.out.println("Enter book name");
				sc.nextLine(); //consume the new line character
				String book_name = sc.nextLine(); // read the input string - book_name
				System.out.println("Enter book price");
				int book_price = sc.nextInt();

				pStmtInsertQuery.setInt(1, book_id);
				pStmtInsertQuery.setString(2, book_name); 
				pStmtInsertQuery.setInt(3, book_price);
				int count = pStmtInsertQuery.executeUpdate();
				System.out.println("Record inserted into DB. Rows affected = " + count);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			con.close();
		}

	}
}
