package com.utkal.supply.service.user;

import com.utkal.supply.model.User;

public interface UserService {
	
	public String validateUser(User user);
	
	public String createUser(User user) throws Exception;

}
