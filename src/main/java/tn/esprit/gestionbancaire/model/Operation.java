package tn.esprit.gestionbancaire.model;

import lombok.Data;
import tn.esprit.gestionbancaire.enums.OperationStatus;
import tn.esprit.gestionbancaire.enums.OperationSubType;
import tn.esprit.gestionbancaire.enums.OperationType;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="TYPE_OP",discriminatorType=DiscriminatorType.STRING,length=1)
public  @Data
class Operation extends AbstractEntity implements Serializable {

    private LocalDate date;
    private BigDecimal amount;
    private Boolean isInternal;
    private OperationType operationtype;
    private OperationSubType operationSubType;
    private OperationStatus  operationStatus;
    private Boolean isArchived;
    @ManyToOne
    @JoinColumn(name = "Account_Id")
    private Account account;

    @OneToMany(mappedBy="operation")
    private Collection<Transaction> transactions;

    public Operation(LocalDate date, BigDecimal v, boolean b, OperationType retrieve, OperationSubType regluement_credit, OperationStatus toBeExecuted) {
        this.date = date;
        this.amount = v;
        this.isInternal = b;
        this.operationtype = retrieve;
        this.operationSubType = regluement_credit;
        this.operationStatus = toBeExecuted;
    }



    public void addTransactions(Collection<Transaction> transactions){
        this.transactions.addAll(transactions);
    }
}
