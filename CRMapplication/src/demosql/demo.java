package demosql;
import java.sql.*;
public class demo {
public static void main(String args[]) throws SQLException {
	
//	//1) create connection
//	Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRM","root","Rajesh@2000");
//	
//	
//	//2)create statement/query
//	Statement statement = connection.createStatement();
//	
//	String s = "INSERT INTO STUDENT VALUES('amma','yoga')";
//	
//	//3) execute the statement/query
//	
//	statement.execute(s);
//	
//	//4 close connection
//	
//	connection.close();
	int monthlyInterestPercent =2;
	int loanAmount = 50000;
	float monthlyLoan =(float) (monthlyInterestPercent/100)*loanAmount;
	
	System.out.println(monthlyLoan);
  }
}
