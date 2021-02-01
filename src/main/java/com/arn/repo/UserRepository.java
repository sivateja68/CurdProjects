package com.arn.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.arn.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {




	
}
