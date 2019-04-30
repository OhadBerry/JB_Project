package program;

import java.sql.Date;

import javabeans.Category;
import javabeans.Company;
import javabeans.Coupon;
import logic.CompaniesController;
import logic.CouponsController;

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
//			myCompaniesController.createCompany(company);
			System.out.println(myCompaniesController.getCompanyByCompanyID(company_id));
//			myCompaniesController.updateCompany(new Company((long)2,"UpdatedCompany2"));
			System.out.println(myCompaniesController.getCompanyByCompanyID(1));
			System.out.println(myCompaniesController.getCompanyByCompanyID(3));
			
			System.out.println("Testing Coupons Controller");
			CouponsController myCouponsController = new CouponsController();
			long coupon_id = 2;
			String coupon_name = "MyCoupon"; String title = coupon_name;
			String description = "MyDescription";
			Date startDate = new Date(0,0,0);
			Date endDate = new Date(100,10,10);
			int amount = 5;
			double price = 75.5;
			String image = "MyImage";
			Category category = Category.Food;
			Coupon coupon = new Coupon(coupon_id, company_id, category, title, description, startDate, endDate, amount, price, image);
			
			myCouponsController.createCoupon(coupon);
			System.out.println(myCouponsController.getCouponByID(coupon_id));
			Coupon updatedCoupon = new Coupon(coupon_id, company_id, category, "MyUpdatedCoupon", description, startDate, endDate, amount, price, image);
			myCouponsController.updateCoupon(updatedCoupon);
			System.out.println(myCouponsController.getCouponByID(coupon_id));
			myCouponsController.deleteCouponByID(coupon_id);
			System.out.println(myCouponsController.getCouponByID(coupon_id));

			
			
			
		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
}
	
	
	
