/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: BusinessRuleViolation
* @functionality: Exception specifically for violate business rules
*/

package Exceptions;

public class BusinessRuleViolation extends Exception {
		public BusinessRuleViolation(String message) {
			super(message);
		}

}
