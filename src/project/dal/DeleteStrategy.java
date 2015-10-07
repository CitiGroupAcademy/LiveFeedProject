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

@Path("/deletestrategy")
public class DeleteStrategy
{
	@GET
	@Produces("text/plain")
	public void getText(@QueryParam("id") int str) throws SQLException
	{
		Connection con = null;
		try
		{	
			
			con = Database.getConnection();
			PreparedStatement st = con.prepareStatement("DELETE FROM strategy WHERE stratID = ?");
			st.setInt(1, str);
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
	 }
}
