package Facade;

import java.util.ArrayList;

import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CustomerFacade extends ClientFacade {

	//-----------------------Properties---------------------------
	
	private int customerID;
	
	//-----------------------Constructor---------------------------
	
	public CustomerFacade(int customerID) {
		super();
		this.customerID = customerID;
	}

	//-----------------------Methods ---------------------------

	public boolean login(String email,String password) {
		return false;}
	
	public void purchaseCoupon(Coupon coupon) throws Exception {
		couponsDAO.addCouponPurchase(customerID,coupon.getId());
		
	}
	
	public ArrayList<Coupon> getCustomerCoupons() throws Exception{
		return couponsDAO.getAllCoupons();
	}
	
	public ArrayList<Coupon> getCustomerCoupons(Category category) throws Exception{
		ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
		ArrayList<Coupon> allCouponsByCategory = null;
		for (Coupon c : allCoupons) {
			if (c.getCategory() == category) {
				allCouponsByCategory.add(c);
			}
		}
		return allCouponsByCategory;
		
	}
	
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws Exception{
		ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
		ArrayList<Coupon> allCouponsByPrice = null;
		for (Coupon c : allCoupons) {
			if (c.getPrice() <= maxPrice) {
				allCouponsByPrice.add(c);
			}
		}
		return allCouponsByPrice;
	}
	
	public Customer getCustomerDetails() throws Exception {
		return customersDAO.getOneCustomer(customerID);
		
	}

}
