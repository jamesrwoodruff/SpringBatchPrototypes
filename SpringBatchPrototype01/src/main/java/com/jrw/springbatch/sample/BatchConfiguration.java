package com.jrw.springbatch.sample;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.JobRegistry;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.JobRegistryBeanPostProcessor;
import org.springframework.batch.core.job.builder.FlowJobBuilder;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {
	
	@Bean
	public ItemReader<Applicant> reader() {
		StaxEventItemReader<Applicant> reader = new StaxEventItemReader<Applicant>();
		reader.setResource(new ClassPathResource("applicantfile.xml"));
		reader.setFragmentRootElementName("applicant");
		Jaxb2Marshaller j = new Jaxb2Marshaller();
		j.setClassesToBeBound(new Class[] { Applicant.class });
		reader.setUnmarshaller(j);
		//System.out.println("j.getContextPath():" + j.getContextPath() + ":end");
		//System.out.println("j.getClass()" + j.getClass());
		return reader;
	}

	@Bean
	public ItemProcessor<Applicant, Applicant> processor() {
		return new ApplicantItemProcessor();
	}

	@Bean
	public ItemWriter<Applicant> writer(DataSource dataSourceMySQL) {
		JdbcBatchItemWriter<Applicant> writer = new JdbcBatchItemWriter<Applicant>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Applicant>());
		writer.setSql("insert into applicant (name,score,dob,street,city,state,zip) values (?,?,?,?,?,?,?)");
		writer.setItemPreparedStatementSetter(new ApplicantItemPreparedStatementSetter());
		writer.setDataSource(dataSourceMySQL);
		return writer;
	}

	@Bean
	public Job loadApplicantsJob(JobBuilderFactory jobs, Step s1, ApplicantBatchRunJobListener cbrjl) throws Exception {
		return ((FlowJobBuilder) ((JobBuilder) ((JobBuilder) jobs.get("loadApplicantsJob")
				.incrementer(new RunIdIncrementer()))
				.listener(cbrjl))
				.flow(s1)
				.end())
				.build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Applicant> reader, 
			ItemWriter<Applicant> writer, ItemProcessor<Applicant, Applicant> processor) {
		return stepBuilderFactory.get("step1a")
				.<Applicant, Applicant> chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSourceMySQL) {
		return new JdbcTemplate(dataSourceMySQL);
	}

	@Bean
	public JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor(JobRegistry jobRegistry) {
		JobRegistryBeanPostProcessor jobRegistryBeanPostProcessor = new JobRegistryBeanPostProcessor();
		jobRegistryBeanPostProcessor.setJobRegistry(jobRegistry);
		return jobRegistryBeanPostProcessor;
	}
}
