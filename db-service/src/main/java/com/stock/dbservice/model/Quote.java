package com.stock.dbservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "quotes", catalog = "test")
public class Quote {

	@Id
	@GeneratedValue
	private Integer id;
	
	@Column
	private String userName;
	
	@Column
	private String quote;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getQuote() {
		return quote;
	}

	public void setQuote(String quote) {
		this.quote = quote;
	}
	
	public Quote() {
		// TODO Auto-generated constructor stub
	}

	public Quote(String userName, String quote) {
		this.userName = userName;
		this.quote = quote;
	}
	
	
}
