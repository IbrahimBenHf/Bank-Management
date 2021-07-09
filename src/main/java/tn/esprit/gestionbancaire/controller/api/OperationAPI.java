package tn.esprit.gestionbancaire.controller.api;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.gestionbancaire.model.Currency;
import tn.esprit.gestionbancaire.model.Operation;

import static tn.esprit.gestionbancaire.utils.Constants.APP_ROOT;

@Api("Operations")
public interface OperationAPI {

    @PostMapping(value = APP_ROOT + "/Operations/create" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "add a Operation", notes = "this methode can add new Operation", response = Operation.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Operation Object added "),
            @ApiResponse(code = 400, message = "Operation Object is invalid")
    })
    ResponseEntity<Operation> save(@RequestBody Operation operation);
}
