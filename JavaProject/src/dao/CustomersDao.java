package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import beans.Customer;
import enums.ErrorType;
import exceptions.ApplicationException;
import idao.ICustomersDao;
import utils.DateUtils;
import utils.JdbcUtils;

public class CustomersDao implements  ICustomersDao{

	@Override
	public long createCustomer(Customer customer) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "INSERT INTO `javaproject`.`customers`\r\n" + 
					"(`Customer_ID`,"+
					"`Customer_FIRSTNAME`,\r\n" + 
					"`Customer_LASTNAME`)\r\n" + 
					"VALUES\r\n" + 
					"(?,\r\n" +
					" ?," +
					"?);\r\n" ;

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			setCustomerIntoPreparedStatement(preparedStatement,customer);

			// Executing the update
			preparedStatement.executeUpdate();
			
			return customer.getId();

		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to create a customer");

		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	
	@Override
	public void updateCustomer(Customer customer) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "UPDATE `javaproject`.`customers`\r\n" + 
					"SET\r\n" +
					"" +
					"`Customer_FIRSTNAME` = ?,\r\n" + 
					"`Customer_LASTNAME` = ?\r\n" + 
					"WHERE `Customer_ID` = ?;";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,customer.getFirstName());
			preparedStatement.setString(2,customer.getLastName());			
			preparedStatement.setLong(3,customer.getId());
			
			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to update a customer");
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	@Override
	public void deleteCustomer(long customerID) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM `javaproject`.`customers`\r\n" + 
					"WHERE Customer_Id = ?;";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1,customerID);
			
			// Executing the update
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to delete a customer");
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	@Override
	public Customer getCustomerById(long customerID) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		UsersDao usersDao = new UsersDao();
		Customer customer = new Customer();

		try {
			connection = JdbcUtils.getConnection();
			
			//Getting user Details from the Users Table
			customer.setUser(usersDao.getUserByID(customerID));

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM customers WHERE customer_id = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerID);

			// Executing the query
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				extractCustomerFromResultSet(resultSet,customer);
				return customer;
			}
			
			return null;
				
		}
			catch (SQLException e) {
				// **If there was an exception in the "try" block above, it is caught here and
				// notifies a level above.
				e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED retrieve a customer by Id");

			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}
	
	public boolean isCustomerExistsById(long customerId) throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
	
		try {
			connection = JdbcUtils.getConnection();
	
			// Creating the SQL query
			String sqlStatement = "SELECT * FROM customers WHERE customer_id = ?";
	
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
	
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, customerId);
	
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
					DateUtils.getCurrentDateAndTime() + "FAILED to check if a customer exists by Id");
			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws Exception {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		Customer customer = new Customer();
		UsersDao usersDao = new UsersDao();


		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM Customers";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Executing the query
			resultSet = preparedStatement.executeQuery();
			
			ArrayList<Customer> allCustomers = new ArrayList<Customer>();
			
			while (resultSet.next()) {
				extractCustomerFromResultSet(resultSet,customer);
				customer.setUser(usersDao.getUserByID(customer.getId()));
				allCustomers.add(customer);	
			}
			return allCustomers;
		}
			catch (SQLException e) {
				// **If there was an exception in the "try" block above, it is caught here and
				// notifies a level above.
				e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to get all the customers");

			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}
	
	private PreparedStatement setCustomerIntoPreparedStatement (PreparedStatement preparedStatement,Customer customer) throws Exception {
		
		try {
		preparedStatement.setLong(1,customer.getId());
		preparedStatement.setString(2,customer.getFirstName());
		preparedStatement.setString(3,customer.getLastName());
		return preparedStatement;
		
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to Set a Customer to a PreparedStatement");
		}
	}
	
	private void extractCustomerFromResultSet (ResultSet resultSet, Customer customer) throws Exception {
		
		try {
			long customer_id = resultSet.getLong("customer_id");
			String firstName = resultSet.getString("Customer_FIRSTNAME");
			String lastName = resultSet.getString("Customer_LASTNAME");
			
			customer.setId(customer_id);
			customer.setFirstName(firstName);
			customer.setLastName(lastName);
			
		return;
		
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to Set a extract a customer from a ResultSet");
		}
	}
	
	
}



