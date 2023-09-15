package br.com.commerce.api.controllers;

import java.util.List;
import java.util.Optional;

import br.com.commerce.api.dto.ProductDto;
import br.com.commerce.api.repositories.ProductRepository;
import br.com.commerce.api.services.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.commerce.api.models.Product;

@RestController
@RequestMapping(value = { "/products" })
public class ProductController {
	@Autowired
	ProductRepository productRepository;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> productList = productRepository.findAll();
		return ResponseEntity.ok(productList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
		Optional<Product> product = productRepository.findById(id);
		return product.<ResponseEntity<Object>>map(value ->
				ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() ->
				ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found."));
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody ProductDto productRecordDto) {
		var productModel = new Product();
		BeanUtils.copyProperties(productRecordDto, productModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(productRepository.save(productModel));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") Long id,
												@RequestBody ProductDto productRecordDto) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		}
		var productModel = product.get();
		BeanUtils.copyProperties(productRecordDto, productModel);
		return ResponseEntity.status(HttpStatus.OK).body(productRepository.save(productModel));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") Long id) {
		Optional<Product> product = productRepository.findById(id);
		if(product.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found.");
		}
		productRepository.delete(product.get());
		return ResponseEntity.noContent().build();
	}

}
