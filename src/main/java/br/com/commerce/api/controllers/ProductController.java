package br.com.commerce.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.commerce.api.dto.product.ProductRequest;
import br.com.commerce.api.dto.product.ProductResponse;
import br.com.commerce.api.services.ProductService;
import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(value = { "/products" })
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	@Cacheable("ProductController.findAll")
	public ResponseEntity<List<ProductResponse>> findAll() {
		log.info(this.getClass().getName() + " | " + "findAll");
		return ResponseEntity.ok(productService.findAllProducts());
	}

	@GetMapping("/{id}")
	@Cacheable("ProductController.findById")
	public ResponseEntity<ProductResponse> findById(@PathVariable Long id) {
		ProductResponse product = productService.findById(id);
		log.info(this.getClass().getName() + " | " + "findById: " + id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@PostMapping
	public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest product) {
		log.info(this.getClass().getName() + " | " + "create: " + product.toString());
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable Long id,
			@RequestBody @Valid ProductRequest product) {
				log.info(this.getClass().getName() + " | " + "update: " + id + product.toString());
		return ResponseEntity.status(HttpStatus.OK).body(productService.update(product, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable Long id) {
		productService.delete(id);
		log.info(this.getClass().getName() + " | " + "delete: " + id);
		return ResponseEntity.noContent().build();
	}

}
