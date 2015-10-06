package project.dal;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

@Path("/getgraphdata")
public class GetGraphData 
{
	@GET
	@Produces("text/plain")
	public ArrayList<String> getText(@QueryParam("sym") String sym) throws SQLException
	{
		ArrayList<String> changes = DataAccess.returnLast50PercentageChanges(sym);
		return changes;
	}
}
