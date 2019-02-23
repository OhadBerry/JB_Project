package Facade;

import java.util.Date;
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

	public boolean login(String email,String password) throws Exception {
		return customersDAO.isCustomerExists(email, password);
	}
	
	public void purchaseCoupon(Coupon coupon) throws Exception {
		if (!(couponsDAO.isCouponPurchaseExists(customerID,coupon.getId())) //Checking that the requested coupon doesn't already exist
			&& (coupon.getAmount() > 0)										//Checking that the requested coupon's amount is more than 0
			&& (coupon.getEndDate().after(new Date()))) 					//Checking that the requested coupon hasn't expired
		{
			couponsDAO.addCouponPurchase(customerID,coupon.getId());
			Coupon updatedCoupon = new 	Coupon(coupon.getId(),coupon.getCompany_id(),coupon.getCategory(),coupon.getTitle(),coupon.getDescription(), //Creating an Updated Coupon
										coupon.getStartDate(),coupon.getEndDate(),(coupon.getAmount()-1),coupon.getPrice(),coupon.getImage());		 //With Amount-1

			couponsDAO.updateCoupon(updatedCoupon);
		}
		
	}
	
	public ArrayList<Coupon> getCustomerCoupons() throws Exception{
		return couponsDAO.getAllCoupons();
	}
	
	public ArrayList<Coupon> getCustomerCoupons(Category category) throws Exception{
		ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
		ArrayList<Coupon> allCouponsByCategory = new ArrayList<Coupon>();
		for (Coupon c : allCoupons) {
			if (c.getCategory() == category) {
				allCouponsByCategory.add(c);
			}
		}
		return allCouponsByCategory;
		
	}
	
	public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws Exception{
		ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
		ArrayList<Coupon> allCouponsByPrice = new ArrayList<Coupon>();
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
