package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import DAO.CustomersDAO;

public abstract class ClientFacade {
	
	//-----------------------Properties--------------------------
	
	CompaniesDAO companiesDAO;
	CouponsDAO couponsDAO;
	CustomersDAO customersDAO;
	
	//-----------------------Methods--------------------------
	
	public boolean login(String email,String password) {
		return false;}
	

}
