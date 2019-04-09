package logic;

import dao.CouponsDao;
import exceptions.ApplicationException;
import javabeans.Company;

public class CouponsController {
	
	private CouponsDao couponsDao;
	
	public CouponsController() {
		this.couponsDao = new CouponsDao();
	}

	public void CreateCompany(Coupon coupon) throws Exception {
		if (isValidCoupon(coupon)) {
			couponsDao.createCoupon(coupon);
		}	
	}
	
	public void deleteCouponsByCompanyID(long companyID) throws ApplicationException {
		couponsDao.deleteCouponByCompanyID(companyID);
	}
	
	private boolean isValidCoupon(Coupon coupon) {
		
	}

}
