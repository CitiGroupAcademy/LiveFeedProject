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

@Path("/insertStrat")
public class insertStrategy {
	
	@GET
	@Produces("text/plain")
	public void insertStrategy(@QueryParam("sym") String sym, @QueryParam("sta") String sta, @QueryParam("bs") String bs, @QueryParam("type") String type) throws SQLException{
		
		String temp = "";
		Connection cn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/montestdb", "root", "password");
			
		}
		catch(SQLException ex){
			System.out.println("Database Connection Error: " + ex);
			
		}
		catch(ClassNotFoundException ex){
			System.out.println("Class not found: " + ex);
		}
			DataAccess.insertStrategy( 1, sym, type, bs, sta);
			
			
			
	}
}

