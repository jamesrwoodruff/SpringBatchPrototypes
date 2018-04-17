package com.jrw.springbatch.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class ApplicantItemProcessor implements ItemProcessor<Applicant, Applicant> {
	
	private final Logger log = LoggerFactory.getLogger(ApplicantItemProcessor.class);

	public Applicant process(Applicant result) throws Exception {
		
		log.info("Processing result :" + result);

		// ignore applicants whose score is < 90
		if (result.getScore() < 90.0) {
			return null;
		}
		
		return result;
	}
}
