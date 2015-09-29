package project.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Database 
{

	public static void main(String[] args) throws Exception
	{
		Connection connect = null;
		connect = getConnection();
		try
		{
			   Statement st = connect.createStatement();
			   
			   //st.executeUpdate("DROP TABLE IF EXISTS  stock ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  strategy ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  user ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  favourite ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  ticker ");
			   
			   st.executeUpdate("CREATE TABLE  user ( "
	                     +"userID INTEGER AUTO_INCREMENT NOT NULL, "
	                     +"email NVARCHAR(10), "
	                     +"password NVARCHAR(10), "
	                     +"PRIMARY KEY (userID)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  stock ( "
	                     +"stockSymbol NVARCHAR(10) NOT NULL, "
	                     +"stockName NVARCHAR(10), "
	                     +"PRIMARY KEY (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  strategy ( "
	                     +"stratID INTEGER AUTO_INCREMENT NOT NULL, "
	                     +"userID INTEGER NOT NULL, "
	                     +"stockSymbol NVARCHAR (10) NOT NULL, "
	                     +"type NVARCHAR(10), "
	                     +"buy INTEGER, "
	                     +"sell INTEGER, "
	                     +"active NVARCHAR(10), "
	                     +"PRIMARY KEY (stratID), "
	                     +"FOREIGN KEY (userID) REFERENCES user (userID), "
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  favourite ( "
	                     +"favID INTEGER AUTO_INCREMENT NOT NULL, "
	                     +"userID INTEGER, "
	                     +"stockSymbol NVARCHAR(10), "
	                     +"PRIMARY KEY (favID), "
	                     +"FOREIGN KEY (userID) REFERENCES user (userID), "
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  ticker ( "
					     +"tickerID INTEGER AUTO_INCREMENT NOT NULL, "
	                     +"stockSymbol NVARCHAR(10), "
	                     +"askPrice DECIMAL(5,2), "
	                     +"bidPrice DECIMAL(5,2), "
	                     +"timeStamp TIMESTAMP, "
	                     +"PRIMARY KEY (tickerID), "
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
	                     + ");" )  ;
		}
		catch (SQLException ex) 
		{
			System.out.println("Database error " + ex);
		}
		finally
		{
			if(connect!=null)
			{
				connect.close();
			}
		}
	}
	public static Connection getConnection()
	{
		Connection cn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/livefeedproject", "root", "password");
		}
		catch(SQLException ex)
		{
			System.out.println("Database Connection error " + ex);
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Class not found " + ex);
		}
		return cn;
	}
}
