package Facade;

import java.util.ArrayList;

import JavaBeans.Company;
import JavaBeans.Customer;

public class AdminFacade extends ClientFacade {
	
		//-----------------------Properties---------------------------
	
	
		//-----------------------Constructor---------------------------
		
		public AdminFacade() {}

		//-----------------------Methods ---------------------------

		public boolean login(String email,String password) {
			return false;}
		
		public void addCompany(Company company) throws Exception {
			companiesDAO.addCompany(company);
		}
		
		public void updateCompany(Company company) throws Exception {
			companiesDAO.updateCompany(company);
		}
		
		public void deleteCompany(int companyID) throws Exception {
			companiesDAO.deleteCompany(companyID);
		}
		
		public ArrayList<Company> getAllCompanies() throws Exception{
			return companiesDAO.getAllCompanies();
		}
		
		public Company getOneCompany(int companyID) throws Exception{
			return companiesDAO.getOneCompany(companyID);
			
		}
		
		public void addCustomer(Customer customer) throws Exception {
			customersDAO.addCustomer(customer);
		}
		
		public void updateCustomer(Customer customer) throws Exception {
			customersDAO.updateCustomer(customer);

		}
		
		public void deleteCustomer(int customerID) throws Exception {
			customersDAO.deleteCustomer(customerID);
		}
		
		public ArrayList<Customer> getAllCustomers() throws Exception{
			return customersDAO.getAllCustomers();
		}
		
		public Customer getOneCustomer(int customerID) throws Exception{
			return customersDAO.getOneCustomer(customerID);
			
		}

}
