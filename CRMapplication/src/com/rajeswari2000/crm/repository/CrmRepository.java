package com.rajeswari2000.crm.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.*;

import java.sql.*;

public class CrmRepository {

	private CrmRepository() {

	}

	public static Connection connection;

	private static CrmRepository crmRepository;

	public static CrmRepository getInstance() {
		try {
			if (crmRepository == null) {
				crmRepository = new CrmRepository();
				connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/CRM", "root", "Rajesh@2000");
			}
		} catch (SQLException e) {
			System.out.println("sql exception");
		}
		return crmRepository;
	}

/*-----------------------------------------  User Login ---------------------------------------------*/
	
	public boolean validateCredentials(String userName, String password, String phoneNumber) {
		Statement statement;
		String s;
		boolean existingUser = false;
		try {
			
			statement = connection.createStatement();
			s = "select * from userCredentials where phoneNumber =\""+ phoneNumber+"\"";
			ResultSet resultSet = statement.executeQuery(s);

			existingUser = resultSet.next();

		} catch (SQLException e) {
			System.out.println("sql exception");
		}

		return existingUser;

	}

	
	/*-----------------------------------------  User signUp ---------------------------------------------*/
	
	public boolean signUpUser(String userName, String password, String phoneNumber) {
		Statement statement;
		String s;
		String insert;
		String insert2;
		boolean existingUser = false;
		try {
			statement = connection.createStatement();
			s = "select * from userCredentials where phoneNumber =\"" + phoneNumber + "\"";
			ResultSet resultSet = statement.executeQuery(s);
   
			// if existing user intimate the user to login
			existingUser = resultSet.next();

			//if not existing user (new user) add user into userCredentials and insert user into leads table
			
			if (!existingUser) {
				insert = "insert into userCredentials (userName,password,phoneNumber) values (\"" + userName + "\",\""
						+ password + "\",\"" + phoneNumber + "\")";
				statement.execute(insert);
				
				insert2 = "insert into Leads (userName,phoneNumber) values (\"" + userName + "\",\"" + phoneNumber + "\")";
				statement.execute(insert2);
				
			}

		} catch (SQLException e) {
			System.out.println("sql exception");
		}

		return existingUser;

	}

	public boolean isLeadCheck(String phoneNumber) {

		Statement statement;
		String s;
		boolean isLead = false;
		try {
			statement = connection.createStatement();
			s = "select isLead from userCredentials where phoneNumber =\"" + phoneNumber + "\"";
			ResultSet resultSet = statement.executeQuery(s);

			if (resultSet.next()) {
				isLead = resultSet.getBoolean("isLead");
			}

		} catch (SQLException e) {
			System.out.println("sql exception");
		}

		return isLead;

	}

	public boolean callStatus(String phoneNumber) {

		Statement statement;
		String s;
		boolean iscallreceived = false;
		try {
			statement = connection.createStatement();
			s = "select callRecieved from Leads where phoneNumber =\"" + phoneNumber + "\"";
			ResultSet resultSet = statement.executeQuery(s);

			if (resultSet.next()) {
				iscallreceived = resultSet.getBoolean("callRecieved");
			}

		} catch (SQLException e) {
			System.out.println("sql exception");
		}

		return iscallreceived;

	}

	public List<Integer> fetchCallDetails() {

		Statement statement;
		String s;
		List<Integer> details = new ArrayList<>();
		boolean iscallreceived = false;
		try {
			statement = connection.createStatement();
			s = "select loanAmount,monthlyInterestPercent from Accounts";
			ResultSet resultSet = statement.executeQuery(s);

			if (resultSet.next()) {
				details.add(resultSet.getInt("loanAmount"));
				details.add(resultSet.getInt("monthlyInterestPercent"));
			}

		} catch (SQLException e) {
			System.out.println("sql exception");
		}

		return details;

	}

	public void storeContactDetails(String userName, String phoneNumber, String location, String accountNumber,
			int loanAmount, int minimumLoanAmount, int monthlyInterestPercent, float monthlyLoan) {
		// update in contacts table

		try {
			Statement statement = connection.createStatement();
//			String s = "select * from userCredentials where phoneNumber = \"" + phoneNumber + "\"";
//			ResultSet resultSet = statement.executeQuery(s);

			String insert = "insert into Contacts (userName,phoneNumber,location,accountNumber,loanAmount,monthlyInterestPercent,monthlyInterestAmount) values ( \"" + userName + " \",\""+ phoneNumber + " \",\" " + location + " \",\" " + accountNumber + " \",\" " + loanAmount
					+ " \",\" " + monthlyInterestPercent + " \",\" " + monthlyLoan + " \")";
			statement.execute(insert);

		} catch (SQLException e) {
			System.out.println("sql exception in storeContactDetails 1");
		}

		// change isLead in userCredentials
		try {
			Statement statement = connection.createStatement();
			String update = "update userCredentials set isLead = " + false + " where phoneNumber = \"" + phoneNumber + "\"";
			statement.execute(update);

		} catch (SQLException e) {
			System.out.println("sql exception in storeContactDetails 2");
		}

		// remove lead from leads table

		try {
			Statement statement = connection.createStatement();
			String delete = "delete from Leads where phoneNumber =  \"" + phoneNumber + "\"";
			statement.execute(delete);

		} catch (SQLException e) {
			System.out.println("sql exception in storeContactDetails 3");
		}

	}

	public List getContactDetails(String phoneNumber) {

		List contactDetails = new ArrayList();

		Statement statement;
		String contactSelect;
	
		try {
			statement = connection.createStatement();
			contactSelect = "select * from Contacts where phoneNumber =\"" + phoneNumber + "\"";
			ResultSet resultSet = statement.executeQuery(contactSelect);

			if (resultSet.next()) {
				contactDetails.add(resultSet.getString("userName"));
				contactDetails.add(resultSet.getString("phoneNumber"));
				contactDetails.add(resultSet.getString("location"));
				contactDetails.add(resultSet.getString("accountNumber"));
				contactDetails.add(resultSet.getInt("loanAmount"));
				contactDetails.add(resultSet.getInt("monthlyInterestPercent"));
				contactDetails.add(resultSet.getInt("monthlyInterestAmount"));

			}

		} catch (SQLException e) {
			System.out.println("sql exception in getContactDetails");
		}
		return contactDetails;

	}

	
	
	public boolean validateAccount(String userName, String password) {
		boolean isValidAccount =  true;
		Statement statement;
		try {
			statement = connection.createStatement();
			
			String validQuery = "Select * from Accounts";
			ResultSet resultSet = statement.executeQuery(validQuery);
			if(!resultSet.next()) {
				String insert;
				try {
					statement = connection.createStatement();
				
						insert = "insert into Accounts (userName,password) values (\"" + userName + "\",\""
								+ password + "\")";
						statement.execute(insert);
						
					

				} catch (SQLException e) {
					System.out.println("sql exception in validateAccount 1");
				}
				
				
			}
			else {
				try {
				
				Statement statement2 = connection.createStatement();
				
				String selectQuery = "select * from Accounts where userName = \""+userName+"\" And password=\"" +password+"\"";
				
				ResultSet resultSet2 = statement2.executeQuery(selectQuery);
				if(resultSet2.next()) {
					isValidAccount = true;
				}
				else {
					isValidAccount = false;
				}
				}
				catch (SQLException e) {
					System.out.println("sql exception in validateAccount 2");
				}
			}
		}
		catch (SQLException e) {
			System.out.println("sql exception in validateAccount 3");
		}
		
		return isValidAccount ;
	}

	public HashMap<String, String> getLeads() {
		HashMap<String,String> leads = new HashMap<>();
		
		try {
		Statement statement = connection.createStatement();
		String selectQuery = "select * from Leads ";
		ResultSet resultSet = statement.executeQuery(selectQuery);
		
		while(resultSet.next()) {
			leads.put(resultSet.getString("phoneNumber"), resultSet.getString("userName"));
		 
		}
		}
		catch (SQLException e) {
			System.out.println("sql exception in getLeads1");
		}
	
		return leads;
	}

	public void setCallReceived(String phoneNumber) {
		Statement statement;
		try {
			statement = connection.createStatement();
			String update = "update Leads set callRecieved = " + true + " where phoneNumber = \"" + phoneNumber+ "\"";
			statement.execute(update);

		} catch (SQLException e) {
			System.out.println("sql exception in setCallReceived");
		}
		
	}

	public ArrayList<ArrayList> fetchContactDetails() {
		ArrayList<ArrayList> listOfList = new ArrayList<>();
		Statement statement;
		String contactSelect;
		
		try {
			statement = connection.createStatement();
			contactSelect = "select * from Contacts" ;
			ResultSet resultSet = statement.executeQuery(contactSelect);

			while(resultSet.next()) {
				ArrayList contactDetails = new ArrayList();
				contactDetails.add(resultSet.getString("userName"));
				contactDetails.add(resultSet.getString("phoneNumber"));
				contactDetails.add(resultSet.getString("location"));
				contactDetails.add(resultSet.getString("accountNumber"));
				contactDetails.add(resultSet.getInt("loanAmount"));
				contactDetails.add(resultSet.getInt("monthlyInterestPercent"));
				contactDetails.add(resultSet.getInt("monthlyInterestAmount"));
				contactDetails.add(resultSet.getFloat("individualProfit"));
				listOfList.add(contactDetails);
			}

		} catch (SQLException e) {
			System.out.println("sql exception in fetchContactDetails");
		}
		return listOfList;
		
	}

	public void updateIndividualProfit() {
		
		try {
			
			float totalProfitAmount = 0;
			float individualProfitPercent=0;
			Statement statement = connection.createStatement();
			String selectQuery = "select * from Contacts ";
			ResultSet resultSet = statement.executeQuery(selectQuery);
			
			while(resultSet.next()) {
				totalProfitAmount+=resultSet.getInt("monthlyInterestAmount");
			}
			
			try {
				String query2 = "SELECT*FROM Contacts";
				ResultSet resultSet2 = statement.executeQuery(query2);
			
            while(resultSet2.next()) {
            	individualProfitPercent = (resultSet2.getFloat("monthlyInterestAmount")/totalProfitAmount)*100;
			    String phoneNumber = resultSet2.getString("phoneNumber");
			    
			    try {
			    	Statement statement2 = connection.createStatement();
			    	
			    	String query = "update Contacts set individualProfit="+individualProfitPercent+"where phoneNumber = \"" + phoneNumber+ "\"";
			        statement2.execute(query);
			    }
			    catch (SQLException e) {
					System.out.println("sql exception");
				}
            }
				
            }
			catch (SQLException e) {
				System.out.println("sql exception");
			}
			}
			catch (SQLException e) {
				System.out.println("sql exception");
			}
		
		
	}
}
