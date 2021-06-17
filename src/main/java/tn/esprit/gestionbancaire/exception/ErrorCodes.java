package tn.esprit.gestionbancaire.exception;


public enum ErrorCodes {

    CREDIT_NOT_FOUND(1000),
    CREDIT_NOT_VALID(1001),
    CREDIT_IS_NOT_CLOSED(1002),

    // Liste des exception techniaues
    UPDATE_PHOTO_EXCEPTION(14000),
    UNKNOWN_CONTEXT(14001)
    ;

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

