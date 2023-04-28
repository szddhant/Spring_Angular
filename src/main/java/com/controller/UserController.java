package com.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.repository.UserRepository;
import com.entity.UserEntity;

@CrossOrigin
@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepo;
	
	@PostMapping("/signup")
	public ResponseEntity<UserEntity> signup(@RequestBody UserEntity user) {
		
		Optional<UserEntity> dbUser = userRepo.findByEmail(user.getEmail());
		if(dbUser.isEmpty()) {
			userRepo.save(user);
			return ResponseEntity.ok(user);
		}else{
			return ResponseEntity.unprocessableEntity().body(user);
		}
	}
	
	@GetMapping("/getAllUsers")
	public List<UserEntity> getAllUser(){
		return userRepo.findAll();	
	}
	
	@GetMapping("/findById/{uid}")
	public UserEntity getUserById(@PathVariable("uid")Integer uid) {
		Optional<UserEntity> proOptional = userRepo.findById(uid);
		if(proOptional.isEmpty()) {
			return null;
		}
		else {
			return proOptional.get();
		}
	}
	
	@DeleteMapping("/deleteUser/{uid}")
	public UserEntity deleteUserById(@PathVariable("uid")Integer uid) {
		Optional<UserEntity> proOptional = userRepo.findById(uid);
		if(proOptional.isEmpty()) {
			return null;
		}
		else {
			UserEntity userEntity = proOptional.get();
			userRepo.deleteById(uid);
			return userEntity;
		}
	}
	
	@PutMapping("/updateUser")
	public UserEntity updateUser(@RequestBody UserEntity user) {
		userRepo.save(user);
		return user;
	}
	
}





























