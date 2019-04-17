package program;

import javabeans.Company;
import logic.CompaniesController;

public class Program {

	public static void main(String[] args) {

		try {

			System.out.println("Welcome to the DBDAO program");
			/*	
			TestCompaniesDao.AllTests();
			TestCouponsDao.AllTests();
			TestCustomersDao.AllTests();
			TestUsersDao.AllTests();
			TestPurchasesDao.AllTests();
			*/
			
			System.out.println("Testing Companies Controller");
			CompaniesController myCompaniesController = new CompaniesController();
			long company_id = 2;
			String company_name = "MyCompany";
			Company company = new Company(company_id, company_name);
			myCompaniesController.createCompany(company);
			System.out.println(myCompaniesController.getCompanyByCompanyID(company_id));
			myCompaniesController.updateCompany(new Company((long)2,"UpdatedCompany2"));
			System.out.println(myCompaniesController.getCompanyByCompanyID(1));
			
			
			
		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
}
	
	
	
