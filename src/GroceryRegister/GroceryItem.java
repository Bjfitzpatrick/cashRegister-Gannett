package GroceryRegister;

/**
 * An auxiliary object used to represent the data for a given product, holds product name and cost.
 *
 * @author Brian Fitzpatrick
 * @version 1.0 12/29/15
 */
class GroceryItem{

    /**
     * The name of the product.
     */
    private String product;
    
    /**
     * The price of the product.
     */
    private double price;


    /**
     *  Constructs a new product for a given name and cost.
     *
     * @param name the name of the product
     * @param cost the cost of the product
     */
    public GroceryItem(String name,double cost){
	product = name;
	price = cost;
    }


    /**
     * Retrieves the name of the product.
     *
     * @return the name of the product
     */
    public String getProduct(){
	return product;
    }

    /**
     * Retrieves the price of the product.
     *
     * @return the price of the product
     */
    public double getPrice(){
	return price;
    }

    /**
     * Sets the name of the product.
     *
     * @param name the name of the product
     */
    public void setProduct(String name){
	product = name;
    }

    /**
     * Sets the cost of the product.
     *
     * @param cost the cost of the product
     */
    public void setPrice(double cost){
	price = cost;
    }

}