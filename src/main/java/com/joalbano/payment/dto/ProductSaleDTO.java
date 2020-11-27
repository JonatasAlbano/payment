package com.joalbano.payment.dto;

import java.io.Serializable;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.joalbano.payment.entity.ProductSale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class ProductSaleDTO extends RepresentationModel<ProductSaleDTO> implements Serializable {
	
	private static final long serialVersionUID = -6580552365674325955L;

	private Long id;
	
	private Long productId;
	
	private Integer quantity;
	
	public static ProductSaleDTO toProductSaleDTO(ProductSale productSale) {
		return new ModelMapper().map(productSale, ProductSaleDTO.class);
	}
}
