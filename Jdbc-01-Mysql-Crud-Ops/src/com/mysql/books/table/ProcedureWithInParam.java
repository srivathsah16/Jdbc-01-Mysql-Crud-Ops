package com.mysql.books.table;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Scanner;

public class ProcedureWithInParam {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String PROCEDURE_WITH_IN_PARAM = "CALL getBookById(?);";

	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		CallableStatement cStmt = con.prepareCall(PROCEDURE_WITH_IN_PARAM);
		System.out.println("Enter book id::");
		Scanner sc = new Scanner(System.in);
		int bookId = sc.nextInt();
		cStmt.setInt(1, bookId);
		ResultSet rSet = cStmt.executeQuery();
		while (rSet.next()) {
			System.out.println(rSet.getInt(1) + "--" + rSet.getString(2) + "--" + rSet.getInt(3));
		}
		con.close();
	}
}
