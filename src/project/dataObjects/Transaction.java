package project.dataObjects;

import java.sql.Timestamp;

public class Transaction {

	public Transaction(int transID, String stockSymbol, int amount, double price, String action, String status, Timestamp timestamp ) {
		this.transID = transID;
		this.stockSymbol = stockSymbol;
		this.amount = amount;
		this.price = price;
		this.action = action;
		this.status = status;
		this.timestamp = timestamp;
		
	
	}
	private int transID;
	private String stockSymbol;
	private int amount;
	private double price;
	private String action;
	private String status;
	private Timestamp timestamp;
	
	public int getTransID() {
		return transID;
	}
	public void setTransID(int transID) {
		this.transID = transID;
	}
	public String getStockSymbol() {
		return stockSymbol;
	}
	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	


}
