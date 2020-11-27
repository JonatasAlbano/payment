package com.joalbano.payment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.joalbano.payment.entity.Sale;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long>{

}
