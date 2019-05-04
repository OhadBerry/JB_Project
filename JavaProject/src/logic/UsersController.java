package logic;

import dao.UsersDao;
import exceptions.ApplicationException;
import exceptions.ErrorType;
import javabeans.User;

public class UsersController {

	private UsersDao usersDao;

	public UsersController() {
		this.usersDao = new UsersDao();
	}

	public long createUser(User user) throws ApplicationException{
		if (usersDao.isUserExistsByName(user.getUserName())) {
			throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, "Cannot create new user UserName already exists");
		}
		return usersDao.createUser(user);
	}
	
	public void updateUser(User user) throws ApplicationException{
		if (usersDao.isUserExistsByName(user.getUserName())) {
			throw new ApplicationException(ErrorType.NAME_ALREADY_EXISTS, "Cannot update user UserName already exists");
		}
		usersDao.updateUser(user);
		return;
	}
	
	public void deleteUserbyID(long userId) throws ApplicationException{
		usersDao.deleteUserById(userId);
		return;
	}
	
	public ClientType login(User user) throws ApplicationException {
		if (usersDao.login(user.getUserName(), user.getPassword())) {
			return user.getType();
		}
		return ClientType.UnknownUser;			
	}
	
	public User getUserByUserName(String userName) throws ApplicationException{
		return usersDao.getUserByUserName(userName);
	}
	
	





}
