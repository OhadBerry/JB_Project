package DAO;
import java.util.ArrayList;

import JavaBeans.Coupon;

public interface CouponsDAO {
	
	public void addCoupon (Coupon coupon);
	public void updateCoupon (Coupon coupon);
	public void deleteCoupon (int couponID);
	public ArrayList<Coupon> getAllCoupons();
	public Coupon getOneCoupon(int CouponID);
	public void addCouponPurchase (int customerID, int couponID);
	public void deleteCouponPurchase (int customerID, int couponID);

}
