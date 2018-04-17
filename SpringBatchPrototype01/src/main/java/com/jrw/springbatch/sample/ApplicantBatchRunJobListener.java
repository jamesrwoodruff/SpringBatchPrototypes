package com.jrw.springbatch.sample;

import java.util.Iterator;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

@Component
public class ApplicantBatchRunJobListener implements JobExecutionListener {
	
	private static final Logger log = LoggerFactory.getLogger(ApplicantBatchRunJobListener.class);

	@Override
	public void beforeJob(JobExecution arg0) {
		log.info("ApplicantBatchRunJobListener, beforeJob");
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		log.info("ApplicantBatchRunJobListener, afterJob");

		StringBuilder runinfo = new StringBuilder();

		runinfo.append("\n\n++++++++++++++++ Job Execution ++++++++++++++++++++++ \n");
		runinfo.append("Batch run info for " + jobExecution.getJobInstance().getJobName() + " \n");
		runinfo.append("  Started     : " + jobExecution.getStartTime() + "\n");
		runinfo.append("  Finished    : " + jobExecution.getEndTime() + "\n");
		runinfo.append("  Exit-Code   : " + jobExecution.getExitStatus().getExitCode() + "\n");
		runinfo.append("  Exit-Descr. : " + jobExecution.getExitStatus().getExitDescription() + "\n");
		runinfo.append("  Status      : " + jobExecution.getStatus() + "\n");
		runinfo.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");

		runinfo.append("\nJob-Parameter(s): \n");

		JobParameters jp = jobExecution.getJobParameters();
		
		/*Map.Entry<String, JobParameter> entry;
		for (Iterator<Map.Entry<String, JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter
				.hasNext();) {
			entry = (Map.Entry) iter.next();
			runinfo.append("  " + (String) entry.getKey() + "=" + entry.getValue() + "\n");
		}*/
		
		for (Iterator<Entry<String,JobParameter>> iter = jp.getParameters().entrySet().iterator(); iter.hasNext();) {
			Entry<String,JobParameter> entry = iter.next();
			runinfo.append("  "+entry.getKey()+"="+entry.getValue()+"\n");
		}
		
		for (StepExecution stepExecution : jobExecution.getStepExecutions()) {
			runinfo.append("\n+++++++++++ Step Execution +++++++++++++++++++++++++ \n");
			runinfo.append("Step " + stepExecution.getStepName() + " \n");
			runinfo.append("WriteCount: " + stepExecution.getWriteCount() + "\n");
			runinfo.append("Commits: " + stepExecution.getCommitCount() + "\n");
			runinfo.append("SkipCount: " + stepExecution.getSkipCount() + "\n");
			runinfo.append("Rollbacks: " + stepExecution.getRollbackCount() + "\n");
			runinfo.append("Filter: " + stepExecution.getFilterCount() + "\n");
			runinfo.append("+++++++++++++++++++++++++++++++++++++++++++++++++++++++ \n");
		}
		log.info(runinfo.toString());
	}
}
