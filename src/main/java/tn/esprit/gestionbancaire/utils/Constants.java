package tn.esprit.gestionbancaire.utils;

public interface Constants {

  String APP_ROOT = "gestionbancaire/v1";

  String CREDIT = APP_ROOT + "/credits";

  String DOCUMENT = APP_ROOT + "/document";

  double MIN_SELF_FINANCING = 0.2;

  double MAX_MEDIA_CREDIT = 100000.0;
  double MIN_MEDIA_CREDIT = 10000.0;
}
