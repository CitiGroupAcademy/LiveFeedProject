package project.dataObjects;

import java.util.Date;

public class Ticker 
{
	private int tickerID;
	private String  stockSymbol;
	private double askPrice;
	private double bidPrice;
	private Date timeStamp;
	
	public int getTickerID() 
	{
		return tickerID;
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
	public double getAskPrice() 
	{
		return askPrice;
	}
	public void setAskPrice(double askPrice) 
	{
		this.askPrice = askPrice;
	}
	public double getBidPrice() 
	{
		return bidPrice;
	}
	public void setBidPrice(double bidPrice) 
	{
		this.bidPrice = bidPrice;
	}
	public Date getTimeStamp() {
		return timeStamp;
	}
	public void setTimeStamp(Date timeStamp) 
	{
		this.timeStamp = timeStamp;
	}
}