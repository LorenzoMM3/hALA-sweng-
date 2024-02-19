package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	//public void initData();
	public boolean signIn(String username, String password);
	public boolean logIn(String username, String password);
}
