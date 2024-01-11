package com.example.Security_only.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Security_only.entity.UserData;
import com.example.Security_only.repostory.UserRepo;


@RestController
@RequestMapping("/control")
public class Spring_controller {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private PasswordEncoder encoder;

	@GetMapping("/get")
	public ResponseEntity<String> getMethodName() {
		return ResponseEntity.ok("get mapping url");
	}
	
	@GetMapping("/admin")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public ResponseEntity<String> adminMethod(){
		return ResponseEntity.ok("admin page");
	}
	
	@GetMapping("/user")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public ResponseEntity<String> userMethod(){
		return ResponseEntity.ok("user page");
	}
	
	@PostMapping("/newUser")
	public ResponseEntity<String> addUser(@RequestBody UserData user){
		
		user.setPassword(encoder.encode(user.getPassword()));
		repo.save(user);
		return ResponseEntity.ok("user added");
	}
	
}
