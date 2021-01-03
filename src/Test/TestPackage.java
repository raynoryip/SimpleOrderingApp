/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: TestPacakge
* @functionality: testing
*/

package Test;

import Exceptions.BusinessRuleViolation;
import Exceptions.InvalidEntry;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;
import Package.Address;
import Package.Customer;
import Package.Product;
import Package.Package;

public class TestPackage {
	
		private Product presetProduct, presetProduct2, presetProduct3;
		private Customer presetCust;
	
		public static void main(String[] args) throws NullorEmptyException, InvalidEntry, BusinessRuleViolation, Max2AddressException {
			TestPackage test = new TestPackage();
			test.loadSeedData();
			test.successRemoveElement();
			test.failRemoveElement1();
			test.failRemoveElement2();
			
		}
		
		public void loadSeedData() throws InvalidEntry, NullorEmptyException, Max2AddressException {
			this.presetProduct = new Product("Box", 10.0, 20.0);
			this.presetProduct2 = new Product("Doll", 3.0, 15.0);
			this.presetProduct3 = new Product("Box", 2.0, 11.0);
			Address address1 = new Address("42", "Pride Avenue", "Elwood", "1184");
			Address address2 = new Address("99", "Puke Avenue", "Alwood", "1008");
			this.presetCust = new Customer("Rowan","Atkinson", address1);
			presetCust.addAddress(address2);
			
		}
		
		
		public void validConstruct() throws NullorEmptyException {
			//Valid Construct 1: one customer, one product//
			Package package1 = new Package(this.presetCust, this.presetProduct);
			System.out.println(package1.getDetails());
			System.out.println(package1.toString());
		}
		
		public void invalidConstruct1() throws NullorEmptyException {
			//Null entry or entries//
			Package package1 = new Package(null, this.presetProduct);
		}
		
		public void invalidConstruct2() throws NullorEmptyException {
			//Null entry or entries//
			Package package1 = new Package(this.presetCust, null);
		}
		
		public void successAddElement1() throws NullorEmptyException, BusinessRuleViolation {
			//Valid Construct 2: one customer, successful adding 2 different product//
			Package package2 = new Package(this.presetCust, this.presetProduct);
			package2.addProduct(this.presetProduct2);
			System.out.println(package2.getDetails());
			System.out.println(package2.toString());
		}
		
		public void successAddElement2() throws NullorEmptyException, BusinessRuleViolation {
			//Valid Construct 3: one customer, successful adding 2 different product, 1 same name product//
			Package package3 = new Package(this.presetCust, this.presetProduct);
			package3.addProduct(this.presetProduct2);
			package3.addProduct(this.presetProduct3);
			System.out.println(package3.getDetails());
			System.out.println(package3.toString());
		}
		
		public void successRemoveElement() throws NullorEmptyException, InvalidEntry, BusinessRuleViolation {
			//Successful removing an existing product from the list//
			//Exception would be raise because it violates the business rule//
			
			Address address1 = new Address("42", "Pride Avenue", "Elwood", "1184");
			Customer customer = new Customer("Rowan","Atkinson", address1);
			Product product1 = new Product("Box", 10.0, 20.0);
			Product product2 = new Product("Doll", 3.0, 15.0);
			Product product3 = new Product("Box", 2.0, 11.0); //Same name as product1//
			
			System.out.println("Method: Success Remove Element()");
			Package package2 = new Package(customer, product1);
			package2.addProduct(product2);
			if(package2.removeProduct(product3)) {
				System.out.println("[ Successfully remove a product ]");
			}
			else {
				System.out.println("[ Failed to remove a product ]");
			}
			System.out.println(package2.getDetails());
			System.out.println(package2.toString());
		}
		
		public void failRemoveElement1() throws NullorEmptyException, InvalidEntry, BusinessRuleViolation {
			//Fail to remove a product from list//
			//Remove a non existing product//
			Address address1 = new Address("42", "Pride Avenue", "Elwood", "1184");
			Customer customer = new Customer("Rowan","Atkinson", address1);
			Product product1 = new Product("Box", 10.0, 20.0);
			Product product2 = new Product("Doll", 3.0, 15.0);
			Product product3 = new Product("Mouse", 2.0, 11.0); 
			
			System.out.println("Method: Failed to Remove Element()");
			Package package2 = new Package(customer, product1);
			package2.addProduct(product2);
			if(package2.removeProduct(product3)) {
				System.out.println("[ Successfully remove a product ]");
			}
			else {
				System.out.println("[ Failed to remove a product ] ");
			}
			System.out.println(package2.getDetails());
			System.out.println(package2.toString());
		}
		
		public void failRemoveElement2() throws NullorEmptyException, InvalidEntry, BusinessRuleViolation {
			//Remove the final element from the list//
			Address address1 = new Address("42", "Pride Avenue", "Elwood", "1184");
			Customer customer = new Customer("Rowan","Atkinson", address1);
			Product product1 = new Product("Box", 10.0, 20.0);
			Product product2 = new Product("Box", 2.0, 11.0); 
			
			System.out.println("Method: Failed to Remove Element()");
			Package package2 = new Package(customer, product1);
			if(package2.removeProduct(product2)) {
				System.out.println("[ Successfully remove a product ]");
			}
			else {
				System.out.println("[ Failed to remove a product ] ");
			}
			System.out.println(package2.getDetails());
			System.out.println(package2.toString());
		}

}
