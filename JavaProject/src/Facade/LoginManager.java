package Facade;

import DAO.CompaniesDAO;
import DAO.CustomersDAO;
import DBDAO.CompaniesDBDAO;
import DBDAO.CustomersDBDAO;

public class LoginManager {
	
	private static LoginManager instance = new LoginManager();;
	private CompaniesDAO companiesDAO = new CompaniesDBDAO();
	private CustomersDAO customersDAO = new CustomersDBDAO();

	
	
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
			{
				System.out.println(email+"|"+password);
				if (companiesDAO.isCompanyExists(email,password)){
					return new CompanyFacade(companiesDAO.getCompanyID(email, password));
				}
			}
			
			case Customer:
				if (customersDAO.isCustomerExists(email,password)) {
					return new CustomerFacade(customersDAO.getCustomerID(email, password));
				}
		}
		return null;
	}

}
