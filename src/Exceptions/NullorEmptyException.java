/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name:NullorEmptyException
* @functionality: Exception for null or empty entries.
*/

package Exceptions;

public class NullorEmptyException extends Exception {
		public NullorEmptyException(String message) {
			super(message);
		}
}
