/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: TestPlatPackage
* @functionality: testing
*/

package Test;

import Exceptions.BusinessRuleViolation;
import Exceptions.InvalidEntry;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;
import Package.Address;
import Package.Customer;
import Package.PlatinumPackage;
import Package.Product;

public class TestPlatPackage {

	private Product presetProduct, presetProduct2;
	private Customer presetCust;
	private PlatinumPackage package1;
	
	public static void main(String[] args) throws InvalidEntry, NullorEmptyException, Max2AddressException, BusinessRuleViolation {
		TestPlatPackage test = new TestPlatPackage();
		test.loadSeedData();
		test.runAllTest();
	}
	/*--------------------------------------------------------------*/
	public void loadSeedData() throws InvalidEntry, NullorEmptyException, Max2AddressException {
		this.presetProduct = new Product("Box", 10.0, 20.0);
		this.presetProduct2 = new Product("Doll", 3.0, 15.0);
		Address address1 = new Address("42", "Pride Avenue", "Elwood", "1184");
		Address address2 = new Address("99", "Puke Avenue", "Alwood", "1008");
		this.presetCust = new Customer("Rowan","Atkinson", address1);
		presetCust.addAddress(address2);
		
	}
	
	public void runAllTest() throws NullorEmptyException, BusinessRuleViolation, InvalidEntry {
			validConstruct();
			invalidConstruct1();
			invalidConstruct2();
			invalidConstruct3();
			invalidConstruct4();
			invalidConstruct5();
			invalidConstruct6();
			updateMemberNo();
			failUpdateMemberNo();
			
	}
	/*--------------------------------------------------------------*/
	/*Some of the valid/invalid construct and operations was tested on the TestPackage(Parent) Class, so
	  we won't do duplicated test again here*/
	
	
	public void validConstruct() throws NullorEmptyException, InvalidEntry, BusinessRuleViolation {
		//Test for a valid memberNum, and all other valid entries and operations
		Address address1 = new Address("42", "Pride Avenue", "Elwood", "1184");
		Customer customer = new Customer("Rowan","Atkinson", address1);
		Product product1 = new Product("Box", 10.0, 20.0);
		
		String memberNum = "X9S9V6I0Y8";
		this.package1 = new PlatinumPackage(customer, product1, memberNum);
		package1.addProduct(presetProduct2);
		System.out.println(package1.getDetails());
		System.out.println(package1.toString());
	}
	
	public void invalidConstruct1() throws NullorEmptyException, BusinessRuleViolation {
		//Test for null memberNum, all other customer, product entry are valid.
		//Output should raise exception.
		try {
			String memberNum = null;
			this.package1 = new PlatinumPackage(this.presetCust, this.presetProduct, memberNum);
		}
		catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		catch(BusinessRuleViolation e) {
			System.out.println(e.getMessage());
		}
	}

	public void invalidConstruct2() throws NullorEmptyException, BusinessRuleViolation {
		//Test for empty memberNum, all other entries are valid.
		//Output should raise exception.
		try {
			String memberNum = "";
			this.package1 = new PlatinumPackage(this.presetCust, this.presetProduct, memberNum);
		}
		catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		catch(BusinessRuleViolation e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void invalidConstruct3() throws NullorEmptyException, BusinessRuleViolation {
		//Test for 9 characters memberNum, all other entries are valid.
		//Output should raise exception.
		try {
			String memberNum = "D0B4M4S7Y";
			this.package1 = new PlatinumPackage(this.presetCust, this.presetProduct, memberNum);
		}
		catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		catch(BusinessRuleViolation e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void invalidConstruct4() throws NullorEmptyException, BusinessRuleViolation {
		//Test for 11 characters memberNum, all other entries are valid.
		//Output should raise exception.
		try {
			String memberNum = "D0B4M4S7Y5A";
			this.package1 = new PlatinumPackage(this.presetCust, this.presetProduct, memberNum);
		}
		catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		catch(BusinessRuleViolation e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void invalidConstruct5() throws NullorEmptyException, BusinessRuleViolation {
		//Test for non-alternating memberNum, all other entries are valid.
		try {
			String memberNum = "D0BM44S7Y5";
			this.package1 = new PlatinumPackage(this.presetCust, this.presetProduct, memberNum);
		}
		catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		catch(BusinessRuleViolation e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void invalidConstruct6() throws NullorEmptyException, BusinessRuleViolation {
		//Test for non-alphanumeric memberNum, all other entries are valid.
		try {
			String memberNum = "D$B4M4S7Y5";
			this.package1 = new PlatinumPackage(this.presetCust, this.presetProduct, memberNum);
		}
		catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		catch(BusinessRuleViolation e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void updateMemberNo() throws NullorEmptyException, BusinessRuleViolation {
		//Test updating a valid member No
		String memberNum = "X9S9V6I0Y8";
		this.package1 = new PlatinumPackage(this.presetCust, this.presetProduct, memberNum);
		System.out.println("Before Updating(valid memNo):");
		System.out.println(package1.getDetails());
		System.out.println("After Updating(valid memNo):");
		this.package1.updateMemberNumber("U8M4Z3N9F5");
		System.out.println(package1.getDetails());
	}
	
	public void failUpdateMemberNo() throws NullorEmptyException, BusinessRuleViolation {
		//Test updating a invalid member No
		try {
			String memberNum = "D$B4M4S7Y5";
			this.package1 = new PlatinumPackage(this.presetCust, this.presetProduct, memberNum);
			System.out.println("Before Updating(valid memNo):");
			System.out.println(package1.getDetails());
			System.out.println("After Updating(valid memNo):");
			this.package1.updateMemberNumber("D$B4M4S7Y5");
			System.out.println(package1.getDetails());
		}catch(BusinessRuleViolation e) {
			System.out.println(e.getMessage());
		}
	}
}
