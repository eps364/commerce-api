package br.com.commerce.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.Product.ProductMapper;
import br.com.commerce.api.dto.Product.ProductRequest;
import br.com.commerce.api.dto.Product.ProductResponse;
import br.com.commerce.api.models.Product;
import br.com.commerce.api.repositories.ProductRepository;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public List<ProductResponse> findAllProducts() {
        return productMapper.toListProductResponse(productRepository.findAll());
    }

    public ProductResponse findById(Long id) {
        return productMapper.toProductResponse(productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Product not found id: " + id)));
    }

    public ProductResponse save(ProductRequest product) {
        Product product0 = productMapper.toProduct(product);
        return productMapper.toProductResponse(productRepository.save(product0));
    }

    public ProductResponse update(ProductRequest product, Long id) {
        Product produtUpdate = productMapper.toProduct(product);
        if (productRepository.findById(id).isPresent()) {
            produtUpdate.setId(id);
            return productMapper.toProductResponse(productRepository.save(produtUpdate));
        }
        return null;
    }

    public void delete(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
        }
    }

}
