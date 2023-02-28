package com.rajeswari2000.crm.user;

public interface UserControllerCallBack {

	void setUserDetails(String userName, String password, String phoneNumber);

	void newUserSignUp(String userName, String password, String phoneNumber);

	void setContactDetails(String userName, String phoneNumber, String location, String accountNumber, int loanAmount, int minimumLoanAmount, int monthlyInterestPercent);

}
