package dailyList;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
	
	static Connection con = null;
	
	static Connection databaseIntitalize(){
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/dailylist","root","Trmt@1212");
	}catch(Exception e) {
		e.printStackTrace();
	}
	return con;
	
	}

}
