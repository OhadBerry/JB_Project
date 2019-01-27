package DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.CouponsDAO;
import JavaBeans.Coupon;

public class CouponsDBDAO implements  CouponsDAO{
	private ConnectionPool connectionPool;

	@Override
	public void addCoupon(Coupon coupon) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("INSERT INTO COMPANIES(company_id, category_id, title, description, startDate, endDate, amount, price, image) " + "VALUES('%d', '%d', '%s', '%s', '%d', '%d', '%d', '%d', '%s')",
					coupon.getCompany_id(), coupon.getCategory_id(), coupon.getTitle(),coupon.getDescription() , coupon.getStartDate() , coupon.getEndDate() , coupon.getAmount(), coupon.getPrice(), coupon.getImage() );

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
