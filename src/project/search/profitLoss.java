package project.search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
