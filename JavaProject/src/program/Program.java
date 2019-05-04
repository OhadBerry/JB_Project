package program;

import java.sql.Date;

import javabeans.Category;
import javabeans.Company;
import javabeans.Coupon;
import javabeans.Customer;
import javabeans.User;
import logic.ClientType;
import logic.CompaniesController;
import logic.CouponsController;
import logic.CustomersController;

public class Program {

	public static void main(String[] args) {

		try {

			System.out.println("Welcome to the DBDAO program");
			/*	
			TestCompaniesDao.AllTests();
			TestCouponsDao.AllTests();
			TestCustomersDao.AllTests();
			TestUsersDao.AllTests();
			TestPurchasesDao.AllTests();
			*/
			
			//Creating Company Objects for test
			System.out.println("Testing Companies Controller");
			CompaniesController myCompaniesController = new CompaniesController();
			String company_name = "MyCompany";
			Company company = new Company(company_name);
			
			//Running Controller Methods
//			myCompaniesController.createCompany(company);
			System.out.println(myCompaniesController.getCompanyByCompanyID(1));
//			myCompaniesController.updateCompany(new Company((long)2,"UpdatedCompany2"));
			
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
			
//			myCouponsController.createCoupon(coupon);
			System.out.println(myCouponsController.getCouponByID(coupon_id));
			Coupon updatedCoupon = new Coupon(coupon_id, company_id, category, "MyUpdatedCoupon", description, startDate, endDate, amount, price, image);
//			myCouponsController.updateCoupon(updatedCoupon);
			System.out.println(myCouponsController.getCouponByID(coupon_id));
//			myCouponsController.deleteCouponByID(coupon_id);
			
			System.out.println("Testing Customers Controller");
			CustomersController myCustomersController = new CustomersController();
			String userName = "ohad.berry@gmail.com6";
			String password = "********";
			Long companyId = null;
			ClientType type = ClientType.Customer;
			User user = new User(userName, password, companyId, type);
			User toBeUpdatedUser = new User("Updated1234", password, companyId, type);
			toBeUpdatedUser.setId(9);
			System.out.println(toBeUpdatedUser);
			String firstName = "Ohad";
			String lastName = "Bairey";
			Customer customer = new Customer(firstName, lastName, user);
			Customer toBeUpdatedCustomer = new Customer("Endaros","LastName", toBeUpdatedUser);
			long customerID = 9;
			
			
			myCustomersController.createCustomer(customer);
			System.out.println(myCustomersController.getCustomerByID(customerID));
			myCustomersController.updateCustomer(toBeUpdatedCustomer);
			System.out.println(myCustomersController.getCustomerByID(customerID));
			myCustomersController.deleteCustomerByID(8);

			
			
			
		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
}
	
	
	
