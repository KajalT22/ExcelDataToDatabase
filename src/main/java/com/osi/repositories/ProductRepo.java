package com.osi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.osi.entities.Product;

public interface ProductRepo extends JpaRepository<Product, Integer> {

}
