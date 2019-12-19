package com.maybank.entities;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity(name = "merchant")
@Table(name = "merchant", schema = "maybank_schema")
public class Merchant implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "merchant_id" ,nullable = false, unique = true, length = 10)
	private int merchantId;
	
	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "productId", targetEntity =
	 * Product.class) private Set<Product> products;
	 */
	
	
	@Column(name = "merchant_name", nullable = false, length = 150)
	private String merchantName;
	
	@Column(name = "merchant_desc", length = 500)
	private String merchantDesc;

	public int getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(int merchantId) {
		this.merchantId = merchantId;
	}

	public String getMerchantName() {
		return merchantName;
	}

	public void setMerchantName(String merchantName) {
		this.merchantName = merchantName;
	}

	public String getMerchantDesc() {
		return merchantDesc;
	}

	public void setMerchantDesc(String merchantDesc) {
		this.merchantDesc = merchantDesc;
	}
	
	
	
	
	
	
	
}
