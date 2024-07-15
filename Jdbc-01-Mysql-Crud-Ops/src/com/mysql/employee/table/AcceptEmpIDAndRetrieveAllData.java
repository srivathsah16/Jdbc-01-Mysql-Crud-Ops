package com.mysql.employee.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

/*
 * Develop a JDBC application to read emp_ID from the keyboard and retrieve Employee data along with address based on emp_id from database table.
 */
public class AcceptEmpIDAndRetrieveAllData {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter emp ID::");
		int empId = sc.nextInt();
		String retrieveEmpAddData = "SELECT * FROM EMP e, ADDR a WHERE e.eid=a.eid AND e.eid = ?";
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		PreparedStatement pstmt = con.prepareStatement(retrieveEmpAddData);
		pstmt.setInt(1, empId);
		ResultSet rSet = pstmt.executeQuery();
		if (rSet.next()) {
			System.out.println("ename = " + rSet.getString("ename") + ", esalary = " + rSet.getDouble("esalary")
					+ ", city=" + rSet.getString("city") + ", state=" + rSet.getString("state") + ", country = "
					+ rSet.getString("country"));
		} else {
			System.out.println("Enter valid emp ID.");
		}

		con.close();
	}
}
