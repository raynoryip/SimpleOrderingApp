/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: Product
* @functionality: store all the product values
*/

package Package;

import Exceptions.InvalidEntry;
import Exceptions.NullorEmptyException;

public class Product {
		
	private String productName;
	private double weight;
	private double cost;
	
	/*------------------------------------------------------------------------------------------------*/
	public Product(String name, double weight, double cost) throws InvalidEntry, NullorEmptyException {
			checkName(name);
			checkWeight(weight);
			checkCost(cost);
	}
	
	/*------------------------------------------------------------------------------------------------*/
	private void checkName(String name) throws NullorEmptyException {
		if(name==null||name.equals("")) {
			throw new NullorEmptyException("[ Error: name shouldn't be null or empty ]\n");
		}
		this.productName = name;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	private void checkWeight(double weight) throws InvalidEntry{
		if(weight<=0.0) {
			throw new InvalidEntry("[ Error: Weight should be greater than 0 ]\n");
		}
		this.weight = weight;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	private void checkCost(double cost) throws InvalidEntry {
		if(cost <= 0.0) {
			throw new InvalidEntry("[ Error: Cost should be greater than 0 ]\n");
		}
		this.cost = cost;
	}
	/*------------------------------------------------------------------------------------------------*/
	public String getDetails() {
		
		String nameDetail = String.format("%-20s%s\n", "Name:", this.productName);
		String weightDetail = String.format("%-20s%sg\n", "Weight:", Double.toString(this.weight));
		String costDetail = String.format("%-20s$%s\n", "Cost:", Double.toString(this.cost));
		
		return nameDetail + weightDetail + costDetail;
	}
	
	public String toString() {
		return this.productName+ ":"+this.weight+":"+this.cost;
	}
	
	/*------------------------------------------------------------------------------------------------*/
	//Some getter methods//
	
	public String getName() {
		return this.productName;
	}
	
	public double getWeight() {
		return this.weight;
	}
	
	public double getCost() {
		return this.cost;
	}
}
