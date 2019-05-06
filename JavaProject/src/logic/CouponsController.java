package logic;

import java.util.ArrayList;
import java.util.Date;

import beans.Coupon;
import dao.CouponsDao;
import enums.ErrorType;
import exceptions.ApplicationException;
import utils.ValidationUtils;

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
	
	public void updateCoupon(Coupon toBeUpdatedCoupon) throws Exception {
		Coupon oldCoupon = couponsDao.getCouponbyId(toBeUpdatedCoupon.getId());
		
		//Creating new coupon without updating ID and Company ID
		Coupon updatedCoupon = new Coupon(
				oldCoupon.getId(),
				oldCoupon.getCompany_id(),
				toBeUpdatedCoupon.getCategory(),
				toBeUpdatedCoupon.getTitle(), 
				toBeUpdatedCoupon.getDescription(), 
				toBeUpdatedCoupon.getStartDate(), 
				toBeUpdatedCoupon.getEndDate(), 
				toBeUpdatedCoupon.getAmount(), 
				toBeUpdatedCoupon.getPrice(), 
				toBeUpdatedCoupon.getImage());
		
		//Sending this coupon to be validated and if valid, updated and DB
		if (isValidCoupon(updatedCoupon)) {
			couponsDao.updateCoupon(updatedCoupon);
		}
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
	
	private boolean isValidCoupon (Coupon coupon) throws ApplicationException{

		//Validating Coupon's Strings
		if (!ValidationUtils.isValidString(coupon.getTitle())) {
			throw new ApplicationException(ErrorType.INVALID_STRING, "Coupon's title is invalid");
		}
		if (!ValidationUtils.isValidString(coupon.getDescription())) {
			throw new ApplicationException(ErrorType.INVALID_STRING, "Coupon's description is invalid");
		}
		if (!ValidationUtils.isValidString(coupon.getImage())) {
			throw new ApplicationException(ErrorType.INVALID_STRING, "Coupon's image is invalid");
		}			
		//If Coupon's title, description and image are valid Strings check if A company's coupon exists with the same title (if so throw exception)
		if (couponsDao.isCouponExistsByTitleAndCompanyID(coupon.getTitle(),coupon.getCompany_id())) {
			throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, "Invalid coupon, the coupon's title already exists for a coupon with the same company_id value");
		}
		
		//Validating Amount and price
		if (coupon.getAmount() < 0)
			throw new ApplicationException(ErrorType.INVALID_AMOUNT_VALUE, "Coupon's amount can only be a positive number");
		if (coupon.getPrice() < 0)
			throw new ApplicationException(ErrorType.INVALID_PRICE_VALUE, "Coupon's price can only be a positive number");
		
		//Validating Dates
		if (coupon.getEndDate().before(new Date()))
			throw new ApplicationException(ErrorType.INVALID_END_DATE, "coupon's end_date can only be after today's date");			
		if (coupon.getStartDate().after(coupon.getEndDate()))
			throw new ApplicationException(ErrorType.INVALID_START_AND_END_DATES, "coupon's end_date can only be after coupon's start_date");	
		
		return true;	
	}

}
