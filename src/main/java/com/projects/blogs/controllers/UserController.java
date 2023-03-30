package com.projects.blogs.controllers;

import java.util.List;
import java.util.Map;

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

import com.projects.blogs.bean.UserBean;
import com.projects.blogs.services.UserService;
import com.projects.blogs.services.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	
	@PostMapping("/")
	public ResponseEntity<UserBean> createUser(@Valid @RequestBody UserBean user){
		UserBean createdUser=userService.createUser(user);
		return new ResponseEntity<>(createdUser,HttpStatus.CREATED);
	}
	
	@PutMapping("/{userId}")
	public ResponseEntity<UserBean> updateUser(@Valid @RequestBody UserBean user, @PathVariable("userId") Integer userId){
		UserBean updatedUser=userService.updateUser(user,userId);
		return ResponseEntity.ok(updatedUser);
	}
	@DeleteMapping("/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable("userId") Integer userId){
		userService.deleteUser(userId);
		return new ResponseEntity(Map.of("message","user deleted successfully"),HttpStatus.OK);	
	}
	
	@GetMapping("/")
	public ResponseEntity<List<UserBean>> getAllUser(){
		return ResponseEntity.ok(userService.getAllUsers());
	}
	@GetMapping("/{userId}")
	public ResponseEntity<UserBean> getAllUser(@PathVariable("userId") Integer userId){
		return ResponseEntity.ok(userService.getUserById(userId));
	}
	
}
