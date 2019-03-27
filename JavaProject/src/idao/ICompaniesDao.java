package idao;
import java.util.ArrayList;

import javabeans.Company;

public interface ICompaniesDao {
	public boolean isCompanyExistsById(long companyId) throws Exception;
	public void createCompany (Company company) throws Exception;
	public void updateCompany (Company company) throws Exception;
	public void deleteCompanyById (long companyId) throws Exception;
	public Company getCompanyById(long companyId) throws Exception;
	public ArrayList<Company> getAllCompanies() throws Exception;

}
