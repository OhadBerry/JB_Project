package idao;

import exceptions.ApplicationException;
import javabeans.User;

public interface IUsersDao {
	
	public void createUser(User user) throws ApplicationException;
	public void updateUser(User user) throws ApplicationException;
	public void deleteUserById(Long user_id) throws ApplicationException;
	public void getUserDetailsByUsernameAndPassword(String username, String password) throws ApplicationException;


}
