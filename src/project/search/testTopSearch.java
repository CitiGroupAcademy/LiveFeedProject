package project.search;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.GetQuotes;

@Path("testtopsearch")
public class testTopSearch 
{
	@GET
	@Produces("text/html")
	public String getText() throws SQLException
	{
		TreeMap<Double, String> tmap = new TreeMap<>(Collections.reverseOrder());
		String html = "";
		ArrayList<String> quotes = null;
		try 
		{
			quotes = GetQuotes.returnStockPercent();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		html += "<table class='standard'><th>Stock Symbol</th><th>Ask</th><th>Bid</th><th>Percentage Change</th>";
		for(String quote : quotes)
		{
			String[] fields = quote.split(",");
			fields[3] = fields[3].replaceAll("\"", "");
			fields[0] = fields[0].replaceAll("\"", "");
			fields[3] = fields[3].replaceAll("\\+", "");
			fields[3] = fields[3].replaceAll("\\-", "");
			fields[3] = fields[3].replaceAll("%", "");
			tmap.put(Double.parseDouble(fields[3]),fields[0]+","+fields[1]+","+fields[2]);
		}
		System.out.println(tmap);
		Set set = tmap.entrySet();
		  // Get an iterator
		Iterator i = set.iterator();
		for(int r = 0; r<5; r++)
		{
			Map.Entry me = (Map.Entry)i.next();
			String[] stockData = ((String) me.getValue()).split(",");
			html += "<tr><td><a href='graphPage.jsp?sym="+ stockData[0] + "'>" + stockData[0] + "</a></td><td>"+stockData[1]+"</td><td>"+stockData[2]+"</td><td>"+me.getKey()+"</td></tr>";
		}
		html += "</table>";
		return html;
	}
}