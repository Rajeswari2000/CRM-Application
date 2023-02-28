package com.rajeswari2000.crm.accounts;
import java.util.*;
import java.util.HashMap;

import com.rajeswari2000.crm.repository.CrmRepository;

public class AccountsModel implements AccountsModelCallBack {

	private AccountsModelControllerCallBack accountsController;

	public AccountsModel(AccountsModelControllerCallBack accountsController) {
		this.accountsController = accountsController;
	}

	public void validateAccount(String userName, String password) {
		boolean isValid = CrmRepository.getInstance().validateAccount(userName, password);

		if (isValid) {
			CrmRepository.getInstance().updateIndividualProfit();
			accountsController.loginSuccess();
		}
		else accountsController.invalidCredentials();
	}

	public void showLeads() {
		HashMap<String, String> leads = CrmRepository.getInstance().getLeads();
	     
		if (leads.isEmpty()) {
			accountsController.noLeads();
		} else {
			accountsController.showLeadsDetails(leads);
		}
	}

	public void callingLead(String phoneNumber) {
		CrmRepository.getInstance().setCallReceived(phoneNumber);

	}

	public void showContactDetails() {
		ArrayList<ArrayList> listofList = CrmRepository.getInstance().fetchContactDetails();
		
		if(listofList.isEmpty()) {
			accountsController.noContacts();
		}
		else {
			accountsController.showContacts(listofList);
		}
	}
}
