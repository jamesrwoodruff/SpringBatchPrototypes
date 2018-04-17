package com.jrw.springbatch.sample;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * A POJO used to unmarshall an xml address to a Java class.
 */
@XmlRootElement(name = "address")
public class Address {
	private String street;
	private String city;
	private String state;
	private String zip;

	@XmlElement(name = "street")
	public String getStreet() {
		return this.street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	@XmlElement(name = "city")
	public String getCity() {
		return this.city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	@XmlElement(name = "state")
	public String getState() {
		return this.state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@XmlElement(name = "zip")
	public String getZip() {
		return this.zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public String toString() {
		return "Address [street=" + this.street + 
				"city=" + this.city + 
				"state=" + this.state + 
				"zip=" + this.zip + 
				"]";
	}
}
