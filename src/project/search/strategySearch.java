package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.DataAccess;
import project.dal.Database;
import project.dal.GetQuotes;
import project.dataObjects.Strategy;

@Path("stratsearch")
public class strategySearch 
{
	@GET
	@Produces("text/html")
	public String getText() throws SQLException
	{
		Connection con = null;
		ResultSet rs = null;
		String html = "";
		ArrayList<Strategy> strategies = null;
		try 
		{
			strategies = DataAccess.getStrats();
			
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		html += "<table class='standard'><th>Stock Symbol</th><th>Type</th><th>Buy/Sell</th><th>Active</th><th>Edit</th><th>Remove</th>";
		for(Strategy strategy : strategies)
		{
			int id = strategy.getStratID();
			String symbol = strategy.getStockSymbol();
			String type = strategy.getType();
			String buysell = strategy.getBuySell();
			String active = strategy.getActive();
			html += "<tr><td><a href='graphPage.jsp?sym="+ symbol + "'>" + symbol + "</a></td><td>"+type+"</td><td>"+buysell+"</td><td>"+active+"</td><td><a href='stratedit.jsp?id="+ id + "'>edit</a></td><td><a href='stratremove.jsp?id="+ id + "'>remove</a></td>";
		}
		html += "</table>";
		return html;
		
	}
}