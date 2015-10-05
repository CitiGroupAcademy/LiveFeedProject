package project.dataObjects;

public class Strategy 
{
	private int stratID;
	private int userID;
	private String stockSymbol;
	private String type;
	private String buySell;
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
			String buySell, String active) {
		this.stratID = stratID;
		this.userID = userID;
		this.stockSymbol = stockSymbol;
		this.type = type;
		this.buySell = buySell;
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
	
	public String getActive() 
	{
		return active;
	}
	public void setActive(String active) 
	{
		this.active = active;
	}
	/**
	 * @return the buySell
	 */
	public String getBuySell() {
		return buySell;
	}
	/**
	 * @param buySell the buySell to set
	 */
	public void setBuySell(String buySell) {
		this.buySell = buySell;
	}
	
	public String toString(){
		return this.stratID + " " + this.userID + " " + this.stockSymbol + " " + this.type + " " + this.buySell + " " + this.active;
	}
}
