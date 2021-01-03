/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: TestProduct
* @functionality: testing
*/

package Test;

import Exceptions.InvalidEntry;
import Exceptions.NullorEmptyException;
import Package.Product;

public class TestProduct {

	public static void main(String[] args) throws InvalidEntry, NullorEmptyException {
			TestProduct test = new TestProduct();
			test.runAllTest();

	}
	
	public void runAllTest() throws InvalidEntry, NullorEmptyException {
		validConstruct();
		emptyName();
		nullName();
		zeroWeight();
		zeroCost();
	}
	
	public void validConstruct() throws InvalidEntry, NullorEmptyException {
		try {
			Product product = new Product("Box", 10.5, 23.5);
			System.out.println(product.getDetails());
			System.out.println(product.toString());
		}catch(InvalidEntry e) {
			System.out.println(e.getMessage());
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void emptyName() throws InvalidEntry, NullorEmptyException {
		//Test empty Name//
		try {
			Product product = new Product("", 10.5, 23.5);
		}catch(InvalidEntry e) {
			System.out.println(e.getMessage());
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void nullName() throws InvalidEntry, NullorEmptyException {
		//Test null Name//
		try {
			Product product = new Product(null, 10.5, 23.5);
		}catch(InvalidEntry e) {
			System.out.println(e.getMessage());
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void zeroWeight() throws InvalidEntry, NullorEmptyException {
		//Test 0 weight or negative weight//
		try {
			Product product = new Product("Box", 0, 23.5);
			Product product2 = new Product("Box", -1, 23.5);
		}catch(InvalidEntry e) {
			System.out.println(e.getMessage());
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void zeroCost() throws InvalidEntry, NullorEmptyException {
		//Test 0 cost or negative cost//
		try {
			Product product = new Product("Box", 10.5, 0);
			Product product2 = new Product("Box", 10.5, -1);
		}catch(InvalidEntry e) {
			System.out.println(e.getMessage());
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
	}
	
}
