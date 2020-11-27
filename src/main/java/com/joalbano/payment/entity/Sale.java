package com.joalbano.payment.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.modelmapper.ModelMapper;
import org.springframework.format.annotation.DateTimeFormat;

import com.joalbano.payment.dto.SaleDTO;

import lombok.Data;

@Entity
@Table(name="sale")
@Data
public class Sale implements Serializable {
	
	private static final long serialVersionUID = -4586524037379656832L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@DateTimeFormat(pattern = "MM/dd/yyyy")
	@Column(nullable = false)
	private Date date;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "sale", cascade = CascadeType.REFRESH)
	private List<ProductSale> products;
	
	@Column(name = "total_price", nullable = false, length = 10)
	private Double totalPrice;
	
	public static Sale toProduct(SaleDTO saleDTO) {
		return new ModelMapper().map(saleDTO, Sale.class);
	}
}
