/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: PlatinumPackages
* @functionality: same as Package class but some extra functions
*/

package Package;

import Exceptions.BusinessRuleViolation;
import Exceptions.NullorEmptyException;

public class PlatinumPackage extends Package {

	
	public PlatinumPackage(Customer customer, Product product, String memberNumber) throws NullorEmptyException, BusinessRuleViolation {
		super(customer, product);
		memberNumValidation(memberNumber);
		
	}
	
	/*--------------------------------------------------------------------------------*/
	
	public boolean updateMemberNumber(String memberNumber) throws NullorEmptyException, BusinessRuleViolation {
		if(memberNumValidation(memberNumber)) {
			this.memberNumber = memberNumber;
		}
		return true;
	}
	/*--------------------------------------------------------------------------------*/
	/*This Method check if the memberNum follow the business rules
	 	1) Null or Empty --> raise Exception
	 	2) Length != 10 --> raise Exception
	 	3) do a while loop, check 2 characters together
	 			only valid when a alphabet followed by a number
	 			other possibilities --> raise Exception 
	  */
	private boolean memberNumValidation(String memberNumber) throws NullorEmptyException, BusinessRuleViolation {
		//1)
		if(memberNumber==null||memberNumber.equals("")) {
			throw new NullorEmptyException("[ Error: memberNumber should not be null or empty ]\n");
		}
		
		String[] splitNumber = memberNumber.split("");
		//2)
		if(splitNumber.length!=10) {
			throw new BusinessRuleViolation("[ Error: memberNumber should be 10 alphanumeric characters ]\n");
		}
		
		char firstChar, secondChar;
		int index = 0;
		//3)
		while(index<splitNumber.length) {
				firstChar = splitNumber[index].charAt(0);
				secondChar = splitNumber[index+1].charAt(0);
				if(!(Character.isLetter(firstChar)&&Character.isDigit(secondChar))) {
					throw new BusinessRuleViolation("Member number doesn't match alpha-numeric alternating requirement");
				}
				index += 2;
		}
		this.memberNumber = memberNumber;
		return true;
	}
	/*--------------------------------------------------------------------------------*/
	//This method override the parent class getDetails() method and customize it with platinum package style.
	
	@Override
	public String getDetails() {
			String header = "-----------------------\nPlatinum Package Info\n-----------------------\n";
			String custDetail = this.customer.getDetails();
			String memberNo = String.format("%-20s%s\n", "Member No:", this.memberNumber);
			String productDetail = "Product Ordered:\n";
			String temp = "";
			for(int index=0;index<this.productList.length;index++) {
				temp = "Product " + (index+1) + "\n"; 
				productDetail += temp + productList[index].getDetails() +"\n";
			}
			return header + memberNo + custDetail + productDetail;
	}
	
	
}

