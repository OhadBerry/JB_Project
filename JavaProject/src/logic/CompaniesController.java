package logic;

import dao.CompaniesDao;
import exceptions.ApplicationException;
import exceptions.ErrorType;
import javabeans.Company;

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
		if (isCompanyNameExists(company)) {
			throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, "Failed to create Company, Company Name Already Exists");
		}
		
		companiesDao.createCompany(company);
	}
	
	public void updateCompany(Company company) throws Exception {
		if (isValidCompany(company)) {
			companiesDao.updateCompany(company);
			return;
		}	
		throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, "Failed to update Company, Company Name Already Exists"); 
	}
	
	public void deleteCompany(long companyID) throws ApplicationException {
		couponsController.deleteCouponsByCompanyID(companyID);
		purchasesController.deletePurchasesByCompanyID(companyID);
	}
	
	public Company getCompanyByCompanyID(long companyID) throws Exception {
		return companiesDao.getCompanyById(companyID);
	}

	private boolean isCompanyNameExists(Company company) throws Exception {
		if (companiesDao.isCompanyExistsByName(company.getName())) {
			return true;
		}
		return false;
	}

}
