package com.joalbano.payment.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.hateoas.RepresentationModel;

import com.joalbano.payment.entity.Sale;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class SaleDTO extends RepresentationModel<SaleDTO> implements Serializable {
	
	private static final long serialVersionUID = 5449678318911035270L;

	private Long id;
	
	private Date date;
	
	private List<ProductSaleDTO> products;
	
	private Double totalPrice;
	
	public static SaleDTO toSaleDTO(Sale sale) {
		return new ModelMapper().map(sale, SaleDTO.class);
	}

}
