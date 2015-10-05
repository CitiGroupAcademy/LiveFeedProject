package project.dataObjects;

public class Strategy 
{
	private int stratID;
	private int userID;
	private String stockSymbol;
	private String type;
	private int buy;
	private int sell;
	private String active;
	
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
	
	/**
	 * @param stratID
	 * @param userID
	 * @param stockSymbol
	 * @param type
	 * @param buy
	 * @param sell
	 * @param active
	 */
	public Strategy(int stratID, int userID, String stockSymbol, String type,
			int buy, int sell, String active) {
		this.stratID = stratID;
		this.userID = userID;
		this.stockSymbol = stockSymbol;
		this.type = type;
		this.buy = buy;
		this.sell = sell;
		this.active = active;
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
	
	public String getActive() 
	{
		return active;
	}
	public void setActive(String active) 
	{
		this.active = active;
	}
}
