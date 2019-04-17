package testDao;

import dao.UsersDao;
import javabeans.User;
import logic.ClientType;

public class TestUsersDao {
	
	
	public static void AllTests() throws Exception {

		System.out.println("Testing UsersDao");
		
		UsersDao myUsersDao = new UsersDao();
		long id = 1;
		String userName = "myUserName";
		String password = "myPassword";
		long companyID = 1;
		ClientType type = ClientType.Customer;
		
		User user = new User(id, userName, password, companyID, type);
		
		long updatedId = 2;
		String updatedUserName = "myUpdatedUserName";
		String updatedPassword = "myUpdatedPassword";
		long updatedCompanyID = 1;
		ClientType updatedType = ClientType.Customer;
		
		User updatedUser = new User(updatedId, updatedUserName, updatedPassword, updatedCompanyID, updatedType);
		long user_ID = 1;
		
		myUsersDao.createUser(user);
		myUsersDao.updateUser(updatedUser);
		System.out.println("Getting user number "+user_ID+" :"+myUsersDao.getUserByID(user_ID));			
		//	myUsersDao.deleteUserById(user_ID);
	}

}
