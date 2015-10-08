package project.search;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.DataAccess;
import project.dataObjects.Strategy;

@Path("stratsearch")
public class strategySearch 
{
	@GET
	@Produces("text/html")
	public String getText() throws SQLException
	{
		String html = "";
		ArrayList<Strategy> strategies = null;
		try 
		{
			strategies = DataAccess.getStrats();
			html += "<table class='standard'><th>Stock Symbol</th><th>Type</th><th>Active</th><th>Edit</th><th>Remove</th>";
			for(Strategy strategy : strategies)
			{
				int id = strategy.getStratID();
				String symbol = strategy.getStockSymbol();
				String type = strategy.getType();
				String active = strategy.getActive();
				html += "<tr><td><a href='graphPage.jsp?sym="+ symbol + "'>" + symbol + "</a></td><td>"+type+"</td><td>"+active+"</td><td><a href='strategyedit.jsp?id="+ id + "&sym=" + symbol + "&type=" + type + "&sta=" + active +"'>edit</a></td><td><a href='search/deletestrategy?id="+ id + "'onclick='delStrat(this.id);'>remove</a></td>";
			}
			html += "</table>";
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		return html;
	}
}