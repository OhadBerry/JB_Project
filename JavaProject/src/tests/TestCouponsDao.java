package tests;

import java.sql.Date;

import beans.Category;
import beans.Coupon;
import dao.CouponsDao;

public class TestCouponsDao {

	public static void AllTests() throws Exception {
		System.out.println("Testing CouponsDao");
		
		CouponsDao myCouponsDao = new CouponsDao();
		Coupon coupon = new Coupon((long)1,(long) 1,Category.Food, "myExpiredCoupon", "TestCoupon", new Date(0,0,0), new Date(119,1,1),1,10.0,"myImage");
		Coupon updatedCoupon = new Coupon((long)2,(long)1,Category.Food, "myUpdatedTestCoupon", "updatedTestCoupon", new Date(0,0,0), new Date(120,1,1),1,11.0,"myUpdatedImage");
		long couponId = 1;
		
		myCouponsDao.createCoupon(coupon);
//		myCouponsDao.updateCoupon(updatedCoupon);
//		System.out.println("Getting coupon number "+couponId+" :"+myCouponsDao.getCouponbyId(couponId));
//		myCouponsDao.deleteCouponById(couponId);
		System.out.println("Getting all coupons :"+myCouponsDao.getAllCoupons());
	}
}
