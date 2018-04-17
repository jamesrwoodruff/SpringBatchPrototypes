package com.jrw.springbatch.sample;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ApplicantLoadJobLauncher {

	private static final Logger log = LoggerFactory.getLogger(ApplicantLoadJobLauncher.class);
	
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	Job loadApplicantsJob;
	@Autowired
	ApplicantDataReporter applicantDataReporter;

	public static void main(String... args) throws JobParametersInvalidException,
												   JobExecutionAlreadyRunningException, JobRestartException,
												   JobInstanceAlreadyCompleteException {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
		ApplicantLoadJobLauncher main = (ApplicantLoadJobLauncher) context.getBean(ApplicantLoadJobLauncher.class);
		
		JobParametersBuilder builder = new JobParametersBuilder();
		builder.addDate("date", new Date());
		main.jobLauncher.run(main.loadApplicantsJob, builder.toJobParameters());

		log.info("After applicant load job runs, check record count on table:");
		main.applicantDataReporter.reportApplicants();
		context.close();
	}
}
