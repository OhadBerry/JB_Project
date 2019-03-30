package exceptions;

import java.sql.SQLException;
import java.util.Date;

public class ApplicationException extends Exception {
	
	public ApplicationException(Exception theException, ErrorType errorType, String exceptionMessage) {
		super(theException);
		System.out.println(errorType+" "+exceptionMessage);
	}

	public ApplicationException(ErrorType generalError, String string, SQLException e) {
		// TODO Auto-generated constructor stub
	}

}
