package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import exceptions.ApplicationException;
import exceptions.ErrorType;
import idao.ICouponsDao;
import javabeans.Category;
import javabeans.Coupon;
import utils.DateUtils;
import utils.JdbcUtils;

public class CouponDao implements  ICouponsDao{

	@Override
	public void createCoupon(Coupon coupon) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "INSERT INTO `javaproject`.`coupons`\r\n" + 
					"`company_id`,\r\n" + 
					"`category_id`,\r\n" + 
					"`Coupon_TITLE`,\r\n" + 
					"`Coupon_DESCRIPTION`,\r\n" + 
					"`Coupon_START_DATE`,\r\n" + 
					"`Coupon_END_DATE`,\r\n" + 
					"`Coupon_AMOUNT`,\r\n" + 
					"`Coupon_PRICE`,\r\n" + 
					"`Coupon_IMAGE`)\r\n" + 
					"VALUES\r\n" + 
					"?,\r\n" + 
					"?,\r\n" + 
					"?,\r\n" + 
					"?,\r\n" + 
					"?,\r\n" + 
					"?,\r\n" + 
					"?,\r\n" + 
					"?,\r\n" + 
					"?);";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1,coupon.getCompany_id());
			preparedStatement.setLong(2,coupon.getCategory_id());
			preparedStatement.setString(3, coupon.getTitle());
			preparedStatement.setString(4, coupon.getDescription());
			preparedStatement.setDate(5,(Date)coupon.getStartDate());
			preparedStatement.setDate(6,(Date)coupon.getEndDate());
			preparedStatement.setInt(7, coupon.getAmount());
			preparedStatement.setDouble(8, coupon.getPrice());
			preparedStatement.setString(9, coupon.getImage());

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTimeString() + "FAILED to create a company");
			// throw new Exception("Failed to create company " + company.toString()+"Failed
			// " ,e);
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	
	@Override
	public void updateCoupon(Coupon coupon) throws Exception {
		
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "UPDATE `javaproject`.`coupons`\r\n" + 
					"SET\r\n" + 
					"`company_id` = ?,\r\n" + 
					"`category_id` = ?,\r\n" + 
					"`Coupon_TITLE` = ?,\r\n" + 
					"`Coupon_DESCRIPTION` = ?,\r\n" + 
					"`Coupon_START_DATE` = ?,\r\n" + 
					"`Coupon_END_DATE` = ?,\r\n" + 
					"`Coupon_AMOUNT` = ?,\r\n" + 
					"`Coupon_PRICE` = ?,\r\n" + 
					"`Coupon_IMAGE` = ?\r\n" + 
					"WHERE `Coupon_ID` = ?;";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1,coupon.getCompany_id());
			preparedStatement.setLong(2,coupon.getCategory_id());
			preparedStatement.setString(3, coupon.getTitle());
			preparedStatement.setString(4, coupon.getDescription());
			preparedStatement.setDate(5,(Date)coupon.getStartDate());
			preparedStatement.setDate(6,(Date)coupon.getEndDate());
			preparedStatement.setInt(7, coupon.getAmount());
			preparedStatement.setDouble(8, coupon.getPrice());
			preparedStatement.setString(9, coupon.getImage());
			preparedStatement.setLong(10,coupon.getId());
			
			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTimeString() + "FAILED to create company");
			// throw new Exception("Failed to create company " + company.toString()+"Failed
			// " ,e);
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	@Override
	public void deleteCouponById(long couponId) throws Exception {
		
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM `javaproject`.`coupons`\r\n" + 
					"WHERE Coupon_id = ? ;\r\n" + 
					"";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, couponId);
			
			// Executing the update
			preparedStatement.executeUpdate();
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	@Override
	public Coupon getOneCouponbyId(long couponId) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Coupon> getAllCoupons() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
