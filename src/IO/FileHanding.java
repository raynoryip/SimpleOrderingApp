/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: FileHanding
* @functionality: A class to perform IO actions (write and read)
*/

package IO;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import Application.MiBayApplication;
import Exceptions.InvalidEntry;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;

public class FileHanding {
		public void writeInData(MiBayApplication app, String name) throws IOException {
		
				File file = new File(name);
				FileWriter write = new FileWriter(file);
				PrintWriter printw = new PrintWriter(write);
				
				printw.println(app.custToStringEX());
				printw.println(app.prodToStringEX());
				printw.close();
		}
		
		public void extractData(MiBayApplication app, File file) throws FileNotFoundException, Max2AddressException, NullorEmptyException, InvalidEntry {
				
					Scanner fileRead = new Scanner(file);
					
					int index = 0;
					boolean reachSplitLine = false;

					
					while(fileRead.hasNextLine()) {
						String text = fileRead.nextLine();
						System.out.println(text);
						if(text.equals("")){
							fileRead.close();
							return;
						}
						
						if(reachSplitLine==false) {
							if(text.equals("----------")) {
								reachSplitLine = true;
								continue;
							}
							else {
								loadCust(app, text);
							}
						}
						
						else {
							loadProd(app, text);
						}
					}
		}
		
		private void loadCust(MiBayApplication app, String line) throws Max2AddressException, NullorEmptyException, InvalidEntry {
				app.revertCustArray(line);
		}
		
		private void loadProd(MiBayApplication app, String line) throws Max2AddressException, NullorEmptyException, InvalidEntry {
			app.revertProdArray(line);
	}
}
