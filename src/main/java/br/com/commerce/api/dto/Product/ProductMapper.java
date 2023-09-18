package br.com.commerce.api.dto.Product;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.commerce.api.models.Product;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Product toProduct(ProductRequest product) {
        return modelMapper.map(product, Product.class);
    }

    public ProductRequest toProductRequest(Product product) {
        return modelMapper.map(product, ProductRequest.class);
    }

    public ProductResponse toProductResponse(Product optional) {
        return modelMapper.map(optional, ProductResponse.class);
    }

    public ProductResponse toProductResponse(Optional<Product> optional) {
        return modelMapper.map(optional, ProductResponse.class);
    }

}
