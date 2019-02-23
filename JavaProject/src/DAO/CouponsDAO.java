package DAO;
import java.util.ArrayList;

import JavaBeans.Coupon;

public interface CouponsDAO {
	
	public void addCoupon (Coupon coupon) throws Exception;
	public void updateCoupon (Coupon coupon) throws Exception;
	public void deleteCoupon (int couponID) throws Exception;
	public ArrayList<Coupon> getAllCoupons() throws Exception;
	public Coupon getOneCoupon(int CouponID) throws Exception;
	public void addCouponPurchase (int customerID, int couponID) throws Exception;
	public void deleteCouponPurchase (int customerID, int couponID) throws Exception;
	public boolean isCouponPurchaseExists(int customerID, int couponID) throws Exception;

}
