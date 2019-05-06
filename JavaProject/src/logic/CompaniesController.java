package logic;

import beans.Company;
import dao.CompaniesDao;
import enums.ErrorType;
import exceptions.ApplicationException;
import utils.ValidationUtils;

public class CompaniesController {
	
	private CompaniesDao companiesDao;
	private CouponsController couponsController;
	private PurchasesController purchasesController;
	
	public CompaniesController() {
		this.companiesDao = new CompaniesDao();
		this.couponsController = new CouponsController();
		this.purchasesController = new PurchasesController();
	}
	
	public void createCompany(Company company) throws Exception {
		if (isValidCompany(company)) {
			companiesDao.createCompany(company);
		}
	}
	
	public void updateCompany(Company company) throws Exception {
		if (isValidCompany(company)) {
			companiesDao.updateCompany(company);
		}	
	}
	
	public void deleteCompany(long companyID) throws ApplicationException {
		couponsController.deleteCouponsByCompanyID(companyID);
		purchasesController.deletePurchasesByCompanyID(companyID);
	}
	
	public Company getCompanyByCompanyID(long companyID) throws Exception {
		return companiesDao.getCompanyById(companyID);
	}

	private boolean isValidCompany(Company company) throws Exception {

		//Validating Integrity of Company Name
		if (ValidationUtils.isValidString(company.getName())) {
			if (companiesDao.isCompanyExistsByName(company.getName())) {
				throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, "Failed to create Company, Company Name Already Exists");
			}
		}
		return true;
	}

}
