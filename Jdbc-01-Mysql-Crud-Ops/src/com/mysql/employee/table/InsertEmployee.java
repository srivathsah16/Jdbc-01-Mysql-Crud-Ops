package com.mysql.employee.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertEmployee {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	static Integer empId = 0;
	static String empName = "";
	static Double empSalary = 0d;
	static String empDept = "";
	static String empGender = "";
	static String empWorkLoc = "";
	static int loopCount = 1;
	static boolean boolean1 = false;
	static boolean boolean2 = false;

	public static void main(String[] args) throws SQLException {
		Scanner sc = new Scanner(System.in);
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);

		// before inserting record, make sure there is no record with same emp_id in the
		// db table (because emp_id is primary key in EMPLOYEE table.)
		String selectSql = "SELECT * FROM employee WHERE emp_id = ?";
		PreparedStatement pStmtSelect = con.prepareStatement(selectSql);
		ResultSet rSet = null;

		while (true) {
			try {
				System.out.println("Enter Employee details..\nEnter emp id");
				if (loopCount > 1) {
					sc.nextLine();
				}
				empId = sc.nextInt();
				sc.nextLine();// to consume new line character
				checkPrimaryKeyConstraint(pStmtSelect, rSet, sc);
				System.out.println("Enter emp name");
				empName = sc.nextLine();
				checkEmpName(sc);
				System.out.println("Enter emp salary");
				empSalary = sc.nextDouble();
				sc.nextLine(); // to consume new line character
				System.out.println("Enter emp department");
				empDept = sc.nextLine();
				System.out.println("Enter emp gender");
				empGender = sc.nextLine();
				System.out.println("Enter emp work location");
				empWorkLoc = sc.nextLine();
			} catch (Exception e) {
				System.out.println("Invalid input, please enter correct details.");
				loopCount++;
				continue;
			}
			break;
		}

		String insertQuery = "INSERT INTO employee VALUES(?,?,?,?,?,?)";

		PreparedStatement pStmt = con.prepareStatement(insertQuery);
		pStmt.setInt(1, empId);
		pStmt.setString(2, empName);
		pStmt.setDouble(3, empSalary);
		pStmt.setString(4, empDept);
		pStmt.setString(5, empGender);
		pStmt.setString(6, empWorkLoc);

		int rowsAffected = pStmt.executeUpdate();
		System.out.println("Record successfully inserted into DB, rows affected = " + rowsAffected);
		con.close();
	}

	private static void checkPrimaryKeyConstraint(PreparedStatement pStmtSelect, ResultSet rSet, Scanner sc)
			throws SQLException {
		while (true) {
			pStmtSelect.setInt(1, empId);
			rSet = pStmtSelect.executeQuery();
			if (rSet.next()) {
				System.out.println(
						"A record already exists in DB with same emp_id. So, Kindly enter a different emp_id.");
				empId = sc.nextInt();
				sc.nextLine();
				continue;
			}
			break;
		}
	}

	private static void checkEmpName(Scanner sc) {
		while (true) {
			try {
				int num = Integer.parseInt(empName);
				System.out.println("Invalid name. Please enter valid emp name.");
				empName = sc.nextLine();
				continue;
			} catch (Exception e) {
				break;
			}
		}
	}
}
