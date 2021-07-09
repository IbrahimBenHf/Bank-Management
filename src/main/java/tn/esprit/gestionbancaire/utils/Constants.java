package tn.esprit.gestionbancaire.utils;

public interface Constants {

  String APP_ROOT = "gestionbancaire/v1";

  String CREDIT = APP_ROOT + "/credits";

  String DOCUMENT = APP_ROOT + "/document";

  double MIN_SELF_FINANCING = 0.2;

  double MAX_VEHICLE_CREDIT = 120000.0;
  double MIN_VEHICLE_CREDIT = 15000.0;

  double MAX_PERSONAL_CREDIT = 60000.0;
  double MIN_PERSONAL_CREDIT = 8000.0;

  String ACCOUNT_NUMBER_PREFIX = "01";
}
