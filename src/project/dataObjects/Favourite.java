package project.dataObjects;

public class Favourite 
{
	private int favID;
	private String stockSymbol;
	
	public int getFavID() 
	{
		return favID;
	}
	public void setFavID(int favID) 
	{
		this.favID = favID;
	}

	public Favourite(int favID, int userID, String stockSymbol) {
		this.favID = favID;
		this.stockSymbol = stockSymbol;
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
