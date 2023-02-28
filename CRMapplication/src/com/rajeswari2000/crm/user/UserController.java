package com.rajeswari2000.crm.user;

import java.util.List;

public class UserController implements UserControllerCallBack, UserModelControllerCallBack {

	private UserViewCallBack userView;
	private UserModelCallBack userModel;

	public UserController(UserViewCallBack userView) {
		this.userView = userView;
		this.userModel = new UserModel(this);
	}

	public void setUserDetails(String userName, String password, String phoneNumber) {
		userModel.setUser(userName, password, phoneNumber);

	}

	public void newUserSignUp(String userName, String password, String phoneNumber) {
		if (userModel.signUp(userName, password, phoneNumber)) {
			userView.existingUser();
		} else
			userView.signUpSuccess();

	}

	public void loginSuccessfull() {
		userView.loginSuccess();

	}

	public void loginfailure() {
		userView.loginFailure();

	}

	public void showCallDetails(List<Integer> details, String userName, String phoneNumber) {

		userView.showDetails(details, userName, phoneNumber);
	}

	public void setContactDetails(String userName, String phoneNumber, String location, String accountNumber,
			int loanAmount, int minimumLoanAmount, int monthlyInterestPercent) {
		userModel.setContactDetails(userName, phoneNumber, location, accountNumber, loanAmount, minimumLoanAmount,
				monthlyInterestPercent);

	}

	public void dealConfirmation(float monthlyLoan) {

		userView.showDealConfirmation(monthlyLoan);
	}

	public void noCallsReceived() {
		userView.callsNotReceived();

	}

	public void showContactDetails(List details) {
		userView.displayContactDetails(details);

	}

}
