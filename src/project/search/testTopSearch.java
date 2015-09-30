package project.search;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.Database;

@Path("testtopsearch")
public class testTopSearch 
{
	public static String symbol = "";
	public static int movingAverageNo = 0;
	public static int captureNumber = 0;
	public static int decrementor = 0;
	public static double bidMovingAverage = 0;
	public static double askMovingAverage = 0;
	public static double totalBids = 0;
	public static double totalAsks = 0;
	
	public static Queue<Double> bidAverageCollection = new LinkedList<Double>();
	public static Queue<Double> askAverageCollection = new LinkedList<Double>();

	public static String main() throws Exception 
	{
		captureNumber = 1;
		decrementor = captureNumber;
		Connection connect = null;
		String html = "";

		while (true) 
		{
			connect = Database.getConnection();
			Statement st = connect.createStatement();
	   
			StringBuilder url = new StringBuilder(
					"http://finance.yahoo.com/d/quotes.csv?s=");
			
			ResultSet rs = st.executeQuery("SELECT s.stockSymbol, s.stockName FROM stock s");
			ArrayList<String> stockSymbols = new ArrayList<>();
			ArrayList<String> stockNames = new ArrayList<>();
			while(rs.next())
			{
				stockSymbols.add(rs.getString("stockSymbol"));
				stockNames.add(rs.getString("stockName"));
			}
			for(String stockSymbol : stockSymbols)
			{
				url.append(stockSymbol + "+");
			}
			// url.deleteCharAt(url.length() - 1);

			// Properties is for bid and ask
			url.append("&f=sab&e=.csv");

			String theUrl = url.toString();

			URL obj = new URL(theUrl);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// This is a GET request
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			html += "<table class='standard'><th>Stock Symbol</th><th>Stock Name</th><th>Ask</th><th>Bid</th>";
			while ((inputLine = in.readLine()) != null && decrementor != 0) 
			{
				String[] fields = inputLine.split(",");

				for(int i=0; i<stockNames.size();i++)
				{
					html += "<tr><td><a href='graphPage.jsp?sym="+ stockSymbols.get(i) + "'>" + stockSymbols.get(i) + "</a></td><td>"+stockNames.get(i)+"</td><td>"+fields[0]+"</td><td>"+fields[1]+"</td></tr>";
				}
				
				decrementor--;

			}

			if (decrementor == 0) {
				break;
			}
		}

		return html;
	}
}