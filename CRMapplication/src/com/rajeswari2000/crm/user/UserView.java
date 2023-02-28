package com.rajeswari2000.crm.user;

import java.util.List;
import java.util.Scanner;

import com.rajeswari2000.crm.HomePage;

public class UserView implements UserViewCallBack {

	private UserControllerCallBack userController;
	private Scanner scanner = new Scanner(System.in);

	public UserView() {
		this.userController = new UserController(this);
	}

	public void userLogin() {
		System.out.println("Enter user name");
		String userName = scanner.next();
		System.out.println("Enter password");
		String password = scanner.next();
		System.out.println("Enter your phone number");
		String phoneNumber = scanner.next();

		userController.setUserDetails(userName, password, phoneNumber);

	}

	public void userSignUp() {
		System.out.println("Enter user name");
		String userName = scanner.next();
		System.out.println("Enter password");
		String password = scanner.next();
		System.out.println("Enter your phone number");
		String phoneNumber = scanner.next();

		userController.newUserSignUp(userName, password, phoneNumber);
	}

	public void existingUser() {
		System.out.println("\nExisting user.Please login to continue\n ");
		userLogin();

	}

	public void loginSuccess() {
		System.out.println("\nYou have logged in successfully\n");

	}

	public void loginFailure() {
		System.out.println("Invalid credentials\n");
		System.out.println("Enter your details again\n");
		userLogin();

	}

	public void signUpSuccess() {
		System.out.println("\nYou have signed up successfully\n");
		System.out.println("Please login to continue.\n");
		userLogin();

	}

	public void showDetails(List<Integer> details, String userName, String phoneNumber) {
		System.out.println("\nYou are eligible to apply for a minimum loan amount of Rs." + details.get(0));
		System.out.println("Monthly interest is: " + details.get(1));

		int minimumLoanAmount = details.get(0);
		int monthlyInterestPercent = details.get(1);

		System.out.println("\nEnter 1 to apply for loan");
		System.out.println("Enter 2 to reject\n");

		String option = scanner.next();
		if (option.equals("1"))
			getDetails(userName, phoneNumber, minimumLoanAmount, monthlyInterestPercent);
		else
			logOut();
	}

	private void logOut() {
		HomePage homePage = new HomePage();
		homePage.mainMenu();
	}

	public void getDetails(String userName, String phoneNumber, int minimumLoanAmount, int monthlyInterestPercent) {
		System.out.println("Enter your location: ");
		String location = scanner.next();

		System.out.println("Enter your account number: ");
		String accountNumber = scanner.next();

		int loanAmount = 0;
		while (loanAmount <= minimumLoanAmount || loanAmount > 1000000) {
			System.out.println("Enter loan amount:  ");
			loanAmount = scanner.nextInt();
		}

		userController.setContactDetails(userName, phoneNumber, location, accountNumber, loanAmount, minimumLoanAmount,
				monthlyInterestPercent);

	}

	public void showDealConfirmation(float monthlyLoan) {
		System.out.println("Deal confirmed");
		System.out.println("Your monthly interest amount of Rs." + monthlyLoan);
		logOut();
	}

	public void callsNotReceived() {
		System.out.println("\n---------------> No calls recieved <-----------------");
		logOut();

	}

	public void displayContactDetails(List details) {

		System.out.println("\n----------> Loan details <-----------\n");
		System.out.println("Account number:              " + details.get(3));
		System.out.println("Loan amount:                   " + details.get(4));
		System.out.println("Monthly interest percentage:   " + details.get(5));
		System.out.println("Monthly interest amount:       " + details.get(6));

		logOut();

	}

}
