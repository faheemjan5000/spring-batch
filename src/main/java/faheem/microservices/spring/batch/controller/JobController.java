package faheem.microservices.spring.batch.controller;

import lombok.extern.slf4j.Slf4j;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/jobs")
@RestController
@Slf4j
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

   @Autowired
  private Job job;



    @PostMapping("/importCustomers")
    public BatchStatus importCSVToDatabase(){
        log.info("inside importCSVToDatabase() method...");
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("startAt",System.currentTimeMillis()).toJobParameters();
        JobExecution jobExecution = null;

        try {
           jobExecution = jobLauncher.run(job,jobParameters);
           log.info("status : {} ",jobExecution.getStatus());
           while(jobExecution.isRunning()){
               log.info("Batch is running...");
           }
        } catch (JobExecutionAlreadyRunningException | JobInstanceAlreadyCompleteException | JobParametersInvalidException | JobRestartException e) {
            e.printStackTrace();
        }
        log.info("done writing customers into database!");
        return jobExecution.getStatus();
    }
}
