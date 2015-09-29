package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.Database;

@Path("/favsearch")
public class favouritesSearch 
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
	   
			ResultSet rs = st.executeQuery("SELECT f.stockSymbol, s.stockName, t.askPrice, t.bidPrice, s.percentageChange FROM favourite f JOIN stock s ON s.stockSymbol = f.stockSymbol JOIN ticker t ON t.stockSymbol = s.stockSymbol");
			html += "<table><th>Stock Symbol</th><th>Stock Name</th><th>Ask</th><th>Bid</th><th>Percentage Change</th>";
			while(rs.next())
			{
				html += "<tr><td><a href='graphPage.jsp?sym="+ rs.getString("stockSymbol") + "'>" + rs.getString("stockSymbol") + "</a></td><td>"+rs.getString("stockName")+"</td><td>"+rs.getString("askPrice")+"</td><td>"+rs.getString("bidPrice")+"</td><td>"+rs.getString("percentageChange")+"</td></tr>";			
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