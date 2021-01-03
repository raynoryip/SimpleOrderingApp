/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: InvalidProcedure
* @functionality: Exception specifically for invalid operations and procedures
*/

package Exceptions;

public class InvalidProcedure extends Exception {
	public InvalidProcedure(String message) {
		super(message);
	}
}
