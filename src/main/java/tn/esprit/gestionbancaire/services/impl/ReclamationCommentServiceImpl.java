package tn.esprit.gestionbancaire.services.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.gestionbancaire.exception.EntityNotFoundException;
import tn.esprit.gestionbancaire.exception.ErrorCodes;
import tn.esprit.gestionbancaire.model.ReclamationComment;
import tn.esprit.gestionbancaire.repository.ReclamationCommentRepository;
import tn.esprit.gestionbancaire.services.ReclamationCommentService;

import java.time.Instant;
import java.util.List;

@Service
@Slf4j
public class ReclamationCommentServiceImpl implements ReclamationCommentService {

    private ReclamationCommentRepository reclamationCommentRepository;
    @Autowired
    public ReclamationCommentServiceImpl(ReclamationCommentRepository reclamationCommentRepository) {
        this.reclamationCommentRepository = reclamationCommentRepository;
    }

    @Override
    public ReclamationComment findById(Integer id) {
        return reclamationCommentRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(
                        "There is no comment found with ID = " + id,
                        ErrorCodes.RECLAMATION_COMMENT_NOT_FOUND)
        );
    }

    @Override
    public List<ReclamationComment> findAll() {
        return reclamationCommentRepository.findAll();
    }

    @Override
    public ReclamationComment save(ReclamationComment reclamationComment) {
        reclamationComment.setCreationDate(Instant.now());
        reclamationComment.setLastModifiedDate(Instant.now());
        return  reclamationCommentRepository.save(reclamationComment);
    }

    @Override
    public ReclamationComment update(Integer id,ReclamationComment newReclamationComment) {
        ReclamationComment reclamationComment = findById(id);
        reclamationComment.setLastModifiedDate(Instant.now());
        reclamationComment.setComment(newReclamationComment.getComment());
        return  reclamationCommentRepository.save(reclamationComment);
    }

    @Override
    public void delete(Integer id) {
        reclamationCommentRepository.deleteById(id);

    }
}
