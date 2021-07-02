package tn.esprit.gestionbancaire.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import tn.esprit.gestionbancaire.model.User;
import tn.esprit.gestionbancaire.repository.UserRepository;

@Service
public class UserService implements IUserService{

	@Autowired
	UserRepository userRepository;
	@Autowired
	PasswordEncoder passwordEncoder;

	@Override
	public User getUserById(Long id) {
		// TODO Auto-generated method stub
		return userRepository.findById(id).get() ;
	}

	@Override
	public User saveUser(User user) {
		// TODO Auto-generated method stub
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userRepository.save(user);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);
	}

	@Override
	public User updateUser(User user) {
		// TODO Auto-generated method stub
		return userRepository.save(user) ;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
		  String username = ((UserDetails)principal).getUsername();
		} else {
		  String username = principal.toString();
		}
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return userRepository.findUserByUserName(username);
	}
}
