package logic;

import beans.Customer;
import dao.CustomersDao;
import enums.ErrorType;
import exceptions.ApplicationException;

public class CustomersController {
	
	private CustomersDao customersDao;
	private UsersController usersController;
	private PurchasesController purchasesController;
	
	public CustomersController() {
		this.customersDao = new CustomersDao();
		this.usersController = new UsersController();
		this.purchasesController = new PurchasesController();
	}
	
	public long createCustomer(Customer customer) throws Exception  {
		//Creating a new User	
		long id = this.usersController.createUser(customer.getUser());
		
		//Setting customer ID which is actually customer.user.id
		customer.setId(id);
				
		//Creating new customer with newly updated id in beans
		return customersDao.createCustomer(customer);
		
	}
	
	public void updateCustomer (Customer toBeUpdatedCustomer) throws Exception {
		//Updating User
		usersController.updateUser(toBeUpdatedCustomer.getUser());
		
		//Updating Customer
		customersDao.updateCustomer(toBeUpdatedCustomer);
	}
	
	public void deleteCustomerByID (long customerID) throws Exception {
		if (!this.customersDao.isCustomerExistsById(customerID)) {
			throw new ApplicationException(ErrorType.ID_DONT_EXIST,"Could not delete customer by ID, this ID does not exist in the DB");
		}
		
		//Delete the purchases made by the Customer in the DB
		purchasesController.deletePurchasesByCustomerID(customerID);

		//Delete the Customer Details in the DB
		customersDao.deleteCustomer(customerID);
		
		//Delete the User Details in the DB
		usersController.deleteUserbyID(customerID);
	}
	
	public Customer getCustomerByID (long customerID) throws Exception {
		return customersDao.getCustomerById(customerID);
	}
	
	

}
