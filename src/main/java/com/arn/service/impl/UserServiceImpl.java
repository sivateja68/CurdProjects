package com.arn.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.arn.model.User;
import com.arn.repo.UserRepository;
import com.arn.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository repo;
	
	//this method is related to save
	@Override
	public Integer saveUser(User user) {
		return repo.save(user).getId();
	}

	@Override
	public void updateUser(User user) {
		repo.save(user);
	}

	@Override
	public void deteleUser(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public Optional<User> getOneUser(Integer id) {
		return repo.findById(id);
	}

	@Override
	public List<User> getAllUser() {
		return repo.findAll();
	}

	@Override
	public boolean isExist(Integer id) {
		return repo.existsById(id);
	}

}
