package logic;

import beans.User;
import dao.UsersDao;
import enums.ClientType;
import enums.ErrorType;
import exceptions.ApplicationException;

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
	
	public User getUserbyID(long userId) throws ApplicationException{
		return usersDao.getUserbyID(userId);
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
