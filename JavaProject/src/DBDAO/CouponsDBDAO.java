package DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DAO.CouponsDAO;
import JavaBeans.Coupon;

public class CouponsDBDAO implements  CouponsDAO{
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) throws Exception {

		Connection connection = null;

		try {

			connection = connectionPool.getConnection();
			System.out.println("Hi");

			//Creating a String out of Date in the correct format for an SQL Statement "yyyy/MM/dd" 
			String pattern = "yyyy/MM/dd";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String startDateStr = simpleDateFormat.format(coupon.getStartDate());
			String endDateStr = simpleDateFormat.format(coupon.getEndDate());
			
			String sql = String.format("INSERT INTO COUPONS(title, description, start_Date, end_Date, amount, price, image) " + "VALUES('%s', '%s', '%s', '%s', '%d', '%d', '%s')",
					coupon.getTitle(),coupon.getDescription() , startDateStr , endDateStr , coupon.getAmount(), (int)coupon.getPrice(), coupon.getImage() );
			
			System.out.println(sql);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					coupon.setId(id); // Add the new created id into the company object.
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}		
	}

	@Override
	public void updateCoupon(Coupon coupon) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCoupon(int couponID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Coupon> getAllCoupons() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Coupon getOneCoupon(int CouponID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) {
		// TODO Auto-generated method stub
		
	}

}
