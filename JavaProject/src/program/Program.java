package program;

import javabeans.Company;
import test.TestCompanyDao;

public class Program {

	public static void main(String[] args) {

		try {

			System.out.println("Welcome to the DBDAO program");
			TestCompanyDao testBench = new TestCompanyDao();
			testBench.testCreateCompany(testBench.getCompany());
			testBench.testUpdateCompany(new Company("UpdatedCompany"));
			testBench.testIsCompanyExistsById(31);
			testBench.testGetOneCompanyById(31);
			testBench.testDeleteCompanyById(30);
			testBench.testGetAllCompanies();			
			
			
		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
}
	
	
	
