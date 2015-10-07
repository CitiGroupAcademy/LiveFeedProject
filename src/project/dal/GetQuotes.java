package project.dal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import org.jboss.logging.Logger;

import project.dal.OrderManager.OrderResult;
import project.dataObjects.OwnedStock;
import project.dataObjects.Stock;
import project.dataObjects.Strategy;

/**
 * Quote class accesses Yahoo market data and saves to database ticker
 *
 * regards to yahoo market data for for their services
 * 
 * @author Citi 2015
 *
 */
public class GetQuotes extends Thread {

	/**
	 * Symbol inputed by user
	 */
	public static String symbol = "";

	/**
	 * Moving average number inputed by user
	 */
	public static int movingAverageNo = 0;

	/**
	 * Amount for the capture number
	 */
	public static int captureNumber = 0;

	/**
	 * Decrementor decrements with each loop iteration
	 */
	public static int decrementor = 0;

	/**
	 * Bid moving average
	 */
	public static double bidMovingAverage = 0;

	/**
	 * ask moving average
	 */
	public static double askMovingAverage = 0;

	/**
	 * total bids
	 */
	public static double totalBids = 0;

	/**
	 * total asks
	 */
	public static double totalForMovingAverage = 0;

	/**
	 * Start time for moving average calculation
	 */
	public static Date startTime;

	/**
	 * Start time plus 5 minutes
	 */
	public static Date startPlus5Min;

	/**
	 * Incrementing time for moving average calculation
	 */
	public static Date currentTime = new Date();

	/**
	 * Main method to start
	 * 
	 * @param args
	 * @throws Exception
	 */
	@Override
	public void run() {
		try {
			insertIntoTicker();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			insertIntoTicker();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Runs indefinitely
	 * 
	 * @throws Exception
	 */
	public static void insertIntoTicker() throws Exception {

		// clear data in database
		DataAccess.clearTicker();

		startTime = new Date();

		startPlus5Min = new Date(System.currentTimeMillis() + 5 * 60 * 1000);

		System.out.println("running");

		while (true) {

			currentTime = new Date();

			StringBuilder url = new StringBuilder(
					"http://finance.yahoo.com/d/quotes.csv?s=");

			for (Stock s : DataAccess.getStocks()) {
				url.append(s.getStockSymbol() + ",");
			}

			url.append("&f=sabp2opk4j5m3l1&e=.csv");

			String theUrl = url.toString();

			URL obj = new URL(theUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;

			while ((inputLine = in.readLine()) != null) {

				String[] fields = inputLine.split(",");

				if (fields[1].equalsIgnoreCase("N/A")
						|| fields[2].equalsIgnoreCase("N/A")) {

					continue;
				}

				DataAccess.insertTicker(fields[0].replaceAll("\"", ""), Double
						.parseDouble(fields[1]), Double.parseDouble(fields[2]),
						Double.parseDouble(removeLastChar(fields[9])
								.replaceAll("\"", "")));

				fields[3] = removeLastChar(fields[3]).replaceAll("\"", "");

				DataAccess.updateStockChange(fields[0].replaceAll("\"", ""),
						fields[3], fields[4], fields[5], fields[6], fields[7],
						Double.parseDouble(fields[8]), Double
								.parseDouble(DataAccess
										.calculateMovingAverage(fields[0])));

			}
			// Sleep thread to reduce processing
			// start strategies after 5 minutes (so 5 minute moving average
			// is calculated)
			if (currentTime.after(startPlus5Min)) {

				moivngAverageStrategy();
				moivngAverageExponential();
			}
			Thread.sleep(5000);
		}
	}

	/**
	 * Method calculates the moving average for strategies
	 */
	private static void moivngAverageStrategy() {

		// arraylist of moving average strategies from database
		ArrayList<Strategy> movingAverageObjects = DataAccess
				.getActiveStatsMoving();

		for (Strategy s : movingAverageObjects) {
			for (Stock stock : DataAccess.getStocks()) {

				// get stock objects for the strategy
				if (s.getStockSymbol().equalsIgnoreCase(stock.getStockSymbol())) {

					// if stock difference in moving average less than 0 buy
					if (stock.getDifferenceInMovingAv() < 0 && DataAccess.amountOfOwnedStock(stock
							.getStockSymbol()) == 0) {

						try {

							OrderResult or = OrderManager.getInstance()
									.buyOrder(
											stock.getStockSymbol(),
											returnAskOrBid(
													stock.getStockSymbol(),
													"buy"), 10);

						} catch (Exception e) {
							Logger log = Logger.getLogger("DATA ACCESS LAYER:");
							log.error("ERROR" + e);
							e.printStackTrace();
						}

						// else id difference in moving averages more than 0
						// sell
					} else if (stock.getDifferenceInMovingAv() > 0) {

						try {

							if (checkStockToSell(stock.getStockSymbol())) {

								OrderResult or = OrderManager
										.getInstance()
										.sellOrder(
												stock.getStockSymbol(),
												returnAskOrBid(
														stock.getStockSymbol(),
														"sell"),
												DataAccess
														.amountOfOwnedStock(stock
																.getStockSymbol()));
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Logger log = Logger.getLogger("DATA ACCESS LAYER:");
							log.error("ERROR" + e);
						}
					}
					// sell
				}
			}
		}

	}

	/**
	 * Method calculates the moving average for strategies and buys difference
	 */
	private static void moivngAverageExponential() {

		// arraylist of moving average strategies from database
		ArrayList<Strategy> movingAverageObjects = DataAccess
				.getActiveStatsMovingExp();

		for (Strategy s : movingAverageObjects) {
			for (Stock stock : DataAccess.getStocks()) {

				// get stock objects for the strategy
				if (s.getStockSymbol().equalsIgnoreCase(stock.getStockSymbol())) {

					// if stock difference in moving average less than 0 
						// and we are flat on (have 0 stocks)
					if (stock.getDifferenceInMovingAv() < 0
							&& DataAccess.amountOfOwnedStock(stock
									.getStockSymbol()) == 0) {

						try {

							OrderResult or = OrderManager.getInstance()
									.buyOrder(
											stock.getStockSymbol(),
											returnAskOrBid(
													stock.getStockSymbol(),
													"buy"),
											stock.getStocksToBuyForExpon());

						} catch (Exception e) {
							Logger log = Logger.getLogger("DATA ACCESS LAYER:");
							log.error("ERROR" + e);
							e.printStackTrace();
						}

						// else id difference in moving averages more than 0
						// sell
					} else if (stock.getDifferenceInMovingAv() > 0) {

						try {

							if (checkStockToSell(stock.getStockSymbol())) {

								OrderResult or = OrderManager
										.getInstance()
										.sellOrder(
												stock.getStockSymbol(),
												returnAskOrBid(
														stock.getStockSymbol(),
														"sell"),
												DataAccess
														.amountOfOwnedStock(stock
																.getStockSymbol()));
							}
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
							Logger log = Logger.getLogger("DATA ACCESS LAYER:");
							log.error("ERROR" + e);
						}
					}
					// sell
				}
			}
		}

	}

	/**
	 * Method deleted the last character in the string
	 * 
	 * @param s
	 * @return
	 */
	public static String removeLastChar(String s) {
		if (s == null || s.length() == 0) {
			return s;
		}
		return s.substring(0, s.length() - 2);
	}

	/**
	 * Returns a list of stocks from yahoo with symbol, ask, bid, change in
	 * percentage
	 * 
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String> returnStockPercent() throws Exception {

		ArrayList<String> temp = new ArrayList<String>();
		StringBuilder url = new StringBuilder(
				"http://finance.yahoo.com/d/quotes.csv?s=");

		for (Stock s : DataAccess.getStocks()) {
			url.append(s.getStockSymbol() + ",");
		}

		url.append("&f=sabp2&e=.csv");

		String theUrl = url.toString();

		URL obj = new URL(theUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			String[] fields = inputLine.split(",");

			temp.add(fields[0] + "," + fields[1] + "," + fields[2] + ","
					+ fields[3]);

		}
		return temp;

	}

	public static double returnAskOrBid(String stock, String cmd)
			throws Exception {
		double temp = 0.0;
		StringBuilder url = new StringBuilder(
				"http://finance.yahoo.com/d/quotes.csv?s=");

		url.append(stock + ",");

		if (cmd.equals("buy")) {
			url.append("&f=a&e=.csv");
		} else if (cmd.equals("sell")) {
			url.append("&f=b&e=.csv");
		}

		String theUrl = url.toString();

		URL obj = new URL(theUrl);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("GET");
		con.setRequestProperty("User-Agent", "Mozilla/5.0");
		int responseCode = con.getResponseCode();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			temp = Double.parseDouble(inputLine);
		}
		return temp;
	}

	/**
	 * Method checks that stocks are owned in a company
	 * 
	 * @param symbol
	 * @return boolean
	 */
	public static boolean checkStockToSell(String symbol) {

		boolean sell = false;

		for (OwnedStock os : DataAccess.getOwnedStocks()) {

			if (os.getStockSymbol().equalsIgnoreCase(symbol)) {

				if (os.getAmount() != 0) {
					sell = true;
				}
			}
		}

		return sell;
	}
}
