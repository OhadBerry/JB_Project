package idao;

public interface IPurchasesDao {
	
	public void createCouponPurchase (long customer_ID, long coupon_ID) throws Exception;
	public void deleteCouponPurchase (long customer_ID, long coupon_ID) throws Exception;
	public boolean isCouponPurchaseExists(long customer_ID, long coupon_ID) throws Exception;

}
