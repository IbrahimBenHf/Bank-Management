package tn.esprit.gestionbancaire.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.Map;

import static tn.esprit.gestionbancaire.utils.Constants.APP_ROOT;

@Api("creditSimilateur")
public interface CreditSimilateurApi {

    @GetMapping(value = APP_ROOT + "/creditsimilateur/vehiclecreditsimilateur/{vehicleAmout}/{vehicleFiscalPower}/{selfFinancing}/{repaymentPeriod}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Simulate vehicle credit", notes = "Simulate vehicle credit ", responseContainer = "Map<Integer, Double>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Map of repayments / Void list")
    })
    Map<Integer, Double> vehicleCredit(@PathVariable("vehicleAmout") double vehicleAmout,@PathVariable("vehicleFiscalPower") Integer vehicleFiscalPower,@PathVariable("selfFinancing") double selfFinancing,@PathVariable("repaymentPeriod") Integer repaymentPeriod);

    @GetMapping(value = APP_ROOT + "/creditsimilateur/vehiclecreditsimilateur/{vehicleAmout}/{vehicleFiscalPower}/{selfFinancing}/{repaymentPeriod}/{rate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Simulate vehicle credit", notes = "Similate vehicle credit ", responseContainer = "Map<Integer, Double>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Map of repayments / Void list")
    })
    Map<Integer, Double> vehicleCredit(@PathVariable("vehicleAmout") double vehicleAmout,@PathVariable("vehicleFiscalPower") Integer vehicleFiscalPower,@PathVariable("selfFinancing") double selfFinancing,@PathVariable("repaymentPeriod") Integer repaymentPeriod,@PathVariable("rate") double rate);


    @GetMapping(value = APP_ROOT + "/creditsimilateur/personalcreditsimilateur/{creditAmout}/{repaymentPeriod}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Simulate personal credit", notes = "Simulate personal credit ", responseContainer = "Map<Integer, Double>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Map of repayments / Void list")
    })
    Map<Integer, Double> personalCredit(@PathVariable("creditAmout") double creditAmout,@PathVariable("repaymentPeriod") Integer repaymentPeriod);


    @GetMapping(value = APP_ROOT + "/creditsimilateur/personalcreditsimilateur/{creditAmout}/{repaymentPeriod}/{rate}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Simulate personal credit", notes = "Simulate personal Credit ", responseContainer = "Map<Integer, Double>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Map of repayments / Void list")
    })
    Map<Integer, Double> personalCredit(@PathVariable("creditAmout") double creditAmout,@PathVariable("repaymentPeriod") Integer repaymentPeriod,@PathVariable("selfFinancing") double rate);


    @GetMapping(value = APP_ROOT + "/creditsimilateur/creditvehiclesimilateur/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Simulate vegucles credit", notes = "Simulate vegucles credit ", responseContainer = "long")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Map of repayments / Void list")
    })
    Map<String, Integer> getUserScore(@PathVariable("id") Integer id);
}
