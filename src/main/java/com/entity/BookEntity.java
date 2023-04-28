package com.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Book")
public class BookEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bid;
	private String name;
	private float price;
	private Integer qty;
	private String authname;
	private boolean availableind;
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer bid) {
		this.bid = bid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public Integer getQty() {
		return qty;
	}
	public void setQty(Integer qty) {
		this.qty = qty;
	}
	public String getAuthname() {
		return authname;
	}
	public void setAuth_name(String authname) {
		this.authname = authname;
	}
	public boolean isAvailableind() {
		return availableind;
	}
	public void setAvailableind(boolean availableind) {
		this.availableind = availableind;
	}
	
	
	
}
