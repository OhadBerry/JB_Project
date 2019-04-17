package idao;

import javabeans.Purchase;

public interface IPurchasesDao {
	
	
	void createCouponPurchase(Purchase purchase) throws Exception;
	public void deleteCouponPurchase (long customer_ID, long coupon_ID) throws Exception;
	public boolean isCouponPurchaseExists(long customer_ID, long coupon_ID) throws Exception;
	

}
