/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: Main
* @functionality: -- 
*/

package Main;

import java.io.IOException;

import Application.Menu;
import Exceptions.BusinessRuleViolation;
import Exceptions.InvalidEntry;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;
import Tools.CustomizeUtility;

public class Main {
	
	public static void main(String[] args) throws InvalidEntry, NullorEmptyException, Max2AddressException, BusinessRuleViolation, IOException {
		Menu test = new Menu();
		test.run();
	}

}
