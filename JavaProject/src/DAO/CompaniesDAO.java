package DAO;
import java.util.ArrayList;

import JavaBeans.Company;

public interface CompaniesDAO {
	public boolean isCompanyExists(String email, String password) throws Exception;
	public void addCompany (Company company) throws Exception;
	public void updateCompany (Company company) throws Exception;
	public void deleteCompany (int companyID) throws Exception;
	public ArrayList<Company> getAllCompanies() throws Exception;
	public Company getOneCompany(int companyID) throws Exception;

}
