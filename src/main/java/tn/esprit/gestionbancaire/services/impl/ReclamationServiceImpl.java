package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.enums.ReclamationStatus;
import tn.esprit.gestionbancaire.exception.EntityNotFoundException;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.exception.InvalidOperationException;
import tn.esprit.gestionbancaire.model.Reclamation;
import tn.esprit.gestionbancaire.repository.ReclamationRepository;
import tn.esprit.gestionbancaire.services.ReclamationService;

import java.time.Instant;
import java.util.List;
@Service
@Slf4j
public class ReclamationServiceImpl implements ReclamationService {

    private ReclamationRepository reclamationRepository;
    @Autowired
    public ReclamationServiceImpl(ReclamationRepository reclamationRepository) {
        this.reclamationRepository = reclamationRepository;
    }



    @Override
    public Reclamation save(Reclamation reclamation) {
        reclamation.setStatus(ReclamationStatus.OPEN);
        reclamation.setCreationDate(Instant.now());
        reclamation.setLastModifiedDate(Instant.now());
        return  reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation updateReclamationStatus(Integer idReclamation, ReclamationStatus reclamationStatus){
        checkIdReclamation(idReclamation);
        Reclamation reclamation = checkReclamationStatus(idReclamation);
        reclamation.setStatus(reclamationStatus);
        return reclamationRepository.save(reclamation);
    }

    @Override
    public Reclamation findById(Integer id) {

        return reclamationRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "There is no reclamation found with ID = " + id,
                        ErrorCodes.RECLAMATION_NOT_FOUND)
        );
    }


    @Override
    public List<Reclamation> findAll() {
        return reclamationRepository.findAll();
    }

    @Override
    public void delete(Integer id) {

        reclamationRepository.deleteById(id);

    }

    private void checkIdReclamation(Integer IdReclamation) {
        if (IdReclamation == null) {
            throw new InvalidOperationException("ID is null",
                    ErrorCodes.RECLAMATION_NULL);
        }
    }

    private Reclamation checkReclamationStatus(Integer idReclamation) {
        Reclamation reclamation = findById(idReclamation);
        if (reclamation.isReclamationClosed()) {
            throw new InvalidOperationException("Is not allowed to change Reclamation status when he's done", ErrorCodes.RECLAMATION_NOT_FOUND);
        }
        return reclamation;
    }
}