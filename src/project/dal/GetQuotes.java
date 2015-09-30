package project.dal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import project.dataObjects.Stock;

public class GetQuotes {

	public static void main(String[] args) throws Exception {

		// clear data in database
		DataAccess.clearTicker();

		while (true) {

			StringBuilder url = new StringBuilder(
					"http://finance.yahoo.com/d/quotes.csv?s=");

			for (Stock s : DataAccess.getStocks()){
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
				System.out.println(fields[0] + " " + fields[1] + " " + fields[2] + " " + fields[3] + " " + fields[4] + " " + fields[5] + fields[6] + " "+ fields[7]);
				
					DataAccess.insertTicker(fields[0].replaceAll("\"", ""),
							Double.parseDouble(fields[1]),
							Double.parseDouble(fields[2]));
				
					
					if(!fields[3].equals("N/A")){
						
						DataAccess.updateStockChange(fields[0].replaceAll("\"", ""), removeLastChar(fields[3]).replaceAll("\"", ""), fields[4], fields[5], fields[6], fields[7] );
					}

			}

		}
		
	}
	
	/**
	 * Method deleted the last character in the string
	 * @param s
	 * @return
	 */
	public static String removeLastChar(String s) {
	    if (s == null || s.length() == 0) {
	        return s;
	    }
	    return s.substring(0, s.length()-2);
	}
	
}

