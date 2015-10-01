package project.dataObjects;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Testfavourite {

	int favID, userID;

	String symbol;

	Favourite favour;
	
	@Before
	public void setUp() throws Exception {
		
		userID = 1;
		favID = 1;
		symbol = "test";
		
		favour = new Favourite(favID, userID, symbol);
		
	}

	@Test
	public void getterSetterFavID() {
		
		assertEquals(favID, favour.getFavID());
	}
	
	@Test
	public void getterSetterUserID() {
		
		assertEquals(userID, favour.getUserID());
	}
	
	@Test
	public void getterSetterSymbol() {
		
		assertEquals(symbol, favour.getStockSymbol());
	}

}
