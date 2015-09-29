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
			   while(rs.next())
			   {
				   html += "<a href='graphPage.jsp?sym="+ rs.getString("stockSymbol") + "'</a></br>";
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
