package tests;

import java.sql.Date;

import beans.Category;
import beans.Company;
import beans.Coupon;
import beans.Customer;
import beans.Purchase;
import beans.User;
import enums.ClientType;
import logic.CompaniesController;
import logic.CouponsController;
import logic.CustomersController;
import logic.PurchasesController;
import logic.UsersController;

public class ControllerTests {
	
	public static void testCompaniesController() throws Exception{
		//Creating Company Objects for test
		System.out.println("Testing Companies Controller");
		CompaniesController myCompaniesController = new CompaniesController();
		String company_name = "MyCompany";
		Company company = new Company(company_name);
		
		//Running Controller Methods
//		myCompaniesController.createCompany(company);
		System.out.println(myCompaniesController.getCompanyByCompanyID(1));
//		myCompaniesController.updateCompany(new Company((long)2,"UpdatedCompany2"));
	}
	
	public static void testCouponsController() throws Exception{
		//Creating Coupon Objects for test
		System.out.println("Testing Coupons Controller");
		CouponsController myCouponsController = new CouponsController();
		long coupon_id = 1;
		long company_id = 1;
		String coupon_name = "MyCoupon"; String title = coupon_name;
		String description = "MyDescription";
		Date startDate = new Date(119,5,1);
		Date endDate = new Date(120,10,10);
		int amount = 5;
		double price = 75.5;
		String image = "MyImage";
		Category category = Category.Food;
		Coupon coupon = new Coupon(company_id, category, title, description, startDate, endDate, amount, price, image);
		
//		myCouponsController.createCoupon(coupon);
		System.out.println(myCouponsController.getCouponByID(coupon_id));
		Coupon updatedCoupon = new Coupon(coupon_id, company_id, category, "MyUpdatedCoupon", description, startDate, endDate, amount, price, image);
//		myCouponsController.updateCoupon(updatedCoupon);
//		System.out.println(myCouponsController.getCouponByID(coupon_id));
//		myCouponsController.deleteCouponByID(coupon_id);
	}	
	
	public static void testCustomersController() throws Exception{
		System.out.println("Testing Customers Controller");
		CustomersController myCustomersController = new CustomersController();
		String userName = "ohad.berry@gmail.com6";
		String password = "********";
		Long companyId = null;
		ClientType type = ClientType.Customer;
		User userCustomer = new User(userName, password, companyId, type);
		User toBeUpdatedUser = new User("Updated1234", password, companyId, type);
		toBeUpdatedUser.setId(9);
		
		String firstName = "Ohad";
		String lastName = "Bairey";
		Customer customer = new Customer(firstName, lastName, userCustomer);
		Customer toBeUpdatedCustomer = new Customer("Endaros","LastName", toBeUpdatedUser);
		long customerID = 9;
		
		
//		customerID = myCustomersController.createCustomer(customer);
		System.out.println(myCustomersController.getCustomerByID(customerID));
//		myCustomersController.updateCustomer(toBeUpdatedCustomer);
//		System.out.println(myCustomersController.getCustomerByID(customerID));
//		System.out.println(myCustomersController.getCustomerByID(10));
//		myCustomersController.deleteCustomerByID(10);
//		System.out.println(myCustomersController.getCustomerByID(10));
	}
	
	public static void testPurchasesController() throws Exception{
		System.out.println("Testing Purchases Controller");
		PurchasesController myPurchasesController = new PurchasesController();
		long customer_ID = 1;
		long coupon_ID = 1;
		int amount = 50;
		Purchase purchase = new Purchase(customer_ID, coupon_ID, amount);
		long purchaseID = 2; 
		
//		purchaseID = myPurchasesController.createPurchase(purchase);
		System.out.println(myPurchasesController.getPurchaseByPurchaseID(purchaseID));
		myPurchasesController.deletePurchasesByPurchaseID(purchaseID);
		System.out.println(myPurchasesController.getPurchaseByPurchaseID(purchaseID));
	}
	
	public static void testUsersController() throws Exception{		
		System.out.println("Testing Users Controller");
		UsersController myUsersController = new UsersController();
		long companyId = 1;
		ClientType type = ClientType.Customer;
		User user = new User("Me","myPassword",companyId, type);
		long userID = 1;
		
		userID = myUsersController.createUser(user);
		System.out.println(myUsersController.getUserbyID(userID));
		myUsersController.deleteUserbyID(userID);
		System.out.println(myUsersController.getUserbyID(userID));
	}

}
