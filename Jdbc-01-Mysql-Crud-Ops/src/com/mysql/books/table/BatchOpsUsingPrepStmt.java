package com.mysql.books.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Scanner;

public class BatchOpsUsingPrepStmt {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		String insertSql = "INSERT INTO books VALUES (?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(insertSql);
		Scanner sc = new Scanner(System.in);
		System.out.println("How many records would you like to insert into DB?");
		int recordsCount = sc.nextInt();
		int bookId = 0;
		String bookName = "";
		int bookPrice = 0;
		for (int i = 1; i <= recordsCount; i++) {
			System.out.println("Enter Id of book - " + i);
			bookId = sc.nextInt();
			System.out.println("Enter name of book - " + i);
			sc.nextLine();
			bookName = sc.nextLine();
			System.out.println("Enter price of book - " + i);
			bookPrice = sc.nextInt();
			pstmt.setInt(1, bookId);
			pstmt.setString(2, bookName);
			pstmt.setInt(3, bookPrice);
			pstmt.addBatch();
		}
		int[] count = pstmt.executeBatch();
		for(int i:count) {
			System.out.println(i);
		}
		con.close();
		System.out.println("Insertion completed...");
	}
}
