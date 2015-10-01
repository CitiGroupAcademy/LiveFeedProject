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

}
