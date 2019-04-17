package testDao;

import java.sql.Date;

import dao.CustomersDao;
import javabeans.Customer;

public class TestCustomersDao {
	public static void AllTests() throws Exception {
		System.out.println("Testing CustomersDao");
		
		CustomersDao myCustomersDao = new CustomersDao();
		long customer_id = 3;
		String firstName = "myCustomerFirstName";
		String lastName = "myCustomerLasttName";
		Customer customer = new Customer(customer_id, firstName, lastName);
				
		long updatedCustomer_id = 2;
		String updatedFirstName = "myUpdatedCustomerFirstName";
		String updatedLastName = "myUpdatedCustomerLasttName";
		Customer updatedCustomer = new Customer(updatedCustomer_id, updatedFirstName, updatedLastName);
		
		myCustomersDao.createCustomer(customer);
		myCustomersDao.updateCustomer(updatedCustomer);
		System.out.println("Getting customer number "+customer_id+" :"+myCustomersDao.getCustomerById(customer_id));
		myCustomersDao.deleteCustomer(customer_id);
		System.out.println("Getting all customers :"+myCustomersDao.getAllCustomers());
	}

}
