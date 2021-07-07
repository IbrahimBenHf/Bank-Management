package tn.esprit.gestionbancaire.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import tn.esprit.gestionbancaire.model.AccountRequest;

import static tn.esprit.gestionbancaire.utils.Constants.APP_ROOT;

@Api("AccountRequest")
public interface AccountRequestApi {

    @PostMapping(APP_ROOT + "/account_request/request/{idTemplate}")
    @ApiOperation(value = "", notes = "", response = AccountRequest.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Credit modified "),
            @ApiResponse(code = 400, message = "Credit is invalid")
    })
    ResponseEntity<Object> saveForNewClient(@RequestBody AccountRequest accountRequest,
                                        @PathVariable("idTemplate") long idTemplate);
}
