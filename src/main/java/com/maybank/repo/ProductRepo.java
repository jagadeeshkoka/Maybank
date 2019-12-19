package com.maybank.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.maybank.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>, CrudRepository<Product, Integer>{

	@Query("from Product p where p.productTitle=:name")
	public Product getByProductTitle(String name);
	
}
