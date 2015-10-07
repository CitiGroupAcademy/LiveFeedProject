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

@Path("/bottomsearch")
public class stockData 
{
	@GET
	@Produces("text/html")
	public String getText(@QueryParam("sym") String sym) throws SQLException
	{
		Connection con = null;
		con = Database.getConnection();
		Statement st = con.createStatement();
		String html = "";
		html += "<table class='standard'>";
		ResultSet rs = st.executeQuery("SELECT s.openingPrice, s.closePrice, s.changeYearHigh, s.changeYearLow FROM stock s WHERE s.stockSymbol='"+sym+"'");
		ResultSet rs2 = st.executeQuery("SELECT t.askPrice, t.bidPrice FROM ticker t WHERE t.stockSymbol='"+sym+"' ORDER BY t.timeStamp DESC LIMIT 1");
		html += "<tr><td>Bid Price</td><td>"+rs2.getDouble(2)+"</td><td>Close Price</td><td>"+rs.getDouble(2)+"</td></tr>";
		html += "<tr><td>Ask Price</td><td>"+rs2.getDouble(1)+"</td><td>Change Year High</td><td>"+rs.getDouble(3)+"</td></tr>";
		html += "<tr><td>Open Price</td><td>"+rs.getDouble(1)+"</td><td>Change Year Low</td><td>"+rs.getDouble(4)+"</td></tr>";
		html += "</table>";
		return html;
	}
}
