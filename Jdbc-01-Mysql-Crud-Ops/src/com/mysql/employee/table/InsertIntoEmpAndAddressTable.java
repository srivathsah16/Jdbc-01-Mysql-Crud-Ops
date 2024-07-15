package com.mysql.employee.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

/*
 * Read Employee and emp address data from keyboard and insert into DB table.
Emp data: ID, name, salary
Emp address : city, state, country
Note: Employee data should be inserted into the Employee table and the address data should be inserted into the address table. 
 */
public class InsertIntoEmpAndAddressTable {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws Exception {

		System.out.println("Enter Employee Details. \n Enter id:");
		Scanner sc = new Scanner(System.in);
		int id = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter name:");
		String name = sc.nextLine();
		System.out.println("Enter salary:");
		double salary = sc.nextDouble();
		sc.nextLine();
		System.out.println("Enter city:");
		String city = sc.nextLine();
		System.out.println("Enter state:");
		String state = sc.nextLine();
		System.out.println("Enter country:");
		String country = sc.nextLine();

		String empInsertSql = "INSERT INTO emp VALUES (?,?,?)";
		String addrInsertSql = "INSERT INTO addr VALUES (?,?,?,?)";
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		// By default, con - autoCommit is set to TRUE.
		con.setAutoCommit(false);
		try {
			PreparedStatement pstmt = con.prepareStatement(empInsertSql);
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setDouble(3, salary);
			pstmt.executeUpdate();

			pstmt = con.prepareStatement(addrInsertSql);
			pstmt.setString(1, city);
			pstmt.setString(2, state);
			pstmt.setString(3, country);
			pstmt.setInt(4, id);
			pstmt.executeUpdate();

			con.commit();
			System.out.println("Records inserted...");
		} catch (Exception e) {
			System.out.println(e.getMessage() + "Transaction rolled back...");
			con.rollback();
		}

		con.close();
	}
}
