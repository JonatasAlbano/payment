package com.joalbano.payment.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.joalbano.payment.dto.SaleDTO;
import com.joalbano.payment.entity.ProductSale;
import com.joalbano.payment.entity.Sale;
import com.joalbano.payment.exception.ResourceNotFoundException;
import com.joalbano.payment.repository.ProductSaleRepository;
import com.joalbano.payment.repository.SaleRepository;

@Service
public class SaleService {

	@Autowired
	private SaleRepository saleRepository;
	@Autowired
	private ProductSaleRepository productSaleRepository;

	public SaleDTO create(SaleDTO saleDTO) {
		Sale sale = saleRepository.save(Sale.toProduct(saleDTO));
		
		List<ProductSale> savedProducts = new ArrayList<>();
		saleDTO.getProducts().forEach(p -> {
			ProductSale ps = ProductSale.toProductSale(p);
			ps.setSale(sale);
			savedProducts.add(productSaleRepository.save(ps));
		});
		sale.setProducts(savedProducts);
		return SaleDTO.toSaleDTO(sale);
	}
	
	public Page<SaleDTO> findAll(Pageable pageable) {
		var page = saleRepository.findAll(pageable);
		return page.map(this::convertToSaleDTO);
	}

	private SaleDTO convertToSaleDTO(Sale sale) {
		return SaleDTO.toSaleDTO(sale);
	}
	
	public SaleDTO findById(Long id) {
		var entity = saleRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found"));
		return SaleDTO.toSaleDTO(entity);
	}
}
