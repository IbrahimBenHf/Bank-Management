package tn.esprit.gestionbancaire.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum AccountRequestStatus {
    OPEN,
    IN_PROGRESS,
    DONE;

    @JsonValue
    public String getStatus() {
        return this.name();
    }
}
