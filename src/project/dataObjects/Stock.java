package project.dataObjects;

public class Stock {
	private String stockSymbol;
	private String stockName;
	private String percentageChange;
	private String openingPrice;
	private String closePrice;
	private String changeYearHigh;
	private String changeYearLow;
	private double shortMovingAverage;
	private double longMovingAverage;
	private double differenceInMovingAv;

	/**
	 * @param stockSymbol
	 * @param stockName
	 * @param percentageChange
	 * @param openingPrice
	 * @param closePrice
	 * @param changeYearHigh
	 * @param changeYearLow
	 * @param shortMovingAverage
	 * @param longMovingAverage
	 */
	public Stock(String stockSymbol, String stockName, String percentageChange,
			String openingPrice, String closePrice, String changeYearHigh,
			String changeYearLow, double shortMovingAverage,
			double longMovingAverage) {
		this.stockSymbol = stockSymbol;
		this.stockName = stockName;
		this.percentageChange = percentageChange;
		this.openingPrice = openingPrice;
		this.closePrice = closePrice;
		this.changeYearHigh = changeYearHigh;
		this.changeYearLow = changeYearLow;
		this.shortMovingAverage = shortMovingAverage;
		this.longMovingAverage = longMovingAverage;
	}

	public String getStockSymbol() {
		return stockSymbol;
	}

	public void setStockSymbol(String stockSymbol) {
		this.stockSymbol = stockSymbol;
	}

	public String getPercentageChange() {
		return percentageChange;
	}

	public void setPercentageChange(String percentageChange) {
		this.percentageChange = percentageChange;
	}

	public String getOpeningPrice() {
		return openingPrice;
	}

	public void setOpeningPrice(String openingPrice) {
		this.openingPrice = openingPrice;
	}

	public String getClosePrice() {
		return closePrice;
	}

	public void setClosePrice(String closePrice) {
		this.closePrice = closePrice;
	}

	public String getChangeYearHigh() {
		return changeYearHigh;
	}

	public void setChangeYearHigh(String changeYearHigh) {
		this.changeYearHigh = changeYearHigh;
	}

	public String getChangeYearLow() {
		return changeYearLow;
	}

	public void setChangeYearLow(String changeYearLow) {
		this.changeYearLow = changeYearLow;
	}

	public double getShortMovingAverage() {
		return shortMovingAverage;
	}

	public void setShortMovingAverage(double shortMovingAverage) {
		this.shortMovingAverage = shortMovingAverage;
	}

	public double getLongMovingAverage() {
		return longMovingAverage;
	}

	public void setLongMovingAverage(double longMovingAverage) {
		this.longMovingAverage = longMovingAverage;
	}

	public String getStockName() {
		return stockName;
	}

	public void setStockName(String stockName) {
		this.stockName = stockName;
	}
	
	public  String toString(){
		return String.format(" %s %s %s %s %s %s %s %.4f %.4f ", this.stockSymbol, this.stockName, this.percentageChange, this.openingPrice, this.closePrice, this.changeYearLow, this.changeYearHigh, this.shortMovingAverage, this.longMovingAverage );
		
	}

	/**
	 * @return the differenceInMovingAv
	 */
	public double getDifferenceInMovingAv() {
		return this.shortMovingAverage - this.longMovingAverage ;
	}

}
