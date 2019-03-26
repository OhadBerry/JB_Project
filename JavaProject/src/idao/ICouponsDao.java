package idao;
import java.util.ArrayList;

import javabeans.Coupon;

public interface ICouponsDao {
	
	public void addCoupon (Coupon coupon) throws Exception;
	public void updateCoupon (Coupon coupon) throws Exception;
	public void deleteCoupon (long couponID) throws Exception;
	public Coupon getOneCouponbyId(long CouponID) throws Exception;
	public ArrayList<Coupon> getAllCoupons() throws Exception;

}
