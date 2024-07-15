package com.mysql.employee.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

//Develop a JDBC application to retrieve employee records from the database table based on given search criteria.
//Work_location:
//Department:
//Gender:
//	If the user doesn’t give any value for search criteria, retrieve all records from table.
//	If the user provides search criteria, retrieve records based on the given criteria.
//	Users may provide any 1 value or any 2 values or all 3 values.

public class DynamicQuerySelectionEmployee {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter Emp Dept:");
		String empDept = sc.nextLine().strip();
		System.out.println("Enter Emp Gender:");
		String empGender = sc.nextLine().strip();
		System.out.println("Enter Emp Work Location:");
		String empWorkLocation = sc.nextLine().strip();

		StringBuilder sql = new StringBuilder("SELECT * FROM employee WHERE 1=1");
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);

		if (empDept != null && !empDept.equals("")) {
			sql.append(" AND emp_dept = ?");
		}
		if (empGender != null && !empGender.equals("")) {
			sql.append(" AND emp_gender = ?");
		}
		if (empWorkLocation != null && !empWorkLocation.equals("")) {
			sql.append(" AND emp_work_location = ?");
		}

		PreparedStatement pstmt = con.prepareStatement(sql.toString());
		int position = 1;
		if (empDept != null && !empDept.equals("")) {
			pstmt.setString(position, empDept);
			position++;
		}
		if (empGender != null && !empGender.equals("")) {
			pstmt.setString(position, empGender);
			position++;
		}
		if (empWorkLocation != null && !empWorkLocation.equals("")) {
			pstmt.setString(position, empWorkLocation);
		}
		System.out.println("Query prepared based on User Input = " + sql);
		ResultSet rSet = pstmt.executeQuery();
		while (rSet.next()) {
			System.out.println(rSet.getInt(1) + "--" + rSet.getString(2) + "--" + rSet.getDouble(3) + "--"
					+ rSet.getString(4) + "--" + rSet.getString(5) + "--" + rSet.getString(6));
		}
		con.close();
	}
}
