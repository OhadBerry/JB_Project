package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import exceptions.ApplicationException;
import exceptions.ErrorType;
import idao.ICompaniesDao;
import javabeans.Company;
import javabeans.Coupon;
import utils.DateUtils;
import utils.JdbcUtils;

public class CompanyDao implements ICompaniesDao {
	

	public boolean isCompanyExistsById(long companyId) throws Exception {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "SELECT * FROM companies WHERE COMPANY_ID = ?";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);
			
			// Executing the query, if result contains any data return true, otherwise return false
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				return true;
			}
			return false;
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}


	public void createCompany(Company company) throws ApplicationException {
		
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "INSERT INTO companies (company_Name) VALUES (?)";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, company.getName());

			// Executing the update
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			// **If there was an exception in the "try" block above, it is caught here and
			// notifies a level above.
			e.printStackTrace();
			throw new ApplicationException(e,ErrorType.GENERAL_ERROR,
					DateUtils.getCurrentDateAndTimeString() + "FAILED to create a company");
			// throw new Exception("Failed to create company " + company.toString()+"Failed
			// " ,e);
		} finally {
			// Closing the resources
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public void updateCompany(Company company) throws Exception {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// Establish a connection from the connection manager
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			// CompanyID is defined as a primary key and auto incremented
			String sqlStatement = "UPDATE `javaproject`.`companies` SET `Company_NAME` = ? WHERE `Company_ID` = ?;";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setString(1, company.getName());
			preparedStatement.setLong(2, company.getId());
			
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

	public void deleteCompanyById(long companyId) throws Exception {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();
			
			// Creating the SQL query
			String sqlStatement = "DELETE FROM `javaproject`.`companies` WHERE `Company_ID` = ?;";
			
			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);
			
			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);
			
			// Executing the update
			preparedStatement.executeUpdate();
			
		// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}

	public ArrayList<Company> getAllCompanies() throws Exception {

		
		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM companies";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);			

			// Executing the query
			ResultSet resultSet = preparedStatement.executeQuery();
			
			ArrayList<Company> theCompanies = new ArrayList<Company>(); 
			
			while (resultSet.next()) {
				
				Long id = resultSet.getLong("Company_Id");
				String name = resultSet.getString("Company_NAME");
				theCompanies.add(new Company(id, name));
			}
			return theCompanies;
			
			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}	
			


	public Company getOneCompanyById(long companyId) throws Exception {

		// Turn on the connections
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			connection = JdbcUtils.getConnection();

			// Creating the SQL query
			String sqlStatement = "SELECT * FROM companies WHERE COMPANY_ID = ?";

			// Combining between the syntax and our connection
			preparedStatement = connection.prepareStatement(sqlStatement);

			// Replacing the question marks in the statement above with the relevant data
			preparedStatement.setLong(1, companyId);

			// Executing the query
			ResultSet resultSet = preparedStatement.executeQuery();
			
			if (resultSet.next()) {
				String name = resultSet.getString("Company_NAME");
				return new Company(companyId,name);
			}
			
			return null;
			
			// Closing the resources
		} finally {
			JdbcUtils.closeResources(connection, preparedStatement);
		}
	}
}
