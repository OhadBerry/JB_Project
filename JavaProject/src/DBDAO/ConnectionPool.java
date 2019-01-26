package DBDAO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Stack;

public class ConnectionPool {
	
	
	private static ConnectionPool instance = new ConnectionPool();
	
	
	private Stack<Connection> connections = new Stack<>();
	private static final String connectionString = "jdbc:mysql://localhost:3306/test";
	private static final String driver = "com.mysql.jdbc.Driver";

	private ConnectionPool() {
		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e1) {
			e1.printStackTrace();
		}
		for(int i = 1; i <= 10; i++) {
			try {
				Connection conn = DriverManager.getConnection(connectionString);
				connections.push(conn);
				System.out.println(i);
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