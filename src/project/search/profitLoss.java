package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import project.dal.DataAccess;
import project.dal.Database;

public class profitLoss 
{
	public String getText() throws SQLException
	{
		Connection con = null;
		String sumStr = "£";
		double sum = 0;
		int count = 0;
		try
		{
			con = Database.getConnection();
			Statement st = con.createStatement();
	   
			ResultSet rs = st.executeQuery("SELECT o.stockSymbol, o.buyPrice, o.amount FROM ownedStock");
			
			while(rs.next())
			{
				count++;
			}
			
			String[][] stocks = new String[count][3];
			rs = st.executeQuery("SELECT o.stockSymbol, o.buyPrice, o.amount FROM ownedStock");
			
			while(rs.next())
			{
				stocks[count][0] = rs.getString("stockSymbol");
				stocks[count][1] = rs.getString("buyPrice");
				stocks[count][2] = rs.getString("amount");
			}
			for(int r = 0; r<count; r++)
			{
				rs = st.executeQuery("SELECT t.bidPrice FROM ticker t WHERE t.stockSymbol = '"+ stocks[r][0] +"' ORDER BY t.timeStamp DESC LIMIT 1");
				while(rs.next())
				{
					sum += (rs.getDouble("bidPrice")*Double.parseDouble(stocks[r][2]))-(Double.parseDouble(stocks[r][1])*Double.parseDouble(stocks[r][2]));
				}
			}
			
			SimpleDateFormat dt = new SimpleDateFormat("yyyy-mm-dd"); 
			Date date = new Date(); 
			String currDate = dt.format(date);
			rs = st.executeQuery("SELECT p.profitID, p.dailyAmount, p.overallAmount, p.timestamp FROM profit p ORDER BY p.profitID DESC LIMIT 1");
			String timeStamp = dt.format(rs.getString("timeStamp"));
			if(currDate.equals(timeStamp))
			{
				DataAccess.insertProfit(Integer.parseInt(rs.getString("profitID")), Double.parseDouble(rs.getString("dailyAmount"))+sum, Double.parseDouble(rs.getString("overallAmount"))+sum);
			}
			else
			{
				DataAccess.insertProfit(Integer.parseInt(rs.getString("profitID")), sum, Double.parseDouble(rs.getString("dailyAmount"))+sum, currDate);
			}
			
			sumStr += sum;
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
		return sumStr;
	}
}
