package tn.esprit.gestionbancaire.controller;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.gestionbancaire.controller.api.BatchApi;
import tn.esprit.gestionbancaire.services.CreditService;

import java.util.HashMap;
import java.util.Map;
@RestController
public class BatchController  implements BatchApi {
    private CreditService creditService;

    @Autowired
    public BatchController(
            CreditService creditService
    ) {
        this.creditService = creditService;
    }
    @Override
    @Scheduled(cron ="00 00 20 */1 * *" )
    public void autoValidatorBatch() {
        creditService.autoValidate();
    }

//    @Autowired
//    private JobLauncher jobLauncher;
//    @Autowired
//    private Job job;

//    @Override
//    public void startBatch() {
//        JobParameters jobParameters = new JobParametersBuilder()
//                .addLong("startAt", System.currentTimeMillis()).toJobParameters();
//        try {
//            jobLauncher.run(job, jobParameters);
//        } catch (JobExecutionAlreadyRunningException | JobRestartException
//                | JobInstanceAlreadyCompleteException | JobParametersInvalidException e) {
//
//            e.printStackTrace();
//        }
//    }
}
