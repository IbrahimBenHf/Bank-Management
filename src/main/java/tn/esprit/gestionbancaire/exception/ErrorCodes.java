package tn.esprit.gestionbancaire.exception;


public enum ErrorCodes {

    CREDIT_NOT_FOUND(1000),
    CREDIT_NOT_VALID(1001),
    CREDIT_IS_NOT_CLOSED(1002),
    CREDIT_IS_NULL(1003),
    CREDIT_NON_MODIFIABLE(1004),

    RECLAMATION_NOT_FOUND(2000),
    RECLAMATION_COMMENT_NOT_FOUND(2001),
    RECLAMATION_NULL(2002),
    // Liste des exception techniaues
    UPDATE_PHOTO_EXCEPTION(14000),
    UNKNOWN_CONTEXT(14001),
    // ErrorCodesForCurrency&Operation&Transaction
    TX_IS_NULL(22001),
    TX_NOT_FOUND(22002),

    OPERATION_NOT_FOUND(22011),
    OPERATION_NOT_VALID(22012),
    OPERATION_IS_NULL(22013),
    OPERATION_NON_MODIFIABLE(22014)
    ;

    private int code;

    ErrorCodes(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}

