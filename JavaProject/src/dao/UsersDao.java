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

public class UsersDao implements IUsersDao{
	
	public void createUser(User user) throws ApplicationException {
		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;

		try {
			//Establish a connection from the connection manager

			//Creating the SQL query
			//CompanyID is defined as a primary key and auto incremented
			String sqlStatement="INSERT INTO Users (user_name, user_password, user_type, company_id) VALUES(?,?,?, ?)";

			//Combining between the syntax and our connection
			preparedStatement=connection.prepareStatement(sqlStatement);

			//Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1,user.getUserName());
			preparedStatement.setString(2,user.getPassword());
			preparedStatement.setString(3, user.getType().name());
			preparedStatement.setLong(4, user.getCompanyId());

			//Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			//If there was an exception in the "try" block above, it is caught here and notifies a level above.
			throw new ApplicationException(e, ErrorType.GENERAL_ERROR, DateUtils.getCurrentDateAndTime().toString()
					+" Create company failed");
		} 
		finally {
			//Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
	
	
	public boolean login(String user, String password) throws ApplicationException {
		//Turn on the connections
		Connection connection=null;
		PreparedStatement preparedStatement=null;
		ResultSet result=null;
		Company company=new Company();

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
					+" Get company has failed");
		}
		finally {
			//Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement, result);	
		}
	}


	@Override
	public void updateUser(User user) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteUserById(Long user_id) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void getUserDetailsByUsernameAndPassword(String username, String password) throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

}
