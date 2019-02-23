package Test;

import java.util.ArrayList;

import DBDAO.CompaniesDBDAO;
import Facade.AdminFacade;
import Facade.ClientType;
import Facade.LoginManager;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class Test {
	
	public static void testAll() throws Exception {
		
		//Creating the necessary instances in order to run all tests in AdminFacade
		
		//Creating a Company
		String email = "admin@admin.com";
		String password = "admin";
		String companyName = "myCompany";
		Company company = new Company(companyName,email,password,new ArrayList<Coupon>());
		
		//Creating a Customer
		String customerName = "myCustomer";
		Customer customer = new Customer(customerName, "myLastName", "myCustomerEmail@email.com", "myPassword", null);
		
		

		AdminFacade myAdminFacade = (AdminFacade) LoginManager.getInstance().login(email,password, ClientType.Administrator);
		

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
		
		
	}

}
