package com.arn.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.arn.model.User;
import com.arn.service.IUserService;

@RestController
@RequestMapping("/user")
public class UserRestControll {

	@Autowired
	private IUserService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> save(@RequestBody User user){
		ResponseEntity<String> resp;
		Integer id = service.saveUser(user);
		
		try {
			
			resp=new ResponseEntity<String>("User '"+ id+"' is saved and ",HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			resp=new ResponseEntity<String>("User '"+ id+"' is Not saved",HttpStatus.INTERNAL_SERVER_ERROR);
		}	
		return resp;
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> delete(@PathVariable Integer id){
		ResponseEntity<String>  resp;
		if(service.isExist(id)) {
			service.deteleUser(id);
			resp= new ResponseEntity<String>("User '"+id+"' deleted",HttpStatus.OK);
		}else
		resp= new ResponseEntity<String>("User '"+id+"'Not Exist",HttpStatus.BAD_REQUEST);
		
		return resp;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAll(){
		List<User> list = service.getAllUser();
		return  ResponseEntity.ok(list);
	}
	
	@GetMapping("/one/{id}")
	public ResponseEntity<?> getOne(@PathVariable Integer id){
		ResponseEntity<?> resp;
		Optional<User> opt = service.getOneUser(id);
		if(opt.isPresent()) {
			resp = new ResponseEntity<>(opt.get(),HttpStatus.OK);
		}else {
			
			resp = new ResponseEntity<>("Data Not Found ",HttpStatus.OK);
		}
		return resp;
	}
	
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody User user){
		ResponseEntity<?> resp;
	
			if(user.getId()==null || !service.isExist(user.getId())) {
				resp = new ResponseEntity<>("Data Not Found ",HttpStatus.NOT_FOUND);
			}else {
				service.updateUser(user);
				resp = new ResponseEntity<>("User '"+user.getId()+"'is updated",HttpStatus.OK);
			}
		return resp;
	}
	
}
