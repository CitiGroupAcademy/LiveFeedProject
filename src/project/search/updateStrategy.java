package project.search;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import project.dal.DataAccess;
import project.dal.Database;

@Path("/updateStrat")
public class updateStrategy {
	
	@GET
	@Produces("text/plain")
	public void updateStrategy(@QueryParam("sym") String sym, @QueryParam("sta") String sta, @QueryParam("bs") String bs, @QueryParam("type") String type, @QueryParam("id") int id) throws SQLException{
		
		String temp = "";
		
		DataAccess.updateStrategy(1, sym, type, bs, sta, id);
			
			
			
	}
}

