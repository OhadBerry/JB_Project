package Program;

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
			testCouponsDBDAO();
			testCustomersDBDAO();

		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
	
	public static void testCompaniesDBDAO() throws Exception {
		
		//Create an instance of CompanyDBDAO
		CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();

		//------------------Adding a 10 new Companies--------------------------------
		for (int i = 1; i <= 10; i++) {
			// Create an instance of Company with values
			String companyName = "myCompany"+i;
			Company company = new Company(companyName,"myCompanyEmail@email.com","myPassword",null);
			
			// Adding a new Company
			System.out.println("Adding a new Company "+companyName+" to Table 'Companies'");
			companiesDBDAO.addCompany(company);
			System.out.println("Company "+companyName+" Added to Table 'Companies'");
		}
		
		//------------------Testing isCompanyExists method with: "myCompanyEmail@email.com", "myPassword" "--------------------------------
		String testEmail = "myCompanyEmail@email.com";
		String testPassword = "myPassword";
		System.out.println("Testing isCompanyExists method with: "+testEmail+","+testPassword);
		System.out.println(companiesDBDAO.isCompanyExists(testEmail, testPassword));
		System.out.println("isCompanyExists method tested with: "+testEmail+","+testPassword);

		//------------------Updating a Company with companyUpdated--------------------------------	
		// Create an instance of Company with updated values to insert into "UpdateCompany"
		int companyUpdatedID = 5;
		Company companyUpdated = new Company(companyUpdatedID,"myCompanyUpdated","myCompanyUpdated@email.com","myPasswordUpdated",null);
		
		//Updating Company
		System.out.println("Updating Company "+companyUpdatedID+" with companyUpdated");
		companiesDBDAO.updateCompany(companyUpdated);
		System.out.println("Updated Company "+companyUpdatedID+" with companyUpdated");
		
		//------------------Deleting a Company with deleteCompany--------------------------------	
		// Setting the ID of the company that will be deleted
		int companyDeleteID = 15;
		
		//Deleting Company
		System.out.println("Deleting Company "+companyDeleteID+" with deleteCompany");
		companiesDBDAO.deleteCompany(companyDeleteID);		
		System.out.println("Deleted Company "+companyDeleteID+" with deleteCompany");
		
		//------------------Testing get one Company with getOneCompany--------------------------------
		// Setting the ID of the company that will be read
		int oneCompanyID = 1;
		
		System.out.println("Reading Company "+oneCompanyID+" with getOneCompany");
		Company oneCompany = companiesDBDAO.getOneCompany(oneCompanyID);
		System.out.println("Read Company "+oneCompanyID+" with getOneCompany");
		System.out.println(oneCompany.toString());
		
		//------------------Testing get all Companies with getAllCompanies--------------------------------
		System.out.println("Reading all companies with getAllCompanies");
		ArrayList<Company> allCompanies = companiesDBDAO.getAllCompanies();
		System.out.println("Read all companies with getAllCompanies");
		System.out.println("All companies Data is:");
		System.out.println(allCompanies);
		
		
	}
	
	public static void testCouponsDBDAO() throws Exception {
		
		//Creating an Instance of couponsDBDAO
		CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
		
		//------------------Adding a 10 new Coupons--------------------------------
		for (int i = 1; i <= 10; i++) {
			// Create an instance of Coupon with values
			String couponName = "myCoupon "+i;
			@SuppressWarnings("deprecation")
			Coupon coupon = new Coupon(1, Category.Amusement_Park, couponName, "myDescription", new Date(), new Date(119, 1, 20), 100, 200,
					"myImage");					
			// Adding a new Coupon
			System.out.println("Adding a new Coupon "+couponName+" to Table 'Coupons'");
			couponsDBDAO.addCoupon(coupon);
			System.out.println("Coupon "+couponName+" Added to Table 'Coupons'");
		}
	
		
		//------------------Updating a Coupon with updateCoupon--------------------------------	
		// Create an instance of Coupon with updated values to insert into "updateCoupon"
		int couponUpdatedID = 28;
		@SuppressWarnings("deprecation")
		Coupon updatedCoupon = new Coupon(couponUpdatedID,1, Category.Pool, "myUpdatedCoupon", "myDescription", new Date(),
				new Date(119, 1, 20), 100, 200, "myImage");
		
		//Updating Company
		System.out.println("Updating Coupon "+couponUpdatedID+" with updateCoupon");
		couponsDBDAO.updateCoupon(updatedCoupon);
		System.out.println("Updated Coupon "+couponUpdatedID+" with updateCoupon");

		
		//------------------Deleting a Coupon with deleteCoupon--------------------------------	
		// Setting the ID of the coupon that will be deleted
		int couponDeleteID = 29;
		
		//Deleting Coupon
		System.out.println("Deleting Coupon "+couponDeleteID+" with deleteCoupon");
		couponsDBDAO.deleteCoupon(couponDeleteID);		
		System.out.println("Deleted Coupon "+couponDeleteID+" with deleteCoupon");
	
		
		//------------------Testing get one Coupon with getOneCoupon--------------------------------
		// Setting the ID of the coupon that will be read
		int oneCouponID = 30;
		
		System.out.println("Reading Coupon "+oneCouponID+" with getOneCoupon");
		Coupon oneCoupon = couponsDBDAO.getOneCoupon(oneCouponID);
		System.out.println("Read Coupon "+oneCouponID+" with getOneCoupon");
		System.out.println("Coupon number "+oneCouponID+" details are:");
		System.out.println(oneCoupon.toString());
	
		
		//------------------Testing get all Companies with getAllCompanies--------------------------------
		System.out.println("Reading all coupons with getAllCoupons");
		ArrayList<Coupon> allCoupons = couponsDBDAO.getAllCoupons();
		System.out.println("Read all coupons with getAllCoupons");
		System.out.println("All coupons data is:");
		System.out.println(allCoupons);
		
		//------------------Testing addCouponPurchase--------------------------------
		int customerTestID = 5;
		int couponTestID = 31;
		System.out.println("Adding Coupon Purchase with Customer ID: "+customerTestID+" and Coupon ID: "+couponTestID);
		couponsDBDAO.addCouponPurchase(customerTestID,couponTestID);		
		System.out.println("Added Coupon Purchase with Customer ID: "+customerTestID+" and Coupon ID: "+couponTestID);

	}
	
	public static void testCustomersDBDAO() throws Exception {
		
		//Creating a new instance of CustomersDBDAO
		CustomersDBDAO myCustomersDBDAO = new CustomersDBDAO();
		
		//------------------Adding a 10 new Customers--------------------------------
		for (int i = 1; i <= 10; i++) {

			//Defining Customer name with running index
			String customerName = "myCustomer"+i;
			
			//Creating a new Customer with values
			Customer myCustomer = new Customer(customerName, "myLastName", "myCustomerEmail@email.com", "myPassword", null);
			
			// Adding a new Customer
			System.out.println("Adding a new Customer "+customerName+" to Table 'Customers'");
			myCustomersDBDAO.addCustomer(myCustomer);
			System.out.println("Customer "+customerName+" Added to Table 'Customers'");
		}
				
		
		//------------------Testing isCustomerExists method with: "myCustomerEmail@email.com", "myPassword" "--------------------------------
		String testEmail = "myCustomerEmail@email.com";
		String testPassword = "myPassword";
		System.out.println("Testing isCustomerExists method with: "+testEmail+","+testPassword);
		System.out.println(myCustomersDBDAO.isCustomerExists(testEmail, testPassword));
		System.out.println("isCustomerExists method tested with: "+testEmail+","+testPassword);

		//------------------Updating a Customer with myCustomerUpdated--------------------------------	
		// Create an instance of Customer with updated values to insert into "UpdateCustomer"
		int customerUpdatedID = 5;
		Customer myCustomerUpdated = new Customer(customerUpdatedID,"myCustomerUpdated","lastNameUpdated","myCustomerUpdated@email.com","myPasswordUpdated",null);
		
		//Updating Company
		System.out.println("Updating Customer "+customerUpdatedID+" with companyUpdated");
		myCustomersDBDAO.updateCustomer(myCustomerUpdated);
		System.out.println("Updated Customer "+customerUpdatedID+" with companyUpdated");
		
		//------------------Deleting a Customer with deleteCustomer--------------------------------	
		// Setting the ID of the customer that will be deleted
		int customerDeleteID = 9;
		
		//Deleting Company
		System.out.println("Deleting Customer "+customerDeleteID+" with deleteCustomer");
		myCustomersDBDAO.deleteCustomer(customerDeleteID);		
		System.out.println("Deleted Customer "+customerDeleteID+" with deleteCustomer");
		
		//------------------Testing get one Customer with getOneCustomer--------------------------------
		// Setting the ID of the Customer that will be read
		int oneCustomerID = 5;
		
		System.out.println("Reading Customer "+oneCustomerID+" with getOneCustomer");
		Customer oneCustomer = myCustomersDBDAO.getOneCustomer(oneCustomerID);
		System.out.println("Read Customer "+oneCustomerID+" with getOneCustomer");
		System.out.println(oneCustomer.toString());
		
		//------------------Testing get all Customers with getAllCustomers--------------------------------
		System.out.println("Reading all customers with getAllCustomers");
		ArrayList<Customer> allCustomers = myCustomersDBDAO.getAllCustomers();
		System.out.println("Read all customers with getAllCustomers");
		System.out.println("All customers Data is:");
		System.out.println(allCustomers);
		

	}
}
