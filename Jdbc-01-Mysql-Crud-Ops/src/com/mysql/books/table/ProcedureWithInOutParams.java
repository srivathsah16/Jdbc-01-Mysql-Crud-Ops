package com.mysql.books.table;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Types;
import java.util.Scanner;

public class ProcedureWithInOutParams {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String PROCEDURE_WITH_INOUT_PARAM = "CALL getBookNameByPrice(?,?)";

	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		CallableStatement cStmt = con.prepareCall("CALL getBookNameByPrice(?,?)");
		System.out.println("Enter book price::");
		Scanner sc = new Scanner(System.in);
		int bookPrice = sc.nextInt();
		cStmt.setInt(1, bookPrice);
		cStmt.registerOutParameter(2, Types.VARCHAR);
		ResultSet rSet = cStmt.executeQuery();
		while (rSet.next()) {
			System.out.println(rSet.getString(1));
		}
		con.close();
	}
}
