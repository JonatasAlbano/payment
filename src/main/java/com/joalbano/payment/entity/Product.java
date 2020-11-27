package com.joalbano.payment.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.joalbano.payment.dto.ProductDTO;

import lombok.Data;

@Entity
@Table(name="product")
@Data
public class Product {

	@Id
	private Long id;
	
	@Column(nullable = false, length = 10)
	private Integer stock;
	
	public static Product toProduct(ProductDTO productDTO) {
		return new ModelMapper().map(productDTO, Product.class);
	}
}
