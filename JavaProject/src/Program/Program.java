package Program;


import java.sql.SQLException;

import DBDAO.CompaniesDBDAO;
import JavaBeans.Company;

public class Program {


    public static void main(String[] args) {

        try {

            System.out.println("Welcome to the companiesDBDAO program");

            CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();

            Company company = new Company(0, null, null, null, null);
            company.setName("JohnBryce");
            company.setEmail("johnBryce@gmail.com");
            company.setPassword("abcd");
            
            Company companyUpdated = new Company(0, null, null, null, null);
            companyUpdated.setName("JohnBryceUpdated");
            companyUpdated.setEmail("johnBryce@gmail.comUpdated");
            companyUpdated.setPassword("abcdUpdated");
            companyUpdated.setId(5);


            
            companiesDBDAO.addCompany(company);
            System.out.println(companiesDBDAO.isCompanyExists("johnBryce@gmail.com","abcd"));
            companiesDBDAO.updateCompany(companyUpdated);
            System.out.println("Company Updated to "+companyUpdated.getName());
            Company company3 = companiesDBDAO.getOneCompany(5);
            System.out.println("Company3 ID: " + company3.getId());
            System.out.println("Company ID: " + company.getId());

        } catch (Exception ex) {

            System.out.println("Error: " + ex.getMessage());
        }

    }

}
