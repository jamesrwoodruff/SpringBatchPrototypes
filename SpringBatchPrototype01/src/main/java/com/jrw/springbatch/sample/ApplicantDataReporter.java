package com.jrw.springbatch.sample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class ApplicantDataReporter {

	private static final Logger log = LoggerFactory.getLogger(ApplicantDataReporter.class);
	
	@Autowired
	JdbcTemplate jdbcTemplate;

	public void reportApplicants() {		
		String sql = "select count(*) from applicant";
		int count = ((Integer) this.jdbcTemplate.queryForObject(sql,
				new Object[0], Integer.class)).intValue();
		log.info(" How many applicant records are in the database?:" + count);
	}
}
