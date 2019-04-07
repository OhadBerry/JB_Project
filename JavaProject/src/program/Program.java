package program;


import java.sql.Date;

import dao.CouponsDao;
import dao.CustomersDao;
import dao.PurchasesDao;
import dao.UsersDao;
import javabeans.Category;
import javabeans.Company;
import javabeans.Coupon;
import javabeans.Customer;
import javabeans.Purchase;
import javabeans.User;
import logic.ClientType;
import test.TestCompaniesDao;

public class Program {

	public static void main(String[] args) {

		try {

			System.out.println("Welcome to the DBDAO program");
			
			System.out.println("Testing CompaniesDao");
			TestCompaniesDao companiesTestBench = new TestCompaniesDao();
			companiesTestBench.testCreateCompany(companiesTestBench.getCompany());
			companiesTestBench.testUpdateCompany(new Company("UpdatedCompany"));
			companiesTestBench.testIsCompanyExistsById(31);
			companiesTestBench.testGetOneCompanyById(31);
			companiesTestBench.testDeleteCompanyById(30);
			companiesTestBench.testGetAllCompanies();
			
			System.out.println("Testing CouponsDao");
	
			CouponsDao myCouponsDao = new CouponsDao();
			Coupon coupon = new Coupon((long)1,(long) 1,Category.Food, "myTestCoupon", "TestCoupon", new Date(0,0,0), new Date(120,1,1),1,10.0,"myImage");
			Coupon updatedCoupon = new Coupon((long)2,(long)1,Category.Food, "myUpdatedTestCoupon", "updatedTestCoupon", new Date(0,0,0), new Date(120,1,1),1,11.0,"myUpdatedImage");
			long couponId = 1;
			
//			myCouponsDao.createCoupon(coupon);
			myCouponsDao.updateCoupon(updatedCoupon);
			System.out.println("Getting coupon number "+couponId+" :"+myCouponsDao.getCouponbyId(couponId));
//			myCouponsDao.deleteCouponById(couponId);
			System.out.println("Getting all coupons :"+myCouponsDao.getAllCoupons());
			
			System.out.println("Testing UsersDao");
			
			UsersDao myUsersDao = new UsersDao();
			long id = 1;
			String userName = "myUserName";
			String password = "myPassword";
			long companyID = 1;
			ClientType type = ClientType.Customer;
			
			User user = new User(id, userName, password, companyID, type);
			
			long updatedId = 2;
			String updatedUserName = "myUpdatedUserName";
			String updatedPassword = "myUpdatedPassword";
			long updatedCompanyID = 1;
			ClientType updatedType = ClientType.Customer;
			
			User updatedUser = new User(updatedId, updatedUserName, updatedPassword, updatedCompanyID, updatedType);
			long user_ID = 1;
			
			myUsersDao.createUser(user);
			myUsersDao.updateUser(updatedUser);
			System.out.println("Getting user number "+user_ID+" :"+myUsersDao.getUserByID(user_ID));			
//			myUsersDao.deleteUserById(user_ID);

			System.out.println("Testing CustomersDao");
			
			CustomersDao myCustomersDao = new CustomersDao();
			long customer_id = 3;
			String firstName = "myCustomerFirstName";
			String lastName = "myCustomerLasttName";
			Customer customer = new Customer(customer_id, firstName, lastName);
					
			long updatedCustomer_id = 2;
			String updatedFirstName = "myUpdatedCustomerFirstName";
			String updatedLastName = "myUpdatedCustomerLasttName";
			Customer updatedCustomer = new Customer(updatedCustomer_id, updatedFirstName, updatedLastName);
			
//			myCustomersDao.createCustomer(customer);
			myCustomersDao.updateCustomer(updatedCustomer);
			System.out.println("Getting customer number "+customer_id+" :"+myCustomersDao.getCustomerById(customer_id));
//			myCustomersDao.deleteCustomer(customer_id);
			System.out.println("Getting all customers :"+myCustomersDao.getAllCustomers());
			
			System.out.println("Testing PurchasesDao");
			
			PurchasesDao myPurchasesDao = new PurchasesDao();
			System.out.println("Customer id is: "+customer_id+" Coupon Id is: "+couponId);
			Purchase purchase = new Purchase(customer_id,couponId);
			
			myPurchasesDao.createCouponPurchase(purchase.getCustomer_id(),purchase.getCoupon_id());
			System.out.println(myPurchasesDao.isCouponPurchaseExists(purchase.getCustomer_id(),purchase.getCoupon_id()));
			myPurchasesDao.deleteCouponPurchase(purchase.getCustomer_id(),purchase.getCoupon_id());
			
			
			
		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
}
	
	
	
