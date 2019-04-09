package idao;

import exceptions.ApplicationException;
import javabeans.User;

public interface IUsersDao {
	
	public long createUser(User user) throws ApplicationException;
	public void updateUser(User user) throws ApplicationException;
	public void deleteUserById(long User_ID) throws ApplicationException;
	public User getUserByID (long User_ID) throws ApplicationException;
	public boolean login(String user, String password) throws ApplicationException;

}
