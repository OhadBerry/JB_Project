package DBDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;


public class ConnectionPool {
	
	
	private static ConnectionPool instance = new ConnectionPool();
	
	
	private Stack<Connection> connections = new Stack<>();
	private static final String connectionString = "jdbc:mysql://localhost:3306/javaproject";
	private static final String driver = "com.mysql.jdbc.Driver";

	private ConnectionPool() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for(int i = 1; i <= 10; i++) {
			try {
				Connection conn = DriverManager.getConnection(connectionString,"root",null);
				connections.push(conn);
				System.out.println("Connection "+i+" pushed");

			}
			catch (SQLException e) { }
		}
	}

	public static ConnectionPool getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws InterruptedException {
		
		
		synchronized(connections) {
			
			if(connections.isEmpty()) {
				connections.wait();
			}
			
			return connections.pop();
		}
	}

	public void restoreConnection(Connection conn) {
		
		synchronized(connections) {
			connections.push(conn);
			connections.notify();
		}
	}
	
	public void closeAllConnection() throws InterruptedException {
		
		synchronized(connections) {

			while(connections.size() < 10) {
				connections.wait();
			}
			
			for (Connection conn : connections) {
				try { conn.close(); } catch (Exception e) { }
			}			
		}
	}
}



/*
public class ConnectionPool {
	
	
	private static ConnectionPool instance = new ConnectionPool();
	
	
	private Stack<Connection> connections = new Stack<>();


	private ConnectionPool() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {}
			for(int i = 1; i <= 10; i++) {
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/javaproject","root","Sparhawk");
					connections.push(conn);
				}
				catch (SQLException e) {
					System.out.println(e.getMessage());
				}
			}

	}

	public static ConnectionPool getInstance() {
		return instance;
	}
	
	public Connection getConnection() throws InterruptedException {
		
		synchronized(connections) {

			if(connections.isEmpty()) {
				connections.wait();
			}
			
			return connections.pop();
		}
	}

	public void restoreConnection(Connection conn) {
		
		synchronized(connections) {
			connections.push(conn);
			connections.notify();
		}
	}
	
	public void closeAllConnection() throws InterruptedException {
		
		synchronized(connections) {

			while(connections.size() < 10) {
				connections.wait();
			}
			
			for (Connection conn : connections) {
				try { conn.close(); } catch (Exception e) { }
			}			
		}
	}
}
*/