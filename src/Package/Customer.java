/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: Customer
* @functionality: store all the customer values
*/

package Package;

import Exceptions.InvalidEntry;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;

public class Customer {
	
		private String firstName;
		private String lastName;
		private Address primaryAdd;
		private Address alternateAdd;
		
		/*-----------------------------------------------------------------------------------------------------*/
		//Constructor with only one Address
		public Customer(String firstName, String lastName, Address address) throws NullorEmptyException, InvalidEntry {
			
				if(firstName==null||firstName.equals("")) {
					throw new NullorEmptyException("[ Error: Name should not be null or empty value ]\n");
				}
				else if(!(onlyAlpha(firstName))) {
					throw new InvalidEntry("[ Error: FirstName should only contains alphabetic Letter ]\n");
				}
				else {
					this.firstName = firstName;
				}
					
				if(lastName==null||lastName.equals("")) {
					throw new NullorEmptyException("[ Error: Name should not be null or empty value ]\n");
				}
				else if(!(onlyAlpha(lastName))) {
					throw new InvalidEntry("[ Error: LastName should only contains alphabetic Letter ]\n");
				}
				else {
					this.lastName = lastName;
				}
				
				if(address!=null) {
						this.primaryAdd = address;
				}
				else {
					throw new NullorEmptyException("[ Error: Address should not be null values ]\n");
				}
		}
		/*-----------------------------------------------------------------------------------------------------*/
		/*Add an alternate address, true only when it has 1 primary address(primaryAdd!=null) and
		Alternate Address hasn't been assigned(alternateAdd==null) */
		
		public boolean addAddress(String streetNo, String streetName, String suburb, String postcode) throws Max2AddressException, NullorEmptyException, InvalidEntry {
			Address address = new Address(streetNo, streetName, suburb, postcode);
			if(this.primaryAdd!=null&&this.alternateAdd==null) {
				this.alternateAdd = address;
				return true;
			}
			else {
				throw new Max2AddressException("[ Error: You can only have 1 Primary and 1 Alternate Address ]\n");
			}
		}
		
		/*-----------------------------------------------------------------------------------------------------*/
		/*Overloading the above method */
		
		public boolean addAddress(Address address) throws Max2AddressException {
			if(this.primaryAdd!=null&&this.alternateAdd==null) {
				this.alternateAdd = address;
				return true;
			}
			else {
				throw new Max2AddressException("[ Error: You can only have 1 Primary and 1 Alternate Address ]\n");
			}
		}
		
		/*-----------------------------------------------------------------------------------------------------*/
		//GetDetails Method//
		
		public String getDetails() {
			String nameDetail = String.format("%-20s%s\n", "Customer Name: ", this.firstName + " " + this.lastName);
			String PriAddressDetail = "";
			String AltAddressDetail = "";
			if(this.primaryAdd!=null) {
				PriAddressDetail = String.format("%-20s%s", "Primary Address: ", this.primaryAdd.getDetails());
			}
			if(this.alternateAdd!=null) {
				AltAddressDetail = String.format("%-20s%s\n", "Alternate Address: ", this.alternateAdd.getDetails());
			}
		
			return nameDetail + PriAddressDetail + AltAddressDetail;
		}
		/*-----------------------------------------------------------------------------------------------------*/
		//Overloading GetDetails Method for only address(for editing address)//
		
		public String getDetails(String command) throws InvalidEntry {
			if(command.equalsIgnoreCase("Address")) {
				String PriAddressDetail = "";
				String AltAddressDetail = "";
				if(this.primaryAdd!=null) {
					PriAddressDetail = "0 " + this.primaryAdd.getDetails();
				}
				if(this.alternateAdd!=null) {
					AltAddressDetail = "1 " + this.alternateAdd.getDetails();
				}
				return PriAddressDetail + AltAddressDetail;
			}
			else {
				throw new InvalidEntry("Command was not recognizable.");
			}
		}
		
		/*-----------------------------------------------------------------------------------------------------*/
		//ToString Method//
		
		public String toString() {
			String nameDetail = this.firstName + " " + this.lastName +":";
			String PriAddressDetail = this.primaryAdd.toString();
			String AltAddressDetail = "";
			if(this.alternateAdd==null) {
				AltAddressDetail = "N/A";
			}
			else {
				AltAddressDetail = this.alternateAdd.toString();
			}
			return nameDetail + PriAddressDetail +":"+ AltAddressDetail;
		}
		/*-----------------------------------------------------------------------------------------------------*/
		//Change Preferred Primary, Alternate Address//
		public boolean swapPreferred() throws NullorEmptyException {			
			Address temp = this.primaryAdd;
			if(this.alternateAdd==null) {
				throw new NullorEmptyException("[ Error: Sorry You don't have an alternate address to swap ]\n");
			}
			else {
				this.primaryAdd = this.alternateAdd;
				this.alternateAdd = temp;
				return true;
			}
		}
		/*-----------------------------------------------------------------------------------------------------*/
		//Edit Address//
		public void editAddress(Address address, boolean isPrimary) {
			if(address!=null) {
				if(isPrimary) {
					this.primaryAdd = address;
				}
				else {
					this.alternateAdd = address;
				}
			}
		}
		/*-----------------------------------------------------------------------------------------------------*/
		//Edit Customer Name//
		public void editName(String firstName, String lastName) throws NullorEmptyException, InvalidEntry {
			if(firstName==null||firstName.equals("")) {
				throw new NullorEmptyException("[ Error: Name should not be null or empty value ]\n");
			}
			else if(!(onlyAlpha(firstName))) {
				throw new InvalidEntry("[ Error: FirstName should only contains alphabetic Letter ]\n");
			}
			else {
				this.firstName = firstName;
			}
				
			if(lastName==null||lastName.equals("")) {
				throw new NullorEmptyException("[ Error: Name should not be null or empty value ]\n");
			}
			else if(!(onlyAlpha(lastName))) {
				throw new InvalidEntry("[ Error: LastName should only contains alphabetic Letter ]\n");
			}
			else {
				this.lastName = lastName;
			}
		}
		/*-----------------------------------------------------------------------------------------------------*/
		public int howManyAddress() {
			int num = 1;
			if(this.alternateAdd!=null) {
				num += 1;
			}
			return num;
		}
		/*-----------------------------------------------------------------------------------------------------*/
		// A recursive method to check if the input string only contains alphabet.
		private boolean onlyAlpha(String str) {
			if(str.equals("")) { 
				return true;
			}
			if(!(Character.isLetter(str.charAt(0))||str.charAt(0)==" ".charAt(0))) {
				return false;
			}
			return onlyAlpha(str.substring(1));
		}
		
}
