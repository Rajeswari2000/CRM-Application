package com.rajeswari2000.crm.accounts;

public interface AccountsModelCallBack {

	void validateAccount(String userName, String password);

	void showLeads();

	void callingLead(String phoneNumber);

	void showContactDetails();

}
