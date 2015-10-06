package project.dataObjects;

public class OwnedStock {
	private int ownedID;
	private String stockSymbol;
	private int amount;

	/**
	 * @param ownedID
	 * @param stockSymbol
	 * @param amount
	 */
	public OwnedStock(int ownedID, String stockSymbol, int amount) {
		this.ownedID = ownedID;
		this.stockSymbol = stockSymbol;
		this.amount = amount;
	}

	public int getOwnedID() {
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

	/**
	 * @return the amount
	 */
	public int getAmount() {
		return amount;
	}

	/**
	 * @param amount
	 *            the amount to set
	 */
	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	public String toString(){
		return this.stockSymbol + " " + this.amount;
	}
}
