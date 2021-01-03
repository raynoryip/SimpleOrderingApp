/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: InvalidEntry
* @functionality: Exception specifically for invalid entry
*/


package Exceptions;

public class InvalidEntry extends Exception{
		public InvalidEntry(String message) {
			super(message);
		}
	
}
