package com.rajeswari2000.crm.user;

import java.util.List;

public interface UserModelControllerCallBack {

	void loginSuccessfull();

	void loginfailure();

	void showCallDetails(List<Integer> details, String userName, String phoneNumber);

	void setContactDetails(String userName, String phoneNumber, String location, String accountNumber, int loanAmount,
			int minimumLoanAmount, int monthlyInterestPercent);

	void dealConfirmation(float monthlyLoan);

	void noCallsReceived();

	void showContactDetails(List details);

}
