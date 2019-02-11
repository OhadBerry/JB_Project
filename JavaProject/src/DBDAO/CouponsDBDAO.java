package DBDAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DAO.CouponsDAO;
import JavaBeans.Coupon;
import JavaBeans.Category;


public class CouponsDBDAO implements  CouponsDAO{
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public void addCoupon(Coupon coupon) throws Exception {
		Connection connection = null;

		try {
			
			connection = connectionPool.getConnection();
			
			//Preparing Dates in String format in order to be read by SQL server
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String startDateString = format.format(coupon.getStartDate() );
			String endDateString = format.format(coupon.getEndDate() );
			
			//Preparing String Insert statement for sql server
			String sql = String.format("INSERT INTO COUPONS("
					+ "company_id, "
					+ "category_id, "
					+ "title, "
					+ "description, "
					+ "start_Date, "
					+ "end_Date, "
					+ "amount, "
					+ "price, "
					+ "image) " + 
					"VALUES('%d', '%d', '%s', '%s', '%s', '%s', '%d', '%d', '%s')",
					coupon.getCompany_id(),
					coupon.getCategory_id(),
					coupon.getTitle(),
					coupon.getDescription(), 
					startDateString , 
					endDateString, 
					coupon.getAmount(), 
					(int)coupon.getPrice(),
					coupon.getImage() );
			
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
	public void updateCoupon(Coupon coupon) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();
			
			//Preparing Dates in String format in order to be read by SQL server
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String startDateString = format.format(coupon.getStartDate() );
			String endDateString = format.format(coupon.getEndDate() );

			String sql = String.format("UPDATE `javaproject`.`coupons`\r\n" + 
					"SET\r\n" +  
					"`company_id` = '%d',\r\n" + 
					"`category_id` = '%d',\r\n" + 
					"`TITLE` ='%s',\r\n" + 
					"`DESCRIPTION` = '%s',\r\n" + 
					"`START_DATE` = '%s',\r\n" + 
					"`END_DATE` = '%s',\r\n" + 
					"`AMOUNT` =  '%d',\r\n" + 
					"`PRICE` = '%d',\r\n" + 
					"`IMAGE` = '%s'\r\n" + 
					"WHERE `ID` = '%d';",
					coupon.getCompany_id(), 
					coupon.getCategory_id(), 
					coupon.getTitle(),
					coupon.getDescription() , 
					startDateString, 
					endDateString, 
					coupon.getAmount(), 
					(int)coupon.getPrice(), 
					coupon.getImage(),
					coupon.getId());
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			} 
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public void deleteCoupon(int couponID) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM COUPONS WHERE ID=%d", couponID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public ArrayList<Coupon> getAllCoupons() throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM COUPONS";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();

					while (resultSet.next()) {
						
						int id = resultSet.getInt("ID");
						int company_id = resultSet.getInt("company_id");
						int category_id = resultSet.getInt("category_id");
						Category category = Category.values[category_id];
						String title = resultSet.getString("title");
						String description = resultSet.getString("description");
						Date startDate = resultSet.getDate("start_Date");
						Date endDate = resultSet.getDate("end_Date");
						int amount = resultSet.getInt("amount");
						double price = resultSet.getInt("price");
						String image = resultSet.getString("image");

						Coupon coupon = new Coupon(id, company_id, category, title, description, startDate, endDate, amount, price, image);

						allCoupons.add(coupon);
					}

					return allCoupons;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public Coupon getOneCoupon(int CouponID) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM Coupons where ID=%d", CouponID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {
					while (resultSet.first()) {
						int id = resultSet.getInt("ID");
						int company_id = resultSet.getInt("company_id");
						int category_id = resultSet.getInt("category_id");
						Category category = Category.values[category_id];
						String title = resultSet.getString("title");
						String description = resultSet.getString("description");
						Date startDate = resultSet.getDate("start_Date");
						Date endDate = resultSet.getDate("end_Date");
						int amount = resultSet.getInt("amount");
						double price = resultSet.getInt("price");
						String image = resultSet.getString("image");

						Coupon coupon = new Coupon(id, company_id, category, title, description, startDate, endDate, amount, price, image);

						return coupon;
					}
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
		return null;
	}


	@Override
	public void addCouponPurchase(int customerID, int couponID) throws Exception {
		
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();
			// Preparing String Insert statement for sql server
			String sql = String.format(
					"INSERT INTO customers_vs_coupons(" + "customer_id, " + "coupon_id) " + "VALUES('%d', '%d')",
					customerID, couponID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.first();
					int id = resultSet.getInt(1);
					System.out.println("Returned ID is: " + id); // Print out the new created ID.
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public void deleteCouponPurchase(int customerID, int couponID) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM customers_vs_coupons WHERE ID=%d", customerID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

}
