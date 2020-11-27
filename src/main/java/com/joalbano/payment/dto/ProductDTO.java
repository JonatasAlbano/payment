package com.joalbano.payment.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.joalbano.payment.entity.Product;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductDTO extends RepresentationModel<ProductDTO> implements Serializable {

	private static final long serialVersionUID = 8842350820185180547L;

	private Long id;
	
	private Integer stock;
	
	public static ProductDTO toProductDTO(Product product) {
		return new ModelMapper().map(product, ProductDTO.class);
	}
}
