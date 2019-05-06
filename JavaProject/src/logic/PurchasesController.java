package logic;

import beans.Purchase;
import dao.PurchasesDao;
import exceptions.ApplicationException;

public class PurchasesController {
	
	private PurchasesDao purchasesDao;
	
	public PurchasesController() {
		this.purchasesDao = new PurchasesDao();
	}
	
	public long createPurchase(Purchase purchase) throws Exception {
		return purchasesDao.createCouponPurchase(purchase);
	}
	
	public Purchase getPurchaseByPurchaseID(long purchaseID) throws ApplicationException {
		return purchasesDao.getPurchaseByPurchaseID(purchaseID);
	}

	public  void deletePurchasesByCompanyID(long companyID) throws ApplicationException {
		purchasesDao.deletePurchasesByCompanyID(companyID);
	}
	
	public  void deletePurchasesByPurchaseID(long purchaseID) throws ApplicationException {
		purchasesDao.deletePurchasesByPurchaseID(purchaseID);
	}

	public void deletePurchasesByCouponID(long couponID) throws ApplicationException {
		purchasesDao.deleteCouponPurchaseByCouponID(couponID);
		
	}

	public void deletePurchasesByCustomerID(long customerID) throws ApplicationException {
		purchasesDao.deletePurchasesByCustomerID(customerID);		
	}

}
