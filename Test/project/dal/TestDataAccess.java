package project.dal;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class TestDataAccess {

	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test that the connection is not null
	 */
	@Test
	public void testConnectionNotNull(){
		assertNotNull(DataAccess.getConnection());
	}

	/**
	 * Test that the ticker table is cleared when clearTicker() method is used
	 */
	@Test
	public void testThatTickerTableCleared(){
		DataAccess.insertTicker("AAPL", 20.20, 20.20,20.20);
		DataAccess.clearTicker();
		assertEquals("[]", DataAccess.getTicker().toString());
	}
	
	/**
	 * Test that ticker method is inserting tuples to ticker table
	 */
	@Test
	public void testTickerMethod(){
		DataAccess.clearTicker();
		DataAccess.insertTicker("AAPL", 20.20, 20.20,20.20);
		assertNotNull(DataAccess.getTicker());
		DataAccess.clearTicker();
	}
	
	/**
	 * Tests that there are tuples existing in the stock table
	 */
	@Test
	public void testStockTableContainsTuples(){
	
		assertNotNull(DataAccess.getStocks());
		
	}
}
