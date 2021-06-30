package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.services.CreditSimulateurService;
import static tn.esprit.gestionbancaire.utils.Constants.MIN_SELF_FINANCING;

import java.util.HashMap;
import java.util.Map;
@Service
@Slf4j
public class CreditSimulateurServiceImpl implements CreditSimulateurService {
    @Value("${user.simple.rate}")
    double simpleUserRate;

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
        Map<Integer,Double> map = new HashMap<>();
        double creditAmout = vehicleAmout - selfFinancing;
        double rest = creditAmout;
        double fixedRepayment = creditAmout / repaymentPeriod;
        double monthRepayment = 0.0;
        for(int i = 0; i < repaymentPeriod ; i++){
            monthRepayment = fixedRepayment + (rest * simpleUserRate);
            map.put(i+1,monthRepayment);
            rest = rest - monthRepayment;
        }
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
