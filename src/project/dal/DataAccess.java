package project.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	public static ArrayList<String> getStocks() {

		ArrayList<String> temp = new ArrayList<String>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select stockSymbol from stock ");

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
}
