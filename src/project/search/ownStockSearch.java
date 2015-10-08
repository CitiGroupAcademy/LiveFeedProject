package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import project.dal.DataAccess;
import project.dataObjects.OwnedStock;


@Path("ownsearch")
public class ownStockSearch
{
	@GET
	@Produces("text/html")
	public String getText() throws SQLException
	{
		Connection con = null;
		ResultSet rs = null;
		String html = "";
		ArrayList<OwnedStock> ostocks = null;
		try 
		{
			ostocks = DataAccess.getOwnedStocks();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		html += "<table class='standard'><th>Stock Symbol</th><th>Amount</th>";
		for(OwnedStock ostock : ostocks)
		{
			String symbol = ostock.getStockSymbol();
			int amount = ostock.getAmount();		
			html += "<tr><td><a href='graphPage.jsp?sym="+ symbol + "'>" + symbol + "</a></td><td>"+amount+"</td>";
		}
		html += "</table>";
		return html;
	}
}