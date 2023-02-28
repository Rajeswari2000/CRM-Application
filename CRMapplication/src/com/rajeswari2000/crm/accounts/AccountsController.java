package com.rajeswari2000.crm.accounts;

import java.util.*;
import java.util.HashMap;

public class AccountsController implements AccountsModelControllerCallBack,AccountsControllerCallBack{

	
	private AccountsModelCallBack accountsModel;
	private AccountsViewCallBack accountsView;
	public AccountsController(AccountsViewCallBack accountsView) {
		this.accountsView = accountsView;
		this.accountsModel = new AccountsModel(this);
	}
	
	public void verifyAccounts(String userName, String password) {
		accountsModel.validateAccount(userName,password);
		
	}
	
	public void loginSuccess() {
		accountsView.loginSuccessful();
		
	}
	
	public void invalidCredentials() {
		accountsView.loginFailure();
		
	}
	
	public void showLeadsDetails() {
		accountsModel.showLeads();
		
	}


	public void noLeads() {
		
		accountsView.leadsNotAvailable();
	}

	
	public void showLeadsDetails(HashMap<String, String> leads) {
		accountsView.showLeadsDetails(leads);
		
	}

	
	public void callLead(String phoneNumber) {
		accountsModel.callingLead(phoneNumber);
		
	}

	@Override
	public void showContacts() {
		accountsModel.showContactDetails();
		
	}

	@Override
	public void noContacts() {
		
		accountsView.noContactsAvailable();
	}

	@Override
	public void showContacts(ArrayList<ArrayList> listofList) {
		
		accountsView.showAllContacts(listofList);
		
		
	}
}
