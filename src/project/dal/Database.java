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
			st.executeUpdate("DROP TABLE IF EXISTS  transaction ");
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
					+ "StdDev DECIMAL (8,6), "
					+ "PRIMARY KEY (stockSymbol)" + ");");

			st.executeUpdate("CREATE TABLE  strategy ( "
					+ "stratID INTEGER AUTO_INCREMENT NOT NULL, "
					+ "userID INTEGER NOT NULL, "
					+ "stockSymbol NVARCHAR (10) NOT NULL, "
					+ "type NVARCHAR(10), "
					+ "buySell NVARCHAR(20), "
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
			
			st.executeUpdate("CREATE TABLE  transaction ( "
					+ "transactionID INTEGER AUTO_INCREMENT NOT NULL, "
					+ "stockSymbol NVARCHAR(10) NOT NULL, " 
					+ "amount INTEGER, "
					+ "stockPrice DECIMAL(7,4), "
					+ "action NVARCHAR(15), "
					+ "status NVARCHAR(225), "
					+ "timeStamp TIMESTAMP, "
					+ "PRIMARY KEY (transactionID), "
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

			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('GLEN.L', 'GLENCORE', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('RWA.L', 'ROBERT WALT.', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('UKM.L', 'UK MAIL GROUP', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('VOW.F', 'Volkswagon AG', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('PHI.L', 'PACIFIC HORIZON', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('MRW.L', 'Morrison Supermarkets PLC', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('BP.L', 'BP PLC', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('VOD.L', 'Vodafone Group PLC', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('TSCO.L', 'Tesco PLC', 0)");
			st.executeUpdate("INSERT INTO stock(stockSymbol, stockName, percentageChange) VALUES('LLOY.L', 'Lloyds Banking Group PLC', 0)");
			
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('GLEN.L', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('RWA.L', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('UKM.L', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('VOW.F', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('PHI.L', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('MRW.L', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('BP.L', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('VOD.L', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('TSCO.L', 10.00, 8.00)");
			st.executeUpdate("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) VALUES('LLOY.L', 10.00, 8.00)");
		} 
			catch (SQLException ex) 
			{
			System.out.println("Database error " + ex);
			Logger log = Logger.getLogger("DATABASE CREATION:");
			log.error("ERROR" + ex);

			
		} 
			finally 
			{
			if (connect != null) 
			{
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
