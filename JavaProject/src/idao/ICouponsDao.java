package idao;
import java.util.ArrayList;

import javabeans.Coupon;

public interface ICouponsDao {
	
	public void createCoupon (Coupon coupon) throws Exception;
	public void updateCoupon (Coupon coupon) throws Exception;
	public void deleteCouponById (long couponId) throws Exception;
	public Coupon getOneCouponbyId(long couponId) throws Exception;
	public ArrayList<Coupon> getAllCoupons() throws Exception;

}
