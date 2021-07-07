package tn.esprit.gestionbancaire.exception;


public enum ErrorCodes {

    CREDIT_NOT_FOUND(1000),
    CREDIT_NOT_VALID(1001),
    CREDIT_IS_NOT_CLOSED(1002),
    CREDIT_IS_NULL(1003),
    CREDIT_NON_MODIFIABLE(1004),
    CREDIT_SIMILATEUR_SELF_FINANCING_INSUFFICIENT(2000),
    MEDIA_CREDIT_SIMILATEUR_AMOUNT(2001),

    RECLAMATION_NOT_FOUND(2000),
    RECLAMATION_COMMENT_NOT_FOUND(2001),
    RECLAMATION_NULL(2002),
    // Liste des exception techniaues
    UPDATE_PHOTO_EXCEPTION(14000),
    UNKNOWN_CONTEXT(14001),

    ACCOUNT_TEMPLATE_NOT_FOUND(3000),
    ACCOUNT_TEMPLATE_NOT_VALID(3001),

    CREDIT_TEMPLATE_NOT_FOUND(3000),
    CREDIT_TEMPLATE_NOT_VALID(3001),
    ;

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

