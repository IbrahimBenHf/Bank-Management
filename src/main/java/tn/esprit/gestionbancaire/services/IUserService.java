package tn.esprit.gestionbancaire.services;

import java.util.List;
import java.util.Optional;

import tn.esprit.gestionbancaire.model.User;

public interface IUserService {

	public User getUserById(Long id);
	public User saveUser(User user);
	public void deleteUser(Long id);
	public User updateUser(User user);
	public List<User> getAll();
	public Optional<User> getUserByUsername(String username);

}
