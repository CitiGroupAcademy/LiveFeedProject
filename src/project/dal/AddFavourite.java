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
	public void getText(@QueryParam("sym") String str) throws SQLException
	{
		DataAccess.insertIntoFav(1, str);
	}
}
