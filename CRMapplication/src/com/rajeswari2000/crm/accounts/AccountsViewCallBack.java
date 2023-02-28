package com.rajeswari2000.crm.accounts;

import java.util.ArrayList;
import java.util.HashMap;

public interface AccountsViewCallBack {

	void loginSuccessful();

	void loginFailure();

	void leadsNotAvailable();

	void showLeadsDetails(HashMap<String, String> leads);

	void noContactsAvailable();

	void showAllContacts(ArrayList<ArrayList> listofList);

}
