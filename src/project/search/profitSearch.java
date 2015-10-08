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
import project.dataObjects.Profit;



@Path("profitSearch")
public class profitSearch
{
	@GET
	@Produces("text/html")
	public String getText() throws SQLException
	{
		String html = "";
		ArrayList<Profit> profit = null;
		try 
		{
			profit = DataAccess.getProfit();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		html += "<table class='standard' class='tableSection'><th>ProfitID</th><th>Daily Amount</th><th>Overall Amount</th><th>TimeStamp</th>";
		for(Profit p : profit)
		{
			int id = p.getProfitID();
			double dailyAmount = p.getDailyAmount();
			double overallAmount = p.getOverallAmount();
			Timestamp timestamp = p.getTimestamp();
			html += "<tr><td>"+id+"</td><td>"+dailyAmount+"</td><td>"+overallAmount+"</td><td>"+timestamp+"</td>";
		}
		html += "</table>";
		return html;
	}
}