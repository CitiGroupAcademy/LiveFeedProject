package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import project.dal.Database;

@Path("/stocksearch")
public class stockSearch 
{
	@GET
	@Produces("text/plain")
	public String getText(@QueryParam("str") String str) throws SQLException
	{
		Connection con = null;
		String html = "";
		try
		{
			con = Database.getConnection();
			Statement st = con.createStatement();
			ArrayList<String> stocks = new ArrayList<>();
			ResultSet rs = st.executeQuery("SELECT stockSymbol, stockName FROM stock WHERE stockName LIKE '" + str + "%'");
			html += "<table class='standard'><th>Stock Symbol</th><th>Stock Name</th>";
			while(rs.next())
			{
				stocks.add(rs.getString("stockSymbol")+","+ rs.getString("stockName"));
			}
			for(String stock : stocks)
			{
				String[] fields = stock.split(",");
				html += "<tr><td><a href='graphPage.jsp?sym="+ fields[0] + "'>" + fields[0] + "</a></td><td>"+fields[1]+"</td></tr>";
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
