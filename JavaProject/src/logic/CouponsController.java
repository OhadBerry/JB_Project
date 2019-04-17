package logic;

import java.util.ArrayList;

import dao.CouponsDao;
import exceptions.ApplicationException;
import javabeans.Coupon;

public class CouponsController {
	
	private CouponsDao couponsDao;
	private PurchasesController purchasesController;
	
	public CouponsController() {
		this.couponsDao = new CouponsDao();
	}

	public void createCoupon(Coupon coupon) throws Exception {
		if (isValidCoupon(coupon)) {
			couponsDao.createCoupon(coupon);
		}	
	}
	
	public void updateCoupon(Coupon updatedCoupon) throws Exception {
		Coupon oldCoupon = couponsDao.getCouponbyId(updatedCoupon.getId());
		
		//Creating new valid coupon without updating ID and Company ID
		Coupon validCoupon = new Coupon(
				oldCoupon.getId(),
				oldCoupon.getCompany_id(),
				updatedCoupon.getCategory(),
				updatedCoupon.getTitle(), 
				updatedCoupon.getDescription(), 
				updatedCoupon.getStartDate(), 
				updatedCoupon.getEndDate(), 
				updatedCoupon.getAmount(), 
				updatedCoupon.getPrice(), 
				updatedCoupon.getImage());
		
		//Sending the valid coupon to update in the DB
		couponsDao.updateCoupon(validCoupon);
	}
	
	public void deleteCouponByID (long couponID) throws Exception {
		purchasesController.deletePurchasesByCouponID(couponID);
		couponsDao.deleteCouponById(couponID);
	}
	
	//This method is used by CompaniesController to delete all the Coupons that belong to a specific company
	public void deleteCouponsByCompanyID(long companyID) throws ApplicationException {
		couponsDao.deleteCouponByCompanyID(companyID);
	}
	
	public Coupon getCouponByID(long couponID) throws Exception {
		return couponsDao.getCouponbyId(couponID);
	}
	
	public ArrayList<Coupon> getAllCoupons() throws Exception {
		return couponsDao.getAllCoupons();
	}
	
	private boolean isValidCoupon(Coupon coupon) throws ApplicationException {
		if (!couponsDao.isCouponExistsByTitleAndCompanyID(coupon.getTitle(),coupon.getCompany_id())) {
			return true;
		}
		return false;			 
	}

}
