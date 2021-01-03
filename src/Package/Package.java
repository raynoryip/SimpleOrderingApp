/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: Package
* @functionality: the parent class- Package, and store customer details, with their chosen products
*/

package Package;

import Exceptions.BusinessRuleViolation;
import Exceptions.NullorEmptyException;
import Tools.CustomizeUtility;

public class Package {
	
		protected Customer customer;
		protected Product[] productList = new Product[0];
		protected String memberNumber;
		protected CustomizeUtility cust = new CustomizeUtility();
		
		/*------------------------------------------------------------------------------*/
		public Package(Customer customer, Product product) throws NullorEmptyException {
				if(customer!=null) {
					this.customer = customer;
				}
				else {
					throw new NullorEmptyException("[ Error: Customer could not be null value ]\n");
				}
				
				if(product!=null) {
					this.productList = cust.appendProdArr(product, this.productList);
				}
				else {
					throw new NullorEmptyException("[ Error: Product could not be null value ]\n");
				}
		}
		/*------------------------------------------------------------------------------*/
		public boolean addProduct(Product product) throws BusinessRuleViolation {
				
				if(sameProductExist(product)) {
					throw new BusinessRuleViolation("You cannot add exactly same product");
				}
				else {
					this.productList = cust.appendProdArr(product, this.productList);
					return true;
				}
		}
		/*------------------------------------------------------------------------------*/
		private boolean sameProductExist(Product product) {
			for(Product prod: this.productList) {
				if(prod.getName().equalsIgnoreCase(product.getName())) {
					if(prod.getWeight()==product.getWeight() && prod.getCost()==prod.getCost()) {
						return true;
					}
				}
			}
			return false;
		}
		
		/*------------------------------------------------------------------------------*/
		/* Possibilties:
		 * 1) if the removing product name exist in the array --> 
		 * 				if there is only one instance --> remove
		 * 				if there are duplicates --> remove the one prior in the array 
		 * 				return true
		 * 2) if the removing product name doesn't exist in the array -->
		 * 				return false
		*/
		public boolean removeProduct(Product product) throws BusinessRuleViolation {
			int arrLen = this.productList.length;
			if(arrLen==1) {
				throw new BusinessRuleViolation("You should have at least 1 product in your package");
			}
			
			for(int index=0;index<arrLen;index++) {
				if(product.getName().equalsIgnoreCase(this.productList[index].getName())) { 
					this.productList = removeArr(product, index);
					return true;
				}
			}
			return false;
		}	
		/*------------------------------------------------------------------------------*/
		/*This method copy all products in the original array to a new array.
		 * and then return the newArr. 
		 * To prevent removing duplicated items, we will only remove the same named item once.*/
		
		protected Product[] removeArr(Product product, int targetIndex) throws BusinessRuleViolation {
			int arrLen = this.productList.length;
			Product[] newArr = new Product[arrLen-1];
			if(arrLen<= 1) {
				throw new BusinessRuleViolation("[ Error: There must be at least 1 product in the list ]\n");
			}
			else {
				int newArrIndex = 0;
				for(int index=0;index<arrLen;index++) {
						if(index!=targetIndex) {
							newArr[newArrIndex] = this.productList[index];
							newArrIndex+=1;}
				}
				return newArr;
			}
		}
		/*------------------------------------------------------------------------------*/
		public String getDetails() {
			String header = "-----------------------\nPackage Info\n-----------------------\n";
			String custDetail = this.customer.getDetails();
			String productDetail = "Product Ordered:\n";
			String temp = "";
			for(int index=0;index<this.productList.length;index++) {
				temp = "Product " + (index+1) + "\n"; 
				productDetail += temp + productList[index].getDetails() +"\n";
			}
			return header + custDetail + productDetail;
		}
		
		/*------------------------------------------------------------------------------*/
		public String toString() {
			String custDetail = this.customer.toString();
			String productDetail = "";
			for(int index=0;index<this.productList.length;index++) {
				if(index==0) {
					productDetail += this.productList[index].toString();
				}
				else {
					productDetail += ":" + this.productList[index].toString();
				}
			}
			return custDetail+"\n"+productDetail + "\n" + this.memberNumber;
			
		}
		/*------------------------------------------------------------------------------*/
		public String retrieveCustName() {
			return this.toString().split(":")[0] + " " + this.toString().split(":")[1];
		}
		
		/*------------------------------------------------------------------------------*/
		public Product[] retrieveProductList() {
			return this.productList;
		}
		
		
}
