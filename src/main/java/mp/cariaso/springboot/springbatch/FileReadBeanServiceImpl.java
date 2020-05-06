package mp.cariaso.springboot.springbatch;

import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class FileReadBeanServiceImpl {

    @Autowired
    private JobLauncher asyncJobLauncher;

    @Autowired
    private JobLauncher jobLauncher; //default job launcher

    @Autowired
    private Job asyncMultiStepJob;

    @Autowired
    private Job firstStepJob;


    public void runAsync() {

        JobParameters params = new JobParametersBuilder()
                .addString("JOB_ID", UUID.randomUUID().toString())
                .toJobParameters();

        ExitStatus exitStatus = ExitStatus.EXECUTING;

        JobExecution jobExecution = null;

        try {
            jobExecution = asyncJobLauncher.run(asyncMultiStepJob, params);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }

        exitStatus = jobExecution.getExitStatus();

    }

    public void runFirstStep() {

        JobParameters params = new JobParametersBuilder()
                .addString("JOB_ID", UUID.randomUUID().toString())
                .toJobParameters();

        ExitStatus exitStatus = ExitStatus.EXECUTING;

        JobExecution jobExecution = null;

        try {
            jobExecution = jobLauncher.run(firstStepJob, params);
        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }

        exitStatus = jobExecution.getExitStatus();
    }
}
