package idao;
import java.util.ArrayList;

import javabeans.Customer;

public interface ICustomersDao {
	
	public void addCustomer (Customer customer) throws Exception;
	public void updateCustomer (Customer customer) throws Exception;
	public void deleteCustomer (long customerID) throws Exception;
	public Customer getOneCustomerById(long customerID) throws Exception;
	public ArrayList<Customer> getAllCustomers() throws Exception;

}

