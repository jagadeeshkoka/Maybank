package com.maybank.entities;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.Generated;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name = "product", schema = "maybank_schema")
public class Product implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id", unique = true, nullable = false, updatable = false, length = 10)
	private int productId;
	
	
	@Column(name = "product_url", nullable = false, length = 250) 
	private String url;
	
	@Column(name = "product_title", nullable = false, length = 150)
	private String productTitle;
	
	@OneToMany( mappedBy = "productId" , cascade = CascadeType.DETACH, fetch = FetchType.LAZY, targetEntity = ProductImages.class )
	private Set<String> imageUrls;
	
	@Column(name = "category_id")
	private String catregorId;
	
	@ManyToOne(cascade = CascadeType.DETACH ,targetEntity = Category.class)
	@JoinColumn(name = "categorId")
	private Category category;
	
	
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	@Column(name = "roduct_price", nullable = false)
	private double productPrice;
	
	@Column(name = "product_msrp")
	private int productMsrp;
	
	@Column(name = "prodcut_availability", nullable = false)
	private boolean productAvailability;
	
	@Column(name = "merchant_id", nullable = false)
	private String merchantId;
	
	@ManyToOne(cascade = CascadeType.DETACH, targetEntity = Merchant.class, fetch = FetchType.LAZY)
	private Merchant merchant;
	
	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	@Column(name = "product_description" , length = 650)
	private String productDesc;

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getProductTitle() {
		return productTitle;
	}

	public void setProductTitle(String productTitle) {
		this.productTitle = productTitle;
	}


	public String getCatregorId() {
		return catregorId;
	}

	public void setCatregorId(String catregorId) {
		this.catregorId = catregorId;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductMsrp() {
		return productMsrp;
	}

	public void setProductMsrp(int productMsrp) {
		this.productMsrp = productMsrp;
	}

	public boolean isProductAvailability() {
		return productAvailability;
	}

	public void setProductAvailability(boolean productAvailability) {
		this.productAvailability = productAvailability;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		this.productDesc = productDesc;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
