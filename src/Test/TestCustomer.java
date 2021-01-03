/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: TestCustomer
* @functionality: testing
*/

package Test;

import Package.Customer;
import Exceptions.InvalidEntry;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;
import Package.Address;

public class TestCustomer {

	private Address presetAddress1;
	private Address presetAddress2;
		
	public static void main(String[] args) throws NullorEmptyException, InvalidEntry, Max2AddressException {
		TestCustomer test = new TestCustomer();
		test.loadSeedAddress();
		test.runAllTest();
	}
	
	
	private void loadSeedAddress() throws NullorEmptyException, InvalidEntry {
			this.presetAddress1 = new Address("42", "Pride Avenue", "Elwood", "1184");
			this.presetAddress2 = new Address("91","Sebastian Street","Carlton","2053");
	}
	
	public void runAllTest() throws NullorEmptyException, InvalidEntry, Max2AddressException {
			validConstruct();
			emptyFirstName();
			emptyLastName();
			nullFirstName();
			nullLastName();
			nullAddress();
			operations1();
			operations2();
			operations3();
			operations4();
	}
	/*------------------------------------------------------------------------------------------------*/
	public void validConstruct() throws NullorEmptyException, InvalidEntry, Max2AddressException {
		//Valid Construction//
		Customer customer1 = new Customer("Matthew", "Broderick", this.presetAddress1);
		Customer customer2 = new Customer("Rowan","Atkinson", this.presetAddress2);
		customer1.addAddress("91","Sebastian Street","Carlton","2053");
		customer2.addAddress("42", "Pride Avenue", "Elwood", "1184");
		System.out.println("Valid Construction");
		System.out.println("-------------------------------------------------");
		System.out.println(customer1.getDetails());
		System.out.println(customer1.toString());
		System.out.print("\n");
		System.out.println(customer2.getDetails());
		System.out.println(customer2.toString());
		System.out.print("\n");
	}
	/*------------------------------------------------------------------------------------------------*/
	/*These invalid construct will only display an error message and return to the menu() class*/
	
	public void emptyFirstName() throws NullorEmptyException, InvalidEntry {
		//Invalid Construction: Empty firstName//
		try {
			System.out.println("Test: [ Invalid Construction: Empty firstName ]");
		Customer emptyFirstName = new Customer("", "Broderick", this.presetAddress1);
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	public void emptyLastName() throws NullorEmptyException, InvalidEntry {
		//Invalid Construction: Empty lastName//
		try {
			System.out.println("Test: [ Invalid Construction: Empty lastName ]");
			Customer emptyLastName = new Customer("Matthew", "", this.presetAddress1);
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	public void nullFirstName() throws NullorEmptyException, InvalidEntry {
		//Invalid Construction: Null firstName/
		try {
			System.out.println("Test: [ Invalid Construction: Null firstName ]");
			Customer nullFirstName = new Customer(null, "Broderick", this.presetAddress1);
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	public void nullLastName() throws NullorEmptyException, InvalidEntry {
		//Invalid Construction: Null lastName/
		try {
			System.out.println("Test: [ Invalid Construction: Null lastName ]");
			Customer nullLastName = new Customer("Matthew", null, this.presetAddress1);
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	public void nullAddress() throws NullorEmptyException, InvalidEntry {
		//Invalid Construction: Null Address//
		try {
			System.out.println("Test: [ Invalid Construction: Null Address ]");
			Customer nullAddress = new Customer("Matthew", "Broderick", null);
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	public void operations1() throws NullorEmptyException, InvalidEntry {
		//Setting a preferred alternate address when it does not exist//
		try {
			System.out.println("Test: [ Setting a preferred alternate address when it does not exist ]");
			Customer operation1 = new Customer("Matthew", "Broderick", this.presetAddress1);
			operation1.swapPreferred();
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	public void operations2() throws NullorEmptyException, InvalidEntry, Max2AddressException {
		//Adding a second alternate address//
		try {
			System.out.println("Test: [ Adding a second alternate address ]");
			Customer operation2 = new Customer("Matthew", "Broderick", this.presetAddress1);
			operation2.addAddress("91","Sebastian Street","Carlton","2053");
			operation2.addAddress("83","Sebastian Street","Carlton","2053");
		}catch(Max2AddressException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
	
	public void operations3() throws NullorEmptyException, InvalidEntry, Max2AddressException {
		//Setting a preferred alternate address when it does exist//
		System.out.println("Test: [ Setting a preferred alternate address when it does exist ]");
		Customer operation3 = new Customer("Matthew", "Broderick", this.presetAddress1);
		operation3.addAddress("91","Sebastian Street","Carlton","2053");
		System.out.println("Before swapping: ");
		System.out.println(operation3.getDetails());
		operation3.swapPreferred();
		System.out.println("After swapping: ");
		System.out.println(operation3.getDetails());
	}	
	
	public void operations4() throws NullorEmptyException, InvalidEntry, Max2AddressException {
		//Adding a third address, in my program design it is as same as adding a second alternate address//
		System.out.println("Test: [ Adding a third address, in my program design it is as same as adding a second alternate address ]");
		try {
			Customer operation4 = new Customer("Matthew", "Broderick", this.presetAddress1);
			operation4.addAddress("91","Sebastian Street","Carlton","2053");
			operation4.addAddress("83","Sebastian Street","Carlton","2053");
			operation4.addAddress("53","Sebastian Street","Carlton","2053");
		}catch(Max2AddressException e) {
			System.out.println(e.getMessage());
			System.out.println();
		}
	}
}
