package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import project.dal.Database;

@Path("/pricesearch")
public class stockPriceSearch 
{
	@GET
	@Produces("text/html")
	public String getText(@QueryParam("sym") String sym) throws SQLException
	{
		Connection con = null;
		String html = "";
		ResultSet rs = null;
		try
		{
			con = Database.getConnection();
			Statement st = con.createStatement();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			//get current date time with Date()
			Date date = new Date();
			String dateStr = dateFormat.format(date);
			   
			int hour = Calendar.HOUR;
			html += "<table><th>TIME</th>";
			for(int hourCount = 4; hourCount<hour; hourCount++)
			{
				html += "<th>"+hourCount+":00</th>";
			}
			html += "<tr><td>Ask Price</td>";
			for(int hourCount = 4; hourCount<hour; hourCount++)
			{
				rs = st.executeQuery("SELECT askPrice FROM ticker WHERE stockSymbol = '"+sym+"' AND timeStamp LIKE '"+dateStr+" "+hourCount+"%' ORDER BY timestamp DESC LIMIT 1");
				html += "<td>"+rs.getString("askPrice")+"<td>";
			}
			html += "</tr><tr><td>Bid Price</td>";
			for(int hourCount = 4; hourCount<hour; hourCount++)
			{
				rs = st.executeQuery("SELECT bidPrice FROM ticker WHERE stockSymbol = '"+sym+"' AND timeStamp LIKE '"+dateStr+" "+hourCount+"%' ORDER BY timeStamp DESC LIMIT 1");
				html += "<td>"+rs.getString("bidPrice")+"<td>";
			}
			html += "</tr></table>";
		}
		catch (SQLException ex) 
		{
			System.out.println("Database error " + ex);
		}
		finally
		{
			if(con!=null)
			{
				con.close();
			}
		}
		return html;
	}
}
