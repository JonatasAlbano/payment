package com.joalbano.payment.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;

import com.joalbano.payment.dto.ProductSaleDTO;

import lombok.Data;

@Entity
@Table(name="product_sale")
@Data
public class ProductSale implements Serializable {

	private static final long serialVersionUID = 1730074114239108134L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "product_id", nullable = false, length = 10)
	private Long productId;
	
	@Column(nullable = false, length = 10)
	private Integer quantity;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "sale_id")
	private Sale sale;
	
	public static ProductSale toProductSale(ProductSaleDTO productSaleDTO) {
		return new ModelMapper().map(productSaleDTO, ProductSale.class);
	}
}
