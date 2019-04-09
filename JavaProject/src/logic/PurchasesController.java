package logic;

import dao.PurchasesDao;
import exceptions.ApplicationException;

public class PurchasesController {
	
	private PurchasesDao purchasesDao;
	
	public PurchasesController() {
		this.purchasesDao = new PurchasesDao();
	}
	

	public  void deletePurchasesByCompanyID(long companyID) throws ApplicationException {
		purchasesDao.deletePurchasesByCompanyID(companyID);
	}

}
