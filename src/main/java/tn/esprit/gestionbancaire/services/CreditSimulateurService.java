package tn.esprit.gestionbancaire.services;

import java.util.Map;

public interface CreditSimulateurService {
    String creditMedia(Integer creditAmout, Integer repaymentPeriod);
    Map<Integer, Double> creditVehicle(double vehicleAmout, Integer vehicleFiscalPower, double selfFinancing, Integer repaymentPeriod);
    String creditConsumer(Integer creditAmout, Integer repaymentPeriod);

    String creditMedia(Integer creditAmout, Integer repaymentPeriod, double rate);
    String creditVehicle(double vehicleAmout, Integer vehicleFiscalPower, Integer selfFinancing, Integer repaymentPeriod, double rate);
    String creditConsumer(Integer creditAmout, Integer repaymentPeriod, double rate);
}
