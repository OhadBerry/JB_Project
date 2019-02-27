package Program;

import java.util.ArrayList;
import java.util.Date;

import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;
import JavaBeans.Coupon;
import JavaBeans.Customer;

public class CouponExpirationDailyJob implements Runnable{
	private CouponsDBDAO couponsDAO = new CouponsDBDAO();
	private CustomersDBDAO customersDAO = new CustomersDBDAO();
	private boolean quit = false;
	
	
	//----------------------------------Constructors--------------------------------
	
	public CouponExpirationDailyJob(CouponsDBDAO couponsDAO, CustomersDBDAO customersDAO, boolean quit) {
		super();
		this.couponsDAO = couponsDAO;
		this.customersDAO = customersDAO;
		this.quit = quit;
	}
	
	public CouponExpirationDailyJob(){};
	
	
	//----------------------------------Methods --------------------------------
	
	public void run() {
		try {
			quit = false;
			while (!quit && ((new Date().getMinutes() == 00) && (new Date().getHours() == 0))) {
				System.out.println("*****Beginning Erase Loop*****");
				ArrayList<Coupon> allCoupons = couponsDAO.getAllCoupons();
				if (allCoupons != null) {
					for (Coupon theCoupon : allCoupons) {
						if (theCoupon.getEndDate().before(new Date())) {
							// Getting a list of all theCustomers in order to delete all of the records of
							// this coupon being purchased
							ArrayList<Customer> allCustomers = customersDAO.getAllCustomers();
		
							// Deleting all of the records of the Coupon's purchases by customers
							if (allCustomers != null) {
								for (Customer c : allCustomers)
									couponsDAO.deleteCouponPurchase(c.getId(),theCoupon.getId());
							}
							System.out.println("Deleting Coupon");
							couponsDAO.deleteCoupon(theCoupon.getId());
							System.out.println("Deleting Coupon Deleted");
						}
					}
				}
				Thread.sleep(5000); //Execute only once a Second
			}
//			Thread.sleep(1000*60*60*24); //Execute only once a Day
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	};
	


	public void stop() {quit = true;};

	
	
}


