package project.dal;

import project.dal.OrderManager.OrderResult;

public class UseQuickfix {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		OrderResult or = OrderManager.getInstance().buyOrder("AAPL", 14.5, 50);
		System.out.println(or.price);
	}

}
