package DAO;
import java.util.ArrayList;

import JavaBeans.Company;
import JavaBeans.Customer;

public interface CustomersDAO {
	public boolean isCustomerExists(String email, String password);
	public void addCustomer (Customer customer) throws Exception;
	public void updateCustomer (Customer customer);
	public void deleteCustomer (int customerID);
	public ArrayList<Customer> getAllCustomers();
	public Company getOneCustomer(int customerID);

}

