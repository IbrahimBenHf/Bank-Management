package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidEntityException;
import tn.esprit.gestionbancaire.model.AdministrativeDocument;
import tn.esprit.gestionbancaire.repository.AdministrativeDocumentRepository;
import tn.esprit.gestionbancaire.services.AdministrativeDocumentService;
import tn.esprit.gestionbancaire.validator.AdministrativeDocumentValidator;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class AdministrativeDocumentServiceImpl implements AdministrativeDocumentService {

    private AdministrativeDocumentRepository administrativeDocumentRepository;

    @Autowired
    public AdministrativeDocumentServiceImpl(AdministrativeDocumentRepository administrativeDocumentRepository) {
        this.administrativeDocumentRepository = administrativeDocumentRepository;

    }


    @Override
    public AdministrativeDocument save(AdministrativeDocument administrativeDocument) {
        List<String> errors = AdministrativeDocumentValidator.validate(administrativeDocument);
        if (!errors.isEmpty()) {
            log.error("administrativeDocument is not valid {}", administrativeDocument);
            throw new InvalidEntityException("Credit is not valid", ErrorCodes.CREDIT_NOT_VALID, errors);
        }

        return administrativeDocumentRepository.save(administrativeDocument);
    }

    @Override
    public AdministrativeDocument update(AdministrativeDocument administrativeDocument) {
        return null;
    }

    @Override
    public List<AdministrativeDocument> findAllByCreditId(Integer id) {
        return administrativeDocumentRepository.findAllByCreditId(id);
    }

    @Override
    public AdministrativeDocument findById(Integer id) {
        return administrativeDocumentRepository.getById(id);
    }


    @Override
    public void delete(Integer id) {
        if (id == null) {
            log.error("Administrative Document ID is null");
            return;
        }
        administrativeDocumentRepository.deleteById(id);
    }

    @Override
    public AdministrativeDocument updateAdministrativeDocumentImage(Integer id, String imageURL) {
        if (id == null) {
            log.error("Administrative Document ID is null");
        }

        Optional<AdministrativeDocument> byId = administrativeDocumentRepository.findById(id);
        AdministrativeDocument administrativeDocument = null;
        if (byId.isPresent()) {
            administrativeDocument = byId.get();
            administrativeDocument.setPhoto(imageURL);
            administrativeDocumentRepository.save(administrativeDocument);
        }

        return administrativeDocument;


    }
}
