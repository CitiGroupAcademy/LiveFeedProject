package project.dal;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Database 
{

	public static void main(String[] args) throws Exception
	{
		Connection connect = null;
		Scanner sc = new Scanner(System.in);
		connect = getConnection();
		try
		{
			   Statement st = connect.createStatement();
			   
			   //st.executeUpdate("DROP TABLE IF EXISTS  stock ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  strategies ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  user ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  favourites ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  ticker ");
			   
			   st.executeUpdate("CREATE TABLE  users ( "
	                     +"userID INTEGER NOT NULL,"
	                     +"email TEXT,"
	                     +"password TEXT"
	                     +"PRIMARY KEY (userID)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  stocks ( "
	                     +"stockSymbol TEXT NOT NULL,"
	                     +"stockName TEXT,"
	                     +"PRIMARY KEY (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  strategies ( "
	                     +"stratID INTEGER NOT NULL,"
	                     +"userID INTEGER NOT NULL,"
	                     +"stockSymbol TEXT NOT NULL,"
	                     +"type TEXT,"
	                     +"buy INTEGER,"
	                     +"sell INTEGER,"
	                     +"active TEXT,"
	                     +"PRIMARY KEY (stratID),"
	                     +"FOREIGN KEY (userID) REFERENCES users (userID)"
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stocks (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  favourites ( "
	                     +"favID INTEGER NOT NULL,"
	                     +"userID INTEGER,"
	                     +"stockSymbol TEXT"
	                     +"PRIMARY KEY (favID)"
	                     +"FOREIGN KEY (userID) REFERENCES users (userID)"
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stocks (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  ticker ( "
					     +"tickerID INTEGER,"
	                     +"stockSymbol TEXT,"
	                     +"askPrice TEXT,"
	                     +"bidPrice TEXT"
	                     +"PRIMARY KEY (tickerID)"
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stocks (stockSymbol)"
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
			cn = DriverManager.getConnection("jdbc:mysql://localhost/montestdb", "root", "password");
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
