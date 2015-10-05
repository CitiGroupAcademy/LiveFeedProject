package project.dal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jboss.logging.*;

import com.sun.xml.internal.ws.spi.db.FieldSetter;

import project.dataObjects.Stock;
import project.dataObjects.Strategy;

/**
 * Quote class accesses Yahoo market data and saves to database ticker
 * 
 * @author Citi 2015
 *
 */
public class GetQuotes {
	
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
	 *Bid moving average
	 */
	public static double bidMovingAverage = 0;

	/**
	 *ask moving average
	 */
	public static double askMovingAverage = 0;
	
	/**
	 *total bids
	 */
	public static double totalBids = 0;
	
	/**
	 *total asks
	 */
	public static double totalForMovingAverage = 0;
	
	/**
	 * Start time for moving average calculation
	 */
	public static LocalDateTime startTime;
	
	/**
	 * Incrementing time for moving average calculation
	 */
	public static LocalDateTime currentTime;
	
	
	/**
	 *queue collection used to hold the bid averages, polls when there is the number equal to the moving average number
	 */
	public static Queue<Double> shortMovingAverageCollection = null;
	
	public static HashMap<String, Double> stocksMap = new HashMap<>();
	

	public static void main(String[] args) throws Exception {
		insertIntoTicker();
	}
	
	/**
	 * Runs indefinitely 
	 * @throws Exception
	 */
	public static void insertIntoTicker() throws Exception{
		
		// clear data in database
		DataAccess.clearTicker();
		
		startTime = LocalDateTime.now();

		while (true) {

			currentTime = LocalDateTime.now();

			StringBuilder url = new StringBuilder(
					"http://finance.yahoo.com/d/quotes.csv?s=");

			for (Stock s : DataAccess.getStocks()) {
				url.append(s.getStockSymbol() + ",");
				stocksMap.put(s.getStockSymbol(), .0);
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
				for(String key : stocksMap.keySet()){
					if(key.equalsIgnoreCase(fields[0])){
						stocksMap.put(key, calculateAskMovingAverage((Double.parseDouble(fields[2]) + Double.parseDouble(fields[3]))/2));
					}
				}
				
				System.out.println(fields[0] + " " + fields[1] + " "
						+ fields[2] + " " + fields[3] + " " + fields[4] + " "
						+ fields[5] + fields[6] + " " + fields[7] + "   Fifty day:" + fields[8] + "  Short:"+ DataAccess.calculateMovingAverage(fields[0]));

				DataAccess.insertTicker(fields[0].replaceAll("\"", ""),
						Double.parseDouble(fields[1]),
						Double.parseDouble(fields[2]),
						Double.parseDouble(removeLastChar(fields[9]).replaceAll("\"", "")));

				fields[3] = removeLastChar(fields[3]).replaceAll("\"", "");
				
				DataAccess.updateStockChange(fields[0].replaceAll("\"", ""),
						fields[3],
						fields[4], fields[5], fields[6], fields[7], Double.parseDouble(fields[8]), Double.parseDouble(DataAccess.calculateMovingAverage(fields[0])));
				
				moivngAverageStrategy();
				
			}
				//Sleep thread to reduce processing
				Thread.sleep(1000);
		}
	}
	
	/**
	 * Method calculates the moving average for strategies 
	 */
	private static void moivngAverageStrategy() {
		
		ArrayList <Strategy> movingAverageObjects = DataAccess.getActiveStatsMoving();
		
		for(Strategy s : movingAverageObjects){
			
			for(Stock stock : DataAccess.getStocks()){
				
				if(s.getStockSymbol().equalsIgnoreCase(stock.getStockSymbol())){
					
					
					if(stock.getDifferenceInMovingAv() < 0){
						
						// buy
					}else if(stock.getDifferenceInMovingAv() > 0){
						// sell
					}
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
	 * Returns a list of stocks from yahoo with symbol, ask, bid, change in percentage
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
			
			temp.add(fields[0] + "," + fields[1] + "," +fields[2]+ "," + fields[3]);
			
	

		}
		return temp;

	}

	
	/**
	 * Calculates the moving average with a queue collection
	 * @param nextNumber
	 * @return
	 */
	public static double calculateAskMovingAverage(double nextNumber) {

		shortMovingAverageCollection.add(nextNumber);

		if (currentTime.isAfter(startTime.plusMinutes(1))) {
			totalForMovingAverage = 0;
		
			shortMovingAverageCollection.poll();

			for (double element : shortMovingAverageCollection) {
				totalForMovingAverage += element;
			}
			
		}

		return totalForMovingAverage /shortMovingAverageCollection.size();
	}


}
