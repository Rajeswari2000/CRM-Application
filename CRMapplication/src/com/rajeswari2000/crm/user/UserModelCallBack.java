package com.rajeswari2000.crm.user;

public interface UserModelCallBack {

	void setUser(String userName, String password, String phoneNumber);

	boolean signUp(String userName, String password, String phoneNumber);

	void setContactDetails(String userName, String phoneNumber, String location, String accountNumber, int loanAmount, int minimumLoanAmount, int monthlyInterestPercent);


}
