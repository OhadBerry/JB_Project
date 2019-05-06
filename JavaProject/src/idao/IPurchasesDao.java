package idao;

import beans.Purchase;

public interface IPurchasesDao {
	
	
	long createCouponPurchase(Purchase purchase) throws Exception;
	public boolean isCouponPurchaseExists(long customer_ID, long coupon_ID) throws Exception;
	void deleteCouponPurchaseByCustomerIDAndCouponID(long customer_ID, long coupon_ID) throws Exception;
	

}
