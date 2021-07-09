package tn.esprit.gestionbancaire.validator;

import org.springframework.util.StringUtils;
import tn.esprit.gestionbancaire.model.Credit;

import java.util.ArrayList;
import java.util.List;

public class CreditValidator {

    public static List<String> validate(Credit credit) {
        List<String> errors = new ArrayList<>();
        if (credit == null) {
            errors.add("Can you set Credit type (CAR CREDIT\n" +
                    "PERSONAL CREDIT\n" );
            return errors;
        }
        if ( credit.getAmount() < credit.getCreditTemplate().getMinValue() || credit.getAmount() > credit.getCreditTemplate().getMaxValue()){
            errors.add("Credit Amount should be in " +credit.getCreditTemplate().getMinValue() + " adn "+credit.getCreditTemplate().getMaxValue());
        }
        if ( credit.getCreditTemplate().getCreditType().equals("Vehicle")){
            if( credit.getSelfFinancing() < credit.getAmount() * 0.2 ){
                errors.add("Self Financing must be > = 20% of credit amount " +credit.getAmount() * 0.2 );
            }
            if( credit.getVehicleFiscalPower() < 3){
                errors.add("Fiscal Power must be > 3 of credit amount " +credit.getAmount() * 0.2 );
            }
        }
        return errors;
    }

}
