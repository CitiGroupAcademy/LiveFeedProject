package project.dal;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetQuotes {

	public static void main(String[] args) throws Exception {

		// clear data in database
		DataAccess.clearTicker();

		while (true) {

			StringBuilder url = new StringBuilder(
					"http://finance.yahoo.com/d/quotes.csv?s=");

			for (String s : DataAccess.getStocks()){
				url.append(s + ",");
			}
				
			url.append("&f=sab&e=.csv");

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

				DataAccess.insertTicker(fields[0].replaceAll("\"", ""),
						Double.parseDouble(fields[1]),
						Double.parseDouble(fields[2]));

			}

		}

	}

}
