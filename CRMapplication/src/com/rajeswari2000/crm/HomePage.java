package com.rajeswari2000.crm;

import com.rajeswari2000.crm.accounts.*;
import com.rajeswari2000.crm.user.UserView;

import java.util.Scanner;

public class HomePage {

	private Scanner scanner = new Scanner(System.in);

	public static void main(String args[]) {
		HomePage homePage = new HomePage();
		homePage.mainMenu();

	}

	public void mainMenu() {
		System.out.println("\n****************** Welcome to  CRM *******************\n");
		System.out.println("Enter 1 for accounts login");
		System.out.println("Enter 2 for user login");
		System.out.println("Enter 3 for user sign up\n");

		String choice = scanner.next();

		switch (choice) {
		case ("1"):
			AccountsView accountsView = new AccountsView();
			accountsView.Accountlogin();
			break;
		case ("2"):
			UserView userView = new UserView();
			userView.userLogin();
			break;
		case ("3"):
			UserView user = new UserView();
			user.userSignUp();
			break;
		default:
			System.out.println("\nInvalid option\n\n");
			mainMenu();
			break;
		}
	}
}
