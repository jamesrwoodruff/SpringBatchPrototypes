package com.jrw.springbatch.sample;

import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.explore.JobExplorer;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.JobOperator;
import org.springframework.batch.core.launch.support.SimpleJobOperator;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({ "com.jrw.springbatch.sample" })
public class ApplicationConfiguration {

	@Autowired
	JobRepository jobRepository;
	@Autowired
	JobRegistry jobRegistry;
	@Autowired
	JobLauncher jobLauncher;
	@Autowired
	JobExplorer jobExplorer;

	@Bean
	public JobOperator jobOperator() {
		SimpleJobOperator jobOperator = new SimpleJobOperator();
		jobOperator.setJobExplorer(this.jobExplorer);
		jobOperator.setJobLauncher(this.jobLauncher);
		jobOperator.setJobRegistry(this.jobRegistry);
		jobOperator.setJobRepository(this.jobRepository);
		return jobOperator;
	}
}
