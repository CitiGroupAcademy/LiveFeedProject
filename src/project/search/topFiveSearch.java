package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.Database;

@Path("/topsearch")
public class topFiveSearch 
{
	@GET
	@Produces("text/plain")
	public String getText() throws SQLException
	{
		Connection con = null;
		String html = "";
		try
		{
			con = Database.getConnection();
			Statement st = con.createStatement();
	   
			ResultSet rs = st.executeQuery("SELECT s.stockSymbol, s.stockName FROM stock s JOIN ticker t ON t.stockSymbol = s.stockSymbol ORDER BY t.percentageChange DESC LIMIT 5");
			html += "<table>";
			while(rs.next())
			{
				html += "<tr><td><a href='graphPage.jsp?sym="+ rs.getString("stockSymbol") + "'>" + rs.getString("stockName") + "</a></td></tr>";
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