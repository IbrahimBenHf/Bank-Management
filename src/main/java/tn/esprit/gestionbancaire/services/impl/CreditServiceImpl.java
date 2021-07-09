package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import tn.esprit.gestionbancaire.enums.AdministrativeDocumentType;
import tn.esprit.gestionbancaire.exception.EntityNotFoundException;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.model.AdministrativeDocument;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.enums.CreditStatus;
import tn.esprit.gestionbancaire.model.CreditTemplate;
import tn.esprit.gestionbancaire.repository.CreditRepository;
import tn.esprit.gestionbancaire.services.CreditService;
import tn.esprit.gestionbancaire.services.CreditSimulateurService;
import tn.esprit.gestionbancaire.services.CreditTemplateService;
import tn.esprit.gestionbancaire.services.MailService;
import tn.esprit.gestionbancaire.validator.CreditValidator;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class CreditServiceImpl implements CreditService {

    private CreditRepository creditRepository;
    private CreditSimulateurService creditSimulateurService;
    private MailService mailService;
    private CreditTemplateService creditTemplateService;
    @Autowired
    public CreditServiceImpl(CreditRepository creditRepository,
                             @Lazy CreditSimulateurService creditSimulateurService, MailService mailService, CreditTemplateService creditTemplateService) {
        this.creditRepository = creditRepository;
        this.creditSimulateurService = creditSimulateurService;
        this.mailService = mailService;
        this.creditTemplateService = creditTemplateService;
    }



    @Override
    public Credit save(Credit credit) {
        List<String> errors = CreditValidator.validate(credit,creditTemplateService.findById(credit.getCreditTemplate().getId()));
        if (!errors.isEmpty()) {
            log.error("Credit is not valid {}", credit);
            throw new InvalidEntityException("Credit is not valid", ErrorCodes.CREDIT_NOT_VALID, errors);
        }
        //if(credit.getCreditTemplate().getId() == 2){
        //  credit.setAmount(creditSimulateurService.vehicleCredit(credit.getAmount(),credit.getVehicleFiscalPower(),credit.getSelfFinancing(),credit.getRepaymentPeriod()).entrySet().stream().mapToDouble(e->e.getValue()).sum());
        //}
        credit.setCreationDate(Instant.now());
        credit.setLastModifiedDate(Instant.now());
        credit.setCreditStatus(CreditStatus.OPEN);
        mailService.creditNotify(credit,null);
        return  creditRepository.save(credit);
    }

    @Override
    public Credit updateCreditStatus(Integer idCredit, CreditStatus creditStatus) {
         
        checkIdCredit(idCredit);
        if (!StringUtils.hasLength(String.valueOf(creditStatus))) {
            log.error("Credit status is NULL");
            throw new InvalidOperationException("IS not allowed to chagne status to null",
                    ErrorCodes.CREDIT_NON_MODIFIABLE);
        }
        if(creditStatus.equals(CreditStatus.ACCEPTED)){
            Map<Integer,Double> similation = new HashMap<>();
            Credit credit = checkCreditStatus(idCredit);
            credit.setCreditStatus(creditStatus);
            credit.setArchived(true);
            List<String> notes = credit.getNotes();
            notes.add("Your Credit Request Now is :" + creditStatus);
            credit.setNotes(notes);
            credit.setLastModifiedDate(Instant.now());

            if( credit.getCreditTemplate().getCreditType().equals("Vehicle") ){
                 similation = creditSimulateurService.vehicleCredit(credit.getAmount(),credit.getVehicleFiscalPower(),credit.getSelfFinancing(),credit.getRepaymentPeriod());
            } else if ( credit.getCreditTemplate().getCreditType().equals("Personal") ){
                similation = creditSimulateurService.prsonalCredit(credit.getAmount(),credit.getRepaymentPeriod());
            }
            log.info(""+ similation);
            //call operation service
            //send email to client
            mailService.creditNotify(credit,creditStatus);
        }
        if(creditStatus.equals(CreditStatus.REFUSED)){
            Credit credit = checkCreditStatus(idCredit);
            credit.setCreditStatus(creditStatus);
            credit.setArchived(true);
            List<String> notes = credit.getNotes();
            notes.add("Your Credit Request Now is :" + creditStatus);
            credit.setNotes(notes);
            credit.setLastModifiedDate(Instant.now());
            //send email to client
            mailService.creditNotify(credit,creditStatus);
        }

        Credit credit = checkCreditStatus(idCredit);
        credit.setCreditStatus(creditStatus);
        List<String> notes = credit.getNotes();
        notes.add("Your Credit Request Now is " + creditStatus);
        credit.setNotes(notes);
        credit.setLastModifiedDate(Instant.now());
        //send email to client
        mailService.creditNotify(credit,creditStatus);
        return creditRepository.save(credit);
    }

    @Override
    public Credit findById(Integer id) {
        if (id == null) {
            log.error("Credit ID is null");
            return null;
        }
        return creditRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "There is no credit found with ID = " + id,
                        ErrorCodes.CREDIT_NOT_FOUND)
        );
    }


    @Override
    public List<Credit> findAll() {
        return creditRepository.findAll().stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Credit> findAllByUser(Integer id) {
        return creditRepository.findAllByUser(id);
    }

    @Override
    public List<Credit> findAllNotArchived(Boolean archived) {
        return creditRepository.findAllByArchived(archived).stream()
                .collect(Collectors.toList());
    }

    @Override
    public List<Credit> findAllByCreditStatus(CreditStatus creditStatus) {
        return creditRepository.findAllByCreditStatus(creditStatus);
    }

    //For admin
    @Override
    public void delete(Integer id) {

        if (id == null) {
            log.error("Credit ID is null");
            return;
        }
        Optional<Credit> credit = creditRepository.findById(id);

            if (credit.isPresent() && (credit.get().getCreditStatus().equals(CreditStatus.OPEN) || credit.get().getCreditStatus().equals(CreditStatus.IN_PROGRESS) || credit.get().getCreditStatus().equals(CreditStatus.WAITING))) {
                throw new InvalidOperationException("Faild to delete credit, Credit must be in 'ACCEPTED' or 'REFUSED'", ErrorCodes.CREDIT_IS_NOT_CLOSED);
            }

        creditRepository.deleteById(id);

    }


    @Override
    public List<String> addNote(Integer id,String note) {
        checkIdCredit(id);
        Credit credit = creditRepository.getById(id);
        List<String> notes = credit.getNotes();
        notes.add(note);
        credit.setNotes(notes);
        credit.setLastModifiedDate(Instant.now());
        return notes;
    }

    @Override
    public long countCreditByCreditStatus(CreditStatus status) {
        return creditRepository.countCreditByCreditStatus(status);
    }

    @Override
    public List<Credit> autoValidate() {
        //list of updated credit
        List<Credit> updated = new ArrayList<>();
        //list of opened credit
        List<Credit> credits = creditRepository.findAllByCreditStatus(CreditStatus.OPEN);
        //Administrative Document Type list
        List<String> doctypes = new ArrayList<>();

        for(Credit credit : credits){
            Instant now = Instant.now();
            Instant creationDate = credit.getCreationDate();
            long days = creationDate.until(now, ChronoUnit.DAYS);
            if ( credit.getCreditTemplate().getCreditType().equals("Vehicle")){
                doctypes = Stream.of(AdministrativeDocumentType.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
            }else if (credit.getCreditTemplate().getCreditType().equals("Personal") ){
                doctypes = Stream.of(AdministrativeDocumentType.values())
                        .map(Enum::name)
                        .collect(Collectors.toList());
                doctypes.remove(3);
            }

            if (days >= 2){
                doctypes.stream().forEach(e -> log.info("Docs Types : " + e));

                //test for credit Type
                List<String> administrativeDocumetTypes = new ArrayList<>();
                for (AdministrativeDocument administrativeDocument : credit.getAdministrativeDocuments()){
                    administrativeDocumetTypes.add(administrativeDocument.getAdministrativeDocumentType().toString());
                }
                int i = 0;
                while ( i < doctypes.size() && administrativeDocumetTypes.contains(doctypes.get(i)) ){
                    log.info("Credit N: "+credit.getId() +" have doc of type "+ doctypes.get(i));
                    i++;
                }
                boolean existe = (i == doctypes.size());
                administrativeDocumetTypes.stream().forEach(e -> log.info(e));
                    log.info("Existe : "+existe);
                if(!existe){
                    updated.add(credit);
                    List<String> notes = credit.getNotes();
                    if ( credit.getCreditTemplate().getCreditType().equals("Vehicle")){
                        notes.add("Auto Validator : You need to add all needed Administrative Documents type '    INSURANCE,\n" +
                                "    CNSS,\n" +
                                "    WORK_CONTRACT,\n" +
                                "    FACTURE");
                    }else {
                        notes.add("Auto Validator : You need to add all needed Administrative Documents type '    INSURANCE,\n" +
                                "    CNSS,\n" +
                                "    WORK_CONTRACT,\n");
                    }
                    notes.add("Your Credit Request Now is " + CreditStatus.WAITING);
                    credit.setNotes(notes);
                    credit.setLastModifiedDate(Instant.now());
                    credit.setCreditStatus(CreditStatus.WAITING);
                    mailService.creditNotify(credit,CreditStatus.WAITING);
                    creditRepository.save(credit);
                }

            }
        }
        log.info("Updated Credit list :");
        updated.stream().forEach(e-> log.info("Credit ID: "+e.getId()));
        return updated;
    }

    @Override
    public Map<String, Integer> mostOpenedCreditByType() {
        Map<String, Integer> map = new LinkedHashMap<>();
        Integer vehicleCredit = creditRepository.countCreditByCreditTemplateTitle("Vehicle");
        Integer prsonalCredit = creditRepository.countCreditByCreditTemplateTitle("Personal");
        map.put("Vehicle",vehicleCredit);
        map.put("Personal",prsonalCredit);
        if( vehicleCredit == prsonalCredit ){
            map.put("Personal and Vehicle are equals ",prsonalCredit);
        }else if (vehicleCredit > prsonalCredit){
            map.put("Vehicle credit is the most used credit ",vehicleCredit);
        }else{
            map.put("Personal credit is the most used credit ",prsonalCredit);
        }
        return map;
    }

    private void checkIdCredit(Integer idCredit) {
        if (idCredit == null) {
            log.error("Credit ID is NULL");
            throw new InvalidOperationException("ID is null",
                    ErrorCodes.CREDIT_IS_NULL);
        }
    }

    private Credit checkCreditStatus(Integer idCredit) {
        Credit credit = findById(idCredit);
        if (credit.isCreditClosed()) {
            throw new InvalidOperationException("Is not allowed to change Credit status when he's done", ErrorCodes.CREDIT_NON_MODIFIABLE);
        }
        return credit;
    }
}
