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
import project.txtMessage.TwilioDriver;

@Path("/insertStrat")
public class insertStrategy {
	
	@POST
	@Produces("text/plain")
	public void insertStrategy(@QueryParam("sym") String sym, @QueryParam("sta") String sta, @QueryParam("bs") String bs, @QueryParam("type") String type) throws SQLException{
		
		String temp = "";
		Connection cn = null;
		cn = DataAccess.getConnection();
		DataAccess.insertStrategy( 1, sym, type, bs, sta);
		TwilioDriver.twilioSendMessage(String.format("\nStrategy created!\n Symbol: %s,\n Type: %s,\n Buy/Sell: %s,\n Status: %s", sym.toUpperCase(), type.toUpperCase(), bs.toUpperCase(), sta.toUpperCase()));
	}
}

