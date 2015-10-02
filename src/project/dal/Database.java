package project.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.jboss.logging.*;

public class Database {
	public static void main(String[] args) throws Exception {
		
		Connection connect = null;
		connect = getConnection();

			try {
			Statement st = connect.createStatement();
			st.executeUpdate("DROP TABLE IF EXISTS  strategy ");
			st.executeUpdate("DROP TABLE IF EXISTS  favourite ");
			st.executeUpdate("DROP TABLE IF EXISTS  profit ");
			st.executeUpdate("DROP TABLE IF EXISTS  ownedStock ");
			st.executeUpdate("DROP TABLE IF EXISTS  ticker ");
			st.executeUpdate("DROP TABLE IF EXISTS  user ");
			st.executeUpdate("DROP TABLE IF EXISTS  stock ");

			st.executeUpdate("CREATE TABLE  user ( "
					+ "userID INTEGER AUTO_INCREMENT NOT NULL, "
					+ "email NVARCHAR (20), " + "password NVARCHAR (20), "
					+ "PRIMARY KEY (userID) " + ");");

			st.executeUpdate("CREATE TABLE  stock ( "
					+ "stockSymbol NVARCHAR(10) NOT NULL, "
					+ "stockName NVARCHAR(30) NOT NULL, "
					+ "percentageChange NVARCHAR (10), "
					+ "openingPrice NVARCHAR(10), "
					+ "closePrice NVARCHAR(10), "
					+ "changeYearHigh NVARCHAR(10), "
					+ "changeYearLow NVARCHAR(10), "
					+ "shortMovingAverage DECIMAL(8,4),"
					+ "longMovingAverage DECIMAL(8,4),"
					+ "PRIMARY KEY (stockSymbol)" + ");");

			st.executeUpdate("CREATE TABLE  strategy ( "
					+ "stratID INTEGER AUTO_INCREMENT NOT NULL, "
					+ "userID INTEGER NOT NULL, "
					+ "stockSymbol NVARCHAR (10) NOT NULL, "
					+ "type NVARCHAR(10), "
					+ "buy INTEGER, "
					+ "sell INTEGER, "
					+ "active NVARCHAR(10), "
					+ "PRIMARY KEY (stratID), "
					+ "FOREIGN KEY (userID) REFERENCES user (userID), "
					+ "FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
					+ ");");

			st.executeUpdate("CREATE TABLE  favourite ( "
					+ "favID INTEGER AUTO_INCREMENT NOT NULL, "
					+ "userID INTEGER NOT NULL, "
					+ "stockSymbol NVARCHAR(10) NOT NULL, "
					+ "PRIMARY KEY (favID), "
					+ "FOREIGN KEY (userID) REFERENCES user (userID), "
					+ "FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
					+ ");");

			st.executeUpdate("CREATE TABLE  ticker ( "
					+ "tickerID INTEGER AUTO_INCREMENT NOT NULL, "
					+ "stockSymbol NVARCHAR(10) NOT NULL, "
					+ "askPrice DECIMAL(7,4), "
					+ "bidPrice DECIMAL(7,4), "
					+ "percentChange DECIMAL(7,4), "
					+ "timeStamp TIMESTAMP, "
					+ "PRIMARY KEY (tickerID), "
					+ "FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
					+ ");");

			st.executeUpdate("CREATE TABLE  ownedStock ( "
					+ "ownedID INTEGER AUTO_INCREMENT NOT NULL, "
					+ "stockSymbol NVARCHAR(10) NOT NULL, "
					+ "buyPrice DECIMAL(5,2), "
					+ "amount INTEGER, "
					+ "timeStamp TIMESTAMP, "
					+ "PRIMARY KEY (ownedID), "
					+ "FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
					+ ");");
			
			st.executeUpdate("CREATE TABLE  profit ( "
					+ "profitID INTEGER AUTO_INCREMENT NOT NULL, "
					+ "dailyAmount DECIMAL(5,2), "
					+ "overallAmount DECIMAL(5,2), "
					+ "timeStamp DATE, "
					+ "PRIMARY KEY (profitID) "
					+ ");");

			st.executeUpdate("INSERT INTO user(email, password) VALUES('user@gmail.com', 'password')");

			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('AAPL', 'Apple Inc.', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('MSFT', 'Microsoft Corporation', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('GOOG', 'Google Inc.', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('VOW.DE', 'Volkswagon AG', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('FB', 'Facebook, Inc', 0)");

			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('AAPL', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('MSFT', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('GOOG', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('VOW.DE', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('FB', 10.00, 8.00)");

			Logger log = Logger.getLogger("DATABASE CREATION:");
			log.debug("Database created");
			
		} catch (SQLException ex) {
			System.out.println("Database error " + ex);
			Logger log = Logger.getLogger("DATABASE CREATION:");
			log.error("ERROR" + ex);

			
		} finally {
			if (connect != null) {
				connect.close();
			}
		}
	}

	/**
	 * Method gets connection to database
	 * 
	 * @return connection
	 */
	public static Connection getConnection() {
		Connection cn = null;
		try {
			
			
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost", "root",
					"password");
			Statement st = cn.createStatement();
			st.executeUpdate("CREATE DATABASE IF NOT EXISTS livefeedproject");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/livefeedproject", "root",
					"password");
			
 

		} catch (SQLException ex) {
			System.out.println("Database Connection error " + ex);
			Logger log = Logger.getLogger("DATABASE CREATION:");
			log.error("ERROR" + ex);

		} catch (ClassNotFoundException ex) {
			System.out.println("Class not found " + ex);
			Logger log = Logger.getLogger("DATABASE CREATION:");
			log.error("ERROR" + ex);

		}

		if (cn.equals(null)) {

		}
		return cn;
	}
}
