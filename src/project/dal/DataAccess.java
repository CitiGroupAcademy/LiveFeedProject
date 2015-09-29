package project.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import project.dataObjects.Stock;
import project.dataObjects.Ticker;

public class DataAccess {

	/**
	 * Method gets the connection for the live feed project database
	 * 
	 * @return connection
	 */
	public static Connection getConnection() {

		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection(
					"jdbc:mysql://localhost/livefeedproject", "root",
					"password");

		} catch (SQLException ex) {
			System.out.println("Database Connection error: " + ex);

		} catch (ClassNotFoundException ex) {
			System.out.println("Class not found: " + ex);
		}

		return cn;

	}

	/**
	 * Inserts stocks with their ask price and bid price into the ticker table
	 * 
	 * @param symbol
	 *            String
	 * @param ask
	 *            double
	 * @param bid
	 *            double
	 */
	public static void insertTicker(String symbol, double ask, double bid) {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO ticker(stockSymbol, askPrice, bidPrice) values(?,?,?)");
			st.setString(1, symbol);
			st.setDouble(2, ask);
			st.setDouble(3, bid);

			st.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * Method returns an ArrayList of ticker objects
	 */
	public static ArrayList<Ticker> getTicker() {

		ArrayList<Ticker> temp = new ArrayList<Ticker>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select * from ticker");

			while (rs.next()) {
				temp.add(new Ticker(rs.getInt(1), rs.getString(2), rs.getDouble(3), rs.getDouble(4), rs.getDate(5)));
			}

		} catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		return temp;
	}

	/**
	 * Clears all stocks in ticker table
	 */
	public static void clearTicker() {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn.prepareStatement("delete from ticker");

			st.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
	/**
	 * Inserts stocks with their stock symbol and name into the stock table
	 * 
	 * @param symbol
	 *            String
	 * @param name
	 *            String
	 */
	public static void insertStock(String symbol, String name) {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO stock(stockSymbol, name) values(?,?)");
			st.setString(1, symbol);
			st.setString(2, name);
			st.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * Method returns an ArrayList of stocks from database
	 */
	public static ArrayList<Stock> getStocks() {

		ArrayList<Stock> temp = new ArrayList<Stock>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select * from stock ");

			while (rs.next()) {
				temp.add(new Stock(rs.getString(1), rs.getString(2)));
			}

		} catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return temp;

	}
	
	/**
	 * Method gets the top 5 stocks based on the percentage change
	 * @return
	 */
	public static ArrayList<String> getTop5(){
		
		ArrayList<String> temp = new ArrayList<String>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select top 5 from stock");

			while (rs.next()) {
				temp.add(rs.getString(1));
			}

		} catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return temp;
	}
	
	/**
	 * Method gets the bottom 5 stocks based on the percentage change
	 * @return
	 */
	public static ArrayList<String> getBottom5(){
		
		ArrayList<String> temp = new ArrayList<String>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select top 5 from stock    ");

			while (rs.next()) {
				temp.add(rs.getString(1));
			}

		} catch (SQLException ex) {
			System.out.println("Error getting data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return temp;
	}

	public static void updateStockChange(String symbol, String percentageChange) {
		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("UPDATE stock SET percentagechange = ? WHERE stockSymbol = ?");
			st.setString(1, percentageChange);
			st.setString(2, symbol);
			st.executeUpdate();

		} catch (SQLException ex) {
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}

	}
	
}
