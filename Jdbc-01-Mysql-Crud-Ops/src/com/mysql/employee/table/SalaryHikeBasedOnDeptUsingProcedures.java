package com.mysql.employee.table;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalaryHikeBasedOnDeptUsingProcedures {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws Exception {
//		Map<String, Double> hikeDetailsMap = new HashMap<>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter hike % for Development");
		double develop = sc.nextDouble();
//		hikeDetailsMap.put("Development", develop);
		System.out.println("Enter hike % for Tetsing");
		double tetsing = sc.nextDouble();
		// hikeDetailsMap.put("Testing", tetsing);
		System.out.println("Enter hike % for Music");
		double music = sc.nextDouble();
		// hikeDetailsMap.put("Music", music);
		System.out.println("Enter hike % for HR");
		double hr = sc.nextDouble();
		// hikeDetailsMap.put("HR", hr);

		String procedure = "CALL updateSalaryBasedOnDept(?,?,?,?)";

		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		CallableStatement cStmt = con.prepareCall(procedure);
		cStmt.setDouble(1, develop);
		cStmt.setDouble(2, tetsing);
		cStmt.setDouble(3, music);
		cStmt.setDouble(4, hr);

		cStmt.executeQuery();

		con.close();

	}
}
