package project.dal;

import project.dal.OrderManager.OrderResult;

public class UseQuickfix 
{

	public static void buyStock(String symbol, double price, int amount) 
	{
		OrderResult or = OrderManager.getInstance().buyOrder("AAPL", 14.5, 50);
		System.out.println(or.price);
	}
	
	public static void sellStock(String symbol, double price, int amount) 
	{
		OrderResult or = OrderManager.getInstance().sellOrder("AAPL", 14.5, 50);
		System.out.println(or.price);
	}

}
