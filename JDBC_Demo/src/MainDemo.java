import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class MainDemo {

	/**
	 * 1. import
	 * 2. load register and driver --> com.mysql.jdbc.Driver
	 * 3. create Connection --> Connection
	 * 4. create statements
	 * 5. execute query
	 * 6. process result
	 * 7. close connection
	 * */
	
	public static void main(String[] args) throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		String url = "jdbc:mysql://localhost:3307//myDB";
		String userName = "root";
		String password = "";
		String query = "select * from table where id = 3";
		Connection connection = DriverManager.getConnection(url, userName, password);
		Statement st = connection.createStatement();
		ResultSet res = st.executeQuery(query);
		while(res.next()) {
			String name = res.getString("userName");
		}
		st.close();
		connection.close();
	}

	
	
}
