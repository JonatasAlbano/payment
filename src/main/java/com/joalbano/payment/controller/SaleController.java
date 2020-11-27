package com.joalbano.payment.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joalbano.payment.dto.SaleDTO;
import com.joalbano.payment.services.SaleService;

@RestController
@RequestMapping("/sale")
public class SaleController {
	
	@Autowired
	private SaleService saleService;
	@Autowired
	private PagedResourcesAssembler<SaleDTO> assembler;
	
	@GetMapping(value = "/{id}", produces = {"application/json", "application/xml", "application/x-yaml"})
	public SaleDTO findById(@PathVariable("id") Long id) {
		SaleDTO saleDTO = saleService.findById(id);
		saleDTO.add(linkTo(methodOn(SaleController.class).findById(id)).withSelfRel());
		return saleDTO;
	}
	
	@GetMapping(produces = {"application/json", "application/xml", "application/x-yaml"})
	public ResponseEntity<?> findAll(@RequestParam(value = "page", defaultValue = "0") int page,
			@RequestParam(value = "limit", defaultValue = "12") int limit,
			@RequestParam(value = "direction", defaultValue = "asc") String direction) {
				
		var sortDirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC;
		
		Pageable pageable = PageRequest.of(page,  limit, Sort.by(sortDirection, "date"));
		Page<SaleDTO> listSales = saleService.findAll(pageable);
		listSales.stream()
				    .forEach(p -> p.add(linkTo(methodOn(SaleController.class).findById(p.getId())).withSelfRel()));
	
		PagedModel<EntityModel<SaleDTO>> pagedModel = assembler.toModel(listSales);
		
		return new ResponseEntity<>(pagedModel, HttpStatus.OK);
	}	
	
	@PostMapping(produces = {"application/json", "application/xml", "application/x-yaml"},
				 consumes = {"application/json", "application/xml", "application/x-yaml"})
	public SaleDTO create(@RequestBody SaleDTO saleDTO) {
		SaleDTO createSale = saleService.create(saleDTO);
		createSale.add(linkTo(methodOn(SaleController.class).findById(saleDTO.getId())).withSelfRel());
		return createSale;
	}

}
