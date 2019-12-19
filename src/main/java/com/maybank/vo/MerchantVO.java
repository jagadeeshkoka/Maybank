package com.maybank.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(value = Include.NON_NULL)
public class MerchantVO {

	
	
	private int merchantId;
	private String merchantName;
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
