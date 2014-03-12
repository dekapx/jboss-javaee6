package com.kapx.jboss.javaee6.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ADDRESS")
public class Address {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID")
	private Long id;

	@Column(name = "STREET_ADDRESS_1", length = 25, nullable = false)
	private String streetAddress1;

	@Column(name = "STREET_ADDRESS_2", length = 25, nullable = true)
	private String streetAddress2;

	@Column(name = "CITY", length = 25, nullable = false)
	private String city;

	@Column(name = "STATE", length = 2, nullable = false)
	private String state;

	@Column(name = "ZIP_CODE", length = 5, nullable = false)
	private String zip;

	public Address() {
	}

	public Address(String streetAddress1, String streetAddress2, String city, String state, String zip) {
		super();
		this.streetAddress1 = streetAddress1;
		this.streetAddress2 = streetAddress2;
		this.city = city;
		this.state = state;
		this.zip = zip;
	}

	public Long getId() {
		return id;
	}

	public String getStreetAddress1() {
		return streetAddress1;
	}

	public String getStreetAddress2() {
		return streetAddress2;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public String getZip() {
		return zip;
	}

	@Override
	public String toString() {
		return "[" + streetAddress1 + " " + city + " " + state + " " + zip + "]";
	}
}
