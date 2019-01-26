package DBDAO;

import java.util.ArrayList;

import DAO.CouponsDAO;
import JavaBeans.Coupon;

public class CouponsDBDAO implements  CouponsDAO{
	private ConnectionPool connectionPool;

	@Override
	public void addCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCoupon(int couponID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coupon getOneCoupon(int CouponID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub
		
	}

}
