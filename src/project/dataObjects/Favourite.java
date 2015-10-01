package project.dataObjects;

public class Favourite 
{
	/**
	 * @param favID
	 * @param userID
	 * @param stockSymbol
	 */
	public Favourite(int favID, int userID, String stockSymbol) {
		this.favID = favID;
		this.userID = userID;
		this.stockSymbol = stockSymbol;
	}
	private int favID;
	private int userID;
	private String stockSymbol;
	
	public int getFavID() 
	{
		return favID;
	}
	public void setFavID(int favID) 
	{
		this.favID = favID;
	}
	public int getUserID() 
	{
		return userID;
	}
	
	public void setUserID(int userID) 
	{
		this.userID = userID;
	}
	public String getStockSymbol() 
	{
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) 
	{
		this.stockSymbol = stockSymbol;
	}
}
