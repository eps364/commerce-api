package br.com.commerce.api.services;

import br.com.commerce.api.dto.ProductDto;
import br.com.commerce.api.models.Product;
import br.com.commerce.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProducts(){
        List<Product> productList = productRepository.findAll();
        return productList;
    }




}
