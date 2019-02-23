package DAO;
import java.util.ArrayList;

import JavaBeans.Customer;

public interface CustomersDAO {
	public boolean isCustomerExists(String email, String password) throws Exception;
	public void addCustomer (Customer customer) throws Exception;
	public void updateCustomer (Customer customer) throws Exception;
	public void deleteCustomer (int customerID) throws Exception;
	public ArrayList<Customer> getAllCustomers() throws Exception;
	public Customer getOneCustomer(int customerID) throws Exception;
	public int getCustomerID(String email, String password) throws Exception;

}

