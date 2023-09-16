package br.com.commerce.api.services;

import br.com.commerce.api.models.Product;
import br.com.commerce.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    public List<Product> findAllProducts(){
        return productRepository.findAll();
    }

    public Optional<Product> findById( Long id){
        return productRepository.findById(id);
    }

    public Product save(Product product){
        return productRepository.save(product);
    }

    public Product update (Product product, Long id){
        if(findById(id).isPresent()){
            product.setId(id);
            productRepository.save(product);
        }
        return product;
    }

    public void delete (Long id){
        if(findById(id).isPresent()){
            productRepository.deleteById(id);
        }
    }

}
