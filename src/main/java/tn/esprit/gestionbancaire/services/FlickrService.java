package tn.esprit.gestionbancaire.services;

import java.io.InputStream;

public interface FlickrService {

    String savePhoto(Integer id, InputStream photo, String title);

}
