package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.enums.CreditStatus;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.services.CreditService;
import tn.esprit.gestionbancaire.services.CreditSimulateurService;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

import static tn.esprit.gestionbancaire.utils.Constants.*;

@Service
@Slf4j
public class CreditSimulateurServiceImpl implements CreditSimulateurService {
    @Value("${user.simple.rate}")
    double simpleUserRate;
    @Value("${user.unprofitable.rate}")
    double unprofitableUserRate;
    @Value("${user.profitable.rate}")
    double profitableUserRate;

    private CreditService creditService;

    @Autowired
    public CreditSimulateurServiceImpl(
            CreditService creditService
    )
    {
        this.creditService = creditService;
    }
    @Override
    public Map<Integer, Double> prsonalCredit(double creditAmout, Integer repaymentPeriod) {
        if (creditAmout > MIN_MEDIA_CREDIT && creditAmout < MAX_MEDIA_CREDIT) {
            log.error("Credit Amout should less then 100000 more then 10000 ", creditAmout);
            throw new InvalidOperationException("Credit Amout should less then 100000 more then 10000 !!", ErrorCodes.MEDIA_CREDIT_SIMILATEUR_AMOUNT);
        }
        Map<Integer,Double> map = new HashMap<>();
        double fixedRepayment = creditAmout / repaymentPeriod;
        double rest = creditAmout;
        double monthRepayment = 0.0;
        for(int i = 0; i < repaymentPeriod ; i++){
            monthRepayment = fixedRepayment + (rest * simpleUserRate);
            map.put(i+1,monthRepayment);
            rest = rest - monthRepayment;
        }
        return map;
    }

    @Override
    public Map<Integer, Double> vehicleCredit(double vehicleAmout, Integer vehicleFiscalPower, double selfFinancing, Integer repaymentPeriod) {
        double additionalRate;
        //User user = userService.getCurentUser();
        if (selfFinancing < vehicleAmout * MIN_SELF_FINANCING) {
            log.error("Insufficient Self Financing", selfFinancing);
            log.error("Self Financing should be >= ",vehicleAmout * 0.20);
            throw new InvalidOperationException("Self Financing must be >= 20% of vehicle Amout !!", ErrorCodes.CREDIT_SIMILATEUR_SELF_FINANCING_INSUFFICIENT);
        }
        if (vehicleFiscalPower <= 0 ){
            log.error("Invalid vehicle FiscalPower", vehicleFiscalPower);
            log.error("Vehicle FiscalPower should be >= ",0);
            throw new InvalidOperationException("Vehicle FiscalPower should be >= 0 !!", ErrorCodes.CREDIT_SIMILATEUR_SELF_FINANCING_INSUFFICIENT);

        }else if (vehicleFiscalPower < 4 ) {
            additionalRate = 0.0;
        }else if (vehicleFiscalPower >= 4 && vehicleFiscalPower < 9 ){
            additionalRate = 0.005;
        }else {
            additionalRate = 0.008;
        }
        Map<Integer,Double> map = new HashMap<>();
        double creditAmout = vehicleAmout - selfFinancing;
        double rest = creditAmout;
        double fixedRepayment = creditAmout / repaymentPeriod;
        double monthRepayment = 0.0;
        for(int i = 0; i < repaymentPeriod ; i++){
            //monthRepayment = fixedRepayment + (rest * (getUserRate(user.getScore)+additionalRate) );
            monthRepayment = fixedRepayment + (rest * (getUserRate(25)+additionalRate) );
            map.put(i+1,monthRepayment);
            rest = rest - monthRepayment;
        }
        //double sum = map.entrySet().stream().mapToDouble(e->e.getValue()).sum() / repaymentPeriod;
        //log.info(sum +"");
        return map;
    }
    //for admin
    @Override
    public Map<Integer, Double> prsonalCredit(double creditAmout, Integer repaymentPeriod, double rate) {
        if (creditAmout > MIN_MEDIA_CREDIT && creditAmout < MAX_MEDIA_CREDIT) {
            log.error("Credit Amout should less then 100000 more then 10000 ", creditAmout);
            throw new InvalidOperationException("Credit Amout should less then 100000 more then 10000 !!", ErrorCodes.MEDIA_CREDIT_SIMILATEUR_AMOUNT);
        }
        Map<Integer,Double> map = new HashMap<>();
        double fixedRepayment = creditAmout / repaymentPeriod;
        double rest = creditAmout;
        double monthRepayment = 0.0;
        for(int i = 0; i < repaymentPeriod ; i++){
            monthRepayment = fixedRepayment + (rest * rate);
            map.put(i+1,monthRepayment);
            rest = rest - monthRepayment;
        }
        return map;
    }

    //for admin
    @Override
    public Map<Integer, Double> vehicleCredit(double vehicleAmout, Integer vehicleFiscalPower, double selfFinancing, Integer repaymentPeriod, double rate) {
        double additionalRate;
        //User user = userService.getCurentUser();
        if (selfFinancing < vehicleAmout * MIN_SELF_FINANCING) {
            log.error("Insufficient Self Financing", selfFinancing);
            log.error("Self Financing should be >= ",vehicleAmout * 0.20);
            throw new InvalidOperationException("Self Financing must be >= 20% of vehicle Amout !!", ErrorCodes.CREDIT_SIMILATEUR_SELF_FINANCING_INSUFFICIENT);
        }
        if (vehicleFiscalPower <= 0 ){
            log.error("Invalid vehicle FiscalPower", vehicleFiscalPower);
            log.error("Vehicle FiscalPower should be >= ",0);
            throw new InvalidOperationException("Vehicle FiscalPower should be >= 0 !!", ErrorCodes.CREDIT_SIMILATEUR_SELF_FINANCING_INSUFFICIENT);

        }else if (vehicleFiscalPower < 4 ) {
            additionalRate = 0.0;
        }else if (vehicleFiscalPower >= 4 && vehicleFiscalPower < 9 ){
            additionalRate = 0.005;
        }else {
            additionalRate = 0.008;
        }
        Map<Integer,Double> map = new HashMap<>();
        double creditAmout = vehicleAmout - selfFinancing;
        double rest = creditAmout;
        double fixedRepayment = creditAmout / repaymentPeriod;
        double monthRepayment = 0.0;
        for(int i = 0; i < repaymentPeriod ; i++){
            //monthRepayment = fixedRepayment + (rest * (getUserRate(user.getScore)+additionalRate) );
            monthRepayment = fixedRepayment + (rest * (rate+additionalRate) );
            map.put(i+1,monthRepayment);
            rest = rest - monthRepayment;
        }
        //double sum = map.entrySet().stream().mapToDouble(e->e.getValue()).sum() / repaymentPeriod;
        //log.info(sum +"");
        return map;
    }

    @Override
    public double getUserRate(double score) {
        if(score <= 300){
            return unprofitableUserRate;
        }else if (score >300 && score <= 1100){
            return simpleUserRate;
        }else
            return profitableUserRate;
    }

    @Override
    public Map<String,Integer> getUserStatusOverView(Integer id) {
        double score = 0.0;
        Map<String,Integer> map = new HashMap<>();
        //User user = userService.findById(id);
        Credit credit = creditService.findById(id);
        //
        Instant now = Instant.now();
        //Instant creationDate = user.getCreationDate();
        Instant creationDate = credit.getCreationDate();

        //long transactions = user.countCreditByUser(user);
        long days = creationDate.until(now, ChronoUnit.DAYS);
        //max point for to the oldest user 100
        if (days < 180) {
            map.put("Created", 5);

        }else if (days > 180) {
            map.put("Created", 20);
        }else if (days > 365) {
            map.put("Created", 50);
        }else if (days > 1825){
            map.put("Created", 100);
        }

        //max point for to the transaction 500
        //long transactions = user.countCreditByUser(user);
        long rows = creditService.countCreditByCreditStatus(CreditStatus.OPEN);
        if (rows < 24){
            map.put("Transactions", 20);
        }else if (rows < 200){
            map.put("Transactions", 100);
        }else if (rows < 600){
            map.put("Transactions", 300);
        }else if (rows > 1200){
            map.put("Transactions", 500);
        }

        //max point for to the overdraft 1000 in last 2 year
        //long overdraft = user.countTransationsOverDraftByUser(user);
        long overdraft = creditService.countCreditByCreditStatus(CreditStatus.OPEN);
        if (overdraft < 10 ){
            map.put("Overdraft", 50);
        }else if (overdraft < 25){
            map.put("Overdraft", 200);
        }else if (overdraft < 50){
            map.put("Overdraft", 500);
        }else if (overdraft > 100){
            map.put("Overdraft", 1000);
        }

        return map;
    }


}
