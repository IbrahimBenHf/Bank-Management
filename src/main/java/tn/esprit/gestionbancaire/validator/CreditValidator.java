package tn.esprit.gestionbancaire.validator;

import org.springframework.util.StringUtils;
import tn.esprit.gestionbancaire.model.Credit;

import java.util.ArrayList;
import java.util.List;

public class CreditValidator {

    public static List<String> validate(Credit credit) {
        List<String> errors = new ArrayList<>();
        if (credit == null) {
            errors.add("Can you set Credit type ('CREDIAUTO',CREDIT MEDIA\n" +
                    "CAR CREDIT\n" +
                    "PERSONAL CREDIT\n" +
                    "HOME CREDIT");
            return errors;
        }
        return errors;
    }

}
