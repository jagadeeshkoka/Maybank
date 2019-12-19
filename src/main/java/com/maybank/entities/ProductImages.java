package com.maybank.entities;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.persistence.Table;

@Entity
@Table(name = "product_images" ,schema = "maybank_schema" )
public class ProductImages {
	
	
	@Id
	@GeneratedValue(strategy =  GenerationType.AUTO)
	@Column(name = "image_id", unique = true, nullable = false, length = 10)
	private int imageId;
	
	@Column(name = "image_url", nullable = false)
	private String imageUrl;
	
	@Column(name = "product_id", nullable = false)
	private int productId;
	
	
	
	
	
	@Column(name = "remarks", length = 250)
	private String remarks;
	
	

	public int getImageId() {
		return imageId;
	}

	public void setImageId(int imageId) {
		this.imageId = imageId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
	
	
	

}
