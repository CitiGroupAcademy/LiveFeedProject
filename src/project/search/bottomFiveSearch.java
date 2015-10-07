package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import project.dal.Database;
import project.dal.GetQuotes;

@Path("/bottomsearch")
public class bottomFiveSearch 
{
	@GET
	@Produces("text/html")
	public String getText() throws SQLException
	{
		Connection con = null;
		TreeMap<Double, String> tmap = new TreeMap<>();
		String html = "";
		ArrayList<String> quotes = null;
		try 
		{
			quotes = GetQuotes.returnStockPercent();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		html += "<table class='standard'><th>Stock Symbol</th><th>Ask</th><th>Bid</th><th>Percentage Change</th><th></th>";
		for(String quote : quotes)
		{
			String[] fields = quote.split(",");
			fields[0] = fields[0].replaceAll("\"", "");
			fields[3] = fields[3].replaceAll("\"", "");
			fields[3] = fields[3].replaceAll("%", "");
			tmap.put(Double.parseDouble(fields[3]),fields[0]+","+fields[1]+","+fields[2]);
		}
		Set set = tmap.entrySet();
		  // Get an iterator
		Iterator i = set.iterator();
		for(int r = 0; r<5; r++)
		{
			Map.Entry me = (Map.Entry)i.next();
			String[] stockData = ((String) me.getValue()).split(",");
			String colour = "";
			String key = me.getKey().toString();
			
			if(key.startsWith("-"))
			{
				key = key.replaceAll("\\-", "");
				colour = "red";
			}
			else
			{
				colour = "green";
			}
			
			html += "<tr><td><a href='graphPage.jsp?sym="+ stockData[0] + "'>" + stockData[0] + "</a></td><td>"+stockData[1]+"</td><td>"+stockData[2]+"</td><td style='color:"+colour+"'>"+key+"</td>";
			
			try
			{
				con = Database.getConnection();
				Statement st = con.createStatement();
		   
				ResultSet rs = st.executeQuery("SELECT f.stockSymbol FROM favourite f WHERE f.stockSymbol='"+stockData[0]+"'");
				int count = 0;
				while(rs.next())
				{
					count++;
				}
				if(count==0)
				{
					html+="<td><button id='"+stockData[0]+"' style='width:40px; height:40px;' onclick='addFav(this.id)'><img src='Images/star.png'></button></td></tr>";
				}
				else
				{
					html+="<td><button id='"+stockData[0]+"' style='width:40px; height:40px;' onclick='delFav(this.id)'><img src='Images/bin.png'></button></td></tr>";
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
		}
		html += "</table>";
		return html;
	}
}
