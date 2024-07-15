package com.mysql.employee.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SelectEmployeeDynamically {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	static String empDept = "";
	static String empGender = "";
	static String empWorkLoc = "";

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Emp department");
		empDept = sc.nextLine().strip();
		System.out.println("Enter Emp Gender");
		empGender = sc.nextLine().strip();
		System.out.println("Enter emp Work location");
		empWorkLoc = sc.nextLine().strip();

		StringBuilder selectSql = new StringBuilder("SELECT * FROM employee ");
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		
		// Below is Dynamic Query Creation - based on user input.

		if (empDept != "" && empGender != "" && empWorkLoc != "") {
			// System.out.println("entered if block");
			selectSql.append(" WHERE emp_dept= ? AND emp_gender= ? AND emp_work_location = ?");
		}
		if (empDept != "" && empGender == "" && empWorkLoc == "") {
			selectSql.append(" WHERE emp_dept = ?");
		}
		if (empDept == "" && empGender != "" && empWorkLoc == "") {
			selectSql.append(" WHERE emp_gender = ?");
		}
		if (empDept == "" && empGender == "" && empWorkLoc != "") {
			selectSql.append(" WHERE emp_work_location = ?");
		}
		if (empDept != "" && empGender != "" && empWorkLoc == "") {
			selectSql.append(" WHERE emp_dept = ? AND emp_gender = ?");
		}
		if (empDept != "" && empGender == "" && empWorkLoc != "") {
			selectSql.append(" WHERE emp_dept = ? AND emp_work_location = ?");
		}
		if (empDept == "" && empGender != "" && empWorkLoc != "") {
			selectSql.append(" WHERE emp_gender = ? AND emp_work_location = ?");
		}

		PreparedStatement pstmt = con.prepareStatement(selectSql.toString());

		if (empDept != "" && empGender != "" && empWorkLoc != "") {
			pstmt.setString(1, empDept);
			pstmt.setString(2, empGender);
			pstmt.setString(3, empWorkLoc);
		}
		if (empDept != "" && empGender == "" && empWorkLoc == "") {
			pstmt.setString(1, empDept);
		}
		if (empDept == "" && empGender != "" && empWorkLoc == "") {
			pstmt.setString(1, empGender);
		}
		if (empDept == "" && empGender == "" && empWorkLoc != "") {
			pstmt.setString(1, empWorkLoc);
		}
		if (empDept != "" && empGender != "" && empWorkLoc == "") {
			pstmt.setString(1, empDept);
			pstmt.setString(2, empGender);
		}
		if (empDept != "" && empGender == "" && empWorkLoc != "") {
			pstmt.setString(1, empDept);
			pstmt.setString(2, empWorkLoc);
		}
		if (empDept == "" && empGender != "" && empWorkLoc != "") {
			pstmt.setString(1, empGender);
			pstmt.setString(2, empWorkLoc);
		}
		ResultSet rSet = pstmt.executeQuery();
		System.out.println("Records matching given selection criteria => ");
		while (rSet.next()) {
			System.out.println(rSet.getInt(1) + "--" + rSet.getString(2) + "--" + rSet.getDouble(3) + "--"
					+ rSet.getString(4) + "--" + rSet.getString(5) + "--" + rSet.getString(6));
		}
		con.close();
	}
}
//Development	Male	Bangalore
//Development	Female	Bangalore
//Testing	Male	Mysore
//Development	Female	Mysore
//HR	Male	Hyderabad
//Music	Male	Chennai
//Music	Female	Chennai
//Music	Male	Mysore
