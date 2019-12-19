package com.maybank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maybank.entities.Merchant;

public interface MerchantRepo  extends JpaRepository<Merchant, Integer> {
	

}
