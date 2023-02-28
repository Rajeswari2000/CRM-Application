package com.rajeswari2000.crm.accounts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.rajeswari2000.crm.HomePage;

public class AccountsView implements AccountsViewCallBack {
	private AccountsControllerCallBack accountsController;
	private Scanner scanner = new Scanner(System.in);

	public AccountsView() {
		this.accountsController = new AccountsController(this);
	}

	public void Accountlogin() {
		System.out.println("Enter user name");
		String userName = scanner.next();
		System.out.println("Enter password");
		String password = scanner.next();

		accountsController.verifyAccounts(userName, password);

	}

	public void loginSuccessful() {
		System.out.println("\nYou have logged in successfully\n");

		outer: while (true) {
			System.out.println("Enter 1 to view leads");
			System.out.println("Enter 2 to view contacts");
			System.out.println("Enter 3 to log out");
			String option = scanner.next();
			switch (option) {
			case ("1"):
				accountsController.showLeadsDetails();
				break;
			case ("2"):
				accountsController.showContacts();
				break;
			case ("3"):
				HomePage homePage = new HomePage();
				homePage.mainMenu();
				break outer;
			default:
				System.out.println("Invalid data. Enter your choice again\ns");
				continue outer;

			}
		}

	}

	public void loginFailure() {
		System.out.println("\nInvalid credentials. Enter your detials again\n");
		Accountlogin();
	}

	@Override
	public void leadsNotAvailable() {
		System.out.println("\n------------>No leads available<-----------\n");

	}

	@Override
	public void showLeadsDetails(HashMap<String, String> leads) {

		for (Map.Entry<String, String> entrySet : leads.entrySet()) {
			System.out.println("\nuser name: " + entrySet.getValue());
			System.out.println("phone number: " + entrySet.getKey());

			System.out.println("\nEnter 1 to call lead");
			System.out.println("Enter 2 to view next lead\n");

			String choice = scanner.next();
			if (choice.equals("1")) {
				accountsController.callLead(entrySet.getKey());
				System.out.println("\n---------------> Call sent successfully <----------------");
			} else {
				continue;
			}
		}
		System.out.println();

	}

	public void noContactsAvailable() {
		System.out.println("\n----------> no contacts available <-----------\n");

	}

	public void showAllContacts(ArrayList<ArrayList> listofList) {
		for (ArrayList contacts : listofList) {
			System.out.println("\n--------------------------------------------");
			System.out.println("\nUser Name:                 " + contacts.get(0));
			System.out.println("Phone Number:              " + contacts.get(1));
			System.out.println("Location:                 " + contacts.get(2));
			System.out.println("Account number:           " + contacts.get(3));
			System.out.println("Loan Amount:               " + contacts.get(4));
			System.out.println("Monthly interest percent:  " + contacts.get(5));
			System.out.println("Monthly interest amount:   "+contacts.get(6));
			System.out.println("Invidual profit:           "+contacts.get(7));
			System.out.println("\n---------------------------------------------\n");
		}
	}
}
