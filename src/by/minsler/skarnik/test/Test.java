package by.minsler.skarnik.test;

//import org.apache.log4j.Logger;
import java.sql.*;

public class Test{

	//private static Logger logger = Logger.getLogger(Test.class);

	public static void main(String[] args) {
		
		String driverName = "org.postgresql.Driver";
		String url = "jdbc:postgresql://localhost:5432/skarnik";
		String userName = "testuser";
		String password = "1234";
		Connection connection = null;

		try{
			Class.forName(driverName);
			connection = DriverManager.getConnection(url, userName, password);
			//looger.info();
			System.out.println("success connect");
		} catch(ClassNotFoundException e){
			//logger.fatal("driver for jdbc connection not found");
			System.out.println("driver not found");
		} catch(SQLException e){
			System.out.println("sql exception: " + e);
		}finally{
			if(connection != null){
				try{
					connection.close();
					System.out.println("success close connection");
				} catch(SQLException e){
					System.out.println("sql: " + e);
				}
			}
		}

	}
}