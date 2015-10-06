package project.dal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/addFavourite")
public class AddFavourite 
{
	@GET
	@Produces("text/plain")
	public String getText(@QueryParam("sym") String str) throws SQLException
	{
		Connection con = null;
		String html = "";
		try
		{
			con = Database.getConnection();
			PreparedStatement st = con.prepareStatement("INSERT INTO favourite(userID, stockSymbol) values(?,?)");
			st.setInt(1, 1);
			st.setString(2, str);
			st.executeUpdate();
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
