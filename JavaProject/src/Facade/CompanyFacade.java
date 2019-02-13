package Facade;

import java.util.ArrayList;

import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;

public class CompanyFacade extends ClientFacade {
	
	//-----------------------Properties---------------------------
	
		private int companyID;
		
		//-----------------------Constructor---------------------------
		
		public CompanyFacade(int customerID) {
			super();
			this.companyID = customerID;
		}
		
		//-----------------------Methods ---------------------------

		public boolean login(String email,String password) {
			return false;}
		
		public void addCoupon(Coupon coupon) throws Exception {
			couponsDAO.addCoupon(coupon);
		}
		
		public void updateCoupon(Coupon coupon) throws Exception {
			couponsDAO.updateCoupon(coupon);

		}
		
		public void deleteCoupon(int couponID) throws Exception {
			couponsDAO.deleteCoupon(couponID);
			
		}
		
		public ArrayList<Coupon> getCompanyCoupons() throws Exception{
			ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
			ArrayList<Coupon> allCouponsByCompany = null;
			for (Coupon c : allCoupons) {
				if (c.getCompany_id() == companyID) {
					allCouponsByCompany.add(c);
				}
			}
			return allCouponsByCompany;
		}
		
		public ArrayList<Coupon> getCompanyCoupons(Category category) throws Exception{
			ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
			ArrayList<Coupon> allCouponsByCompanyAndCategory = null;
			for (Coupon c : allCoupons) {
				if (c.getCompany_id() == companyID && c.getCategory() == category) {
					allCouponsByCompanyAndCategory.add(c);
				}
			}
			return allCouponsByCompanyAndCategory;
			
		}
		
		public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws Exception{
			ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
			ArrayList<Coupon> allCouponsByCompanyAndPrice = null;
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
