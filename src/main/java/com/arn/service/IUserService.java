package com.arn.service;

import java.util.List;
import java.util.Optional;

import com.arn.model.User;

public interface IUserService {

	Integer saveUser(User user);
	
	void updateUser(User user);
	
	void deteleUser(Integer id);
	
	Optional<User> getOneUser(Integer id);
	
	List<User> getAllUser();
	
	boolean isExist(Integer id);
	
}

