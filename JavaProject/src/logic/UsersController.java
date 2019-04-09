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
			throw new ApplicationException(ErrorType.NAME_IS_ALREADY_EXISTS, "User name already exist");
		}
		return usersDao.createUser(user);
	}
	
	public ClientType login(User user) throws ApplicationException {
		if (usersDao.login(user.getUserName(), user.getPassword())) {
			return user.getType();
		}
		return ClientType.UnknownUser;			
	}





}
