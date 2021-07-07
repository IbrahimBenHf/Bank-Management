package tn.esprit.gestionbancaire;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableAspectJAutoProxy
public class GestionBancaireApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestionBancaireApplication.class, args);
    }

}
