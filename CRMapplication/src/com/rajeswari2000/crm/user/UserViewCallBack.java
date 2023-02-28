package com.rajeswari2000.crm.user;

import java.util.List;

public interface UserViewCallBack {

	void existingUser();

	void loginSuccess();

	void loginFailure();

	void signUpSuccess();

	void showDetails(List<Integer> details, String userName, String phoneNumber);

	void showDealConfirmation(float monthlyLoan);

	void callsNotReceived();

	void displayContactDetails(List details);

}
