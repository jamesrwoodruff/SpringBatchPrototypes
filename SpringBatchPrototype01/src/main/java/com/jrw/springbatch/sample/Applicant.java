package com.jrw.springbatch.sample;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.joda.time.LocalDate;

/*
 * A POJO used to unmarshall an xml applicant to a Java class.
 */
@XmlRootElement(name = "applicant")
public class Applicant {
	private String name;
	private double score;
	private LocalDate dob;
	private Address address;

	public Applicant() {
	}

	public Applicant(String name, double score, LocalDate dob, Address address) {
		this.name = name;
		this.score = score;
		this.dob = dob;
		this.address = address;
	}

	@XmlElement(name = "name")
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@XmlElement(name = "score")
	public double getScore() {
		return this.score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	@XmlElement(name = "dob")
	@XmlJavaTypeAdapter(type = LocalDate.class, value = LocalDateAdapter.class)
	public LocalDate getDob() {
		return this.dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	@XmlElement(name = "address")
	public Address getAddress() {
		return this.address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String toString() {
		return "applicant [name=" + this.name + 
				", score=" + this.score + 
				", dob=" + this.dob + 
				", address=" + this.address + 
				"]";
	}
}
