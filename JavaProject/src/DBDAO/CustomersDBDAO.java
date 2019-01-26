package DBDAO;

import java.util.ArrayList;

import DAO.CustomersDAO;
import JavaBeans.Company;
import JavaBeans.Customer;

public class CustomersDBDAO implements CustomersDAO{
	private ConnectionPool connectionPool;

	@Override
	public boolean isCustomerExists(String email, String password) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(int customerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getOneCustomer(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

}
