package br.com.commerce.api.dto.Product;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.commerce.api.models.Product;

@Component
public class ProductMapper {

    @Autowired
    private ModelMapper mapper;

    public Product toProduct(ProductRequest product) {
        return mapper.map(product, Product.class);
    }

    public ProductRequest toProductRequest(Product product) {
        return mapper.map(product, ProductRequest.class);
    }

    public ProductResponse toProductResponse(Product optional) {
        return mapper.map(optional, ProductResponse.class);
    }

    public ProductResponse toProductResponse(Optional<Product> optional) {
        return mapper.map(optional, ProductResponse.class);
    }

    public List<ProductResponse> toListProductResponse(List<Product> listProduct) {
        return listProduct.stream()
                .map(this::toProductResponse)
                .collect(Collectors.toList());
    }

}
