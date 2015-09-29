package project.dataObjects;

public class Stock 
{
	private String stockSymbol;
	private String stockName;
	
	public String getStockSymbol() 
	{
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) 
	{
		this.stockSymbol = stockSymbol;
	}
	public String getStockName() 
	{
		return stockName;
	}
	
	/**
	 * @param stockSymbol
	 * @param stockName
	 */
	public Stock(String stockSymbol, String stockName) {
		this.stockSymbol = stockSymbol;
		this.stockName = stockName;
	}
	public void setStockName(String stockName) 
	{
		this.stockName = stockName;
	}
}
