package GroceryRegister;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test class for the register logic
 * 
 * @author Brian Fitzpatrick
 * @version 1.0 12/29/15
 */
public class RegisterTester {
	
	private final String correctInventory = "src/GroceryRegister/groceryInventory/workingTest.txt";
	private final String incorrectInventory = "src/GroceryRegister/groceryInventory/improperFormatTest.txt";
	private final String fakeFile = "src/Grocery/Invetory/fake.txt";
	private final String improperCodeFormat = "src/GroceryRegister/groceryInventory/improperCodeFormat.txt";
	private final String originalTestData = "src/GroceryRegister/groceryInventory/OriginalTestInventory.txt";

	private Double retVal = 0.0;
	
	/**
	 * Test the ability to make a single correct purchase
	 */
	@Test
	public void testSinglePurchase() {
		System.out.println("TEST SINGLE PURCHASE");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M");
		System.out.println(retVal);
		System.out.println("__________________________________");

		assertEquals(retVal,3.76, 0.0);
	}
	
	/**
	 * Tests the ability to make a correct purchase with two items
	 */
	@Test
	public void testDoublePurchase() {
		
		System.out.println("TEST DOUBLE PURCHASE");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M;"
								+ "E5T6-9UZ3-TH15-QR88");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal,12.66, 0.0);
		
	}
	
	/**
	 * Tests the ability to make a correct purchase with three items
	 */
	@Test
	public void testTriplePurchase() {
		System.out.println("TEST TRIPLE PURCHASE");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M;"
				+ "E5T6-9UZ3-TH15-QR88;"
				+ "YRT6-72AS-K736-L4AR");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal,14.43, 0.0);
	}
	
	/**
	 * Tests the ability to make a correct purchase with four items
	 */
	@Test
	public void testQuadruplePurchase() {
		System.out.println("TEST QUADRUPLE PURCHASE");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M;"
				+ "E5T6-9UZ3-TH15-QR88;"
				+ "YRT6-72AS-K736-L4AR;"
				+ "TQ4C-VV6T-75ZX-1RMR");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal,21.80, 0.0);
	}
	
	/**
	 * Tests the ability to make a correct purchase with five items
	 */
	@Test
	public void testQuintuplePurchase() {
		System.out.println("TEST QUINTUPLE PURCHASE");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M;"
				+ "E5T6-9UZ3-TH15-QR88;"
				+ "YRT6-72AS-K736-L4AR;"
				+ "TQ4C-VV6T-75ZX-1RMR;"
				+ "65P1-UDGM-XH2M-LQW2");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal,28.21, 0.0);
	}
	
	/**
	 * Tests behavior when the input string is an empty string
	 */
	@Test
	public void testEmptyCart() {
		System.out.println("TEST EMPTY CART");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 0.0, 0.0);
	}
	
	/**
	 * Tests behavior when an item which is invalid is submitted
	 */
	@Test
	public void testInvalidItem() {
		System.out.println("TEST INVALID ITEM");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("A12T56-4GH7-QPL9-3N432M;");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 0.0, 0.0);
	}
	
	/**
	 * Tests behavior if two items of the same type are in the checkout together
	 */
	@Test
	public void testDuplicateItem() {
		System.out.println("TEST DUPLICATE ITEM");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M;"
							+ "A12T-4GH7-QPL9-3N4M");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 7.53, 0.0);
	}
	
	/**
	 * Tests behavior if an item list ends in a semicolon
	 */
	@Test
	public void testImproperSemicolonPlacement() {
		System.out.println("TEST IMPROPER SEMICOLON PLACEMENT");
		RegisterLogic rL = new RegisterLogic(correctInventory);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M;"
							+ "A12T-4GH7-QPL9-3N4M;");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 7.53, 0.0);
	}
	
	/**
	 * Tests behavior if an improperly formatted file is entered
	 */
	@Test
	public void testImproperLoadingFile() {
		System.out.println("TEST IMPROPER LOADING FILE");
		RegisterLogic rL = new RegisterLogic(incorrectInventory);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 0.0, 0.0);
	}
	
	/**
	 * Tests behavior if a non-existent file is entered
	 */
	@Test
	public void testNonExistentFile() {
		System.out.println("TEST NON-EXISTENT FILE");
		RegisterLogic rL = new RegisterLogic(fakeFile);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 0.0, 0.0);
	}
	
	/**
	 * Tests behavior of a file with an improper format for a code
	 */
	@Test
	public void testImproperCodeFormat() {
		System.out.println("TEST Improper Code Format");
		RegisterLogic rL = new RegisterLogic(improperCodeFormat);
		retVal = rL.checkout("A12T-4GH7-QPL9-3N4M");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 3.76, 0.0);
	}
	
	/**
	 * Tests behavior of the original data file and example
	 */
	@Test
	public void testOriginalFile() {
		System.out.println("TEST ORIGINAL FILE");
		RegisterLogic rL = new RegisterLogic(originalTestData);
		retVal = rL.checkout("A12T-4GH7Y-QPL9-3N4MA;65P1-UDGM-XH2M-LQW2");
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 6.41, 0.0);
	}
	
	/**
	 * Tests behavior with null file and checkout list
	 */
	@Test
	public void testNullBehavior() {
		System.out.println("TEST NULL BEHAVIOR");
		RegisterLogic rL = new RegisterLogic(null);
		retVal = rL.checkout(null);
		System.out.println(retVal);
		System.out.println("__________________________________");
		
		assertEquals(retVal, 0.0, 0.0);
	}
	
	/**
	 * Tests GroceryItem methods
	 */
	@Test
	public void testGroceryItem() {
		System.out.println("TEST GROCERY ITEM");
		GroceryItem g = new GroceryItem("Chicken", 8.55);
		g.setPrice(7.55);
		g.setProduct("Pasta");
		System.out.println(g.getProduct() + " now costs " + g.getPrice());
 		System.out.println("__________________________________");
		
		assertEquals(g.getPrice(), 7.55, 0.0);
		assertEquals(g.getProduct(), "Pasta");
	}

}
