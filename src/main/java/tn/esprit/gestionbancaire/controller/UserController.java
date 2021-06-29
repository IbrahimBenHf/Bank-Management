package tn.esprit.gestionbancaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tn.esprit.gestionbancaire.model.User;
import tn.esprit.gestionbancaire.services.IUserService;

@RestController
public class UserController {

	@Autowired  
	IUserService userService;  
	
	@GetMapping("/user")  
	private ResponseEntity<List<User>> getAll()   
	{  
		return new ResponseEntity<List<User>>(userService.getAll(), HttpStatus.OK);
	} 
	@GetMapping("/user/{id}")  
	private ResponseEntity<User> getUserById(@PathVariable("id") Long id)   
	{  
		return new ResponseEntity<User>(userService.getUserById(id), HttpStatus.OK);
	}  
	@DeleteMapping("/user/{id}")  
	private void deleteBook(@PathVariable("id") Long id)   
	{  
		userService.deleteUser(id);
	}  
	//creating post mapping that post the book detail in the database  
	@PostMapping("/user")  
	private ResponseEntity<User> saveBook(@RequestBody User user)   
	{  
		return new ResponseEntity<User>(userService.saveUser(user), HttpStatus.OK);
	}  
	//creating put mapping that updates the book detail   
	@PutMapping("/user")  
	private ResponseEntity<User> update(@RequestBody User user)   
	{  
		return new ResponseEntity<User>(userService.updateUser(user), HttpStatus.OK);
	}  
}
