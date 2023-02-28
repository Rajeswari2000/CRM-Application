package com.rajeswari2000.crm.accounts;

import java.util.ArrayList;
import java.util.HashMap;

public interface AccountsModelControllerCallBack {

	void loginSuccess();

	void invalidCredentials();

	void noLeads();

	void showLeadsDetails(HashMap<String, String> leads);

	void noContacts();

	void showContacts(ArrayList<ArrayList> listofList);

}
