package Program;


import Test.Test;
import Test.TestDBDAO;

public class Program {

	public static void main(String[] args) {

		try {

			System.out.println("Welcome to the DBDAO program");
			Test.testAll();

		} catch (Exception ex) {

			System.out.println("Error: " + ex.getMessage());
		}

	}
}
	
	
	
