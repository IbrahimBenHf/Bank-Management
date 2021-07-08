package tn.esprit.gestionbancaire.model;

import lombok.Data;
import tn.esprit.gestionbancaire.enums.OperationSubType;
import tn.esprit.gestionbancaire.enums.Operationtype;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
//@DiscriminatorColumn(name="TYPE_OP",discriminatorType=DiscriminatorType.STRING,length=1)
public  @Data
class Operation extends AbstractEntity implements Serializable {

    private Date date;
    private BigDecimal amount;
    private Boolean isInternal;
    private Operationtype operationtype;
    private OperationSubType operationSubType;
    @ManyToOne
    @JoinColumn(name = "Account_Id")
    private Account account;

    @OneToMany(mappedBy="operation")
    private Collection<Transaction> transactions;

}
