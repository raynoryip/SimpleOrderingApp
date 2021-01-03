/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: TestAddress
* @functionality: testing
*/

package Test;

import Exceptions.InvalidEntry;
import Exceptions.NullorEmptyException;
import Package.Address;

public class TestAddress {
	
	public static void main(String[] args) throws NullorEmptyException, InvalidEntry {
			TestAddress test = new TestAddress();
	
			test.runAllTest();
	}
	
	public void validConstruct() throws NullorEmptyException, InvalidEntry {
		//Test for validConstruct, also test setting them into a alternate address//
		Address address1 = new Address("42", "Pride Avenue", "Elwood", "1184");
		System.out.println(address1.getDetails());
		System.out.println(address1.toString());		
		Address address2 = new Address("90", "Pride Avenue", "Elwood", "1184");
		System.out.println(address2.getDetails());
		System.out.println(address2.toString());		
	}
	/*--------------------------------------------------------------------------------*/
	public void runAllTest() throws NullorEmptyException, InvalidEntry {
		    validConstruct();
			invalidStreetNo();
			invalidStreetName();
			invalidSuburb();
			invalidPostcode();
	}
	
	/*--------------------------------------------------------------------------------*/
	/*The below test method would raise Exception since they violated the business rule, 
	 and only display an error message*/
	
	public void invalidStreetNo() throws NullorEmptyException, InvalidEntry {
		//Test empty or null StreetNo//
		try {
			System.out.println("[ Testing for empty streetNo ]");
			Address address1 = new Address("", "Pride Avenue", "Elwood", "1184");
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("[ Testing for null streetNo ]");
			Address address2 = new Address(null, "Pride Avenue", "Elwood", "1184");
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void invalidStreetName() throws NullorEmptyException, InvalidEntry {
		//Test empty or null StreetName//
		try {
			System.out.println("[ Testing for empty streetName ]");
			Address address1 = new Address("42", "", "Elwood", "1184");
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("[ Testing for null streetName ]");
			Address address2 = new Address("42", null , "Elwood", "1184");
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void invalidSuburb() throws NullorEmptyException, InvalidEntry {
		//Test empty or null Suburb//
		try{
			System.out.println("[ Testing for empty suburb ]");
			Address address1 = new Address("42", "Pride Avenue", "", "1184");
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
		try {
			System.out.println("[ Testing for null suburb ]");
			Address address2 = new Address("42", "Pride Avenue" , null, "1184");
		}catch(NullorEmptyException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void invalidPostcode() throws InvalidEntry, NullorEmptyException {
		//Business rules from assignment 1, and they will all throw exceptions when compile //
		try {
			System.out.println("[ Testing for invalid postcodes]");
			Address Address2 = new Address("91","Sebastian Street","Carlton","2x53"); //Postcode with alphabet//
			Address Address3 = new Address("19","John Close","Essendon","304"); //Postcode length < 4// 
			Address Address4 = new Address("57","Elaine Court","St Albans","40215"); //Postcode length > 4//
			Address Address5 = new Address("11","Earl Road","Melbourne","0001"); //Postcode start with '0'// 
			Address Address6 = new Address("83","Dalgliesh Street","South Yarra","9141"); //Postcode start with '9'//
			Address Address7 = new Address("83","Dalgliesh Street","South Yarra",""); //empty postcode//
			Address Address8 = new Address("83","Dalgliesh Street","South Yarra",null); //null name and postcode//
		}catch(InvalidEntry e) {
			System.out.println(e.getMessage());
		}
	}
	
	

}
