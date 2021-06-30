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

    @GetMapping(value = APP_ROOT + "/creditdimilateur/creditvehiclesimilateur/{vehicleAmout}/{vehicleFiscalPower}/{selfFinancing}/{repaymentPeriod}", produces = MediaType.APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Similate vegucles credit", notes = "Similate vegucles credit ", responseContainer = "Map<Integer, Double>")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Map of repayments / Void list")
    })
    Map<Integer, Double> creditVehicle(@PathVariable("vehicleAmout") double vehicleAmout,@PathVariable("vehicleFiscalPower") Integer vehicleFiscalPower,@PathVariable("selfFinancing") double selfFinancing,@PathVariable("repaymentPeriod") Integer repaymentPeriod);

}
