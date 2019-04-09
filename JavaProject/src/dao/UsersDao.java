package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import exceptions.ApplicationException;
import exceptions.ErrorType;
import idao.IUsersDao;
import utils.*;
import javabeans.*;
import logic.ClientType;

public class UsersDao implements IUsersDao{
	
	public long createUser(User user) throws ApplicationException {
		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		try {
			//Establish a connection from the connection manager
			connection=JdbcUtils.getConnection();

			//Creating the SQL query
			//CompanyID is defined as a primary key and auto incremented
			
			String sqlStatement = null;

			// 2 types of users, we insert the companyId parameter for a "company" type user
			if (user.getCompanyId() == null){
				sqlStatement="INSERT INTO Users (user, password, type) VALUES(?,?,?)";
			}
			else {
				sqlStatement="INSERT INTO Users (user, password, type, ) VALUES(?,?,?, ?)";
			}

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement, PreparedStatement.RETURN_GENERATED_KEYS);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,user.getUserName());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3, user.getType().name());

			//Executing the update
			preparedStatement.executeUpdate();
			
			ResultSet resultSet = preparedStatement.getGeneratedKeys();
			if (resultSet.next()) {
				long id = resultSet.getLong(1);
				return id;
			}
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "Failed to create a user");
			
		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime()
					+" Create user failed");
		} 
		finally {
			//Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	


	@Override
	public void updateUser(User user) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "UPDATE `javaproject`.`users`\r\n" + 
					"SET\r\n" +  
					"`user_name` = ?,\r\n" + 
					"`user_password` = ?,\r\n" + 
					"`company_id` = ?,\r\n" + 
					"`user_type` = ?\r\n" + 
					"WHERE `user_id` = ?;";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			setUserIntoPreparedStatement(preparedStatement,user);
			preparedStatement.setLong(5,user.getId());
			
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
	public void deleteUserById(long User_ID) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM `javaproject`.`users`\r\n" + 
					"WHERE User_id = ?;";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1,User_ID);
			
			// Executing the update
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to delete a user");
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	@Override
	public User getUserByID(long User_ID) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM Users WHERE user_ID = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, User_ID);

			// Executing the query
			resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				return extractUserFromResultSet(resultSet);
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

	
	
	public boolean login(String user, String password) throws ApplicationException {
		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;

		try {
			//Establish a connection from the connection manager
			connection=JdbcUtils.getConnection();

			//Creating the SQL query
			String sqlStatement="SELECT * FROM Users WHERE user_name = ? && password = ?";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, user);
			preparedStatement.setString(2, password);

			//Executing the query and saving the DB response in the resultSet.
			result=preparedStatement.executeQuery();

			if (!result.next()) {
				return false;
			}
			return true;
		} catch (SQLException e) {
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException( e, ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime()
					+" Login has failed");
		}
		finally {
			//Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, result);	
		}
	}
	
	private PreparedStatement setUserIntoPreparedStatement(PreparedStatement preparedStatement,User user) throws ApplicationException {
		
		try {
		
		preparedStatement.setString(1,user.getUserName());
		preparedStatement.setString(2,user.getPassword());
		preparedStatement.setLong(3,user.getCompanyId());
		preparedStatement.setString(4,user.getType().name());
		
		return preparedStatement;
		
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to Set a User to a PreparedStatement");
		}
	}
	
	private User extractUserFromResultSet (ResultSet resultSet) throws ApplicationException {
		
		try {
			long id = resultSet.getLong("user_id");
			String userName = resultSet.getString("user_name");
			String password = resultSet.getString("user_password");
			Long companyId = resultSet.getLong("company_id");
			ClientType type = ClientType.valueOf(resultSet.getString("user_type"));
			
		return new User(id, userName, password, companyId, type);
		
		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTime() + "FAILED to extract a User from a ResultSet");
		}
	}



	public boolean isUserExistsByName(String userName) throws ApplicationException {
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM Users WHERE user_name = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, userName);

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
					DateUtils.getCurrentDateAndTime() + "FAILED to check if a user exists by name");
			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement,resultSet);
		}
	}

}
