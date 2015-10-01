package project.dataObjects;

public class OwnedStock 
{
	private int ownedID;
	private String stockSymbol;
	
	public int getOwnedID() 
	{
		return ownedID;
	}
	public void setOwnedID(int ownedID) {
		this.ownedID = ownedID;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
}
