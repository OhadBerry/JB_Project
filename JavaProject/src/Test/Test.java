package Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import DBDAO.CompaniesDBDAO;
import Facade.AdminFacade;
import Facade.ClientType;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import Facade.LoginManager;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import Program.CouponExpirationDailyJob;

public class Test {
	
	public static void testAll() throws Exception {
		
		//Creating the necessary instances in order to run all tests in AdminFacade
		CouponExpirationDailyJob myDailyJob = new CouponExpirationDailyJob();
		myDailyJob.run();
		
		//Creating a Company
		String email = "admin@admin.com";
		String password = "admin";
		String companyName = "myCompany";
		Company company = new Company(companyName,email,password,new ArrayList<Coupon>());
		
		//Creating a Customer
		String customerName = "myCustomer";
		Customer customer = new Customer(customerName, "myLastName", "myCustomerEmail@email.com", "myPassword", null);
		
		
		System.out.println("Getting AdminFacade instance from LoginManager");
		AdminFacade myAdminFacade = (AdminFacade) LoginManager.getInstance().login(email,password, ClientType.Administrator);
				
		System.out.println("*********Beginning AdminFacade Tests**********");
		
		//Running Tests on AdminFacade

		System.out.println("Testing login");
		System.out.println("Login result is: "+myAdminFacade.login(email, password));
		System.out.println("login tested");
		
		System.out.println("Testing addCompany");
		myAdminFacade.addCompany(company);
		System.out.println("addCompany tested");
		
		System.out.println("Testing addCustomer");
		myAdminFacade.addCustomer(customer);
		System.out.println("addCustomer tested");
		
		System.out.println("Testing deleteCompany");
		myAdminFacade.deleteCompany(company.getId());
		System.out.println("deleteCompany tested");
		
		System.out.println("Testing deleteCustomer");
		myAdminFacade.deleteCustomer(customer.getId());
		System.out.println("deleteCustomer tested");
		
		System.out.println("Testing getAllCompanies");
		System.out.println("All Companies are: "+myAdminFacade.getAllCompanies());
		System.out.println("getAllCompanies tested");
		
		System.out.println("Testing getAllCustomers");
		System.out.println("All Customers are: "+myAdminFacade.getAllCustomers());
		System.out.println("getAllCustomers tested");
		
		System.out.println("Testing getOneCompany");
		System.out.println("Company details are: "+myAdminFacade.getOneCompany(2));
		System.out.println("getOneCompany tested");
		
		System.out.println("Testing getOneCustomer");
		System.out.println("Customer details are: "+myAdminFacade.getOneCustomer(2));
		System.out.println("getOneCustomer tested");
		
		//Adding a Customer and Company in order to be Updated and creating updated Customer and Company instances:
		myAdminFacade.addCompany(company);
		System.out.println("Just Added company "+company.getId());
		
		//Creating an updated Company
		int companyUpdatedID = company.getId();
		Company companyUpdated = new Company(companyUpdatedID,"myCompanyUpdated","myCompanyUpdated@email.com","myPasswordUpdated",null);
		
		myAdminFacade.addCustomer(customer);
		System.out.println("Just Added customer "+customer.getId());
		
		//Creating an updated Customer
		int customerUpdatedID = customer.getId();
		Customer myCustomerUpdated = new Customer(customerUpdatedID,"myCustomerUpdated","lastNameUpdated","myCustomerUpdated@email.com","myPasswordUpdated",null);
			
		System.out.println("Testing updateCompany");
		myAdminFacade.updateCompany(companyUpdated);
		System.out.println("updateCompany tested");
		
		System.out.println("Testing updateCustomer");
		myAdminFacade.updateCustomer(myCustomerUpdated);
		System.out.println("updateCustomer tested");
		
		//Creating the necessary instances in order to run all tests in CompanyFacade
		email = "johnBryce@gmail.com";
		password = "abcd";
		int maxPrice = 100;
		System.out.println("Getting CompanyFacade instance from LoginManager");
		CompanyFacade myCompanyFacade = (CompanyFacade) LoginManager.getInstance().login(email,password,ClientType.Company);
		
		String couponName = "myCoupon";
		Coupon coupon = new Coupon(3, Category.Amusement_Park, couponName, "myDescription", new Date(),new Date(119, 1, 20), 100, 200, "myImage");
		
		int couponUpdatedID = coupon.getId()+1;
		Coupon updatedCoupon = new Coupon(couponUpdatedID, 4, Category.Pool, "myUpdatedCoupon", "myDescription",new Date(), new Date(119, 1, 20), 100, 200, "myImage");
		
		System.out.println("*********Beginning CompanyFacade Tests**********");
		
		//Running Tests on CompanyFacade
		
		System.out.println("Testing login");
		System.out.println("Attempting to log in with email: "+email+"and password: "+password+"result is: "+myCompanyFacade.login(email, password));
		System.out.println("login Tested");
		
		System.out.println("Testing addCoupon");
		myCompanyFacade.addCoupon(coupon) ;
		System.out.println("addCoupon Tested");
		
		System.out.println("TestingupdateCoupon");
		myCompanyFacade.updateCoupon(updatedCoupon) ;
		System.out.println("updateCoupon Tested");
		
		System.out.println("Testing deleteCoupon");
		myCompanyFacade.deleteCoupon(coupon.getId()) ;
		System.out.println("deleteCoupon Tested");
		
		System.out.println("Testing getCompanyCoupons");
		System.out.println("Coupons are: "+myCompanyFacade.getCompanyCoupons());
		System.out.println("getCompanyCoupons Tested");
		
		System.out.println("Testing getCompanyCoupons by Category");
		System.out.println("Coupons are: "+myCompanyFacade.getCompanyCoupons(coupon.getCategory()));
		System.out.println("getCompanyCoupons by Category Tested");
		
		System.out.println("Testing getCompanyCoupons Up to Maxprice");
		System.out.println("Coupons are: "+myCompanyFacade.getCompanyCoupons( maxPrice) );
		System.out.println("getCompanyCoupons Up to Maxprice Tested");
		
		System.out.println("Testing getCompanyDetails");
		System.out.println("The Company's details are: "+myCompanyFacade.getCompanyDetails());
		System.out.println("getCompanyDetails Tested");
		
		
		//Creating the necessary instances in order to run all tests in CustomerFacade
		email = "BobAlice@email.com";
		password = "1234";
		System.out.println("Getting AdminFacade instance from LoginManager");
		CustomerFacade myCustomerFacade = (CustomerFacade) LoginManager.getInstance().login(email,password,ClientType.Customer);
		
		System.out.println("*********Beginning CustomerFacade Tests**********");
		
		//Running Tests on CustomerFacade
		
		System.out.println("Testing login");
		System.out.println("Attempting to log in with email: "+email+"and password: "+password+"result is: "+myCustomerFacade.login(email, password));
		System.out.println("login Tested");
		
		System.out.println("Testing purchaseCoupon");
		myCustomerFacade.purchaseCoupon(coupon);
		System.out.println("purchaseCoupon Tested");
		
		System.out.println("Testing getCustomerCoupons");
		System.out.println("Coupons are: "+myCustomerFacade.getCustomerCoupons());
		System.out.println("getCustomerCoupons Tested");
		
		System.out.println("Testing getCustomerCoupons by Category");
		System.out.println("Coupons by Category are: "+myCustomerFacade.getCustomerCoupons(coupon.getCategory()));
		System.out.println("getCustomerCoupons by Category Tested");
		
		System.out.println("Testing getCustomerCoupons by Price");
		System.out.println("Coupons by price are: "+myCustomerFacade.getCustomerCoupons(maxPrice));
		System.out.println("Testing getCustomerCoupons by Price Tested");
		
		System.out.println("Testing getCustomerDetails");
		System.out.println("The Customers Details are: "+myCustomerFacade.getCustomerDetails());
		System.out.println("Tested getCustomerDetails");
		
		myDailyJob.stop();
		
		System.out.println("---------------------Special Tests:----------------------------");
		
		System.out.println("Adding 10 Companies");
		for (int i = 1; i <= 10; i++) {
			myAdminFacade.addCompany(new Company("myName"+i,"myCompanyEmail"+i+"@Email.com","myPassword",null));
		}
		
		System.out.println("Adding 10 Customers");
		for (int i = 1; i <= 10; i++) {
			customerName = "myCustomer"+i;
			String customerEmail = "myCustomerEmail"+i+"@Email.com"; 
			myAdminFacade.addCustomer(new Customer(customerName, "myLastName", customerEmail, "myPassword", null));
		}
		
		System.out.println("Adding 100 different Coupons");
		for (int i = 1; i <= 10; i++) {
			for (int j = 1; j <= 10; j++) {
				couponName = "myCoupon"+i;
				myCompanyFacade.addCoupon(new Coupon(418+i,Category.Amusement_Park, "myCouponTitle", "myCouponDescription",new Date(), new Date(120,10,10),i*j,j*100,"myImage"));
			}
		}
		
		System.out.println("Purchasing 10 Coupons");
		for (int i = 1; i <= 10; i++) {
//			System.out.println(myAdminFacade.getOneCompany(419));
			myCustomerFacade.purchaseCoupon(myAdminFacade.getOneCompany(418+i).getCoupons().get(0));
		}
		
//		System.out.println("Deleting Customer number 2 ");
//		for (int i = 1047; i <=1056 ;i++)
//		myCompanyFacade.deleteCoupon(1074);
//		
	}
}
