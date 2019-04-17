package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.ApplicationException;
import exceptions.ErrorType;
import idao.ICouponsDao;
import javabeans.Category;
import javabeans.Coupon;
import utils.DateUtils;
import utils.JdbcUtils;

public class CouponsDao implements  ICouponsDao{

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
					"(`company_id`,\r\n" + 
					"`category`,\r\n" + 
					"`Coupon_TITLE`,\r\n" + 
					"`Coupon_DESCRIPTION`,\r\n" + 
					"`Coupon_START_DATE`,\r\n" + 
					"`Coupon_END_DATE`,\r\n" + 
					"`Coupon_AMOUNT`,\r\n" + 
					"`Coupon_PRICE`,\r\n" + 
					"`Coupon_IMAGE`)\r\n" + 
					"VALUES\r\n" + 
					"(?,\r\n" + 
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
			setCouponIntoPreparedStatement(preparedStatement,coupon);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to create a coupon");
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
					"`category` = ?,\r\n" + 
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
			setCouponIntoPreparedStatement(preparedStatement,coupon);
			preparedStatement.setLong(10,coupon.getId());
			
			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to update a coupon");
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
					"WHERE Coupon_id = ? ;";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, couponId);
			
			// Executing the update
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to delete a coupon");
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void deleteCouponByCompanyID(long companyID) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM `javaproject`.`coupons`\r\n" + 
					"WHERE company_id = ? ;" ;
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyID);
			
			// Executing the update
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
				DateUtils.getCurrentDateAndTime() + "FAILED to delete coupons by companyID");
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	@Override
	public Coupon getCouponbyId(long couponId) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE coupon_id = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, couponId);

			// Executing the query
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return extractCouponFromResultSet(resultSet);
			}
			
			return null;
				
		}
			catch (SQLException e) {
				// **If there was an exception in the "try" block above, it is caught here and
				// notifies a level above.
				e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED retrieve a coupon by Id");

			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}

	@Override
	public ArrayList<Coupon> getAllCoupons() throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Executing the query
			resultSet = preparedStatement.executeQuery();
			
			ArrayList<Coupon> allCoupons = new ArrayList<Coupon>();
			
			while (resultSet.next()) {
				allCoupons.add(extractCouponFromResultSet(resultSet));	
			}
			return allCoupons;
		}
			catch (SQLException e) {
				// **If there was an exception in the "try" block above, it is caught here and
				// notifies a level above.
				e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to get all coupons");

			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}


	private Coupon extractCouponFromResultSet(ResultSet resultSet) throws ApplicationException {

		try {
			long couponId = resultSet.getLong("coupon_id");
			long company_id = resultSet.getLong("company_id");
			Category category = Category.valueOf(resultSet.getString("category"));
			String title = resultSet.getString("Coupon_TITLE");
			String description = resultSet.getString("Coupon_DESCRIPTION");
			Date startDate = resultSet.getDate("Coupon_START_DATE");
			Date endDate = resultSet.getDate("Coupon_END_DATE");
			int amount = resultSet.getInt("Coupon_AMOUNT");
			double price = resultSet.getDouble("Coupon_PRICE");
			String image = resultSet.getString("Coupon_IMAGE");

			return new Coupon(couponId, company_id, category, title, description, startDate, endDate, amount, price,
					image);
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to Extract a coupon from Resultset");
		}
	}
	
	private PreparedStatement setCouponIntoPreparedStatement (PreparedStatement preparedStatement, Coupon coupon) throws Exception {
		
		try {
			preparedStatement.setLong(1,coupon.getCompany_id());
			preparedStatement.setString(2,coupon.getCategory().name());
			preparedStatement.setString(3, coupon.getTitle());
			preparedStatement.setString(4, coupon.getDescription());
			preparedStatement.setDate(5,(Date)coupon.getStartDate());
			preparedStatement.setDate(6,(Date)coupon.getEndDate());
			preparedStatement.setInt(7, coupon.getAmount());
			preparedStatement.setDouble(8, coupon.getPrice());
			preparedStatement.setString(9, coupon.getImage());
	
			return preparedStatement;
		}
		catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to Extract a coupon from Resultset");
		}
	}


	public boolean isCouponExistsByTitleAndCompanyID(String title, long companyID) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE title = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, title);

			// Executing the query, if result contains any data return true, otherwise
			// return false
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				if (resultSet.getLong("company_id") == companyID)
					return true;
			}
			return false;
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to check if a coupon exists title and companyID");
			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}
}


