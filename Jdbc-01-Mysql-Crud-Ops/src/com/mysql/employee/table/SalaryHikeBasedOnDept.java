package com.mysql.employee.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SalaryHikeBasedOnDept {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws Exception {
		Scanner sc = new Scanner(System.in);
		Map<String, Double> hikeMap = new HashMap<>();
		System.out.println("Enter hike % for Development dept.");
		double dev = sc.nextDouble();
		hikeMap.put("Development", dev);
		System.out.println("Enter hike % for Testing dept.");
		double testing = sc.nextDouble();
		hikeMap.put("Testing", testing);
		System.out.println("Enter hike % for HR dept.");
		double hr = sc.nextDouble();
		hikeMap.put("HR", hr);
		System.out.println("Enter hike % for Music dept.");
		double music = sc.nextDouble();
		hikeMap.put("Music", music);

		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		String updateSql = "UPDATE EMPLOYEE SET EMP_SALARY = EMP_SALARY + (EMP_SALARY *(?/100)) WHERE EMP_DEPT =?";
		PreparedStatement pstmt = con.prepareStatement(updateSql);
		for (Map.Entry<String, Double> entry : hikeMap.entrySet()) {
			pstmt.setDouble(1, entry.getValue());
			pstmt.setString(2, entry.getKey());
			pstmt.executeUpdate();
		}
		System.out.println("Updated...");
		con.close();
	}
}
