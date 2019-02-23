package Facade;

import java.util.ArrayList;

import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CompanyFacade extends ClientFacade {
	
		//-----------------------Properties---------------------------
	
		private int companyID;
		
		//-----------------------Constructor---------------------------
		
		public CompanyFacade(int companyID) {
			super();
			this.companyID = companyID;
		}
		
		//-----------------------Methods ---------------------------

		public boolean login(String email,String password) throws Exception {
			return companiesDAO.isCompanyExists(email, password);
		}
		
		public void addCoupon(Coupon coupon) throws Exception {
			ArrayList<Coupon> companyCoupons = companiesDAO.getOneCompany(coupon.getCompany_id()).getCoupons();
			
			//Checking if the same company already has coupon with the same title
			for (Coupon c : companyCoupons)
				if (c.getTitle() == coupon.getTitle() ) {
					System.out.println("This Company already has a Coupon with the same title");
					return;
				}
		
			couponsDAO.addCoupon(coupon);
		}
		
		public void updateCoupon(Coupon coupon) throws Exception {
			
			//Creating a Coupon with only the correct updated Values in order to send to updateCoupon Method
			Coupon theUpdatedCoupon = new Coupon();			
			theUpdatedCoupon.setAmount(coupon.getAmount());
			theUpdatedCoupon.setCategory_id(coupon.getCategory());
			theUpdatedCoupon.setDescription(coupon.getDescription());
			theUpdatedCoupon.setStartDate(coupon.getStartDate());
			theUpdatedCoupon.setEndDate(coupon.getEndDate());
			theUpdatedCoupon.setImage(coupon.getImage());
			theUpdatedCoupon.setPrice(coupon.getPrice());
			theUpdatedCoupon.setTitle(coupon.getTitle());
			
			//Sending the coupon with the updated values to updateCoupon Method
			couponsDAO.updateCoupon(theUpdatedCoupon);

		}
		
		public void deleteCoupon(int couponID) throws Exception {
			
			//Getting a list of all theCustomers in order to delete all of the records of this coupon being purchased
			ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();

			//Deleting all of the records of the Coupon's purchases by customers
			for (Customer c : allCustomers)
				couponsDAO.deleteCouponPurchase(c.getId(),couponID);
			
			couponsDAO.deleteCoupon(couponID);
			
		}
		
		public ArrayList<Coupon> getCompanyCoupons() throws Exception{
			ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
			ArrayList<Coupon> couponsByCompany = new ArrayList<Coupon>();
			for (Coupon c : allCoupons) {
				if (c.getCompany_id() == companyID) {
					couponsByCompany.add(c);
				}
			}
			return couponsByCompany;
		}
		
		public ArrayList<Coupon> getCompanyCoupons(Category category) throws Exception{
			ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
			ArrayList<Coupon> couponsByCompanyAndCategory = new ArrayList<Coupon>();
			for (Coupon c : allCoupons) {
				if (c.getCompany_id() == companyID && c.getCategory() == category) {
					couponsByCompanyAndCategory.add(c);
				}
			}
			return couponsByCompanyAndCategory;
			
		}
		
		public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws Exception{
			ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
			ArrayList<Coupon> allCouponsByCompanyAndPrice = new ArrayList<Coupon>();
			for (Coupon c : allCoupons) {
				if (c.getCompany_id() == companyID && c.getPrice() <= maxPrice) {
					allCouponsByCompanyAndPrice.add(c);
				}
			}
			return allCouponsByCompanyAndPrice;
		}
		
		public Company getCompanyDetails() throws Exception {
			return companiesDAO.getOneCompany(companyID);
			
		}

}
