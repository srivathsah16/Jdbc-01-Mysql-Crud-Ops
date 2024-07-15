package com.mysql.employee.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

//Develop a JDBC application to provide increment for each employee (Give a 10% increment on the salary amount.)

public class SalaryHike {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String selectSql = "SELECT * FROM employee";

	public static void main(String[] args) throws Exception {
		System.out.println("Enter the % of hike::");
		Scanner sc = new Scanner(System.in);
		double hike = sc.nextDouble();
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		// String updateSql = "UPDATE employee SET emp_salary = ? WHERE emp_id=?";

		// APPROACH 1
		// The below approach is not recommended.
//		while (rSet.next()) {
//			empId = rSet.getInt("emp_id");
//			existingSalary = rSet.getDouble("emp_salary");
////			System.out.println("Current salary of " + rSet.getString("emp_name") + " = " + existingSalary);
//			newSalary = existingSalary + ((increment / 100) * existingSalary);
////			System.out.println("After " + increment + "% of increment, new salary = " + newSalary);
////			rSet.updateDouble("emp_salary", newSalary);
////			rSet.updateRow();
//			pstmt.setDouble(1, newSalary);
//			pstmt.setInt(2, empId);
//			pstmt.executeUpdate();
//		}

		// APPROACH - 2
		// This is the recommended approach:
		String updateSql2 = "UPDATE EMPLOYEE SET EMP_SALARY = EMP_SALARY + (EMP_SALARY * (?/100))";
		PreparedStatement pstmt = con.prepareStatement(updateSql2);
		pstmt.setDouble(1, hike);
		pstmt.executeUpdate();
		System.out.println("Salary update completed...");
		con.close();
	}
}
