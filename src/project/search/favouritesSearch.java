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
	   
			ResultSet rs = st.executeQuery("SELECT stockSymbol FROM favourite");
			
			html += "<table class='standard'><th>Stock Symbol</th><th>Stock Name</th><th>Ask</th><th>Bid</th><th>Percentage Change</th>";
			int count = 0;
			while(rs.next())
			{
				count++;
			}
			if(count>0)
			{
				String[][] stocks = new String[count][3];
				rs = st.executeQuery("SELECT f.stockSymbol, s.stockName, s.percentageChange FROM favourite f JOIN stock s ON s.stockSymbol = f.stockSymbol");
				int counter = 0;
				while(rs.next())
				{
					stocks[counter][0] = rs.getString("stockSymbol");
					stocks[counter][1] = rs.getString("stockName");
					stocks[counter][2] = rs.getString("percentageChange");
					counter++;
				}
				for(int r = 0; r<count; r++)
				{
					rs = st.executeQuery("SELECT t.askPrice, t.bidPrice FROM ticker t WHERE t.stockSymbol = '"+ stocks[r][0] +"' ORDER BY t.timeStamp DESC LIMIT 1");
					while(rs.next())
					{
						html += "<tr><td><a href='graphPage.jsp?sym="+ stocks[r][0] + "'>" + stocks[r][0] + "</a></td><td>"+stocks[r][1]+"</td><td>"+rs.getString("askPrice")+"</td><td>"+rs.getString("bidPrice")+"</td><td>"+stocks[r][2]+"</td></tr>";
					}
				}
				html += "</table>";
			}
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