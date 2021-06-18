package tn.esprit.gestionbancaire.services;


import tn.esprit.gestionbancaire.model.AdministrativeDocument;

import java.util.List;

public interface AdministrativeDocumentService {

    AdministrativeDocument save(AdministrativeDocument administrativeDocument);
    AdministrativeDocument update(AdministrativeDocument administrativeDocument);
    List<AdministrativeDocument> findAllByUserId(Integer id);
    //AdministrativeDocument findById(Integer id);
    void delete(Integer id);
}
