package Facade;

import DAO.CompaniesDAO;
import DAO.CustomersDAO;

public class LoginManager {
	
	private static LoginManager instance = new LoginManager();;
	private CompaniesDAO companiesDAO;
	private CustomersDAO customersDAO;

	
	
	//Declaring a private Constructor
	private LoginManager() {
		
	}
	
	//getInstance Method
	public static LoginManager getInstance() {
		return instance;
	}
	
	//Login Method
	public ClientFacade login(String email,String password,ClientType clientType) throws Exception {
		switch (clientType) {
			
			case Administrator:
				if (email.contentEquals("admin@admin.com") && password.contentEquals("admin")) {
					return new AdminFacade();
				}
			
			case Company:
				if (companiesDAO.isCompanyExists(email,password)){
					return new CompanyFacade(companiesDAO.getCompanyID(email, password));
				}
			
			case Customer:
				if (customersDAO.isCustomerExists(email,password)) {
					return new CustomerFacade(customersDAO.getCustomerID(email, password));
				}
		}
		return null;
	}

}
