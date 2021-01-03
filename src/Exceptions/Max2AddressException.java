/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: Max2AddressException
* @functionality: Exception for if you have more than 2 addresses in customer class
*/

package Exceptions;

public class Max2AddressException extends Exception {
		public Max2AddressException(String message) {
			super(message);
		}
}
