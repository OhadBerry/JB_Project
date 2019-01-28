package Program;


import java.sql.SQLException;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;

import java.util.Date;

public class Program {


    public static void main(String[] args) {

        try {

            System.out.println("Welcome to the companiesDBDAO program");

            
            
            //Create an instance of Company with values
            Company company = new Company(0, null, null, null, null);
            company.setName("JohnBryce");
            company.setEmail("johnBryce@gmail.com");
            company.setPassword("abcd");

            //Create an instance of Company with updated values to insert into "Update" method
            Company companyUpdated = new Company(0, null, null, null, null);
            companyUpdated.setName("JohnBryceUpdated");
            companyUpdated.setEmail("johnBryce@gmail.comUpdated");
            companyUpdated.setPassword("abcdUpdated");
            companyUpdated.setId(5);
            
            
            //Test Coupon DBDAO
            CouponsDBDAO	couponsDBDAO = new CouponsDBDAO();
            
			Coupon coupon = new Coupon(0, 0, 0,"myCoupon", "myDescription", new Date(), new Date(119,1,20), 100, 200, "myImage");
			couponsDBDAO.addCoupon(coupon);

            //Create an instance of Coupon with updated values to insert into "Update" method

            //Test Companies DBDAO
			CompaniesDBDAO 	companiesDBDAO = new CompaniesDBDAO();
			
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
