package project.dal;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;

import org.jboss.logging.Logger;

import project.dataObjects.OwnedStock;
import project.dataObjects.Profit;
import project.dataObjects.Stock;
import project.dataObjects.Strategy;
import project.dataObjects.Ticker;
import project.dataObjects.Transaction;

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
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);

		} catch (ClassNotFoundException ex) {
			System.out.println("Class not found: " + ex);
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);

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
	public static void insertTicker(String symbol, double ask, double bid,
			double percentage) {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO ticker(stockSymbol, askPrice, bidPrice, percentChange) values(?,?,?,?)");
			st.setString(1, symbol);
			st.setDouble(2, ask);
			st.setDouble(3, bid);
			st.setDouble(4, percentage);

			st.executeUpdate();

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

	}
	
	public static ArrayList<Profit> getProfit() {

		ArrayList<Profit> temp = new ArrayList<Profit>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select * from profit");

			while (rs.next()) {
				temp.add(new Profit(rs.getInt(1), rs.getDouble(2), rs.getDouble(3), rs.getTimestamp(4)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;
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
				temp.add(new Ticker(rs.getInt(1), rs.getString(2), rs
						.getDouble(3), rs.getDouble(4), rs.getDate(5)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;
	}

	public static ArrayList<Transaction> getTransaction() {

		ArrayList<Transaction> temp = new ArrayList<Transaction>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select * from transaction");

			while (rs.next()) {
				temp.add(new Transaction(rs.getInt(1), rs.getString(2), rs
						.getInt(3), rs.getDouble(4), rs.getString(5), rs
						.getString(6), rs.getTimestamp(7)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
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
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
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
					.prepareStatement("INSERT INTO stock(stockSymbol, stockName) values(?,?)");
			st.setString(1, symbol);
			st.setString(2, name);
			st.executeUpdate();

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
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
				temp.add(new Stock(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getString(6), rs.getString(7), rs.getDouble(8), rs
						.getDouble(9), rs.getDouble(10)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;

	}

	/**
	 * Method returns an ArrayList of stocks from database
	 */
	public static ArrayList<String> getFavourites() {

		ArrayList<String> temp = new ArrayList<String>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select stockSymbol from favourite ");

			while (rs.next()) {
				temp.add(rs.getString(1));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;

	}

	/**
	 * Method gets the top 5 stocks based on the percentage change
	 * 
	 * @return
	 */
	public static ArrayList<String> getTop5() {

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
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;
	}

	/**
	 * Method gets the bottom 5 stocks based on the percentage change
	 * 
	 * @return
	 */
	public static ArrayList<String> getBottom5() {

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
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;
	}

	public static ArrayList<Strategy> getStrats() {

		ArrayList<Strategy> temp = new ArrayList<>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st.executeQuery("Select * from strategy");

			while (rs.next()) {
				temp.add(new Strategy(rs.getInt(1), rs.getInt(2), rs
						.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;
	}

	public static void updateStockChange(String symbol,
			String percentageChange, String openPrice, String closePrice,
			String changeYearHigh, String changeYearLow,
			double fiftyDayMovingAverage, double fiveMinuteMovingAverage,
			double STD) {
		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("UPDATE stock SET percentagechange = ?, openingPrice = ?, closePrice = ?, changeYearHigh = ?, changeYearLow = ?, longMovingAverage = ?, shortMovingAverage = ?, StdDev = ? WHERE stockSymbol = ?");
			st.setString(1, percentageChange);
			st.setString(2, openPrice);
			st.setString(3, closePrice);
			st.setString(4, changeYearHigh);
			st.setString(5, changeYearLow);
			st.setDouble(6, fiftyDayMovingAverage);
			st.setDouble(7, fiveMinuteMovingAverage);
			st.setDouble(8, STD);
			st.setString(9, symbol);
			st.executeUpdate();

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
	}

	/**
	 * Method inserts a new tuple into the strategy table
	 * 
	 * @param userID
	 *            int
	 * @param stockSymbol
	 *            String
	 * @param type
	 *            String
	 * @param buy
	 *            int
	 * @param sell
	 *            int
	 * @param active
	 *            String
	 */
	public static void insertStrategy(int userID, String stockSymbol,
			String type, String active) {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO strategy (userID, stockSymbol, type, active) values(?,?,?,?)");
			st.setInt(1, userID);
			st.setString(2, stockSymbol);
			st.setString(3, type);
			st.setString(4, active);
			st.executeUpdate();

			if (!getFavourites().contains(stockSymbol)) {

				insertIntoFav(userID, stockSymbol);
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

	}

	public static void updateStrategy(int userID, String stockSymbol,
			String type, String buySell, String active, int stratID) {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("UPDATE strategy SET userID = ?, stockSymbol = ?, type = ?, active = ? WHERE stratID = ?");
			st.setInt(1, userID);
			st.setString(2, stockSymbol);
			st.setString(3, type);
			st.setString(4, active);
			st.setInt(5, stratID);
			st.executeUpdate();

			insertIntoFav(userID, stockSymbol);

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

	}

	/**
	 * Method inserts a new tuple into the strategy table
	 * 
	 * @param userID
	 *            int
	 * @param stockSymbol
	 *            String
	 */
	public static void insertIntoFav(int userID, String stockSymbol) {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO favourite(userID, stockSymbol) VALUES(?,?)");
			st.setInt(1, userID);
			st.setString(2, stockSymbol);

			st.executeUpdate();
		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

	}

	public static void insertProfit(int parseInt, double d, double e) {
		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO profit(profitID, dailyAmount, overallAmount) VALUES(?,?,?)");
			st.setInt(1, parseInt);
			st.setDouble(2, d);
			st.setDouble(3, e);
			st.executeUpdate();

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException ex) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + ex);
				}
			}
		}
	}

	public static void insertProfit(int parseInt, double d, double e,
			String date) {
		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO profit(profitID, dailyAmount, overallAmount, timeStamp) VALUES(?,?,?,?)");
			st.setInt(1, parseInt);
			st.setDouble(2, d);
			st.setDouble(3, e);
			st.setDate(4, Date.valueOf(date));
			st.executeUpdate();

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException ex) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + ex);
				}
			}
		}
	}

	/**
	 * Method returns the last 10 percentage changes
	 * 
	 * @return
	 */

	public static ArrayList<String> returnLast50PercentageChanges(String symbol) {

		ArrayList<String> temp = new ArrayList<String>();
		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("select percentchange, timestamp "
							+ "from ticker " + "where stocksymbol like ? "
							+ "order by timeStamp desc " + "limit 50 ");

			st.setString(1, symbol.replaceAll("\"", ""));

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				temp.add(Double.toString(rs.getDouble(1)) + ","
						+ rs.getTimestamp(2).toString());

			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

		Collections.reverse(temp);
		System.out.println(temp.toString());
		return temp;

	}

	public static String calculateMovingAverage(String symbol) {

		double temp = 0;

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("select avg((askPrice + bidPrice )/2) "
							+ "from ticker "
							+ "where stockSymbol like ? "
							+ "and timestamp between DATE_SUB(NOW(), INTERVAL 5 MINUTE) "
							+ "and NOW();");

			st.setString(1, symbol.replaceAll("\"", ""));

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				temp = rs.getDouble(1);

			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return String.format("%.4f", temp);

	}

	public static ArrayList<Strategy> getActiveStatsMoving() {

		ArrayList<Strategy> temp = new ArrayList<>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from strategy where active = 'active' and type like 'movingAvg'");

			while (rs.next()) {
				temp.add(new Strategy(rs.getInt(1), rs.getInt(2), rs
						.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;
	}

	/**
	 * Method inserts tuple into transaction table
	 * 
	 * @param amount
	 *            int
	 * @param stockPrice
	 *            double
	 * @param action
	 *            String
	 * @param status
	 *            String
	 */
	public static void insertTransaction(String stockSymbol, int amount,
			double stockPrice, String action, String status) {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO transaction(stockSymbol, amount, stockPrice, action, status) values(?,?,?,?,?)");
			st.setString(1, stockSymbol);
			st.setInt(2, amount);
			st.setDouble(3, stockPrice);
			st.setString(4, action);
			st.setString(5, status);
			st.executeUpdate();

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

	}

	public static double getMostRecentStockAskPrice(String symbol) {

		double ask = 0;

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("SELECT askPrice FROM ticker WHERE stockSymbol = ? ORDER BY timeStamp DESC LIMIT 1");

			st.setString(1, symbol);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				ask = rs.getDouble(1);

			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

		return ask;

	}

	/**
	 * Method returns a list of the amount of stocks owned
	 * 
	 * @param symbol
	 * @return
	 */
	public static int amountOfOwnedStock(String symbol) {

		int amount = 0;

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("SELECT sum(amount) FROM ownedStock WHERE stockSymbol = ? ORDER BY timeStamp DESC LIMIT 1");

			st.setString(1, symbol);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				amount = rs.getInt(1);

			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

		return amount;

	}

	/**
	 * Insert into owned stock
	 * 
	 * @param stock
	 * @param shares
	 * @param price
	 */
	public static void insertOwnedStock(String stock, int shares, double price) {
		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("INSERT INTO OwnedStock(stockSymbol, buyPrice, amount) values(?,?,?)");
			st.setString(1, stock);
			st.setDouble(2, price);
			st.setInt(3, shares);
			st.executeUpdate();

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}

	}

	/**
	 * Get the total amount of stock owned in each company
	 * 
	 * @return
	 */
	public static ArrayList<OwnedStock> getOwnedStocks() {
		ArrayList<OwnedStock> temp = new ArrayList<>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("select stockSymbol, sum(amount) as 'Amount of Stocks' "
							+ "from ownedstock group by "
							+ "stockSymbol order by amount");

			while (rs.next()) {
				temp.add(new OwnedStock(0, rs.getString(1), rs.getInt(2)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;

	}

	/**
	 * Method returns an arraylist of active moving average exponential
	 * strategies
	 * 
	 * @return
	 */
	public static ArrayList<Strategy> getActiveStatsMovingExp() {

		ArrayList<Strategy> temp = new ArrayList<>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from strategy where active = 'active' and type like 'movAvgExp'");

			while (rs.next()) {
				temp.add(new Strategy(rs.getInt(1), rs.getInt(2), rs
						.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;
	}

	/**
	 * Method returns an arraylist of active moving average exponential
	 * strategies
	 * 
	 * @return
	 */
	public static ArrayList<Strategy> getBollingerBandStrats() {

		ArrayList<Strategy> temp = new ArrayList<>();

		Connection cn = null;
		try {
			cn = getConnection();
			Statement st = cn.createStatement();
			ResultSet rs = st
					.executeQuery("Select * from strategy where active = 'active' and type like 'bollinger'");

			while (rs.next()) {
				temp.add(new Strategy(rs.getInt(1), rs.getInt(2), rs
						.getString(3), rs.getString(4), rs.getString(5)));
			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;
	}

	public static void confirmOrder() {

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("UPDATE transaction set status = 'Confirmed' where transactionID != -1");
			st.executeUpdate();

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException ex) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + ex);
				}

			}
		}
	}

	public static double calculateStockSTD(String symbol) {

		double temp = 0;

		Connection cn = null;
		try {
			cn = getConnection();
			PreparedStatement st = cn
					.prepareStatement("select stddev((askPrice + bidPrice)/2) "
							+ "from ticker where stockSymbol like ? "
							+ "and timestamp between DATE_SUB(NOW(), INTERVAL 1 hour) and NOW();");

			st.setString(1, symbol.replaceAll("\"", ""));

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				temp = rs.getDouble(1);

			}

		} catch (SQLException ex) {
			Logger log = Logger.getLogger("DATA ACCESS LAYER:");
			log.error("ERROR" + ex);
			System.out.println("Error adding data " + ex);
		} finally {

			if (cn != null) {

				try {
					cn.close();
				} catch (SQLException e) {
					Logger log = Logger.getLogger("DATA ACCESS LAYER:");
					log.error("ERROR" + e);
				}
			}
		}
		return temp;

	}

	public static void removeStrategy(String symbol) {
		Connection con = null;
		try {

			con = Database.getConnection();
			PreparedStatement st = con
					.prepareStatement("DELETE FROM strategy WHERE stockSymbol = ?");
			st.setString(1, symbol);
			st.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Database error " + ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void removeStock(String symbol) {
		Connection con = null;
		try {

			con = Database.getConnection();
			PreparedStatement st = con
					.prepareStatement("DELETE FROM stock WHERE stockSymbol = ?");
			st.setString(1, symbol);
			st.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Database error " + ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public static void removeFavourite(String symbol) {
		Connection con = null;
		try {

			con = Database.getConnection();
			PreparedStatement st = con
					.prepareStatement("DELETE FROM favourite WHERE stockSymbol = ?");
			st.setString(1, symbol);
			st.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Database error " + ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

	
	public static void removeTransaction(String symbol) {
		Connection con = null;
		try {

			con = Database.getConnection();
			PreparedStatement st = con
					.prepareStatement("DELETE FROM transaction WHERE stockSymbol = ?");
			st.setString(1, symbol);
			st.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Database error " + ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}
	
	public static void removeOwnedStock(String symbol) {
		Connection con = null;
		try {

			con = Database.getConnection();
			PreparedStatement st = con
					.prepareStatement("DELETE FROM ownedstock WHERE stockSymbol = ?");
			st.setString(1, symbol);
			st.executeUpdate();
		} catch (SQLException ex) {
			System.out.println("Database error " + ex);
		} finally {
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}		
	}
	
	public static void main(String[] args) {
	}

}
