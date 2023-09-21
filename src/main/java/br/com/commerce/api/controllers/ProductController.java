package br.com.commerce.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import br.com.commerce.api.dto.Product.ProductRequest;
import br.com.commerce.api.dto.Product.ProductResponse;
import br.com.commerce.api.services.ProductService;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = { "/products" })
public class ProductController {

	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<List<ProductResponse>> findAll() {
		return ResponseEntity.ok(productService.findAllProducts());
	}

	@GetMapping("/{id}")
	public ResponseEntity<ProductResponse> findById(@PathVariable(value = "id") Long id) {
		ProductResponse product = productService.findById(id);
		return ResponseEntity.status(HttpStatus.OK).body(product);
	}

	@PostMapping
	public ResponseEntity<ProductResponse> create(@RequestBody @Valid ProductRequest product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<ProductResponse> updateProduct(@PathVariable(value = "id") Long id,
			@RequestBody @Valid ProductRequest product) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.update(product, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value = "id") Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
