package com.joalbano.payment.config;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.joalbano.payment.dto.ProductDTO;
import com.joalbano.payment.entity.Product;
import com.joalbano.payment.repository.ProductRepository;

@Component
public class ProductReceiveMessage {

	@Autowired
	private ProductRepository productRepository;
	
	@RabbitListener(queues = {"${crud.rabbitmq.queue}"})
	public void receive(@Payload ProductDTO productDTO) {
		productRepository.save(Product.toProduct(productDTO));
		
	}
}
