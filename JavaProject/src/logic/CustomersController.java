package logic;

import dao.CustomersDao;
import javabeans.Customer;

public class CustomersController {
	
	private CustomersDao customersDao;
	private UsersController usersController;
	
	public CustomersController() {
		this.customersDao = new CustomersDao();
		this.usersController = new UsersController();
	}
	
	public void createCustomer(Customer customer) throws Exception  {
		long id = this.usersController.createUser(customer.getUser());
		customer.setId(id);
		customersDao.createCustomer(customer);
		
	}
	
	public Customer getCustomerByID (long customerID) throws Exception {
		return customersDao.getCustomerById(customerID);
	}
	
	public void updateCustomer (Customer updatedCustomer) throws Exception {
		customersDao.updateCustomer(updatedCustomer);
	}
	
	public void deleteCustomerByID (long customerID) throws Exception {
		customersDao.deleteCustomer(customerID);
	}

}
