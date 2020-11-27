package com.joalbano.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joalbano.payment.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
