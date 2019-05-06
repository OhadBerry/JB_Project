package idao;
import java.util.ArrayList;

import beans.Customer;

public interface ICustomersDao {
	
	public long createCustomer (Customer customer) throws Exception;
	public void updateCustomer (Customer customer) throws Exception;
	public void deleteCustomer (long customerID) throws Exception;
	public Customer getCustomerById(long customerID) throws Exception;
	public ArrayList<Customer> getAllCustomers() throws Exception;

}

