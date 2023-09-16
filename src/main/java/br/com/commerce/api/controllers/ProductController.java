package br.com.commerce.api.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import br.com.commerce.api.services.ProductService;
import br.com.commerce.api.models.Product;

@RestController
@RequestMapping(value = { "/products" })
public class ProductController {


	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> productList = productService.findAllProducts();
		return ResponseEntity.ok(productList);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Object> findById(@PathVariable(value = "id") Long id) {
		Optional<Product> product = productService.findById(id);
		return product.<ResponseEntity<Object>>map(value ->
				ResponseEntity.status(HttpStatus.OK).body(value)).orElseGet(() ->
				ResponseEntity.status(HttpStatus.NOT_FOUND).body("Product not found."));
	}

	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.CREATED).body(productService.save(product));
	}

	@PutMapping("/{id}")
	public ResponseEntity<Object> updateProduct(@PathVariable(value="id") Long id,
												@RequestBody Product product) {
		return ResponseEntity.status(HttpStatus.OK).body(productService.update(product, id));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Object> deleteProduct(@PathVariable(value="id") Long id) {
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
