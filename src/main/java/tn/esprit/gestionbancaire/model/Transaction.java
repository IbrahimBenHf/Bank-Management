package tn.esprit.gestionbancaire.model;

import lombok.Data;
import tn.esprit.gestionbancaire.enums.TransactionType;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class Transaction extends AbstractEntity implements Serializable {


    private Date date;
    private TransactionType transactionType;
    private Boolean isNegativeTx;
    private Boolean isRevertedTransaction;

    @ManyToOne
    @JoinColumn(name = "Operation_Id")
    private Operation operation;

    public Transaction(Date date, TransactionType txtype, boolean b, boolean b1, Operation operation) {
        this.date = date;
        this.transactionType =txtype;
        this.isNegativeTx = b1;
        this.operation = operation;
    }

    public Transaction() {

    }
}
