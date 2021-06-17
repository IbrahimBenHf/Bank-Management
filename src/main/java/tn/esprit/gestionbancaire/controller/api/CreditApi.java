package tn.esprit.gestionbancaire.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import java.util.List;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.model.EtatCredit;

import static tn.esprit.gestionbancaire.utils.Constants.APP_ROOT;

@Api("credits")
public interface CreditApi {

    @PostMapping(value = APP_ROOT + "/credits/create" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "add a credit", notes = "this methode can add new credit", response = Credit.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Credit Object added "),
            @ApiResponse(code = 400, message = "Credit Object is invalid")
    })
    Credit save(@RequestBody Credit credit);

    @PatchMapping(APP_ROOT + "/credits/update/etat/{idCredit}/{etatCredit}")
    Credit updateEtatCredit(@PathVariable("idCredit") Integer idCredit, @PathVariable("etatCredit") EtatCredit etatCredit);

    @PatchMapping(APP_ROOT + "/credits/update/credit/{idCredit}")
    Credit updateCredit(@PathVariable("idCredit") Integer idCredit);

/*    @DeleteMapping(APP_ROOT + "/commandesclients/delete/article/{idCommande}/{idLigneCommande}")
    ResponseEntity<CommandeClientDto> deleteArticle(@PathVariable("idCommande") Integer idCommande, @PathVariable("idLigneCommande") Integer idLigneCommande);

    @GetMapping(APP_ROOT + "/commandesclients/{idCommandeClient}")
    ResponseEntity<CommandeClientDto> findById(@PathVariable Integer idCommandeClient);

    @GetMapping(APP_ROOT + "/commandesclients/filter/{codeCommandeClient}")
    ResponseEntity<CommandeClientDto> findByCode(@PathVariable("codeCommandeClient") String code);

    @GetMapping(APP_ROOT + "/commandesclients/all")
    ResponseEntity<List<CommandeClientDto>> findAll();

    @GetMapping(APP_ROOT + "/commandesclients/lignesCommande/{idCommande}")
    ResponseEntity<List<LigneCommandeClientDto>> findAllLignesCommandesClientByCommandeClientId(@PathVariable("idCommande") Integer idCommande);

    @DeleteMapping(APP_ROOT + "/commandesclients/delete/{idCommandeClient}")
    ResponseEntity<Void> delete(@PathVariable("idCommandeClient") Integer id);
}*/
}
