package dailyjob;
import java.util.Calendar;
import java.util.Date;
import java.util.TimerTask;

import dao.CouponsDao;

public class DeleteCouponsDailyJob extends TimerTask {
	
	@Override
    public void run() {
		
		CouponsDao myCouponsDao = new CouponsDao();
		
		long now = Calendar.getInstance().getTimeInMillis();
		Date todayDate = new Date(now);
		System.out.println("Date is: "+todayDate);
		System.out.println("Deleting Expired Coupons");
		
		try {
			myCouponsDao.deleteExpiredCoupons();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Expired Coupons deleted");
    }
    
    
}