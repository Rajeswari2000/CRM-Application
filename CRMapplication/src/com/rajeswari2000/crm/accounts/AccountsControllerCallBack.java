package com.rajeswari2000.crm.accounts;

public interface AccountsControllerCallBack {

	void verifyAccounts(String userName, String password);

	void showLeadsDetails();

	void callLead(String key);

	void showContacts();

}
