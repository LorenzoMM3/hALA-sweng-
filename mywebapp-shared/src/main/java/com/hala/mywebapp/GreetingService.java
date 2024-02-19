package com.hala.mywebapp;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * The client side stub for the RPC service.
 */
@RemoteServiceRelativePath("greet")
public interface GreetingService extends RemoteService {
	//public void initData();
	public boolean signIn(String email, String password);
	public boolean logIn(String email, String password);
}
