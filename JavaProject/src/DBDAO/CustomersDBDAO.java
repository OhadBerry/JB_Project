package DBDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import DAO.CustomersDAO;
import JavaBeans.Company;
import JavaBeans.Customer;

public class CustomersDBDAO implements CustomersDAO{
	private ConnectionPool connectionPool = ConnectionPool.getInstance();

	@Override
	public boolean isCustomerExists(String email, String password) {
		return false;
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
	public void updateCustomer(Customer customer) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteCustomer(int customerID) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ArrayList<Customer> getAllCustomers() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Company getOneCustomer(int customerID) {
		// TODO Auto-generated method stub
		return null;
	}

}
