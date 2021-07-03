package tn.esprit.gestionbancaire.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.gestionbancaire.model.Credit;
import tn.esprit.gestionbancaire.model.Currency;

import java.util.List;

import static tn.esprit.gestionbancaire.utils.Constants.APP_ROOT;

@Api("currencies")
public interface CurrencyAPI {

    @PostMapping(value = APP_ROOT + "/currencies/create" , consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "add a Currency", notes = "this methode can add new Currency", response = Currency.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Currency Object added "),
            @ApiResponse(code = 400, message = "Currency Object is invalid")
    })
    ResponseEntity<Currency> save(@RequestBody Currency currency);


    @PatchMapping(APP_ROOT + "/currencies/update/currency/{idCurrency}")
    @ApiOperation(value = "Modify Currency", notes = "this methode can modify Currency", response = Currency.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Currency modified "),
            @ApiResponse(code = 400, message = "Currency is invalid")
    })
    ResponseEntity<Currency> updateCurrency(@PathVariable("idCurrency") Integer idCCurrency,@RequestBody Currency currency );

    @GetMapping(value = APP_ROOT + "/currencies/all", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all currencies", notes = "This methode get all Currency ", responseContainer = "List<Currency>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of Currency / Void list")
    })
    List<Currency> getAll();

    @GetMapping(value = APP_ROOT + "/currencies/available", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all availables currencies", notes = "This methode get all available Currency ", responseContainer = "List<Currency>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of Currency / Void list")
    })
    List<Currency> getAvailableToExchange();

    @GetMapping(value = APP_ROOT + "/currencies/crypto", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Get all CryptoCurrency", notes = "This methode get all CryptoCurrency ", responseContainer = "List<Currency>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of Currency / Void list")
    })
    List<Currency> getAllCryptoCurrency();

    @DeleteMapping(APP_ROOT + "/currencies/delete/currency/{idCurrency}")
    @ApiOperation(value = "Delete currency", notes = "This methode can delete Currency", response = Credit.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Currency deleted "),
            @ApiResponse(code = 400, message = "Currency is invalid")
    })
    void deleteCurrency(@PathVariable("idCurrency") Integer idCurrency);

}
