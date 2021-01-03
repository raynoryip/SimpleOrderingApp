/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: Address
* @functionality: store all the address details
*/

package Package;

import Exceptions.InvalidEntry;
import Exceptions.NullorEmptyException;

public class Address {
		
		private String streetNo;
		private String streetName;
		private String suburb;
		private String postcode;
		
		/*------------------------------------------------------------------------------------------------*/
		public Address(String streetNo, String streetName, String suburb, String postcode) throws NullorEmptyException, InvalidEntry {
				
				if(!(streetNoValid(streetNo))) {
					throw new NullorEmptyException("[ Error: streetNo cannot be empty or null ]\n");
				}
				if(!(streetNameValid(streetName))) {
					throw new NullorEmptyException("[ Error: streetName cannot be empty or null ]\n");
				}
				if(!(suburbValid(suburb))) {
					throw new NullorEmptyException("[ Error: suburb cannot be empty or null ]\n");
				}
				if(!(postcodeValid(postcode))) {
					throw new InvalidEntry("[ Error: postcode shouldn't be null/empty, or starting with 0 or 9, and only digits ]\n");
				}
		}
		/*------------------------------------------------------------------------------------------------*/
		private boolean streetNoValid(String streetNo) {
				if(streetNo==null||streetNo.equals("")) {
					return false;
				}
				else {
					this.streetNo = streetNo;
					return true;
				}		
		}
		/*------------------------------------------------------------------------------------------------*/
		private boolean streetNameValid(String streetName) {
			if(streetName==null||streetName.equals("")) {
				return false;
			}
			else {
				this.streetName = streetName;
				return true;
			}		
		}
		/*------------------------------------------------------------------------------------------------*/
		private boolean suburbValid(String suburb) {
			if(suburb==null||suburb.equals("")) {
				return false;
			}
			else {
				this.suburb = suburb;
				return true;
			}		
		}
		/*------------------------------------------------------------------------------------------------*/
		private boolean postcodeValid(String postcode) {
			if(postcode==null||postcode.equals("")) {
				return false;}
				
			if(postcode.length() != 4) {
				return false; }
					
			if(postcode.charAt(0)== '0' ||postcode.charAt(0)=='9'||containsAlpha(postcode)) {
				return false;}
			
			this.postcode = postcode;
			return true;
		}
		/*------------------------------------------------------------------------------------------------*/
		// recursive method to check if the postcode contains any alphabets//
		private boolean containsAlpha(String postcode) {
			if(postcode.equals("")) {
				return false;
			}
			if(Character.isLetter(postcode.charAt(0))) {
				return true;
			}
			return containsAlpha(postcode.substring(1));
		}
		/*------------------------------------------------------------------------------------------------*/
		public String getDetails() {
			String addressDetail = String.format("%-20s\n", this.streetNo+" "+this.streetName+
					" "+this.suburb+" "+this.postcode);
			return addressDetail;
		}
		/*------------------------------------------------------------------------------------------------*/
		public String toString() {
				return this.streetNo+ "-"+this.streetName+"-"+this.suburb+"-"+this.postcode;
		}

}
