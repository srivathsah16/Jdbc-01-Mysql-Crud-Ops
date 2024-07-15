package com.mysql.books.table;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

public class ProcedureCallEx {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String PROCEDURE = "CALL getBooksData();";

	public static void main(String[] args) throws Exception {
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		CallableStatement cstmt = con.prepareCall(PROCEDURE);
		ResultSet rSet = cstmt.executeQuery();
		while (rSet.next()) {
			System.out.println(rSet.getInt(1) + "--" + rSet.getString(2) + "--" + rSet.getInt(3));
		}
		con.close();
	}
}
