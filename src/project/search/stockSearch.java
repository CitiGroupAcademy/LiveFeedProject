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
			ResultSet rs = st.executeQuery("SELECT stockSymbol, stockName FROM stock WHERE stockName LIKE '" + str + "%' OR stockSymbol LIKE '" + str + "%'");
			ResultSet rs2 = null;
			html += "<table class='standard'><th>Stock Symbol</th><th>Stock Name</th><th></th>";
			while(rs.next())
			{
				stocks.add(rs.getString("stockSymbol")+","+ rs.getString("stockName"));
			}
			for(String stock : stocks)
			{
				String[] fields = stock.split(",");
				rs2 = st.executeQuery("SELECT f.stockSymbol FROM favourite f WHERE f.stockSymbol='"+fields[0]+"'");
				html += "<tr><td><a href='graphPage.jsp?sym="+ fields[0] + "'>" + fields[0] + "</a></td><td>"+fields[1]+"</td>";
				int count = 0;
				while(rs2.next())
				{
					count++;
				}
				if(count==0)
				{
					html+="<td><button id='"+fields[0]+"' style='width:40px; height:40px;' onclick='addFav(this.id);'><img src='Images/star.png'></button></td></tr>";
				}
				else
				{
					html+="<td><button id='"+fields[0]+"' style='width:40px; height:40px;' onclick='delFav(this.id);'><img src='Images/bin.png'></button></td></tr>";
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
