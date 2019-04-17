package logic;

import dao.PurchasesDao;
import exceptions.ApplicationException;

public class PurchasesController {
	
	private PurchasesDao purchasesDao;
	
	public PurchasesController() {
		this.purchasesDao = new PurchasesDao();
	}
	
	public void createPurchase(Purchase purchase) {
		purchasesDao.createCouponPurchase(customer_ID, coupon_ID);
	}

	public  void deletePurchasesByCompanyID(long companyID) throws ApplicationException {
		purchasesDao.deletePurchasesByCompanyID(companyID);
	}


	public void deletePurchasesByCouponID(long couponID) throws ApplicationException {
		purchasesDao.deleteCouponPurchaseByCouponID(couponID);
		
	}

}
