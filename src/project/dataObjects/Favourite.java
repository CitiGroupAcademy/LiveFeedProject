package project.dataObjects;

public class Favourite 
{
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
