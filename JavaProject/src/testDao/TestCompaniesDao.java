package testDao;

import dao.CompaniesDao;
import javabeans.Company;

public class TestCompaniesDao {
	
	//Parameters
	CompaniesDao myCompanyDao = new CompaniesDao(); 
	private Company company;
	private long companyId;
	
	//Constructor
	public TestCompaniesDao() {
		System.out.println("Initializing TestCompanyDao Params");
		company = new Company("myTestCompany");
		companyId = 0;
	}
	
	
	//Getters and Setters
	public Company getCompany() {
		return company;
	}


	public void setCompany(Company company) {
		this.company = company;
	}


	public long getCompanyId() {
		return companyId;
	}


	public void setCompanyId(long companyId) {
		this.companyId = companyId;
	}


	//Methods (Tests) 
	public void testIsCompanyExistsById(long companyId) throws Exception{
		System.out.println("Beginning testIsCompanyExistsById");
		System.out.println(myCompanyDao.isCompanyExistsById(companyId));
		System.out.println("IsCompanyExistsById Tested");
	}
	public void testCreateCompany (Company company) throws Exception{
		System.out.println("Beginning testCreateCompany");
		myCompanyDao.createCompany(company);
		System.out.println("createCompany Tested");
	}
	public void testUpdateCompany (Company company) throws Exception{
		System.out.println("Beginning testUpdateCompany");
		myCompanyDao.updateCompany(company);
		System.out.println("updateCompany Tested");
	}
	public void testDeleteCompanyById (long companyId) throws Exception{
		System.out.println("Beginning testDeleteCompanyById");
		myCompanyDao.deleteCompanyById(companyId);
		System.out.println("deleteCompanyById Tested");
		
	}
	public void testGetOneCompanyById(long companyId) throws Exception{
		System.out.println("Beginning testGetCompanyById");
		System.out.println(myCompanyDao.getCompanyById(companyId));
		System.out.println("getCompanyById Tested");
		
	}
	public void testGetAllCompanies() throws Exception{
		System.out.println("Beginning testGetAllCompanies");
		System.out.println(myCompanyDao.getAllCompanies());
		System.out.println("getAllCompanies Tested");		
	}
	
	public static void AllTests() throws Exception {
		System.out.println("Testing CompaniesDao");
		TestCompaniesDao companiesTestBench = new TestCompaniesDao();
		companiesTestBench.testCreateCompany(companiesTestBench.getCompany());
		companiesTestBench.testUpdateCompany(new Company("UpdatedCompany"));
		companiesTestBench.testIsCompanyExistsById(31);
		companiesTestBench.testGetOneCompanyById(31);
		companiesTestBench.testDeleteCompanyById(30);
		companiesTestBench.testGetAllCompanies();
	}


}
