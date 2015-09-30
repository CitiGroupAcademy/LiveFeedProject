package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.Database;

@Path("topsearch")
public class topFiveSearch 
{
	@GET
	@Produces("text/html")
	public String getText() throws SQLException
	{
		Connection con = null;
		String html = "";
		try
		{
			con = Database.getConnection();
			Statement st = con.createStatement();
	   
			ResultSet rs = st.executeQuery("SELECT s.stockSymbol, s.stockName, s.percentageChange FROM stock s ORDER BY s.percentageChange DESC LIMIT 5");
			html += "<table><th>Stock Symbol</th><th>Stock Name</th><th>Ask</th><th>Bid</th><th>Percentage Change</th>";
			while(rs.next())
			{
				ResultSet rs2 = st.executeQuery("SELECT t.askPrice, t.bidPrice FROM ticker t WHERE t.stockSymbol = '"+ rs.getString("stockSymbol") +"' ORDER BY t.timeStamp DESC LIMIT 1");
				while(rs2.next())
				{
					html += "<tr><td><a href='graphPage.jsp?sym="+ rs.getString("stockSymbol") + "'>" + rs.getString("stockSymbol") + "</a></td><td>"+rs.getString("stockName")+"</td><td>"+rs2.getString("askPrice")+"</td><td>"+rs2.getString("bidPrice")+"</td><td>"+rs.getString("percentageChange")+"</td></tr>";
			
				}
			}
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