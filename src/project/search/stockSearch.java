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
	   
			ResultSet rs = st.executeQuery("SELECT stockSymbol, stockName FROM stock WHERE stockName LIKE '" + str + "%'");
			html += "<table class='standard'><th>Stock Symbol</th><th>Stock Name</th>";
			int count = 0;
			while(rs.next())
			{
				count++;
			}
			String[][] stocks = new String[count][2];
			while(rs.next())
			{
				stocks[count][0] = rs.getString("stockSymbol");
				stocks[count][1] = rs.getString("stockName");
			}
			for(int r = 0; r<count; r++)
			{
				html += "<tr><td><a href='graphPage.jsp?sym="+ stocks[r][0] + "'>" + stocks[r][0] + "</a></td><td>"+stocks[r][1]+"</td></tr>";
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
