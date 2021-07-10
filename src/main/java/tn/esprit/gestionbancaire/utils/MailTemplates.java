package tn.esprit.gestionbancaire.utils;

import tn.esprit.gestionbancaire.enums.CreditStatus;
import tn.esprit.gestionbancaire.enums.ReclamationStatus;
import tn.esprit.gestionbancaire.model.Credit;

public interface MailTemplates {
    String WELCOME = "Welcome to our Bank.";

    String NEW_LAW = "We may ask you to visit our webSite, there has been a new laws set by the " +
            "government that you need to be aware of.";

    static String getTemplate(String template, String Name) {
        if (template.equalsIgnoreCase("WELCOME")) {
            return "Dear " + Name + ", \n" + WELCOME + "\nThank you for your trust.";
        } else if (template.equalsIgnoreCase("NEW")) {
            return "Dear " + Name + ", \n" + NEW_LAW + "\nThank you for your trust.";
        }
        return "Thank you for your trust.";
    }

    static String getNotif(ReclamationStatus reclamationStatus, String title){
        if (ReclamationStatus.IN_PROGRESS.equals(reclamationStatus)){
            return "Hello, \nYour reclamation '"+title+"' is under review now with one of our consultants, we'll provide " +
                    "you with an update when it's resolved."
                    + "\nThank you for your trust.";
        }
        return "Hello, \nYour reclamation '"+title+"' has been fixed and updated with the status "+ reclamationStatus.toString()+". " +
                "you can check it on our website for further information."
                + "\nThank you for your trust.";
    }
    static String creditStatusUpdatedTemplate(Credit credit, CreditStatus creditStatus){
        return "Hello "+credit.getCreditTemplate().getTitle() +" \n" +
                " \n" +
                "Your credit request status has been updated to "+ creditStatus.toString() +"\n" +
                " \n" +
                "Thanks for your trust.";
    }

    static String creditRequestCreated(Credit credit){
        return "Hello "+credit.getCreditTemplate().getTitle() +" \n" +
                " \n" +
                "Your credit request has been created \n" +
                " \n" +
                "Thanks for your trust.";
    }
}
