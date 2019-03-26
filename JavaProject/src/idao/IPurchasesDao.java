package idao;

public interface IPurchasesDao {
	
	public void addCouponPurchase (int customerID, int couponID) throws Exception;
	public void deleteCouponPurchase (int customerID, int couponID) throws Exception;
	public boolean isCouponPurchaseExists(int customerID, int couponID) throws Exception;

}
