package com.mysql.employee.table;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateEmployeeTable {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";

	public static void main(String[] args) throws SQLException {
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		Statement st = con.createStatement();
		String createSql = "create table employee (\r\n" + "emp_id int not null,\r\n" + "emp_name varchar(50),\r\n"
				+ "emp_salary double,\r\n" + "emp_dept varchar(20),\r\n" + "emp_gender varchar(10),\r\n"
				+ "emp_work_location varchar(20),\r\n" + "primary key (emp_id)\r\n" + ");";
		int data = st.executeUpdate(createSql);
		System.out.println(data);
		con.close();
	}
}
