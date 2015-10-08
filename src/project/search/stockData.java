package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import project.dal.Database;

@Path("/stockdata")
public class stockData 
{
	@GET
	@Produces("text/html")
	public String getText(@QueryParam("sym") String sym) throws SQLException
	{
		Connection con = null;
		String html = "";
		ResultSet rs = null;
		
		double open = 0.0;
		double close = 0.0;
		double high = 0.0;
		double low = 0.0;
		double ask = 0.0;
		double bid = 0.0;
		try
		{
			con = Database.getConnection();
			Statement st = con.createStatement();
			rs = st.executeQuery("SELECT s.openingPrice, s.closePrice, s.changeYearHigh, s.changeYearLow FROM stock s WHERE s.stockSymbol='"+sym+"'");
			
			while(rs.next())
			{
				open = rs.getDouble(1);
				close = rs.getDouble(2);
				high = rs.getDouble(3);
				low = rs.getDouble(4);
			}
			
			rs = st.executeQuery("SELECT t.askPrice, t.bidPrice FROM ticker t WHERE t.stockSymbol='"+sym+"' ORDER BY t.timeStamp DESC LIMIT 1");
			while(rs.next())
			{
				ask = rs.getDouble(1);
				bid = rs.getDouble(2);
			}
			
			html += "<table class='standard'>";
			html += "<tr><td>Bid Price:</td><td>"+bid+"</td><td>Close Price:</td><td>"+close+"</td></tr>";
			html += "<tr><td>Ask Price:</td><td>"+ask+"</td><td>Change Year High:</td><td>"+high+"</td></tr>";
			html += "<tr><td>Open Price:</td><td>"+open+"</td><td>Change Year Low:</td><td>"+low+"</td></tr>";
			html += "</table>";
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
