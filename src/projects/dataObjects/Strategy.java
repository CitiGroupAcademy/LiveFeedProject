package projects.dataObjects;

public class Strategy 
{
	private int stratID;
	private int userID;
	private String stockSymbol;
	private String type;
	private int buy;
	private int sell;
	private boolean active;
	
	public int getStratID() 
	{
		return stratID;
	}
	public void setStratID(int stratID) 
	{
		this.stratID = stratID;
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
	public String getType() 
	{
		return type;
	}
	public void setType(String type) 
	{
		this.type = type;
	}
	public int getBuy() 
	{
		return buy;
	}
	public void setBuy(int buy) 
	{
		this.buy = buy;
	}
	public int getSell() 
	{
		return sell;
	}
	public void setSell(int sell) 
	{
		this.sell = sell;
	}
	public boolean isActive() 
	{
		return active;
	}
	public void setActive(boolean active) 
	{
		this.active = active;
	}
}
