package tn.esprit.gestionbancaire;

import java.util.Optional;
import java.util.stream.Stream;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import tn.esprit.gestionbancaire.model.User;
import tn.esprit.gestionbancaire.repository.UserRepository;
import tn.esprit.gestionbancaire.services.UserService;

@SpringBootApplication
public class GestionBancaireApplication {

    public static void main(String[] args) {
    	
        SpringApplication.run(GestionBancaireApplication.class, args);
        
    	
    }
    @Bean
    ApplicationRunner init(UserService service) {
        return args -> {
            Stream.of("admin", "employe", "client").forEach(nom -> {
            	Optional<User> user =service.getUserByUsername(nom);
            	if(user.isEmpty())
            	{
            		service.saveUser(new User(nom,nom,true,nom));
            		System.out.println(nom+" added");
            	}
            });
        };
    }

}
