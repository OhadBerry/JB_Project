package program;

import java.util.Timer;
import java.util.TimerTask;

import dailyjob.DeleteCouponsDailyJob;
import tests.TestCouponsDao;

public class Program {

	public static void main(String[] args) {

		try {

			System.out.println("Welcome to the DBDAO program");
			/*	
			TestCompaniesDao.AllTests();
			TestCouponsDao.AllTests();
			TestCustomersDao.AllTests();
			TestUsersDao.AllTests();
			TestPurchasesDao.AllTests();
			*/
			
			// Creating a task 
			TimerTask timerTask = new DeleteCouponsDailyJob();
			
			// Creating a timer
			Timer timer = new Timer();
			
			// Tell the timer to run the task every 24 hours, starting of now
			timer.scheduleAtFixedRate(timerTask, 0, 1000*24*60*60);
			
			System.out.println("TimerTask started");

			try {
				// 10 seconds delay before canceling the task
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			// Removing the task
			timer.cancel();
			System.out.println("TimerTask cancelled");
			
		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
}
	
	
	
