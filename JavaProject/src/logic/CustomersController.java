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

}
