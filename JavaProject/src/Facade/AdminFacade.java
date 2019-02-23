package Facade;

import java.util.ArrayList;

import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class AdminFacade extends ClientFacade {
	
		//-----------------------Properties---------------------------
	
	
		//-----------------------Constructor---------------------------
		
		public AdminFacade() {}

		//-----------------------Methods ---------------------------

		public boolean login(String email,String password) {
			if (email.contentEquals("admin@admin.com") && password.contentEquals("admin"))
				return true;
			else
				return false;
		}
		
		public void addCompany(Company company) throws Exception {
			
			//Checking that the new Company doesn't have the same Email or Name as an existing Company
			ArrayList<Company> allCompanies = companiesDAO.getAllCompanies();
			for (Company c : allCompanies)
				//If a Company with the same Email or Name as the new Company exists, print an appropriate message and quit the function
				if (c.getName().contentEquals(company.getName()) || c.getEmail().contentEquals(company.getEmail())) {
					System.out.println("A company with the same Name/Email already exists");
					return;
				}
					
			//Adding the Company after validating it doesn't have an identical Name or Email
			companiesDAO.addCompany(company);
		}
		
		public void updateCompany(Company company) throws Exception {
			Company theCompany = companiesDAO.getOneCompany(company.getId());
			
			if (theCompany != null) {
				theCompany.setEmail(company.getEmail());
				theCompany.setPassword(company.getPassword());
				theCompany.setCoupons(company.getCoupons());
				companiesDAO.updateCompany(theCompany);
			}
		}
		
		public void deleteCompany(int companyID) throws Exception {
			
			//Getting a list of All the customers in order to delete all the coupon purchases by this company
			ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();

			//Getting the coupons that the company has
			Company theCompany = companiesDAO.getOneCompany(companyID);
			if (theCompany != null) {
				ArrayList<Coupon> theCoupons = theCompany.getCoupons();
				
				//Deleting the Coupons that the Company created and the history of Coupon Purchases by this company
				for (Coupon theCoupon : theCoupons) {
					//Deleting the history of Coupon Purchases by this company
					for (Customer theCustomer: allCustomers) {
						couponsDAO.deleteCouponPurchase(theCustomer.getId(),theCoupon.getId());
					}
					
					//Deleting the Coupon that this created
					couponsDAO.deleteCoupon(theCoupon.getId());
				}
			}
			
			
			//Deleting the company itself
			companiesDAO.deleteCompany(companyID);
		}
		
		public ArrayList<Company> getAllCompanies() throws Exception{
			return companiesDAO.getAllCompanies();
		}
		
		public Company getOneCompany(int companyID) throws Exception{
			return companiesDAO.getOneCompany(companyID);
			
		}
		
		public void addCustomer(Customer customer) throws Exception {
			ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();
			for (Customer c : allCustomers) {
				if (customer.getEmail().contentEquals(c.getEmail())) {
					System.out.println("A Customer with the same Email already exists");
					return;
				}
			}
			
			customersDAO.addCustomer(customer);
		}
		
		public void updateCustomer(Customer customer) throws Exception {
			
			//Getting a Customer with the details that are about to be updated (all the details but the ID)
			Customer theCustomer = customersDAO.getOneCustomer(customer.getId());
			
			if (theCustomer != null) {
				theCustomer.setEmail(customer.getEmail());
				theCustomer.setFirstName(customer.getFirstName());
				theCustomer.setLastName(customer.getLastName());
				theCustomer.setPassword(customer.getPassword());
				theCustomer.setCoupons(customer.getCoupons());
				
				//Updating the Customer
				customersDAO.updateCustomer(theCustomer);
			}
		}
		
		public void deleteCustomer(int customerID) throws Exception {
			
			//Getting the Customer's details from the Database
			Customer theCustomer = customersDAO.getOneCustomer(customerID);
			
			//Delete the customer only if he exists
			if (theCustomer != null) {		
				
				//Getting the Customer's Coupons
				ArrayList<Coupon> theCoupons = theCustomer.getCoupons();
				
				//all coupon purchases that include this specific customerID will be deleted
				for (Coupon c : theCoupons) {
					couponsDAO.deleteCouponPurchase(customerID,c.getId());
				}
				
				//Delete the Customer
				customersDAO.deleteCustomer(customerID);
			}
		}
		
		public ArrayList<Customer> getAllCustomers() throws Exception{
			return customersDAO.getAllCustomers();
		}
		
		public Customer getOneCustomer(int customerID) throws Exception{
			return customersDAO.getOneCustomer(customerID);
			
		}

}
