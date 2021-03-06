package project.dataObjects;

import java.util.Date;

public class Ticker 
{
	private int tickerID;
	private String stockSymbol;
	private double bidPrice;
	private double askPrice;
	private Date timeStamp;
	
	public int getTickerID() 
	{
		return tickerID;
	}
	
	/**
	 * @param tickerID
	 * @param stockSymbol
	 * @param bidPrice
	 * @param askPrice
	 * @param timeStamp
	 */
	public Ticker(int tickerID, String stockSymbol, double bidPrice,
			double askPrice, Date timeStamp) {
		this.tickerID = tickerID;
		this.stockSymbol = stockSymbol;
		this.bidPrice = bidPrice;
		this.askPrice = askPrice;
		this.timeStamp = timeStamp;
	}
	
	public void setTickerID(int tickerID) 
	{
		this.tickerID = tickerID;
	}
	public String getStockSymbol() 
	{
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) 
	{
		this.stockSymbol = stockSymbol;
	}
	public double getBidPrice() 
	{
		return bidPrice;
	}
	public void setBidPrice(double bidPrice) 
	{
		this.bidPrice = bidPrice;
	}
	public double getAskPrice() 
	{
		return askPrice;
	}
	public void setAskPrice(double askPrice) 
	{
		this.askPrice = askPrice;
	}
	public Date getTimeStamp() 
	{
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) 
	{
		this.timeStamp = timeStamp;
	}
}
