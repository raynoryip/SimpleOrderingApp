/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: TestDelivery
* @functionality: testing
*/

package Test;

import Application.Delivery;
import Exceptions.BusinessRuleViolation;
import Exceptions.InvalidEntry;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;
import Package.Address;
import Package.Customer;
import Package.Product;
import Tools.DateTime;
import Package.Package;
import Package.PlatinumPackage;

public class TestDelivery {

	private Product presetProduct, presetProduct2, presetProduct3;
	private Customer presetCust;
	private Package package1;
	private PlatinumPackage package2;
	
	public static void main(String[] args) throws NullorEmptyException, BusinessRuleViolation, InvalidEntry, Max2AddressException {
			TestDelivery test = new TestDelivery();
			test.loadSeedData();
			test.validConstruct();
			test.invalidDate();
	}
	
	public void loadSeedData() throws InvalidEntry, NullorEmptyException, Max2AddressException, BusinessRuleViolation {
		this.presetProduct = new Product("Box", 10.0, 20.0);
		this.presetProduct2 = new Product("Doll", 3.0, 15.0);
		this.presetProduct3 = new Product("Box", 2.0, 11.0);
		Address address1 = new Address("42", "Pride Avenue", "Elwood", "1184");
		Address address2 = new Address("99", "Puke Avenue", "Alwood", "1008");
		this.presetCust = new Customer("Rowan","Atkinson", address1);
		this.presetCust.addAddress(address2);
		this.package1 =  new Package(this.presetCust, this.presetProduct);
		this.package2 = new PlatinumPackage(this.presetCust, this.presetProduct, "D0B4M4S7Y5");
		this.package2.addProduct(presetProduct2);
		this.package2.addProduct(presetProduct3);
	}
	
	public void validConstruct() throws NullorEmptyException, BusinessRuleViolation {
		//valid construct
		DateTime deliverydate1 = new DateTime(3);
		DateTime deliverydate2 = new DateTime(2);
		
		Delivery delivery1 = new Delivery(this.package1, deliverydate1);
		Delivery delivery2 = new Delivery(this.package2, deliverydate2);
		
		System.out.println(delivery1.getDetails());
		System.out.println(delivery1.toString());
		System.out.println(delivery1.getCustomerLastName());
		System.out.println("[ ------------------------ ]");
		System.out.println(delivery2.getDetails());
		System.out.println(delivery2.toString());
	}
	
	public void invalidDate() throws NullorEmptyException, BusinessRuleViolation {
		
	try {
		//past date
		DateTime deliverydate1 = new DateTime(-1);
		Delivery delivery1 = new Delivery(this.package1, deliverydate1);
		
	}catch(BusinessRuleViolation e) {
		System.out.println(e.getMessage());
	}
	
	try {
		// more than 30 days
		DateTime deliverydate2 = new DateTime(100);
		Delivery delivery2 = new Delivery(this.package2, deliverydate2);
		
	}catch(BusinessRuleViolation e) {
		System.out.println(e.getMessage());
	}
	
	try {
		// null date
		DateTime deliverydate2 = null;
		Delivery delivery2 = new Delivery(this.package2, deliverydate2);
		
	}catch(NullorEmptyException e) {
		System.out.println(e.getMessage());
	}
	
	
	
	
	}

}
