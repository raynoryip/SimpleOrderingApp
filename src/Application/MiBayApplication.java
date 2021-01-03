/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: MiBayApplication
* @functionality: store all the details into arrays after user input their values in the menu class, and perform many operations 
*/

package Application;

import Exceptions.BusinessRuleViolation;
import Exceptions.InvalidEntry;
import Exceptions.InvalidProcedure;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;
import Package.Address;
import Package.Customer;
import Package.Product;
import Package.Package;
import Package.PlatinumPackage;
import Tools.CustomizeUtility;
import Tools.DateTime;

public class MiBayApplication {
	
		private Delivery[] deliveryList = new Delivery[0];
		private Package[] packageList = new Package[0];
		private Customer[] customerList = new Customer[0];
		private Product[] productList = new Product[0];
		private CustomizeUtility custUtil = new CustomizeUtility();
	
		/*--------------------------------------------------------------*/
		//Storing customers into MiBayApplication//
		public void addCustomerMi(Customer customer)  {
			this.customerList = custUtil.appendCustArr(customer, this.customerList);
		}
		/*--------------------------------------------------------------*/
		//Overloading as if there is an alternate Address//
		public void addCustomerMi(Customer customer, Address SecondAddress) throws Max2AddressException  {
			
			customer.addAddress(SecondAddress);
			this.customerList = custUtil.appendCustArr(customer, this.customerList);
		}
		/*--------------------------------------------------------------*/
		//Storing products into MiBayApplication//
		public void addProductMi(Product product) throws BusinessRuleViolation {
			if(sameProductExist(product)) {
				throw new BusinessRuleViolation("The prioduct is already exist in the array");
			}
			else {
				this.productList = custUtil.appendProdArr(product, this.productList);
			}
		}
		/*--------------------------------------------------------------*/
		//Usage: check if the same product already exist in this list
		public boolean sameProductExist(Product product) {
			for(Product prod: this.productList) {
				if(prod.getName().equalsIgnoreCase(product.getName())) {
					if(prod.getWeight()==product.getWeight() && prod.getCost()==prod.getCost()) {
						return true;
					}
				}
			}
			return false;
		}
		
		/*--------------------------------------------------------------*/
		//Usage: Pass all the package detail and delivery date into the Delivery array and Package array in this class 
		public void addPackageMi(Customer customer, Product[] custProductList, DateTime deliveryDate) throws 
																																			NullorEmptyException, BusinessRuleViolation, InvalidEntry {
				Package normPackage;
				Delivery delivery;
				
				normPackage = new Package(customer, custProductList[0]);
				for(int index=1;index<custProductList.length;index++) {
					normPackage.addProduct(custProductList[index]);
				}
				this.packageList = custUtil.appendPackArr(normPackage, this.packageList);
				delivery = new Delivery(normPackage, deliveryDate);
				this.deliveryList = custUtil.appendDeliArr(delivery, this.deliveryList);
		}
		
		/*--------------------------------------------------------------*/
		//Usage: same as above, but this one is for platinum member
		public void addPackageMi(Customer customer, Product[] custProductList, DateTime deliveryDate, String memberNum) throws 
																																			NullorEmptyException, BusinessRuleViolation, InvalidEntry {
	
				PlatinumPackage platPackage;
				Delivery delivery;
				
				platPackage = new PlatinumPackage(customer, custProductList[0], memberNum);
				for(int index=1;index<custProductList.length;index++) {
						platPackage.addProduct(custProductList[index]);
				}
				this.packageList = custUtil.appendPackArr(platPackage, this.packageList);
				delivery = new Delivery(platPackage, deliveryDate);
				this.deliveryList = custUtil.appendDeliArr(delivery, this.deliveryList);
		}
		
		/*--------------------------------------------------------------*/
		//Retrieve the customer list in this system
		public Customer[] retrieveCustList() {
			return this.customerList;
		}
		/*--------------------------------------------------------------*/
		//Retrieve the product list in this system
		public Product[] retrieveProdList() {
			return this.productList;
		}
		/*--------------------------------------------------------------*/
		//Retrieve the product list in this system
		public Package[] retrievePackList() {
			return this.packageList;
		}
		/*--------------------------------------------------------------*/
		//Retrieve the deliveries list in this system
		public Delivery[] retrieveDeliList() {
			return this.deliveryList;
		}
		/*--------------------------------------------------------------*/
		//Retrieve customers in string format
		public String retrieveCust() {
			String custStr = "";
			for(int index=0;index<this.customerList.length;index++) {
				String[] name = this.customerList[index].toString().split(":");
				String firstName = name[0];
				String lastName = name[1];
				custStr += index+1 + ". " + firstName + " " + lastName + "\n";
			}
			return custStr;
		}
		/*--------------------------------------------------------------*/
		//Retrieve products in string format
		public String retreiveProd() {
			String custStr = "";
			for(int index=0;index<this.productList.length;index++) {
				String[] name = this.productList[index].toString().split(":");
				String productName = name[0];
				custStr += index+1 + ". " + productName + "\n";
			}
			return custStr;
		}
		/*--------------------------------------------------------------*/
		//Retrieve delivery details in string format
		public String retreiveDeli() {
			String details = "";
			for(Delivery delivery:this.deliveryList) {
				details += delivery.getDetails();
			}
			return details;
		}
		/*--------------------------------------------------------------*/
		//Retrieve packages details in string format
		public String retreivePack() {
			String details = "";
			for(Package pack:this.packageList) {
				details += pack.getDetails();
			}
			return details;
		}
		/*--------------------------------------------------------------*/
		// A method for outputing desire format of customer array: 
		// e.g. Elise Caldwell:327-Lonsdale St-Melbourne-3000:N/A
		public String custToStringEX() {
			String details = "";
			for(Customer cust: this.customerList) {
				details += cust.toString() + "\n";
			}
			details += "----------";
			return details;
		}
		/*--------------------------------------------------------------*/
		// A method for outputing desire format of product array:
		// e.g Universal Phone Grip Holder:0.5:14.99
		public String prodToStringEX() {
			String details = "";
			for(Product prod: this.productList) {
				details += prod.toString() + "\n";
			}
			return details;
		}
		/*--------------------------------------------------------------*/
		//Usage: revert the data back to the array
		// saved format: 	Elise Caldwell:327-Lonsdale St-Melbourne-3000:N/A
		//  							Declan Stuart:328-Swanston St-Melbourne-3000:330-Swanston St-Melbourne-3000
		
		public void revertCustArray(String custDetails) throws Max2AddressException, NullorEmptyException, InvalidEntry {
				String[] firstBreak = custDetails.split(":");
				
				String firstName = firstBreak[0].split(" ")[0];
				String lastName = firstBreak[0].split(" ")[1];
				
				String priAddStNo = firstBreak[1].split("-")[0];
				String priAddStName = firstBreak[1].split("-")[1];
				String priAddSub = firstBreak[1].split("-")[2];
				String priAddPos = firstBreak[1].split("-")[3];
				
				Address priAdd = new Address(priAddStNo, priAddStName, priAddSub, priAddPos);
				Customer customer = new Customer(firstName, lastName, priAdd);
				
				Address altAdd;
				if(!(firstBreak[2].equals("N/A"))) {
					String altAddStNo = firstBreak[2].split("-")[0];
					String altAddStName = firstBreak[2].split("-")[1];
					String altAddSub = firstBreak[2].split("-")[2];
					String altAddPos = firstBreak[2].split("-")[3];
					altAdd = new Address(altAddStNo, altAddStName, altAddSub, altAddPos);
					customer.addAddress(altAdd);
				}
				
				this.customerList = custUtil.appendCustArr(customer, this.customerList);
		}
		
		/*--------------------------------------------------------------*/
		//Usage: revert the data back to the array
		// saved format: 	Universal Phone Grip Holder:0.5:14.99
		
		public void revertProdArray(String prodDetails) throws Max2AddressException, NullorEmptyException, InvalidEntry {
			String[] firstBreak = prodDetails.split(":");
			String prodName = firstBreak[0];
			double weight = Double.parseDouble(firstBreak[1]);
			double cost = Double.parseDouble(firstBreak[2]);
			
			Product product = new Product(prodName, weight, cost);
			this.productList = custUtil.appendProdArr(product, this.productList);
		}
		
	
}
