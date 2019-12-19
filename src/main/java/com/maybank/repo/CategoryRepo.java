package com.maybank.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.maybank.entities.Category;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
