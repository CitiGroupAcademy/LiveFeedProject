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
			   
			   st.executeUpdate("CREATE TABLE  stock ( "
	                     +"stockSymbol TEXT,"
	                     +"stockName TEXT"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  strategies ( "
	                     +"stratID INT,"
	                     +"userID INT,"
	                     +"stockSymbol TEXT,"
	                     +"type TEXT,"
	                     +"buy INT,"
	                     +"sell INT,"
	                     +"active BYTE"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  user ( "
	                     +"userID INT,"
	                     +"email TEXT,"
	                     +"password TEXT"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  favourites ( "
	                     +"favID INT,"
	                     +"userID INT,"
	                     +"stockSymbol TEXT"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  ticker ( "
					     +"tickerID INT,"
	                     +"stockSymbol TEXT,"
	                     +"askPrice TEXT,"
	                     +"bidPrice TEXT"
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
