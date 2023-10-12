package br.com.commerce.api.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import br.com.commerce.api.dto.product.ProductMapper;
import br.com.commerce.api.dto.product.ProductRequest;
import br.com.commerce.api.dto.product.ProductResponse;
import br.com.commerce.api.models.Product;
import br.com.commerce.api.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Service
public class ProductService {
    private static final String CACHE_FIND_ALL = "ProductService.findAllProducts";
    private static final String CACHE_FIND_BY_ID = "ProductService.findById";

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    @Cacheable(CACHE_FIND_ALL)
    public List<ProductResponse> findAllProducts() {
        log.info(this.getClass().getName() + " | " + "findAllProducts");
        return productMapper.toListProductResponse(productRepository.findAll());
    }

    @Cacheable(CACHE_FIND_BY_ID)
    public ProductResponse findById(Long id) {
        log.info(this.getClass().getName() + " | " + "findById");
        return productMapper.toProductResponse(productRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("Product not found id: " + id)));
    }

    @Transactional
    @CacheEvict({ CACHE_FIND_ALL, CACHE_FIND_BY_ID })
    public ProductResponse save(ProductRequest product) {
        Product product0 = productMapper.toProduct(product);
        return productMapper.toProductResponse(productRepository.save(product0));
    }

    @Transactional
    @CachePut(CACHE_FIND_BY_ID)
    public ProductResponse update(ProductRequest product, Long id) {
        Product produtUpdate = productMapper.toProduct(product);
        if (productRepository.findById(id).isPresent()) {
            produtUpdate.setId(id);
            return productMapper.toProductResponse(productRepository.save(produtUpdate));
        }
        return null;
    }

    @Transactional
    @CachePut(CACHE_FIND_BY_ID)
    public void delete(Long id) {
        if (productRepository.findById(id).isPresent()) {
            productRepository.deleteById(id);
        }
    }

}
