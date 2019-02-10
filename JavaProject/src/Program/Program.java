package Program;

import java.sql.SQLException;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.util.ArrayList;
import java.util.Date;

public class Program {

	public static void main(String[] args) {

		try {

			System.out.println("Welcome to the DBDAO program");
			testCompaniesDBDAO();
			testCustomersDBDAO();
			testCouponsDBDAO();

		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
	
	public static void testCompaniesDBDAO() throws Exception {
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();

		// Create an instance of Company with values
		Company company = new Company(0, null, null, null, null);
		company.setName("JohnBryce");
		company.setEmail("johnBryce@gmail.com");
		company.setPassword("abcd");

		// Create an instance of Company with updated values to insert into "Update"
		// method
		Company companyUpdated = new Company(0, null, null, null, null);
		companyUpdated.setName("JohnBryceUpdated");
		companyUpdated.setEmail("johnBryce@gmail.comUpdated");
		companyUpdated.setPassword("abcdUpdated");
		companyUpdated.setId(5);

		companiesDBDAO.addCompany(company);
		System.out.println(companiesDBDAO.isCompanyExists("johnBryce@gmail.com", "abcd"));
		companiesDBDAO.updateCompany(companyUpdated);
		Company company3 = companiesDBDAO.getOneCompany(5);
		System.out.println("Company3 ID: " + company3.getId());
		
	}
	
	public static void testCouponsDBDAO() throws Exception {
		
		CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
		int randomNumber = (int) (Math.random()*100);

		// Create an instance of Coupon with values
		Coupon coupon = new Coupon(randomNumber, randomNumber, Category.Amusement_Park, "myCoupon", "myDescription", new Date(), new Date(119, 1, 20), 100, 200,
				"myImage");
//		couponsDBDAO.addCoupon(coupon);

		// Create an instance of Coupon with updated values to insert into "updateCoupon"
		Coupon updatedCoupon = new Coupon(randomNumber,randomNumber, Category.Pool, "myUpdatedCoupon", "myDescription", new Date(),
		new Date(119, 1, 20), 100, 200, "myImage");

		//Testing update.Coupon with the coupon 'updatedCoupon'
		couponsDBDAO.updateCoupon(updatedCoupon);
		
		//Testing getAllcoupons with the coupon 'updatedCoupon'
		ArrayList<Coupon> allCoupons = couponsDBDAO.getAllCoupons();
		for (Coupon c : allCoupons)
			System.out.println(c.toString());
		
		//Testing addCouponPurchase by entering random values to 
		couponsDBDAO.addCouponPurchase(23,23);
		
	}
	
	public static void testCustomersDBDAO() throws Exception {
		
		//Creating a new instance of CustomersDBDAO
		CustomersDBDAO myCustomersDBDAO = new CustomersDBDAO();
		
		//Creating a new Customer
		Customer myCustomer = new Customer(0, "Bob", "Alice", "BobAlice@email.com", "1234", null);
		
		//Adding a new Customer to 'Customers' Table
		myCustomersDBDAO.addCustomer(myCustomer);


	}
}
