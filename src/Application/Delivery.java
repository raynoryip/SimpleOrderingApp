/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: Delivery
* @functionality: store all the delivery details
*/

package Application;

import Exceptions.BusinessRuleViolation;
import Exceptions.NullorEmptyException;
import Tools.DateTime;
import Package.Package;

public class Delivery {
	
		private Package package1;
		private DateTime deliveryDate;
	
		/*------------------------------------------------------------------------------------------------*/
		public Delivery(Package package1, DateTime deliveryDate) throws NullorEmptyException, BusinessRuleViolation {
				if(package1!=null) {
					this.package1 = package1;
				}
				else {
					throw new NullorEmptyException("package cannot be null");
				}
				if(deliveryDate!=null) {
					if(deliveryDateValid(deliveryDate)) {
						this.deliveryDate = deliveryDate;
					}
				}
				else {
					throw new NullorEmptyException("time cannot be null");
				}
		}
		
		/*------------------------------------------------------------------------------------------------*/
		public String getDetails() {
			String packageDetail = this.package1.getDetails();
			String dateDetail = String.format("%-20s%s\n", "Delivery Date: ", this.deliveryDate.getFormattedDate());
			return dateDetail + packageDetail;
		}
		
		public String toString() {
			return this.package1.toString() + "\n" + this.deliveryDate.getFormattedDate();
		}
		
		public String getCustomerLastName() {
			return this.toString().split(":")[1].toLowerCase();
		}
		/*------------------------------------------------------------------------------------*/
		//A Getter of retrieving the package//
		public Package retrievePackage() {
			 return this.package1;
		}
		/*------------------------------------------------------------------------------------*/
		//A Getter of retrieving the delivery Date//
		public DateTime retrieveDeliDate() {
			 return this.deliveryDate;
		}
		/*--------------------------------------------------------------*/
		//Date Validation 
		// valid date must between 0 and 30 days//
		private boolean deliveryDateValid(DateTime deliveryDate) throws BusinessRuleViolation {
			DateTime currentDate = new DateTime();
			int diff = DateTime.diffDays(deliveryDate, currentDate);
			
			if(diff>= 0 && diff <= 30) {		
				return true;
			}
			else {
				throw new BusinessRuleViolation("Your date must not be a past date or longer 30 days");
			}
		}
}
