package project.dal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jboss.logging.*;

import project.dataObjects.Stock;

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
	public static double totalAsks = 0;
	
	/**
	 *queue collection used to hold the bid averages, polls when there is the number equal to the moving average number
	 */
	public static Queue<Double> bidAverageCollection = new LinkedList<Double>();
	
	/**
	 *queue collection used to hold the ask averages, polls when there is the number equal to the moving average number
	 */
	public static Queue<Double> askAverageCollection = new LinkedList<Double>();


	public static void main(String[] args) throws Exception {
	}
	
	public static void insertIntoTicker() throws Exception{
		// clear data in database
		DataAccess.clearTicker();
		

		while (true) {


			StringBuilder url = new StringBuilder(
					"http://finance.yahoo.com/d/quotes.csv?s=");

			for (Stock s : DataAccess.getStocks()) {
				url.append(s.getStockSymbol() + ",");
			}

			url.append("&f=sabp2opk4j5&e=.csv");

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
				System.out.println(fields[0] + " " + fields[1] + " "
						+ fields[2] + " " + fields[3] + " " + fields[4] + " "
						+ fields[5] + fields[6] + " " + fields[7]);

				DataAccess.insertTicker(fields[0].replaceAll("\"", ""),
						Double.parseDouble(fields[1]),
						Double.parseDouble(fields[2]));

				DataAccess.updateStockChange(fields[0].replaceAll("\"", ""),
						removeLastChar(fields[3]).replaceAll("\"", ""),
						fields[4], fields[5], fields[6], fields[7]);
				
		

			}

		}
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
	 * Calculates the moving average with a queue collection
	 * @param nextNumber
	 * @return
	 */
	public static double calculateAskMovingAverage(double nextNumber) {

		askAverageCollection.add(nextNumber);

		if (askAverageCollection.size() > movingAverageNo) {
			totalAsks = 0;
		
			askAverageCollection.poll();

			for (double element : askAverageCollection) {
				totalAsks += element;
			}
			
		}

		return totalAsks / movingAverageNo;
	}

	/**
	 * Calculates the moving average with a queue collection
	 * @param nextNumber
	 * @return
	 */
	public static double calculateBidMovingAverage (double nextNumber){

		bidAverageCollection.add(nextNumber);
		
		if (bidAverageCollection.size() > movingAverageNo) {
			totalBids = 0;
			bidAverageCollection.poll();

			for (double element : bidAverageCollection) {
				totalBids += element;
			}
			
		
		}

		return totalBids / movingAverageNo;
	}

}
