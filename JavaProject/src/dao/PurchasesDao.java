package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ApplicationException;
import exceptions.ErrorType;
import idao.IPurchasesDao;
import javabeans.Category;
import javabeans.Coupon;
import javabeans.Purchase;
import utils.DateUtils;
import utils.JdbcUtils;

public class PurchasesDao implements IPurchasesDao{

	public void createCouponPurchase(Purchase purchase) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "INSERT INTO `javaproject`.`purchases`\r\n" + 
					"(`purchase_id`,\r\n" + 
					"`customer_id`,\r\n" + 
					"`coupon_id`,\r\n" + 
					"`amount`)\r\n" + 
					"VALUES\r\n" + 
					"(?,\r\n" + 
					"?,\r\n" + 
					"?,\r\n" + 
					"?);";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			setPurchaseIntoPreparedStatement(preparedStatement,purchase);

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to create a purchase");
			// throw new Exception("Failed to create company " + company.toString()+"Failed
			// " ,e);
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	
	public void deleteCouponPurchaseByCustomerIDAndCouponID(long customer_ID, long coupon_ID) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM `javaproject`.`purchases`\r\n" + 
					"WHERE customer_ID = ? && coupon_ID = ?;";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1,customer_ID);
			preparedStatement.setLong(2,coupon_ID);

			// Executing the update
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to delete a purchase");
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void deletePurchasesByCompanyID(long companyID) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM purchases WHERE coupon_id IN (SELECT id FROM coupons WHERE company_id=?);";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1,companyID);

			// Executing the update
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to delete purchases by companyID");
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	@Override
	public boolean isCouponPurchaseExists(long customer_ID, long coupon_ID) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM `javaproject`.`purchases` WHERE customer_ID = ? && coupon_ID = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customer_ID);
			preparedStatement.setLong(2, coupon_ID);

			// Executing the query, if result contains any data return true, otherwise
			// return false
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return true;
			}
			return false;
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to check if a purchase exists");
			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}

	
	
	private PreparedStatement setPurchaseIntoPreparedStatement (PreparedStatement preparedStatement,Purchase purchase) throws Exception {
		
		try {
		preparedStatement.setLong(1,purchase.getPurchase_ID());
		preparedStatement.setLong(2,purchase.getCustomer_id());
		preparedStatement.setLong(3,purchase.getCoupon_id());
		preparedStatement.setInt(4,purchase.getAmount());

		return preparedStatement;
		
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to Set a Purchase to a PreparedStatement");
		}
	}

	public void deleteCouponPurchaseByCouponID(long couponID) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM `javaproject`.`purchases`\r\n" + 
					"WHERE coupon_ID = ?;";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1,couponID);

			// Executing the update
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to delete a purchase by couponID");
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void deletePurchasesByPurchaseID(long purchaseID) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM `javaproject`.`purchases`\r\n" + 
					"WHERE purchase_id = ?;";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1,purchaseID);

			// Executing the update
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to delete a purchase by purchaseID");
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}



	
	public Purchase getPurchaseByPurchaseID(long purchaseID) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM coupons WHERE purchase_id = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, purchaseID);

			// Executing the query
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return extractPurchaseFromResultSet(resultSet);
			}
			
			return null;
				
		}
			catch (SQLException e) {
				// **If there was an exception in the "try" block above, it is caught here and
				// notifies a level above.
				e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED retrieve a purchase by purchase_Id");

			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}


	private Purchase extractPurchaseFromResultSet(ResultSet resultSet) throws ApplicationException {
		try {
			long purchaseID = resultSet.getLong("purchase_id");
			long couponId = resultSet.getLong("coupon_id");
			long customer_id = resultSet.getLong("customer_id");			
			int amount = resultSet.getInt("AMOUNT");

			return new Purchase(purchaseID, couponId, customer_id,amount);
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to Extract a purchase from Resultset");
		}
	}
}
