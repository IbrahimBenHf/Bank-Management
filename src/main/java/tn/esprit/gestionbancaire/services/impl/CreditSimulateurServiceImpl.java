package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.services.CreditSimulateurService;
import static tn.esprit.gestionbancaire.utils.Constants.DEFAULT_RATE;
import static tn.esprit.gestionbancaire.utils.Constants.MIN_SELF_FINANCING;

import java.util.HashMap;
import java.util.Map;
@Service
@Slf4j
public class CreditSimulateurServiceImpl implements CreditSimulateurService {

    @Override
    public String creditMedia(Integer creditAmout, Integer repaymentPeriod) {
        return null;
    }

    @Override
    public Map<Integer, Double> creditVehicle(double vehicleAmout, Integer vehicleFiscalPower, double selfFinancing, Integer repaymentPeriod) {
        if (selfFinancing < vehicleAmout * MIN_SELF_FINANCING) {
            log.error("Insufficient Self Financing", selfFinancing);
            throw new InvalidOperationException("Self Financing must be >= 20% of vehicle Amout !!", ErrorCodes.CREDIT_SIMILATEUR_SELF_FINANCING_INSUFFICIENT);
        }
        Map<Integer,Double> map = new HashMap<Integer,Double>();
        double creditAmout = vehicleAmout - selfFinancing;
        double rest = creditAmout;
        double fixedRepayment = creditAmout / repaymentPeriod;
        double monthRepayment = 0.0;
        for(int i = 0; i < repaymentPeriod ; i++){
            monthRepayment = fixedRepayment + (rest * DEFAULT_RATE);
            map.put(i+1,monthRepayment);
            rest = rest - monthRepayment;
        }
        map.entrySet().stream().forEach(e-> System.out.println(e));
        return map;
    }


    @Override
    public String creditConsumer(Integer creditAmout, Integer repaymentPeriod) {
        return null;
    }

    @Override
    public String creditMedia(Integer creditAmout, Integer repaymentPeriod, double rate) {
        return null;
    }

    @Override
    public String creditVehicle(double vehicleAmout, Integer vehicleFiscalPower, Integer selfFinancing, Integer repaymentPeriod, double rate) {
        return null;
    }

    @Override
    public String creditConsumer(Integer creditAmout, Integer repaymentPeriod, double rate) {
        return null;
    }
}
