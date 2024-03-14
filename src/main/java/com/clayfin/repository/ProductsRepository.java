package com.clayfin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.clayfin.model.Products;

public interface ProductsRepository extends JpaRepository<Products, Long>{

}
