package com.mysql.books.table;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectBooks_ScrollableResultSet {
	private static final String DB_URL = "jdbc:mysql://localhost:3306/advjavadb";
	private static final String DB_UNAME = "root";
	private static final String DB_PWD = "Sri@1608";
	private static final String SELECT_SQL = "SELECT book_id, book_name, book_price FROM books";

	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(DB_URL, DB_UNAME, DB_PWD);
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		ResultSet rSet = stmt.executeQuery(SELECT_SQL);

//		System.out.println("printing all the records in table starting from first row");
//		printAllRecords(rSet);
//
//		System.out.println("printing records from the last:");
//		printRecordsFromLastRow(rSet);
//
//		System.out.println("checking SCROLL_SENSITIVE feature of the ResultSet");
//		checkScrollSensitiveFeature(rSet);

//		System.out.println("checking rSet sensitivity to changes in database:");
//		checkRSetSensitivity(rSet);

		System.out.println("Checking columns related info - using metadata of rSet");
		checkNoOfColumnsInRSet(rSet);
//		System.out.println("checking deleteRow() method");
//		checkDeleteRowFunctionality(rSet);

		con.close();
	}

	private static void checkNoOfColumnsInRSet(ResultSet rSet) throws SQLException {
		ResultSetMetaData metaData = rSet.getMetaData();
		System.out.println("Total no of columns =" + metaData.getColumnCount());
		System.out.println("Columns names =");
		for(int i=1;i<=metaData.getColumnCount();i++) {
			System.out.println(metaData.getColumnName(i));
		}
	}

	private static void printAllRecords(ResultSet rSet) throws SQLException {
		while (rSet.next()) {
			System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
		}
	}

	private static void printRecordsFromLastRow(ResultSet rSet) throws SQLException {
		// to print records in reverse order ie, from last row.
		rSet.last();
		do {
			System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
		} while (rSet.previous());
	}

	private static void checkScrollSensitiveFeature(ResultSet rSet) throws SQLException, InterruptedException {
		System.out.println("directly jumping to 4th record in result set object.");
		rSet.absolute(4);
		System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
		System.out.println("now, changing this 4th record while the rSet is active.");
		rSet.updateInt(3, 900);
		rSet.updateRow();
		System.out.println("now, going back to previous row:");
		rSet.previous();
		System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
		rSet.next();
		System.out.println("now, again going back to next row:");
		System.out.println("now, you can see the updated value below:");
		System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
	}

	private static void checkDeleteRowFunctionality(ResultSet rSet) throws SQLException {
		rSet.absolute(4);
		System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
		rSet.deleteRow();
		rSet.absolute(4);
		System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
	}

	private static void checkRSetSensitivity(ResultSet rSet) throws IOException, SQLException {
		while (rSet.next()) {
			System.in.read(); // this will make the program wait for user to press any key in keyboard
			System.in.read(); // basically its similar to using Thread.sleep()
			// the idea here is to make the program wait, and while the rSet is active, user
			// will make change in DB. so if rSet is Sensitive, then refreshed data from DB
			// should be fetched and displayed.

			rSet.refreshRow();
			System.out.println(rSet.getInt(1) + "---" + rSet.getString(2) + "---" + rSet.getInt(3));
		}
	}
}
