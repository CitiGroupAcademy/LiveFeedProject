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
		connect = getConnection("");
		try
		{
			
			   Statement st = connect.createStatement();
			   st.executeUpdate("DROP DATABASE livefeedproject");
			   st.executeUpdate("CREATE DATABASE livefeedproject");
				connect = getConnection("livefeedproject");
			   st.executeUpdate("DROP TABLE IF EXISTS  stock ");
			   st.executeUpdate("DROP TABLE IF EXISTS  strategy ");
			   st.executeUpdate("DROP TABLE IF EXISTS  user ");
			   st.executeUpdate("DROP TABLE IF EXISTS  favourite ");
			   st.executeUpdate("DROP TABLE IF EXISTS  ticker ");
			   
<<<<<<< HEAD
			   st.executeUpdate("CREATE TABLE  user ( "
	                     +"userID INTEGER AUTO_INCREMENT NOT NULL, "
	                     +"email NVARCHAR (20), "
	                     +"password NVARCHAR (20), "
	                     +"PRIMARY KEY (userID) "
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  stock ( "
	                     +"stockSymbol NVARCHAR(10) NOT NULL, "
	                     +"stockName NVARCHAR (10), "
	                     +"PRIMARY KEY (stockSymbol) "
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  strategy ( "
	                     +"stratID INTEGER AUTO_INCREMENT NOT NULL, "
	                     +"userID INTEGER NOT NULL, "
	                     +"stockSymbol NVARCHAR (10)NOT NULL, "
	                     +"type NVARCHAR(10), "
	                     +"buy INTEGER, "
	                     +"sell INTEGER, "
	                     +"active NVARCHAR(10), "
	                     +"PRIMARY KEY (stratID), "
	                     +"FOREIGN KEY (userID) REFERENCES user (userID), "
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol) "
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  favourite ( "
	                     +"favID INTEGER AUTO_INCREMENT NOT NULL, "
	                     +"userID INTEGER, "
	                     +"stockSymbol NVARCHAR (10), "
	                     +"PRIMARY KEY (favID), "
	                     +"FOREIGN KEY (userID) REFERENCES user (userID), "
=======
			   //st.executeUpdate("DROP TABLE IF EXISTS  stock ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  strategy ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  user ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  favourite ");
			   //st.executeUpdate("DROP TABLE IF EXISTS  ticker ");
			   
			   st.executeUpdate("CREATE TABLE  user ( "
	                     +"userID INTEGER NOT NULL,"
	                     +"email TEXT,"
	                     +"password TEXT"
	                     +"PRIMARY KEY (userID)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  stock ( "
	                     +"stockSymbol TEXT NOT NULL,"
	                     +"stockName TEXT,"
	                     +"PRIMARY KEY (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  strategy ( "
	                     +"stratID INTEGER NOT NULL,"
	                     +"userID INTEGER NOT NULL,"
	                     +"stockSymbol TEXT NOT NULL,"
	                     +"type TEXT,"
	                     +"buy INTEGER,"
	                     +"sell INTEGER,"
	                     +"active TEXT,"
	                     +"PRIMARY KEY (stratID),"
	                     +"FOREIGN KEY (userID) REFERENCES user (userID)"
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  favourite ( "
	                     +"favID INTEGER NOT NULL,"
	                     +"userID INTEGER,"
	                     +"stockSymbol TEXT"
	                     +"PRIMARY KEY (favID)"
	                     +"FOREIGN KEY (userID) REFERENCES user (userID)"
>>>>>>> origin/master
	                     +"FOREIGN KEY (stockSymbol) REFERENCES stock (stockSymbol)"
	                     + ");" )  ;
			   
			   st.executeUpdate("CREATE TABLE  ticker ( "
<<<<<<< HEAD
					     +"tickerID INTEGER AUTO_INCREMENT NOT NULL, "
	                     +"stockSymbol NVARCHAR(10), "
	                     +"askPrice DECIMAL(5,2), "
	                     +"bidPrice DECIMAL(5,2), "
	                     +"timeStamp TIMESTAMP, "
	                     +"PRIMARY KEY (tickerID), "
=======
					     +"tickerID INTEGER,"
	                     +"stockSymbol TEXT,"
	                     +"askPrice TEXT,"
	                     +"bidPrice TEXT"
	                     +"PRIMARY KEY (tickerID)"
>>>>>>> origin/master
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
	public static Connection getConnection(String databaseURL)
	{
		Connection cn = null;
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			cn = DriverManager.getConnection("jdbc:mysql://localhost/"+databaseURL, "root", "password");
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
