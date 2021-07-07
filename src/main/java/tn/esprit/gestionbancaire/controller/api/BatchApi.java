package tn.esprit.gestionbancaire.controller.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import org.springframework.web.bind.annotation.PostMapping;

import static tn.esprit.gestionbancaire.utils.Constants.APP_ROOT;

@Api("batch")
public interface BatchApi {
//    @PostMapping(value = APP_ROOT + "/batch")
//    @ApiOperation(value = "Get all credits by archive", notes = "This methode get all credits by archive ")
//    @ApiResponses(value = {
//            @ApiResponse(code = 200, message = "List of credits / Void list")
//    })
//    void startBatch() throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException;
    @PostMapping(value = APP_ROOT + "/batch")
    @ApiOperation(value = "Get all credits by archive", notes = "This methode get all credits by archive ")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of credits / Void list")
    })
    void autoValidatorBatch();

}
