package DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import DAO.CustomersDAO;

import JavaBeans.Customer;

public class CustomersDBDAO implements CustomersDAO{
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExists(String email, String password) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT Count(*) AS Count FROM Customers WHERE EMAIL = '%s' AND PASSWORD = '%s'",
					email, password);
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					resultSet.first();

					int count = resultSet.getInt("Count");
					
					return (count > 0);
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public void addCustomer(Customer customer) throws Exception {
		Connection connection = null;

		try {
			String sql = String.format("INSERT INTO Customers("
					+ "ID, "
					+ "FIRSTNAME, "
					+ "LASTNAME, "
					+ "EMAIL, "
					+ "PASSWORD"
					+ ") " 
					+ "VALUES('%d', '%s', '%s', '%s', '%s')",
					customer.getId(),
					customer.getFirstName(),
					customer.getLastName(),
					customer.getEmail(),
					customer.getPassword()
					);
			
			System.out.println(sql);		
			
			connection = connectionPool.getConnection();
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql,
					PreparedStatement.RETURN_GENERATED_KEYS)) {

				preparedStatement.executeUpdate();

				try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
					resultSet.next();
					int id = resultSet.getInt(1);
					customer.setId(id); // Add the new created id into the company object.
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}		
	}

	@Override
	public void updateCustomer(Customer customer) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("UPDATE `javaproject`.`Customers`\r\n" + 
					"SET\r\n" +  
					"`ID` = '%d',\r\n" + 
					"`FIRSTNAME` = '%s',\r\n" + 
					"`LASTNAME` ='%s',\r\n" + 
					"`EMAIL` = '%s',\r\n" + 
					"`PASSWORD` = '%s'\r\n"+ 
					"WHERE `ID` = '%d';",
					customer.getId(),
					customer.getFirstName(),
					customer.getLastName(),
					customer.getEmail(),
					customer.getPassword(),
					customer.getId()
					);
			
			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			} 
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public void deleteCustomer(int customerID) throws Exception {
		Connection connection = null;

		try {

			connection = connectionPool.getConnection();

			String sql = String.format("DELETE FROM Customers WHERE ID=%d", customerID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.executeUpdate();
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}		
	}

	@Override
	public ArrayList<Customer> getAllCustomers() throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = "SELECT * FROM Customers";

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					ArrayList<Customer> allCustomers = new ArrayList<Customer>();

					while (resultSet.next()) {
						
						int id = resultSet.getInt("ID");
						String firstName = resultSet.getString("firstName");
						String lastName = resultSet.getString("lastName");
						String email = resultSet.getString("email");
						String password = resultSet.getString("password");

						Customer customer = new Customer(id, firstName, lastName, email, password,null);

						allCustomers.add(customer);
					}

					return allCustomers;
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
	}

	@Override
	public Customer getOneCustomer(int customerID) throws Exception {
		Connection connection = null;

		try {
			connection = connectionPool.getConnection();

			String sql = String.format("SELECT * FROM Customers where ID=%d", customerID);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

				try (ResultSet resultSet = preparedStatement.executeQuery()) {

					while (resultSet.first()) {
						
						int id = resultSet.getInt("ID");
						String firstName = resultSet.getString("firstName");
						String lastName = resultSet.getString("lastName");
						String email = resultSet.getString("email");
						String password = resultSet.getString("password");

						Customer customer = new Customer(id, firstName, lastName, email, password,null);

						return customer;
					}
				}
			}
		} finally {
			connectionPool.restoreConnection(connection);
		}
		return null;
	}

}
