package com.rajeswari2000.crm.user;

import java.util.*;

import com.rajeswari2000.crm.repository.CrmRepository;

public class UserModel implements UserModelCallBack{

	
	private UserModelControllerCallBack userController;
	
	public UserModel(UserModelControllerCallBack userController) {
		this.userController = userController;
	}

	
	public void setUser(String userName, String password, String phoneNumber) {
		
		boolean existingUser = CrmRepository.getInstance().validateCredentials(userName, password, phoneNumber);
	
	   if(existingUser) {
		   boolean isLead = CrmRepository.getInstance().isLeadCheck(phoneNumber);
		   if(isLead) {
			   boolean isCallrecieved = CrmRepository.getInstance().callStatus(phoneNumber);
			   if(isCallrecieved) {
				   List<Integer> details = CrmRepository.getInstance().fetchCallDetails();
				   userController.showCallDetails(details,userName,phoneNumber);
			   }
			   else {
				   userController.noCallsReceived();
				   
			   }
		   }
		   else {
			   
			  List details = CrmRepository.getInstance().getContactDetails(phoneNumber);
				userController.showContactDetails(details);        
		   }
	   }
	   else {
		   userController.loginfailure();
	   }
	   
		   
   }
		
	


	public boolean signUp(String userName, String password, String phoneNumber) {
		
		return CrmRepository.getInstance().signUpUser(userName, password, phoneNumber);
	}


	
	public void setContactDetails(String userName, String phoneNumber, String location, String accountNumber,
			int loanAmount,int minimumLoanAmount, int monthlyInterestPercent) {
		  float monthlyLoan = (float)((float)monthlyInterestPercent/100)*loanAmount;
		CrmRepository.getInstance().storeContactDetails(userName, phoneNumber,location,accountNumber, loanAmount, minimumLoanAmount, monthlyInterestPercent,monthlyLoan);
		userController.dealConfirmation(monthlyLoan);
	
	}
}
