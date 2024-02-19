package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface GreetingServiceAsync {
	//public void initData(AsyncCallback<Boolean> callback);
	public void signIn(String username, String password, AsyncCallback<Boolean> callback);
	public boolean logIn(String email, String password, AsyncCallback<Boolean> callback);
}
