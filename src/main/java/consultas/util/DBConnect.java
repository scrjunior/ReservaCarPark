package consultas.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {
	
	public Connection connector() throws ClassNotFoundException {
	    Connection con = null;
	    try {
	        Class.forName("com.mysql.cj.jdbc.Driver");
	        String url = "jdbc:mysql://localhost:3306/consultas?user=root&password=Halix2020.&useUnicode=true&characterEncoding=UTF-8";
	        con = DriverManager.getConnection(url);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return con;
	}


}

