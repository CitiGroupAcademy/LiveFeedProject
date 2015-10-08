package project.dataObjects;

import java.sql.Timestamp;


public class Profit {

	public Profit(int profitID, double dailyAmount, double overallAmount, Timestamp timestamp ) {
		this.profitID = profitID;
		this.dailyAmount = dailyAmount;
		this.overallAmount = overallAmount;
		this.timestamp = timestamp;
		
}

private int profitID;
private double dailyAmount;
private double overallAmount;
private Timestamp timestamp;
public int getProfitID() {
	return profitID;
}
public void setProfitID(int profitID) {
	this.profitID = profitID;
}
public double getDailyAmount() {
	return dailyAmount;
}
public void setDailyAmount(double dailyAmount) {
	this.dailyAmount = dailyAmount;
}
public double getOverallAmount() {
	return overallAmount;
}
public void setOverallAmount(double overallAmount) {
	this.overallAmount = overallAmount;
}
public Timestamp getTimestamp() {
	return timestamp;
}
public void setTimestamp(Timestamp timestamp) {
	this.timestamp = timestamp;
}



}