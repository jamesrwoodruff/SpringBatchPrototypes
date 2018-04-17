package com.jrw.springbatch.sample;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;

public class ApplicantItemPreparedStatementSetter implements ItemPreparedStatementSetter<Applicant> {

	public void setValues(Applicant result, PreparedStatement ps)
			throws SQLException {
		ps.setString(1, result.getName());
		ps.setDouble(2, result.getScore());
		ps.setDate(3, new java.sql.Date(result.getDob().toDate().getTime()));
		ps.setString(4, result.getAddress().getStreet());
		ps.setString(5, result.getAddress().getCity());
		ps.setString(6, result.getAddress().getState());
		ps.setString(7, result.getAddress().getZip());
	}
}
