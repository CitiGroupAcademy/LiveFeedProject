package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.DataAccess;
import project.dataObjects.Transaction;


@Path("transsearch")
public class transactionSearch
{
	@GET
	@Produces("text/html")
	public String getText() throws SQLException
	{
		String html = "";
		ArrayList<Transaction> trans = null;
		try 
		{
			trans = DataAccess.getTransaction();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		html += "<table class='standard' class='tableSection'><th>TransID</th><th>Stock Symbol</th><th>Amount</th><th>Price</th><th>Total</th><th>Action</th><th>Status</th><th>TimeStamp</th>";
		for(Transaction t : trans)
		{
			int id = t.getTransID();
			String symbol = t.getStockSymbol();
			int amount = t.getAmount();
			double price = t.getPrice();
			String action = t.getAction();
			String status = t.getStatus();
			Timestamp timestamp = t.getTimestamp();
			html += "<tr><td>"+id+"</td><td><a href='graphPage.jsp?sym="+ symbol + "'>" + symbol + "</a></td><td>"+amount+"</td><td>"+price+"</td><td>"+(price*amount)+"</td><td>"+action+"</td><td>"+status+"</td><td>"+timestamp+"</td>";
		}
		html += "</table>";
		return html;
	}
}