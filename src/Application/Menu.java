/* Programming Technique Assignment 2
 * Project Name: MiBay
* @author Wailam Yip (s3598673)
* @version 1.0
* @since 2019-10-01
* @Method Name: Menu
* @functionality: A simple menu class to display the menu and ask for user input for different functions, also load up the data file.
*/


package Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import Exceptions.BusinessRuleViolation;
import Exceptions.InvalidEntry;
import Exceptions.InvalidProcedure;
import Exceptions.Max2AddressException;
import Exceptions.NullorEmptyException;
import IO.FileHanding;
import Package.Package;
import Package.Address;
import Package.Customer;
import Package.Product;
import Tools.CustomizeUtility;
import Tools.DateTime;

public class Menu {

	private MiBayApplication app = new MiBayApplication();
	private CustomizeUtility custUtil = new CustomizeUtility();
	private Scanner scan = new Scanner(System.in);
	private FileHanding fileP = new FileHanding();

	/*--------------------------------------------------------------*/
	// Main method in the menu class
	// Usage: load data, Initiate the menu
	
	public void run() throws NullorEmptyException, BusinessRuleViolation, InvalidEntry, Max2AddressException, IOException {
			
			String input = "";

			File priFile = new File("Data.txt");
			File bFile = new File("Backup.txt");
			boolean pExists = priFile.exists();
			boolean bExists = bFile.exists();
			
			//if both file exists, which means it is not the first time running the code//
			if(pExists&&bExists) {
				fileP.extractData(this.app, priFile);
				System.out.println("Your data has been loaded to the system");
			}
			else if(pExists==false&&bExists==true) { 		// only load on backup File//
				fileP.extractData(this.app, bFile);
				System.out.println("Your data was loaded from the backUp File.");
			}
	
			while(true) {
				
				System.out.println("*** MiBayApplication System Menu ***");
				System.out.println(String.format("%-50s%s", "Add Customer", "AC"));
				System.out.println(String.format("%-50s%s", "Edit Customer", "EC"));
				System.out.println(String.format("%-50s%s", "Add Product", "AP"));
				System.out.println(String.format("%-50s%s", "Remove Product from Package", "RP"));
				System.out.println(String.format("%-50s%s", "Prepare Order", "PP"));
				System.out.println(String.format("%-50s%s", "Display ALL Deliveries (Sorted by Name)", "DA"));
				System.out.println(String.format("%-50s%s", "Delivery Search (display deliveries on date)", "DS"));
				System.out.println(String.format("%-50s%s", "Seed Data", "SD"));
				System.out.println(String.format("%-50s%s", "Exit Program", "EX"));
				System.out.print("\nEnter selection:	");
				
				input = scan.nextLine();
				
				try {
					inputValid(input);
				}catch(InvalidEntry e){
					System.out.println(e.getMessage());
					continue;
				}
				
				if(input.equalsIgnoreCase("ac")){
					addCustomer();
					continue;
				}
				if(input.equalsIgnoreCase("ec")){
					editCustomer();
					continue;
				}
				if(input.equalsIgnoreCase("ap")) {
					addProduct();
					continue;
				}
				if(input.equalsIgnoreCase("rp")) {
					removeProduct();
					continue;
				}
				if(input.equalsIgnoreCase("pp")) {
					prepareOrder();	
					continue;
				}
				if(input.equalsIgnoreCase("da")) {
					displayDelivery();		
					continue;
				}
				if(input.equalsIgnoreCase("ds")) {
					deliverySearch();		
					continue;
				}
				if(input.equalsIgnoreCase("sd")) {
					seedData();		
					continue;
				}
				if(input.equalsIgnoreCase("ex")){
					this.scan.close();
					fileP.writeInData(this.app, "Data.txt");
					fileP.writeInData(this.app, "Backup.txt");
					System.exit(1);
				}
			}
		}

	/*--------------------------------------------------------------*/
	// A sub method of run()
	// To validate the input
	
	private boolean inputValid(String input) throws InvalidEntry {

		String[] selection = { "ac", "ec", "ap", "pp", "rp", "da", "ds", "sd", "ex" };
		boolean found = false;
		for (String ele : selection) {
			if (ele.equalsIgnoreCase(input)) {
				return true;
			}
		}
		if (found == false) {
			throw new InvalidEntry("[ Error: You entry is not recognized by the system ]\n");
		}
		return false;
	}

	/*--------------------------------------------------------------*/
	// AddCustomer() --> Collect user input and store them into the MiBayApplication 

	private void addCustomer() throws Max2AddressException {

		String firstName, lastName, streetNo, streetName, suburb, postcode;
		String streetNo2, streetName2, suburb2, postcode2;

		System.out.println("[ ------- Add Customer------- ]");
		System.out.println("Enter customer details:");
		System.out.print("Enter first name:");
		firstName = this.scan.nextLine();
		System.out.print("Enter last name:");
		lastName = this.scan.nextLine();

		System.out.print("Enter street number:");
		streetNo = this.scan.nextLine();
		System.out.print("Enter street name:");
		streetName = this.scan.nextLine();
		System.out.print("Enter suburb:");
		suburb = this.scan.nextLine();
		System.out.print("Enter postcode:");
		postcode = this.scan.nextLine();

		System.out.println();
		String wantAlternate = "";
		do {
			System.out.print("Do you want to enter an alternate address? (Y/N): ");
			wantAlternate = scan.nextLine();
			if (!(wantAlternate.equalsIgnoreCase("Y") || wantAlternate.equalsIgnoreCase("N"))) {
				System.out.println("Please only enter Y or N (Case ignored)");
			}
			System.out.println();
		} while (!(wantAlternate.equalsIgnoreCase("Y") || wantAlternate.equalsIgnoreCase("N")));

		if (wantAlternate.equalsIgnoreCase("Y")) {
			System.out.print("Enter street number:");
			streetNo2 = this.scan.nextLine();
			System.out.print("Enter street name:");
			streetName2 = this.scan.nextLine();
			System.out.print("Enter suburb:");
			suburb2 = this.scan.nextLine();
			System.out.print("Enter postcode:");
			postcode2 = this.scan.nextLine();

			try {
				Address priAddress = new Address(streetNo, streetName, suburb, postcode);
				Address altAddress = new Address(streetNo2, streetName2, suburb2, postcode2);
				Customer customer = new Customer(firstName, lastName, priAddress);
				this.app.addCustomerMi(customer, altAddress);

			} catch (NullorEmptyException e) {
				System.out.println(e.getMessage());
				addCustomer();
			} catch (InvalidEntry e) {
				System.out.println(e.getMessage());
				return;
			}
		} else {
			try {
				Address priAddress = new Address(streetNo, streetName, suburb, postcode);
				Customer customer = new Customer(firstName, lastName, priAddress);
				this.app.addCustomerMi(customer);

			} catch (NullorEmptyException e) {
				System.out.println(e.getMessage());
				addCustomer();
			} catch (InvalidEntry e) {
				System.out.println(e.getMessage());
				addCustomer();
			}
		}
		System.out.println(firstName + " " + lastName + " was successfully added to the system.");
		System.out.println(" -- Press enter to return to the main Menu -- \n");
		scan.nextLine();
		return;
	}

	/*--------------------------------------------------------------*/
	// EditCustomer() --> Collect user input and store them into the
	// MiBayApplication Class
	
	private void editCustomer() {
		try {
			if (this.app.retrieveCustList().length == 0)
				throw new InvalidProcedure(
						"[ Wrong Procedure : You should Add Customer first in order to use this function ]\n");
		} catch (InvalidProcedure e) {
			System.out.println(e.getMessage());
			return;
		}

		Customer[] custList = this.app.retrieveCustList();
		for (int index = 0; index < custList.length; index++) {
			System.out.println("Customer " + index);
			System.out.println(custList[index].getDetails());
		}

		int choice = -1;
		System.out.println("which customer would you like to edit?");
		System.out.print("Your choice: ");
		choice = this.scan.nextInt();
		this.scan.nextLine(); // consume the nextInt Line//
		try {
			if (!(choice <= custList.length && choice >= 0)) {
				throw new InvalidEntry("Your selection is not recognized by the system");
			}

			Customer customer = custList[choice];
			System.out.println("Customer" + choice + " was selected.");
			System.out.println("---------------------");
			System.out.println("what operation you would like to perform");
			System.out.println("1. Edit Name");
			System.out.println("2. Edit Address ");
			System.out.print("Your choice: ");
			choice = this.scan.nextInt();
			this.scan.nextLine(); // consume the nextInt Line//
			if (!(choice == 1 || choice == 2)) {
				throw new InvalidEntry("Your selection is not recognized by the system");
			}
			if (choice == 1) {
				String firstName = "";
				String lastName = "";
				System.out.print("Please enter a firstName: ");
				firstName = this.scan.nextLine();
				System.out.println("Please enter a lastName: ");
				lastName = this.scan.nextLine();
				customer.editName(firstName, lastName);
				System.out.println("Customer: " + firstName + " " + lastName + " was updated");
				System.out.println(" -- Press enter to return to the main Menu -- \n");
				scan.nextLine();
				return;
			}
			if (choice == 2) {

				// first confirm how many addresses are there in that customer//
				int numAdd = customer.howManyAddress() - 1;

				String streetNo = "";
				String streetName = "";
				String suburb = "";
				String postcode = "";
				int addChoice = -1;

				System.out.println("which address do you want to edit: ");
				System.out.println(customer.getDetails("Address"));
				System.out.print("Your choice: ");
				addChoice = this.scan.nextInt();
				this.scan.nextLine();
				if (!(addChoice <= numAdd && addChoice >= 0)) {
					throw new InvalidEntry("Your selection is not recognized by the system");
				}
				System.out.println("Please enter a new address");
				System.out.print("Enter street number:");
				streetNo = this.scan.nextLine();
				System.out.print("Enter street name:");
				streetName = this.scan.nextLine();
				System.out.print("Enter suburb:");
				suburb = this.scan.nextLine();
				System.out.print("Enter postcode:");
				postcode = this.scan.nextLine();

				Address address = new Address(streetNo, streetName, suburb, postcode);
				if (addChoice == 1) {
					customer.editAddress(address, false);
				} else {
					customer.editAddress(address, true);
				}
				System.out.println("Your Address has been updated");
			}

		} catch (InvalidEntry e) {
			System.out.println(e.getMessage());
			return;
		} catch (NullorEmptyException e) {
			System.out.println(e.getMessage());
			return;
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			return;
		}
	}
	/*--------------------------------------------------------------*/
	// AddProduct() --> Collect user input and store them into the MiBayApplication
	// Class

	private void addProduct() throws BusinessRuleViolation {

		String productName;
		double weight = 0;
		double cost = 0;
		String addMoreProduct = "";

		do {
			System.out.println("[ ------- Add Product ------- ]");
			System.out.println("Enter product details:");
			System.out.print("Enter name:");
			productName = this.scan.nextLine();
			try {
				System.out.print("Enter weight:");
				weight = this.scan.nextDouble();
				System.out.print("Enter cost:");
				cost = this.scan.nextDouble();
				this.scan.nextLine();
			} catch (InputMismatchException e) {
				this.scan.nextLine();
				System.out.println("[ You should only input a number ]\n");
				addProduct();
			}

			try {
				Product product = new Product(productName, weight, cost);
				this.app.addProductMi(product);

			} catch (InvalidEntry e) {
				System.out.println(e.getMessage());
				return;
			} catch (NullorEmptyException e) {
				System.out.println(e.getMessage());
				return;
			}
			System.out.println(productName + " was successfully added to the system.\n");
			System.out.print("Do you want to add more product? (Y/N): ");
			addMoreProduct = this.scan.nextLine();

		} while (!(addMoreProduct.equalsIgnoreCase("N")));

		System.out.println(" -- Press enter to return to the main Menu -- \n");
		scan.nextLine();
		return;
	}

	/*--------------------------------------------------------------*/
	// Usage: 1) to remove a product from the prod List
	//				 2) remove a product from a package
	
	private void removeProduct() {
		System.out.println("[ ------- Remove Product ------- ]");

		try {

			if (this.app.retrieveDeliList().length == 0) {
				throw new InvalidProcedure("You must at least have 1 product to perform this operations");
			}

			System.out.println("Please select a service: ");
			System.out.println("1. remove a product from the product List by input the name of that product");
			System.out.println("2. remove a product from a package");
			System.out.println("3 return to main menu");
			int choice = this.scan.nextInt();
			this.scan.nextLine();
			if (choice != 1 || choice != 2 || choice != 3) {
				System.out.println("--> Sorry wrong input <--\n");
				return;
			}

			if (choice == 3) {
				return;
			}

			if (choice == 2) {

				System.out.println("Please enter your name to locate a package");
				System.out.print("First Name: ");
				String firstName = this.scan.nextLine();
				System.out.print("Last Name: ");
				String lastName = this.scan.nextLine();

				// Assume customers can't have same name//
				String name = firstName + " " + lastName;
				Delivery[] deliveryList = this.app.retrieveDeliList();
				String packageName = "";
				int targetIndex = 0;

				for (int index = 0; index < deliveryList.length; index++) {
					packageName = deliveryList[index].retrievePackage().retrieveCustName();
					System.out.println(packageName);
					if (name.equalsIgnoreCase(packageName)) {
						targetIndex = index;
						break;
					}
				}

				System.out.println("\n [-- Here is your package detail --] \n");
				System.out.println(deliveryList[targetIndex].getDetails());
				System.out.println("Which product do you want to remove: ");
				int productLen = deliveryList[targetIndex].retrievePackage().retrieveProductList().length;
				choice = this.scan.nextInt();
				this.scan.nextLine();
				if (!(choice > 0 && choice <= productLen)) {
					throw new InvalidEntry("Your selection is not recognized by the system");
				}
				Product[] array = deliveryList[targetIndex].retrievePackage().retrieveProductList();
				if (deliveryList[targetIndex].retrievePackage().removeProduct(array[choice - 1])) {
					System.out.println("Product remove successful. ");
				} else {
					System.out.println("Product remove failed.");
				}
			}

			else {

				String prodName = "";
				System.out.print("Please enter a Product Name: ");
				prodName = this.scan.nextLine();

				Product[] prodList = this.app.retrieveProdList();
				int targetIndex = 0;
				boolean found = false;
				for (int index = 0; index < prodList.length; index++) {
					if (prodList[index].getName().equalsIgnoreCase(prodName)) {
						targetIndex = index;
						found = true;
						break;
					}
				}

				if (found) {
					prodList = removeProductFromList(prodList, targetIndex);
					System.out.println("Product has been successfully removed from the array");
				} else {
					System.out.println("Product not found");
				}
			}

			System.out.println(" -- Press enter to return to the main Menu -- \n");
			scan.nextLine();
			return;
		} catch (InputMismatchException e) {
			System.out.println(e.getMessage());
			return;
		} catch (InvalidEntry e) {
			System.out.println(e.getMessage());
			return;
		} catch (BusinessRuleViolation e) {
			System.out.println(e.getMessage());
			return;

		} catch (InvalidProcedure e) {
			System.out.println(e.getMessage());
			return;
		}
	}

	/*--------------------------------------------------------------*/
	// A sub method of removeProduct() above
	//Usage: generate a new arr without specific index element and return the new arr
	
	private Product[] removeProductFromList(Product[] prodList, int targetIndex) {
		Product[] newArr = new Product[prodList.length - 1];
		int newArrIndex = 0;
		for (int index = 0; index < prodList.length; index++) {
			if (index != targetIndex) {
				newArr[newArrIndex] = prodList[index];
				newArrIndex += 1;
			}
		}
		return newArr;
	}

	/*--------------------------------------------------------------*/
	/*
	 * A sub method of run() PrepareOrder() --> Collect user input and store them
	 * into the MiBayApplication Class, this method is super long, so i split it
	 * into several sub-methods.
	 */

	private void prepareOrder() throws NullorEmptyException, BusinessRuleViolation, InvalidEntry, Max2AddressException, IOException {

		try {

			if (this.app.retrieveCustList().length == 0) {
				throw new InvalidProcedure(
						"[ Wrong Procedure : You should Add Customer first in order to use this function ]\n");
			}
			if (this.app.retrieveProdList().length == 0) {
				throw new InvalidProcedure(
						"[ Wrong Procedure : You should Add Product first in order to use this function ]\n");
			}
		} catch (InvalidProcedure e) {
			System.out.println(e.getMessage());
			return;
		}
		System.out.println("[ ------- Prepare Order ------- ]");
		// we get customer option and convert it to a customer class//
		int custChoice = prepareOrderCust();
		Customer customer = this.app.retrieveCustList()[custChoice - 1];

		int[] prodChoiceList = new int[0];
		int custProdChoice = 0;

		// we do a do-while loop to get products//
		String response = "";
		do {
			response = "";
			custProdChoice = prepareOrderProd(prodChoiceList);
			// if the choice is valid then we append it into the prodChoiceList, and updates it
			prodChoiceList = custUtil.appendIntArr(custProdChoice, prodChoiceList);
			System.out.println("Would you like to add another product? Press N to exit, anything to continue): ");
			response = scan.nextLine();
			if(response.equalsIgnoreCase("N")) {
				break;
			}
		} while (true);

		// Convert the productChoice List into a productList//
		Product[] cusProductList = new Product[prodChoiceList.length];
		int listIndex = 0;
		Product[] retrieveList = this.app.retrieveProdList();
		for (int choice : prodChoiceList) {
			cusProductList[listIndex] = retrieveList[choice - 1];
			listIndex += 1;
		}

		// After we get products, we allow user to select their delivery date//
		DateTime deliverydate;
		deliverydate = prepareOrderDeli();

		// We ask user if that is a platinum package//
		String response2 = "N";

		System.out.println("Is this a Platinum Package (N for No or anything for Yes): ");
		response2 = this.scan.nextLine();

		if (!(response2.equalsIgnoreCase("N"))) {
			String memberNum;
			System.out.print("Please input your member number:");
			memberNum = scan.nextLine();
			System.out.println();
			this.app.addPackageMi(customer, cusProductList, deliverydate, memberNum);
			System.out.println("Your Platinum Package was successfully added to the system");
		} 
		else 
		{
			this.app.addPackageMi(customer, cusProductList, deliverydate);
			System.out.println("Your Package was successfully added to the system");
		}

		System.out.println(" -- Press enter to return to the main Menu -- \n");
		scan.nextLine();
		return;

	}
	/*--------------------------------------------------------------*/
	// A sub method of PrepareOrder() --> Customer

	private int prepareOrderCust() throws NullorEmptyException, BusinessRuleViolation, InvalidEntry, Max2AddressException, IOException {
		int custChoice = 0;
		System.out.println("Please choose a customer from the list:");
		System.out.println(this.app.retrieveCust());
		System.out.print("input: ");
		try {
			custChoice = scan.nextInt();
			this.scan.nextLine();
			if (!(choiceOnList(custChoice, this.app.retrieveCustList().length))) {
				throw new InvalidEntry("[ Error: You do not have such customer ]\n");
			}
		} catch (InputMismatchException e) {
			System.out.println("[ Input Mismatch Error: You should input a number ]\n");
			run();
		} catch (InvalidEntry e) {
			System.out.println(e.getMessage());
			run();
		}

		return custChoice;
	}
	/*--------------------------------------------------------------*/
	// A sub method of PrepareOrder() --> Product
	// Here we validate 1) if the user choice was on the list
	// 2) the choice not yet been chose

	public int prepareOrderProd(int[] prodChoiceList) throws NullorEmptyException, BusinessRuleViolation, InvalidEntry, Max2AddressException, IOException {
		int prodChoice = 0;

		System.out.println("");
		System.out.println("Please choose a product from the list:");
		System.out.println(this.app.retreiveProd());
		System.out.print("input: ");
		try {

			prodChoice = scan.nextInt();
			scan.nextLine();

			if (!(choiceOnList(prodChoice, this.app.retrieveProdList().length))) {
				throw new InvalidEntry("[ Error: You do not have such product ]\n");
			}
			if (!(choiceNotChosen(prodChoice, prodChoiceList))) {
				throw new InvalidEntry("[ Error: You have already choose that product ]\n");
			}
		} catch (InputMismatchException e) {
			System.out.println("[ Input Mismatch Error: You should input a number ]\n");
			run();
		} catch (InvalidEntry e) {
			System.out.println(e.getMessage());
			run();
		}
		return prodChoice;
	}

	/*--------------------------------------------------------------*/
	/*
	 * Sub methods of PrepareOrder() to validate if the user inputs correct choices:
	 * 1)the first method validate the choice is on the list, and 2)the second
	 * method validate if the choice has not yet been chosen fulfilling these 2
	 * options makes a valid choice.
	 */

	private boolean choiceOnList(int choice, int arrLen) {
		int[] listOfChoice = custUtil.range(1, arrLen + 1);
		for (int index = 0; index < listOfChoice.length; index++) {
			if (choice == listOfChoice[index]) {
				return true;
			}
		}
		return false;
	}

	private boolean choiceNotChosen(int choice, int[] choiceArr) {
		if (choiceArr.length == 0) {
			return true;
		}
		// If choice appears in the choice Arr, return false//
		for (int index = 0; index < choiceArr.length; index++) {
			if (choice == choiceArr[index]) {
				return false;
			}
		}
		return true;
	}
	/*--------------------------------------------------------------*/
	// A sub method of PrepareOrder() --> DeliveryDate

	private DateTime prepareOrderDeli() throws NullorEmptyException, BusinessRuleViolation, InvalidEntry, Max2AddressException, IOException {

		DateTime deliveryDate = new DateTime();
		int day = 0;
		int month = 0;
		int year = 0;

		System.out.println("");
		System.out.println("Please enter the delivery date:");
		try {
			System.out.print("Enter Day: ");
			day = scan.nextInt();
			System.out.print("Enter Month: ");
			month = scan.nextInt();
			System.out.print("Enter Year: ");
			year = scan.nextInt();
			this.scan.nextLine();

			deliveryDate = new DateTime(day, month, year);
			DateTime currentDate = new DateTime();
			int diff = DateTime.diffDays(deliveryDate, currentDate);

			if (!(diff >= 0 && diff <= 30)) {
				throw new BusinessRuleViolation("Your date must not be a past date or longer 30 days");
			}

		} catch (InputMismatchException e) {
			System.out.println("[ Input Mismatch Error: You should input a valid date ]\n");
			run();
		} catch (BusinessRuleViolation e) {
			System.out.println(e.getMessage());
			run();
		}

		return deliveryDate;
	}

	/*--------------------------------------------------------------*/
	// Seed Data
	private void seedData() throws NullorEmptyException, BusinessRuleViolation, InvalidEntry, Max2AddressException {

		Customer[] custList = new Customer[5];

		Address address1 = new Address("327", "Lonsdale St", "Melbourne", "3000");
		Address address2 = new Address("328", "Swanston St", "Melbourne", "3000");
		Address address3 = new Address("329", "Lonsdale St", "Melbourne", "3000");
		Address address4 = new Address("330", "Swanston St", "Melbourne", "3000");
		Address address5 = new Address("331", "Lonsdale St", "Melbourne", "3000");

		Customer customerA = new Customer("Elise", "Caldwell", address1);
		Customer customerB = new Customer("Declan", "Stuart", address2);
		customerB.addAddress(address4);
		Customer customerC = new Customer("Anika", "Ferguson", address3);
		customerC.addAddress(address5);
		Customer customerD = new Customer("Robbie", "Porter", address4);
		customerD.addAddress(address2);
		Customer customerE = new Customer("Ismail", "Morgan", address5);
		customerE.addAddress(address1);

		custList[0] = customerA;
		custList[1] = customerB;
		custList[2] = customerC;
		custList[3] = customerD;
		custList[4] = customerE;

		for (Customer cust : custList) {
			this.app.addCustomerMi(cust);
		}

		Product[] prodList = new Product[7];

		Product product1 = new Product("Universal Phone Grip Holder", 0.5, 14.99);
		Product product2 = new Product("Personal Humidifier", 10, 49.99);
		Product product3 = new Product("Bed Sheet", 3.2, 5.69);
		Product product4 = new Product("Headphones", 0.1, 24.99);
		Product product5 = new Product("Lenovo ThinkPad P51", 2.5, 849.00);
		Product product6 = new Product("Perfume", 0.65, 30.56);
		Product product7 = new Product("Polo Shirt", 0.2, 17.59);

		prodList[0] = product1;
		prodList[1] = product2;
		prodList[2] = product3;
		prodList[3] = product4;
		prodList[4] = product5;
		prodList[5] = product6;
		prodList[6] = product7;

		for (Product prod : prodList) {
			this.app.addProductMi(prod);
		}

		Product[] customProdList = { product1, product2 };
		Product[] customProdList2 = { product1, product3, product5 };
		Product[] customProdList3 = { product4, product6 };
		Product[] customProdList4 = { product3, product7 };
		Product[] customProdList5 = { product2, product4 };

		DateTime date1 = new DateTime(2);
		DateTime date2 = new DateTime(3);
		DateTime date3 = new DateTime(5);
		DateTime date4 = new DateTime(10);
		DateTime date5 = new DateTime(20);

		String memberNumber1 = "D0B4M4S7Y5";
		String memberNumber2 = "Z0W0P9J2V6";
		String memberNumber3 = "F9N5S7H9Z1";

		this.app.addPackageMi(customerA, customProdList, date1);
		this.app.addPackageMi(customerB, customProdList2, date2, memberNumber1);
		this.app.addPackageMi(customerC, customProdList3, date3);
		this.app.addPackageMi(customerD, customProdList4, date4, memberNumber2);
		this.app.addPackageMi(customerE, customProdList5, date5, memberNumber3);
		
		System.out.println("Seed Data Loading successful, press enter to return to main menu.");
		scan.nextLine();
		return;

	}

	/*----------------------------------------*/
	//Usage: Use mergeSort to sort the deliveryList and display them
	
	private void displayDelivery() {

		String response = "";

		System.out.println("[ ------- Display Deliveries------- ]");
		do {
			System.out.print("Enter sort order (A/D): ");
			response = this.scan.nextLine();
			if (!(response.equalsIgnoreCase("A") || response.equalsIgnoreCase("D"))) {
				System.out.println("please enter either A or D (case insensitive)");
			}
		} while (!(response.equalsIgnoreCase("A") || response.equalsIgnoreCase("D")));

		Delivery[] deliveryList = this.app.retrieveDeliList();

		System.out.println("--> Summary of All Deliveries <--\n");
		if (response.equalsIgnoreCase("A")) {
			custUtil.mergeSortCust(deliveryList, true);
		} else {
			custUtil.mergeSortCust(deliveryList, false);
		}

		for (Delivery del : deliveryList) {
			System.out.println(del.getDetails());
		}

		System.out.println("Press enter to return to the Main menu");
		this.scan.nextLine();
		return;
	}

	/*----------------------------------------*/
	//Usage: use for searching a particular delivery with the date user provided.
	
	private void deliverySearch() {

		int day = 0;
		int month = 0;
		int year = 0;
		String response = "y";
		System.out.println("[ ------- Delivery Search------- ]");
		try {

			do {
				System.out.print("Enter Day: ");
				day = scan.nextInt();
				System.out.print("Enter Month: ");
				month = scan.nextInt();
				System.out.print("Enter Year: ");
				year = scan.nextInt();
				scan.nextLine();

				DateTime searchDate = new DateTime(day, month, year);
				Delivery[] searchList = this.app.retrieveDeliList();
				Delivery[] foundList = new Delivery[0];

				int diff = 0;
				for (int index = 0; index < searchList.length; index++) {
					diff = DateTime.diffDays(searchDate, searchList[index].retrieveDeliDate());
					if (diff == 0) {
						foundList = custUtil.appendDeliArr(searchList[index], foundList);
					}
				}

				if (foundList.length == 0) {
					System.out.println("\nCould not locate any delivery in that date");
				} else {
					System.out.println("\n[ -- Here is the result -- ] ");
					for (int index = 0; index < foundList.length; index++) {
						System.out.println(foundList[index].getDetails());
					}
				}

				System.out.println("Please enter to exit or input anything to continue");
				response = scan.nextLine();

			} while (!(response.equals("")));

			System.out.println(" -- Press enter to return to the main Menu -- \n");
			scan.nextLine();
			return;

		} catch (InputMismatchException e) {
			System.out.println("[ Error: You entry is not recognized by the system ]");
			return;
		}
	}

}
