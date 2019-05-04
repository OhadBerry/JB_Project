package utils;

import exceptions.ApplicationException;
import exceptions.ErrorType;

public class ValidationUtils {
	
	public static boolean isValidString (String string) throws ApplicationException {
		if (string == null) {
			return false;
		}
		if (string.length() <2) {
			return false;
		}
		return true;
		
	}
}
