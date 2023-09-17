package br.com.commerce.api.services;

import br.com.commerce.api.dto.Product.ProductMapper;
import br.com.commerce.api.dto.Product.ProductRequest;
import br.com.commerce.api.dto.Product.ProductResponse;
import br.com.commerce.api.models.Product;
import br.com.commerce.api.repositories.ProductRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductMapper productMapper;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public ProductResponse findById(Long id){
        return productMapper.toProductResponse(productRepository.findById(id));
    }

    public ProductResponse save(ProductRequest product){
        Product product0 = productMapper.toProduct(product);
        Product productSave = productRepository.save(product0);
        return productMapper.toProductResponse(productSave);
        
    }

    public ProductResponse update (ProductRequest product, Long id){
        Product produtUpdate = productMapper.toProduct(product);
        if(productRepository.findById(id).isPresent()){
            produtUpdate.setId(id);
            productRepository.save(produtUpdate);
            return productMapper.toProductResponse(produtUpdate);
        }
        return null;
    }

    public void delete (Long id){
        if(productRepository.findById(id).isPresent()){
            productRepository.deleteById(id);
        }
    }

}
