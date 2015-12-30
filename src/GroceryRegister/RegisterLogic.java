package GroceryRegister;

import java.util.*;
import java.io.*;
/**
 *
 * Logical unit for handling the processes of a cash register. Accounts for items by their 
 * product codes and produces totals for the desired goods.
 *
 * @author Brian Fitzpatrick
 * @version 1.0 12/29/15
 */
class RegisterLogic{

    /**
     * Name of the file that holds the registered grocery items
     */

    private String groceryInventory = "";
    
    /**
     * Sales tax that is to be applied to the groceries
     */

    private final double SALES_TAX = 1.0875;
   
    /**
     * Hashtable of the inventory of products with their prices and names
     */
    private Hashtable<String, GroceryItem> products = new Hashtable<String, GroceryItem>();


    /**
     * Begins to read in the product codes as a new RegisterLogic object is created.
     */
    public RegisterLogic(String filePath){
    	groceryInventory = filePath;
    	readInProductCodes();
    }

    /**
     * Reads the product codes and assigns them to their product and cost in the hash table, sanitizes input to make
     * sure the file being read follows the proper format.
     */
    private void readInProductCodes()
    {

	BufferedReader reader;
	String line;
	String regex = "[\\w]{4}[-][\\w]{4}[-][\\w]{4}[-][\\w]{4}";
    	try
    	{
    	reader = new BufferedReader(new FileReader(groceryInventory));
   
    	while((line = reader.readLine()) != null)
    	{
    		String[] currLine = line.split(",");
    	try{
		if(currLine[1].matches(regex))
		    {
			GroceryItem storedItem = new GroceryItem(currLine[0], Double.parseDouble(currLine[2]));
			products.put(currLine[1].toUpperCase(),storedItem);
		    }
		else
		    {
			System.err.println("The syntax of the grocery items file is incorrect, please make sure" +
                                           "it is an appropriate inventory file.\n Incorrect code was for: " + currLine[0]);
		    }
    	}
    	catch(ArrayIndexOutOfBoundsException aoobe)
    	{
    		System.err.println("The file you are trying to read has an improper format");
    	}

    	

    	}
    	reader.close();
    	}
    catch(FileNotFoundException fnfe)
    	{
    	    System.err.println("The file you have seleceted was not found.");
    	}
	catch(IOException ioe)
    	{
	    	System.err.println("IO Connection lost");
    	}
    catch(NullPointerException npe)
    	{
    		System.err.println("The file was null");
    	}
   }

    /**
     * Processes a given product code to retrieve the price of the item.
     *
     * @param item the product code for the item being processed
     * @return the price of the given item
     */

    private double processItem(String item) throws NullPointerException
    {
    	try
    	{
    	return products.get(item.toUpperCase()).getPrice();
    	}
    	catch(NullPointerException npe)
    	{
    		System.err.println("The item you are looking for does not exist and will be omitted from the total."
    				+ "\nFailed code: " + item);
        	return 0.0;
    	}
    	
    }

    /**
     * Checks out a given list of product codes and produces a total with tax applied.
     *
     * @param items a string containing product codes in the format "code1;code2;code3;...;codeN"
     * @return the total price of the listed items with tax
     */
    public double checkout(String items)
    {
	double total = 0.0;
	
	try{
	StringTokenizer tokenizer = new StringTokenizer(items,";");
	while (tokenizer.hasMoreElements())
	    total+=processItem(tokenizer.nextElement().toString());

	return (double) Math.round(total*SALES_TAX*100)/100;
	}
	catch(NullPointerException npe)
	{
		System.err.println("The string sent to the checkout was null");
		return total;
	}
    }
}
