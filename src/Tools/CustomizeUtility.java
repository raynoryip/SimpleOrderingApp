/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: CustomizeUtility
* @functionality: this class contains all the handy tools required in the MiBay system, all are for customized purpose.
*/

package Tools;

import java.util.Arrays;

import Application.Delivery;
import Package.Customer;
import Package.Package;
import Package.Product;

public class CustomizeUtility {
		
		//------------------------------------------------------------------//
	    //All utilties are written by myself
		//Author: Wailam Yip 
	
		//A method resemble to Python's range() function, generate a list from start to end, excluding end//
		public int[] range(int start, int end) {
			int min, diff;
			if(start>=end) {
				min = end;
			}
			else {
				min = start;
			}
			
			diff = Math.abs(start-end);
			int[] arr = new int[diff];
			for(int index=0;index<diff;index++) {
				arr[index] = min+index;
			}
			return arr;
		}
		
		/*----------------------------------------*/
		//Usage: append a int arr
		public int[] appendIntArr(int num, int[] intArr) {
			int[] newArr = new int[intArr.length+1];
			for(int index=0;index<intArr.length;index++) {
				newArr[index] = intArr[index];
			}
			newArr[intArr.length] = num;
			return newArr;
		}
		/*--------------------------------------------------------------*/
		//Usage: append a Customer arr
		public Customer[] appendCustArr(Customer customer, Customer[] customerList) {
			Customer[] newArr = new Customer[customerList.length+1];
			
			for(int index=0;index<customerList.length;index++) {
				newArr[index] = customerList[index];
			}
			newArr[customerList.length] = customer;
			return newArr;
		}
		/*--------------------------------------------------------------*/
		//Usage: append a Product arr
		public Product[] appendProdArr(Product product, Product[] productList) {
			Product[] newArr = new Product[productList.length+1];
			
			for(int index=0;index<productList.length;index++) {
				newArr[index] = productList[index];
			}
			newArr[productList.length] = product;
			return newArr;
		}
		/*--------------------------------------------------------------*/
		//Usage: append a Package arr
		public Package[] appendPackArr(Package package1, Package[] packageList) {
			Package[] newArr = new Package[packageList.length+1];
			
			for(int index=0;index<packageList.length;index++) {
				newArr[index] = packageList[index];
			}
			newArr[packageList.length] = package1;
			return newArr;
		}
		/*--------------------------------------------------------------*/
		//Usage append a Delivery arr
		public Delivery[] appendDeliArr(Delivery delivery, Delivery[] deliveryList) {
			Delivery [] newArr = new Delivery[deliveryList.length+1];
			
			for(int index=0;index<deliveryList.length;index++) {
				newArr[index] = deliveryList[index];
			}
			newArr[deliveryList.length] = delivery;
			return newArr;
		}
		
		/*----------------------------------------*/
		// Using similar logic in  mergeSort in Python, I customize it with the delivery class
		// 1) Recursive call to get break it down into leftmost and rightmost elements
		// 2) compare which one is larger, and merge them (depends on ascending or descending order)
		// 3) the array returned is changed 
		
		public Delivery[] mergeSortCust(Delivery[] deliveryList, boolean isAscending) {
			if(deliveryList.length<=1) {
				return deliveryList;
			}
			else {
				int mid = deliveryList.length/2;
				
				//Divide into left and right Arr 
				Delivery[] leftArr = Arrays.copyOfRange(deliveryList, 0, mid); //This method is only use for slicing the arr//
				Delivery[] rightArr = Arrays.copyOfRange(deliveryList, mid, deliveryList.length);
				leftArr = mergeSortCust(leftArr, isAscending); 
				rightArr = mergeSortCust(rightArr, isAscending);
				merge(leftArr, rightArr, deliveryList, isAscending); //merge and sort the "divided arrays"
				return deliveryList;
			}
		}
		/*----------------------------------------*/
		private void merge(Delivery[] leftArr, Delivery[] rightArr, Delivery[] deliveryList, boolean isAscending) {
			int leftArrIndex = 0;
			int rightArrIndex = 0;
			
			for(int index=0;index<deliveryList.length;index++) {
				if(leftArr.length > leftArrIndex && rightArr.length > rightArrIndex) {
					String leftName = leftArr[leftArrIndex].getCustomerLastName();
					String rightName = rightArr[rightArrIndex].getCustomerLastName();
					//if it is in ascending order
					if(isAscending) {
						//if leftName is > or =  rightName, we pick rightside
						
						if(isStr1greaterThanStr2(leftName, rightName)) { 
							deliveryList[index] = rightArr[rightArrIndex];
							rightArrIndex += 1;
							continue;
						}
						else {
							deliveryList[index] = leftArr[leftArrIndex];
							leftArrIndex += 1;
							continue;
						}
					}
					//if it is in descending order
					else {  
						//if leftName is > or =  rightName, we pick leftside
						
						if(isStr1greaterThanStr2(leftName, rightName)) {  
							deliveryList[index] = leftArr[leftArrIndex];
							leftArrIndex += 1;
							continue;
						}
						else {
							deliveryList[index] = rightArr[rightArrIndex];
							rightArrIndex += 1;
							continue;
						}
					}
				}
					
				if(leftArr.length > leftArrIndex) {
					deliveryList[index] = leftArr[leftArrIndex];
					leftArrIndex += 1;
				}
				if(rightArr.length > rightArrIndex) {
					deliveryList[index] = rightArr[rightArrIndex];
					rightArrIndex += 1;
				}
			}
		}
		
		/*--------------------------------------------------------------------------------*/
		//Compare two string letter by letter, if it is alphabetically greater return true, return false otherwise
		// e.g gemstone > ada --> true
		// e.g. gemstone > gzstone--> false
		// e.g. gemstone > gemstone --> true
		
		private boolean isStr1greaterThanStr2(String str1, String str2) {
			//Split two words
			String[] s1split = str1.split("");
			String[] s2split = str2.split("");
			int word1Len = s1split.length; int word2Len = s2split.length;
			//we need to know the length to prevent index error
			if(word1Len >= word2Len) {
				for(int index=0;index<s2split.length;index++) {
					if(s1split[index].charAt(0) >s2split[index].charAt(0) ) {
						return true;
					}
					if(s1split[index].charAt(0) == s2split[index].charAt(0)) {
						continue;
					}
					else {
						return false;
					}
				}
			}
			else {
				for(int index=0;index<s1split.length;index++) {
					if(s1split[index].charAt(0) >s2split[index].charAt(0) ) {
						return true;
					}
					if(s1split[index].charAt(0) == s2split[index].charAt(0)) {
						continue;
					}
					else {
						return false;
					}
				}
			}
			return true; 
		}

}
