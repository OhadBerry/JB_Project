package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDAO;
import DAO.CustomersDAO;
import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;

public abstract class ClientFacade {
	
	//-----------------------Properties--------------------------
	
	CompaniesDAO companiesDAO = new CompaniesDBDAO();
	CouponsDAO couponsDAO = new CouponsDBDAO();
	CustomersDAO customersDAO = new CustomersDBDAO();
	
	//-----------------------Methods--------------------------
	
	public boolean login(String email,String password) throws Exception {
		return false;}
	

}
