package project.dal;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;


public class TestGetQuotes {

	ArrayList<String> stocks;

	@Before
	public void setUp() throws Exception {

		stocks = new ArrayList<String>(GetQuotes.returnStockPercent());

	}

	/**
	 * Tests that the arrayList returned by getQuotes.returnStockPercent() is
	 * not null
	 */
	@Test
	public void testReturnStockPercentNotNull() {

		for (String s : stocks) {

			assertNotNull(s);
		}

	}
	
	@Test
	public void testMovingAverageBuy(){
		DataAccess.insertStock("TEST.L", "Moving Average Test");
		DataAccess.insertStrategy(1, "TEST.L", "movingAvg", "active");
		DataAccess.updateStockChange("TEST.L", "", "", "", "","", 100, 120, 0);
		
		GetQuotes.moivngAverageStrategy();
		
		for(project.dataObjects.Transaction t :DataAccess.getTransaction()){
			
			if(t.getStockSymbol().equalsIgnoreCase("TEST.L")){
				assertEquals(t.getAmount(), 0);
			}
		}
		
		
		removeTestStock("TEST.L");
		
	}
	
	@Test
	public void testMovingAverageSell(){
		DataAccess.insertStock("TEST.L", "Moving Average Test");
		DataAccess.insertStrategy(1, "TEST.L", "movingAvg", "active");
		DataAccess.updateStockChange("TEST.L", "", "", "", "","", 120, 100, 0);
		
		GetQuotes.moivngAverageStrategy();
		
		for(project.dataObjects.Transaction t :DataAccess.getTransaction()){
			
			if(t.getStockSymbol().equalsIgnoreCase("TEST.L")){
				assertEquals(t.getAmount(), -10);
			}
		}
		
		
		removeTestStock("TEST.L");
		
	}
	
	@Test
	public void testMovingAverageExpBuy(){
		DataAccess.insertStock("TEST.L", "Moving Average Test");
		DataAccess.insertStrategy(1, "TEST.L", "movAvgExp", "active");
		DataAccess.updateStockChange("TEST.L", "", "", "", "","", 100, 120, 0);
		
		GetQuotes.moivngAverageStrategy();
		
		for(project.dataObjects.Transaction t :DataAccess.getTransaction()){
			
			if(t.getStockSymbol().equalsIgnoreCase("TEST.L")){
				assertEquals(t.getAmount(), 20);
			}
		}
		
		
		removeTestStock("TEST.L");
		
	}
	
	@Test
	public void testMovingAverageExpSell(){
		DataAccess.insertStock("TEST.L", "Moving Average Test");
		DataAccess.insertStrategy(1, "TEST.L", "movAvgExp", "active");
		DataAccess.updateStockChange("TEST.L", "", "", "", "","", 120, 100, 0);
		
		GetQuotes.moivngAverageStrategy();
		
		for(project.dataObjects.Transaction t :DataAccess.getTransaction()){
			
			if(t.getStockSymbol().equalsIgnoreCase("TEST.L")){
				assertEquals(t.getAmount(), -20);
			}
		}
		
		
		removeTestStock("TEST.L");
		
	}
	
	@Test
	public void testMovingAverageBolBuy(){
		DataAccess.insertStock("TEST.L", "Moving Average Test");
		DataAccess.insertStrategy(1, "TEST.L", "bollinger", "active");
		DataAccess.updateStockChange("TEST.L", "", "", "", "","", 98, 100, 1);
		
		GetQuotes.moivngAverageStrategy();
		
		for(project.dataObjects.Transaction t :DataAccess.getTransaction()){
			
			if(t.getStockSymbol().equalsIgnoreCase("TEST.L")){
				assertEquals(t.getAmount(), 2);
			}
		}
		
		
		removeTestStock("TEST.L");
		
	}
	
	@Test
	public void testMovingAverageBolSell(){
		DataAccess.insertStock("TEST.L", "Moving Average Test");
		DataAccess.insertStrategy(1, "TEST.L", "bollinger", "active");
		DataAccess.updateStockChange("TEST.L", "", "", "", "","", 102, 100, 1);
		
		GetQuotes.moivngAverageStrategy();
		
		for(project.dataObjects.Transaction t :DataAccess.getTransaction()){
			
			if(t.getStockSymbol().equalsIgnoreCase("TEST.L")){
				assertEquals(t.getAmount(), 2);
			}
		}
		
		
		removeTestStock("TEST.L");
		
	}
	
	public static void removeTestStock(String symbol){
		DataAccess.removeFavourite(symbol);
		DataAccess.removeStrategy(symbol);
		DataAccess.removeStock(symbol);
		DataAccess.removeTransaction(symbol);
		DataAccess.removeOwnedStock(symbol);
	}

}
