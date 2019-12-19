package com.maybank.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name = "category" )
@Table(name = "category", schema = "maybank_schema")
public class Category implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "category_id", nullable = false, unique = true, length = 10)
	private int categorId;
	
	@Column(name = "category_desc", length = 500)
	private String categoryDesc;
	
	@Column(name = "cateogry_title", length = 50, nullable = false)
	private String categoryTitle;

	public int getCategorId() {
		return categorId;
	}

	public void setCategorId(int categorId) {
		this.categorId = categorId;
	}

	public String getCategoryDesc() {
		return categoryDesc;
	}

	public void setCategoryDesc(String categoryDesc) {
		this.categoryDesc = categoryDesc;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	
	
	
	

}
